package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.util.tools.VerificationCodeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ForgotPasswordFormController {
    public AnchorPane contextForgotPassword;
    public TextField txtEmail;

    public void sendVerificationOnAction(ActionEvent actionEvent) {
        int verificationCode = new VerificationCodeGenerator().getCode(5);

        if (new DB_Connection().checkEmail(txtEmail.getText())) {
            String fromEmail = "lahirusellahandi@gmail.com";
            String toEmail = txtEmail.getText();
            String host = "127.0.0.1";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp", host); // node => nodemailer, (sendGrid, twilio)
            Session session = Session.getDefaultInstance(properties);

            try {
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(fromEmail));
                mimeMessage.setSubject("Verification Code");
                mimeMessage.setText("Verification code : " + verificationCode);
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

                // Transport.send(mimeMessage);
                // System.out.println("OK");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CodeVerificationForm.fxml"));
                Parent parent = fxmlLoader.load();
                CodeVerificationFormController controller = fxmlLoader.getController();
                controller.setUserData(verificationCode, txtEmail.getText());
                Stage stage = (Stage) contextForgotPassword.getScene().getWindow();
                stage.setScene(new Scene(parent));

            } catch (IOException | MessagingException  e) {
                throw new RuntimeException(e);
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "This Email is Incorrect !").show();
        }
    }

    public void backToLoginPageOnAction(ActionEvent actionEvent) throws IOException {setUI("LoginForm");}

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextForgotPassword.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
