package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        	Parent root = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuView.fxml"));
        	primaryStage.setTitle("Sorting Visualizer");
        	Scene scene = new Scene(root, 1080, 720);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
