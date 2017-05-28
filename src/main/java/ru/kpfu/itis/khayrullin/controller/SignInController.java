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
import ru.kpfu.itis.khayrullin.util.Validator;

public class SignInController {

    private static final String ADMIN_LOGIN = "admin";
    private SceneChangingHandler handler;

    public SignInController() {
        this.handler = SceneChangingHandler.getInstance();
    }

    @Autowired
    @Qualifier("signUpView")
    private View signUpView;

    @Autowired
    @Qualifier("adminView")
    private View adminView;

    @Autowired
    @Qualifier("userView")
    private View userView;

    @FXML
    private TextField et_login;

    @FXML
    private PasswordField et_password;

    @FXML
    private ImageView et_image;

    @Autowired
    private Validator validator;

    @FXML
    public void loginButton(ActionEvent event){
        String login = et_login.getText();
        String password = et_password.getText();
        try {
            validator.isAuthValid(login,password);
            if (login.equals(ADMIN_LOGIN)){
                handler.nextScene(event,adminView);
            } else {
                handler.nextScene(event,userView);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.show();
        }

    }

    @FXML
    public void toSignUpButton(ActionEvent event){
        handler.nextScene(event,signUpView);
    }

}
