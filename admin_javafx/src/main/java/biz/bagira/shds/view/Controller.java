package biz.bagira.shds.view;

import biz.bagira.shds.entity.Advert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.math.BigDecimal;

public class Controller {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextArea text;
    @FXML
    private TextField price;

    private Stage dialogStage;

    private Advert advert;


    @FXML
    private void handleCancel() {
        id.setText("");
        name.setText("");
        text.setText("");
        price.setText("");


    }


    @FXML
    private void handleAdd() {

        if (isInputValid()) {
            advert = new Advert();
            advert.setId(Long.parseLong(id.getText()));
            advert.setName(name.getText());
            advert.setText(text.getText());
            advert.setPrice(new BigDecimal(price.getText()));

            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(advert);
                send(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (id.getText() == null || id.getText().length() == 0) {
            errorMessage += "No valid id!\n";
        }
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (text.getText() == null || text.getText().length() == 0) {
            errorMessage += "No valid text!\n";
        }
        if (price.getText() == null || price.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        } else {

            try {
                Integer.parseInt(id.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid id (must be an integer)!\n";
            }
            try {
                Double.parseDouble(price.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be an double)!\n";
            }

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void send(String json) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("admin.queue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = session.createTextMessage(json);
            System.out.println("Send message: " + text);
            producer.send(message);
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }

    }

}
