package timecontrol_manufactory;

import java.time.LocalTime;

import java.util.regex.Pattern;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

public class MyTimeControl extends Control {
    private static final PseudoClass BLA_CLASS = PseudoClass.getPseudoClass("bla");

    private static final String CONVERTIBLE_REGEX = "now|(\\d{1,2}[:]{0,1}\\d{0,2})";
    private static final String TIME_FORMAT_REGEX = "\\d{2}:\\d{2}";

    private static final String FORMATTED_TIME_PATTERN = "HH:mm";

    private static final Pattern CONVERTIBLE_PATTERN = Pattern.compile(CONVERTIBLE_REGEX);
    private static final Pattern TIME_FORMAT_PATTERN = Pattern.compile(TIME_FORMAT_REGEX);

    private final SkinType skinType;

    // all properties
    private final ObjectProperty<LocalTime> actualTime    = new SimpleObjectProperty<>();
    private final StringProperty            caption       = new SimpleStringProperty();
    private final BooleanProperty           bla           = new SimpleBooleanProperty(){
        @Override
        protected void invalidated() {
            super.invalidated();
            pseudoClassStateChanged(BLA_CLASS, get());
        }
    };

    private final BooleanProperty editable = new SimpleBooleanProperty();

    public MyTimeControl(SkinType skinType) {
        this.skinType = skinType;

        initializeSelf();
    }

    private void initializeSelf() {
        getStyleClass().add("my-time-control");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return skinType.getFactory().apply(this);
    }


    public void loadFonts(String... font){
        for(String f : font){
            Font.loadFont(getClass().getResourceAsStream(f), 0);
        }
    }

    public void addStylesheetFiles(String... stylesheetFile){
        for(String file : stylesheetFile){
            String stylesheet = getClass().getResource(file).toExternalForm();
            getStylesheets().add(stylesheet);
        }
    }


    // generated getter und setter

    public LocalTime getActualTime() {
        return actualTime.get();
    }

    public ObjectProperty<LocalTime> actualTimeProperty() {
        return actualTime;
    }

    public void setActualTime(LocalTime actualTime) {
        this.actualTime.set(actualTime);
    }

    public String getCaption() {
        return caption.get();
    }

    public StringProperty captionProperty() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption.set(caption);
    }

    public boolean isBla() {
        return bla.get();
    }

    public BooleanProperty blaProperty() {
        return bla;
    }

    public void setBla(boolean bla) {
        this.bla.set(bla);
    }

    public boolean isEditable() {
        return editable.get();
    }

    public BooleanProperty editableProperty() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable.set(editable);
    }
}
