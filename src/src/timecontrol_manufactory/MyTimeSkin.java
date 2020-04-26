package timecontrol_manufactory;

import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalTimeStringConverter;
import javafx.util.converter.TimeStringConverter;

class MyTimeSkin extends SkinBase<MyTimeControl> {
    // wird spaeter gebraucht
    private static final int ICON_SIZE  = 12;
    private static final int IMG_OFFSET = 4;

    private static ImageView invalidIcon = new ImageView(new Image(MyTimeSkin.class.getResource("icons/invalid.png").toExternalForm(),
                                                                   ICON_SIZE, ICON_SIZE,
                                                                   true, false));

    private static ImageView validIcon = new ImageView(new Image(MyTimeSkin.class.getResource("icons/valid.png").toExternalForm(),
                                                                   ICON_SIZE, ICON_SIZE,
                                                                   true, false));


    //todo: replace it
    private TextField startTime;
    private TextField endTime;

    private Label captionLabel;

    MyTimeSkin(MyTimeControl control) {
        super(control);
        initializeSelf();
        initializeParts();
        layoutParts();
        setupValueChangeListeners();
        setupBindings();
    }

    private void initializeSelf() {
        // getSkinnable().loadFonts("/fonts/Lato/Lato-Reg.ttf", "/fonts/Lato/Lato-Lig.ttf");
        getSkinnable().addStylesheetFiles("style.css");
    }

    private void initializeParts() {
        startTime = new TextField("Startzeit");
        startTime.getStyleClass().add("start-time");
        startTime.setVisible(getSkinnable().isEditable());

        endTime = new TextField("Endzeit");
        endTime.getStyleClass().add("end-time");
        endTime.setVisible(getSkinnable().isEditable());

        captionLabel = new Label();
        captionLabel.getStyleClass().add("caption-label");
    }

    private void layoutParts() {
        getChildren().addAll(new VBox(captionLabel, startTime, endTime));
    }

    private void setupValueChangeListeners() {
        getSkinnable().editableProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                startTime.setEditable(true);
                endTime.setEditable(true);
            }
            else {
                startTime.setEditable(false);
                endTime.setEditable(false);
            }
        });
    }

    private void setupBindings() {
        captionLabel.textProperty().bind(getSkinnable().captionProperty());
    }
}
