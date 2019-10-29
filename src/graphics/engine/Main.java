package graphics.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private AnchorPane root;

    private Scene3D scene3D;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // load fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graphicsEngine.fxml"));
        root = (AnchorPane) fxmlLoader.load();


        // init app
        primaryStage.setTitle("Graphics Engine");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();


        // set up all buttons and sliders
        Controller controller = fxmlLoader.getController();
        controller.setUpApp();


        // add scene to screen
        scene3D = new Scene3D(controller);
        root.getChildren().add(scene3D.viewport);
    }




    public static void main(String[] args) {
        launch(args);
    }

}
