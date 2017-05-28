package ru.kpfu.itis.khayrullin.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.khayrullin.controller.util.SceneChangingHandler;
import ru.kpfu.itis.khayrullin.entity.View;
import ru.kpfu.itis.khayrullin.model.User;
import ru.kpfu.itis.khayrullin.service.UserService;
import ru.kpfu.itis.khayrullin.util.Validator;

import java.io.IOException;

public class SignUpController {

    private SceneChangingHandler handler;

    @Autowired
    @Qualifier("signInView")
    private View signInView;

    @Autowired
    private UserService userService;

    public SignUpController() {
        handler = SceneChangingHandler.getInstance();
    }

    @Autowired
    private Validator validator;

    @FXML
    private TextField et_login;

    @FXML
    private PasswordField et_password;

    @FXML
    private ImageView et_image_view;

    @FXML
    private PasswordField et_password_confirm;


    @FXML
    public void register(ActionEvent event) throws IOException {
        String login = et_login.getText();
        String password = et_password.getText();
        String confirm = et_password_confirm.getText();

        try {
            validator.isRegistrationValid(login,password,confirm);
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            userService.add(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"you are successfully registered");
            alert.show();
            handler.nextScene(event, signInView);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.show();
        }
    }

    @FXML
    public void toLog(ActionEvent event){
        handler.nextScene(event, signInView);
    }
}
