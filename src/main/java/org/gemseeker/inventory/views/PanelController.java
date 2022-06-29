package org.gemseeker.inventory.views;

import io.reactivex.disposables.CompositeDisposable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public abstract class PanelController {

    protected URL fxmlUrl;
    protected Pane root;

    private final Alert infoDialog;
    private final Alert errorDialog;
    private final Alert confirmDialog;

    protected ArrayList<WindowController> windowControllers = new ArrayList<>();
    protected ArrayList<PanelController> panelControllers = new ArrayList<>();

    protected final CompositeDisposable disposables;

    public PanelController(URL fxmlUrl) {
        this.fxmlUrl = fxmlUrl;

        infoDialog = new Alert(Alert.AlertType.INFORMATION);
        infoDialog.setTitle("Info");
        errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle("Error");
        confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm");

        disposables = new CompositeDisposable();
    }

    public Pane getContentView() {
        if (root == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxmlUrl);
            loader.setController(this);
            try {
                root = loader.load();
                onViewCreated();
            } catch (IOException e) {
                throw new RuntimeException("Failed to load FXML file: " + e);
            }
        }
        return root;
    }

    public abstract void onViewCreated();
    public abstract void onHidden();
    public abstract void onResume();

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
}
