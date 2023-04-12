import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private Stage stage;
    private Scene scene;
    static Scene loginScene;
    @FXML
    private ImageView picHome;

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

    public void switchToLoginScene(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL resource = getClass().getClassLoader().getResource("pictures/home.jpg");
        //String path = top_counties.get(i) + ".jpeg";
        Image image = null;
        try {
            image = new Image(String.valueOf(resource.toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        picHome.setImage(image);
    }
}
