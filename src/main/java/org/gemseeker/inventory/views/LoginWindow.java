package org.gemseeker.inventory.views;

import io.reactivex.Single;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.gemseeker.inventory.Utils;
import org.gemseeker.inventory.data.EmbeddedDatabase;

import java.util.Optional;

public class LoginWindow extends WindowController {

    @FXML private TextField tfUsername;
    @FXML private PasswordField tfPassword;
    @FXML private ProgressBar progressBar;
    @FXML private Button btnLogin;
    @FXML private Button btnRegister;
    @FXML private Button btnExit;

    private final MainWindow mainWindow;
    private final RegisterWindow registerWindow;

    public LoginWindow() {
        super("User Login", LoginWindow.class.getResource("login.fxml"), null);
        mainWindow = new MainWindow();
        registerWindow = new RegisterWindow();

        // NOTE! mainWindow and registerWindow are not going to be added
        // to the list of controllers to be disposed when this window is
        // closed. Adding them to the list will automatically dispose them
        // even while they are open if this LoginWindow is closed.
    }

    @Override
    public void onViewCreated() {
        // Setup default progress bar
        setDefaultProgressBar(progressBar);

        // Set text field and password field as safe input
        Utils.setSafeTextField(tfUsername);
        Utils.setSafeTextField(tfPassword);

        // Add listeners
        disposables.addAll(
                JavaFxObservable.actionEventsOf(btnLogin).subscribe(evt -> {
                    String username = tfUsername.getText();
                    String password = tfPassword.getText();
                    if (!username.isEmpty() && !password.isEmpty()) {
                        verifyLogin(username, password);
                    } else {
                        showInfoDialog("Invalid", "Please fill up username and password and try again.");
                    }
                }),
                JavaFxObservable.actionEventsOf(btnRegister).subscribe(evt -> {
                    registerWindow.show();
                    close();
                }),
                JavaFxObservable.actionEventsOf(btnExit).subscribe(evt -> {
                    Optional<ButtonType> result = showConfirmDialog("Exit Application",
                            "Are you sure you want to exit the application?",
                            ButtonType.YES, ButtonType.NO);
                    if (result.isPresent() && result.get() == ButtonType.YES) onClose();
                })
        );
    }

    private void verifyLogin(String username, String password) {
        showProgress(true);
        disposables.add(Single.fromCallable(() -> {
            EmbeddedDatabase database = EmbeddedDatabase.getInstance();
            return database.getUser(username, password);
        }).subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(user -> {
            showProgress(false);
            if (user != null) {
                mainWindow.show(user);
                close();
            } else {
                showInfoDialog("Login Failed", "Invalid username or password.");
                tfUsername.requestFocus();
            }
        }, err -> {
            showProgress(false);
            showErrorDialog("Database Error", "Error while retrieving user info.", err);
        }));
    }

    @Override
    public void onClose() {
        onDispose();
    }
}
