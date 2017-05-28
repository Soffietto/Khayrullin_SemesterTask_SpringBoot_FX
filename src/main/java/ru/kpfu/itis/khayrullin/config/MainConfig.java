package ru.kpfu.itis.khayrullin.config;

import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.khayrullin.controller.*;
import ru.kpfu.itis.khayrullin.entity.View;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class MainConfig {

    private static final String adminPath = "view/admin.fxml";
    private static final String carFormPath = "view/carform.fxml";
    private static final String rentFormPath = "view/rentform.fxml";
    private static final String signInPath = "view/signin.fxml";
    private static final String signUpPath = "view/signup.fxml";
    private static final String userPath = "view/user.fxml";

    @Bean
    public SignInController getSignInController() throws IOException {
        return (SignInController) getSignInView().getController();
    }

    @Bean
    public SignUpController getSignUpController() throws IOException {
        return (SignUpController) getSignUpView().getController();
    }

    @Bean
    public AdminController getAdminController() throws IOException {
        return (AdminController) getAdminView().getController();
    }

    @Bean
    public UserController getUserController() throws IOException {
        return (UserController) getUserView().getController();
    }

    @Bean
    public CarFormController getFormCarController() throws IOException {
        return (CarFormController) getCarFormView().getController();
    }

    @Bean
    public RentFormController getFormRentController() throws IOException {
        return (RentFormController) getRentFormView().getController();
    }

    @Bean(name = "signInView")
    public View getSignInView() throws IOException {
        return getView(signInPath);
    }

    @Bean(name = "signUpView")
    public View getSignUpView() throws IOException {
        return getView(signUpPath);
    }

    @Bean(name = "adminView")
    public View getAdminView() throws IOException {
        return getView(adminPath);
    }

    @Bean(name = "userView")
    public View getUserView() throws IOException {
        return getView(userPath);
    }

    @Bean(name = "carFormView")
    public View getCarFormView() throws IOException {
        return getView(carFormPath);
    }

    @Bean(name = "rentFormView")
    public View getRentFormView() throws IOException {
        return getView(rentFormPath);
    }


    private View getView(String path) throws IOException {
        InputStream stream = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            stream = getClass().getClassLoader().getResourceAsStream(path);
            System.err.println(path);
            loader.load(stream);
            return new View(loader.getRoot(), loader.getController());
        } catch (IOException e) {
            System.err.println("FXMLLoader error");
            return null;
        } finally {
            if (stream != null)
                stream.close();
        }
    }

}
