import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TipsController implements Initializable {

    private HashMap<String, Integer> pos_data;
    private Stage stage;
    private Scene scene;
    private HashMap<String, String> advices;
    private ArrayList<String> top_counties;
    @FXML
    private Button btFirst;
    @FXML
    private Button btForth;
    @FXML
    private Button btSecond;
    @FXML
    private Button btThird;
    @FXML
    private ImageView picFirst;
    @FXML
    private ImageView picForth;
    @FXML
    private ImageView picSecond;
    @FXML
    private ImageView picThird;
    @FXML
    private Text textSecond;
    @FXML
    private Text textFirst;
    @FXML
    private Text textForth;
    @FXML
    private Text textThird;

    @FXML
    void showFirst(ActionEvent event) {
        picFirst.setVisible(true);
        textFirst.setVisible(true);
    }

    @FXML
    void showForth(ActionEvent event) {
        picForth.setVisible(true);
        textForth.setVisible(true);
    }

    @FXML
    void showSecond(ActionEvent event) {
        picSecond.setVisible(true);
        textSecond.setVisible(true);
    }

    @FXML
    void showThird(ActionEvent event) {
        picThird.setVisible(true);
        textThird.setVisible(true);
    }

    @FXML
    void switchToHomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void getData() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("chartScene.fxml"));
        Parent root = loader.load();
        ChartController ChartController = loader.getController();
        pos_data = ChartController.getPosData();

    }

    public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(HashMap<K, V> map) {

        List<HashMap.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(HashMap.Entry.comparingByValue());

        HashMap<K, V> result = new LinkedHashMap<>();
        for (HashMap.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private void initPictures() {

        ArrayList<ImageView> images = new ArrayList<>();
        images.add(picForth);
        images.add(picThird);
        images.add(picSecond);
        images.add(picFirst);

        for (int i = 3; i >= 0; i--) {
            String path = top_counties.get(i) + ".jpeg";
            Image image = new Image(getClass().getResourceAsStream(path));
            images.get(i).setImage(image);
        }
    }

    private void initText() {


        advices.put()

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        top_counties = new ArrayList<>();
        pos_data = sortByValue(pos_data);
        int j = 0;
        for (HashMap.Entry<String, Integer> i : pos_data.entrySet()) {
            if (j > 6)
                top_counties.add(i.getKey());
            j++;
        }

        btFirst.setText(top_counties.get(3));
        btSecond.setText(top_counties.get(2));
        btThird.setText(top_counties.get(1));
        btForth.setText(top_counties.get(0));

        initPictures();

        picFirst.setVisible(false);
        picSecond.setVisible(false);
        picThird.setVisible(false);
        picForth.setVisible(false);

        textFirst.setVisible(false);
        textSecond.setVisible(false);
        textSecond.setVisible(false);
        textSecond.setVisible(false);
    }
}
