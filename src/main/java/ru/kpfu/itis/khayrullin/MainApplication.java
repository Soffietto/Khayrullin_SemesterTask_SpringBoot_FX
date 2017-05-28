package ru.kpfu.itis.khayrullin;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import ru.kpfu.itis.khayrullin.entity.View;

@Lazy
@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {

    @Value("Azat's project")
    private String title;

    @Autowired
    @Qualifier("signInView")
    private View signInView;

    public static void main(String[] args) {
        launchApp(MainApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(title);
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(signInView.getView()));
        primaryStage.show();
    }
}
