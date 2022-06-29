package org.gemseeker.inventory;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.time.format.DateTimeFormatter;

public final class Utils {

    public static final String APP_NAME = "Inventory Management System";
    public static final String APP_VERSION = "0.0.1";
    private static final String DB_NAME = "inventorydb";

    public static final String APP_FOLDER = getUserHome() + fileSeparator() + ".inventory_system";
    public static final String LOG_FOLDER = APP_FOLDER + fileSeparator() + "logs";
    public static final String DATA_FOLDER = APP_FOLDER + fileSeparator() + "data";
    public static final String IMAGES_FOLDER = APP_FOLDER + fileSeparator() + "images";
    public static final String TEMP_FOLDER = APP_FOLDER + fileSeparator() + "temp";

    public static final String DATABASE_PATH = DATA_FOLDER + fileSeparator() + DB_NAME;
    public static final String SETTINGS_FILE = APP_FOLDER + fileSeparator() + "settings.xml";

    /**
     * Date format MMMM dd, yyyy (Ex: June 01, 2022)
     */
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    /**
     * Date format MMM dd, yyyy (Ex: Jun 01, 2022)
     */
    public static final DateTimeFormatter DATE_FORMAT2 = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    /**
     * Date format MM-dd-yyyy (Ex: 06-01-2022)
     */
    public static final DateTimeFormatter DATE_FORMAT3 = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static String formatNumber(double value) {
        String str = String.format("%.2f", value);
        StringBuilder sb = new StringBuilder();
        int startIndex = str.indexOf('.');
        if (startIndex < 0) startIndex = 0;
        int decCount = 0;
        for (int i=startIndex-1; i>=0; i--) {
            if (decCount == 3) {
                sb.append(",");
                decCount = 0;
            }
            sb.append(str.charAt(i));
            decCount++;
        }
        return sb.reverse().append(str.substring(startIndex)).toString();
    }

    public static void setSafeTextField(TextField textField) {
        if (textField != null) {
            textField.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
                String unsafe = "{}|\\^`~[]'?!";
                if (unsafe.contains(evt.getCharacter())) evt.consume();
            });
        }
    }

    public static void setAsNumericalTextField(TextField...textFields) {
        for (TextField tf : textFields) {
            tf.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
                if (!"-0123456789.".contains(evt.getCharacter())) evt.consume();
                String text = tf.getText();
                String chr = evt.getCharacter();
                if ((chr.equals("-") || chr.equals(".")) && (text.contains("-") || text.contains("."))) {
                    evt.consume();
                }
            });
        }
    }

    public static void setAsIntegerTextField(TextField textField) {
        if (textField != null) {
            textField.addEventFilter(KeyEvent.KEY_TYPED, evt -> {
                if (!"0123456789".contains(evt.getCharacter())) evt.consume();
            });
        }
    }

    public static String fileSeparator() {
        return System.getProperty("file.separator");
    }

    public static String getUserHome() {
        return System.getProperty("user.home");
    }
}
