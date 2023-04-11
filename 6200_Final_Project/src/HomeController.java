import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    private Stage stage;
    private Scene scene;
    static Scene loginScene;

    public void switchToChartScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chartScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void loadLoginScene(Scene t) {
        loginScene = t;
    }
    public void switchToLoginScene(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.show();
    }

    public void switchToTipsScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tipsScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

}
