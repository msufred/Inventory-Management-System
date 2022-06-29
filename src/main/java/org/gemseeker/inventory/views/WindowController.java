package org.gemseeker.inventory.views;

import io.reactivex.disposables.CompositeDisposable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public abstract class WindowController {

    protected Stage stage;
    protected Scene scene;
    protected Pane root;
    protected Stage owner;
    protected String windowTitle;
    protected URL fxmlUrl;

    protected ProgressBar progressBar;
    protected Label progressLabel;

    private final EventHandler<WindowEvent> onCloseRequest = evt -> onClose();

    private final Alert infoDialog;
    private final Alert errorDialog;
    private final Alert confirmDialog;

    private boolean mStageCreated;

    protected ArrayList<WindowController> windowControllers = new ArrayList<>();
    protected ArrayList<PanelController> panelControllers = new ArrayList<>();

    protected final CompositeDisposable disposables;

    public WindowController(String windowTitle, URL fxmlUrl, Stage owner) {
        this(windowTitle, fxmlUrl, null, owner);
    }

    public WindowController(String windowTitle, URL fxmlUrl, Stage stage, Stage owner) {
        this.windowTitle = windowTitle;
        this.fxmlUrl = fxmlUrl;
        this.stage = stage;
        this.owner = owner;

        infoDialog = new Alert(Alert.AlertType.INFORMATION);
        infoDialog.setTitle("Info");
        errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Error");
        confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm");

        disposables = new CompositeDisposable();
    }

    protected Stage getStage() {
        if (stage == null) stage = new Stage();
        if (!mStageCreated) {
            File icon = new File("app-icon.png");
            if (icon.exists()) stage.getIcons().add(new Image(icon.toURI().toString()));
            stage.setTitle(windowTitle);
            if (owner != null) stage.initOwner(owner);
            stage.setOnHidden(onCloseRequest);
            stage.setScene(getScene());
            initWindow(stage);  // override for further setup of window
            mStageCreated = true;
        }
        return stage;
    }

    protected void initWindow(Stage stage) {
        // intentionally left empty
    }

    protected Scene getScene() {
        if (scene == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxmlUrl);
            loader.setController(this);
            try {
                root = loader.load();
                onViewCreated();
                scene = new Scene(root);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load FXML file: " + e);
            }
        }
        return scene;
    }

    public void show() {
        getStage().show();
    }

    public void close() {
        getStage().close();
    }

    public Stage getWindow() {
        return stage;
    }

    public abstract void onViewCreated();
    public abstract void onClose();

    public void onDispose() {
        for (WindowController wc : windowControllers) wc.onDispose();
        windowControllers.clear();
        for (PanelController pc : panelControllers) pc.onDispose();
        panelControllers.clear();
        disposables.dispose();
    }

    public void addWindowController(WindowController windowController) {
        windowControllers.add(windowController);
    }

    public void addPanelController(PanelController panelController) {
        panelControllers.add(panelController);
    }

    public void showInfoDialog(String header, String content) {
        infoDialog.setHeaderText(header);
        infoDialog.setContentText(content);
        infoDialog.showAndWait();
    }

    public void showErrorDialog(String header, String content, Throwable t) {
        errorDialog.setHeaderText(header);
        errorDialog.setContentText(content + ":\n" + t);
        errorDialog.showAndWait();
    }

    public Optional<ButtonType> showConfirmDialog(String header, String content, ButtonType...buttonTypes) {
        confirmDialog.setHeaderText(header);
        confirmDialog.setContentText(content);
        if (buttonTypes.length > 0) confirmDialog.getButtonTypes().setAll(buttonTypes);
        return confirmDialog.showAndWait();
    }

    public void setDefaultProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setDefaultProgressLabel(Label label) {
        this.progressLabel = label;
    }

    public void showProgress(boolean show) {
        showProgress(show, "");
    }

    public void showProgress(boolean show, String progressText) {
        showProgress(show, -1, progressText);
    }

    public void showProgress(boolean show, double progress, String progressText) {
        if (progressBar != null) {
            if (progress == -1) progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            else progressBar.setProgress(progress);
            progressBar.setVisible(show);
        }
        if (progressLabel != null) progressLabel.setText(progressText);
    }
}
