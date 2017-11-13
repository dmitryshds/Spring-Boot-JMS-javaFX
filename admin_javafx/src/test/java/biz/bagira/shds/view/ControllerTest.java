package biz.bagira.shds.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class ControllerTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(getClass().getClassLoader().getResource("view/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void setFieldsTest() {
        TextField id = find("#id");
        id.setText("15");
        verifyThat("#id", hasText("15"));

        TextField name = find("#name");
        name.setText("name");
        verifyThat("#name", hasText("name"));

        TextArea text = find("#text");
        text.setText("text");
        verifyThat("#text", hasText("text"));

        TextField price = find("#price");
        price.setText("price");
        verifyThat("#price", hasText("price"));


    }

    @Test
    public void verifyButtonTest() {

        Button button = find("#add");
        verifyThat(button.getText(), hasText("ADD"));

        Button clear = find("#clear");
        verifyThat(clear.getText(), hasText("CLEAR"));

    }

}
