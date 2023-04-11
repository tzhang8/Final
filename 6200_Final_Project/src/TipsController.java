import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TipsController implements Initializable {

    protected HashMap<String, Integer> pos_data;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button btFirst;
    @FXML
    private Button btForth;
    @FXML
    private Button btSecond;
    @FXML
    private Button btThird;
    @FXML
    void showFirst(ActionEvent event) {

    }

    @FXML
    void showForth(ActionEvent event) {

    }

    @FXML
    void showSecond(ActionEvent event) {

    }

    @FXML
    void showThird(ActionEvent event) {

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> top_counties = new ArrayList<>();
        pos_data = sortByValue(pos_data);
        int j = 0;
        for (HashMap.Entry<String, Integer> i : pos_data.entrySet()) {
            if (j == 4)
                break;
            top_counties.add(i.getKey());
            j++;
        }

        btFirst.setText(top_counties.get(0));
        btSecond.setText(top_counties.get(1));
        btThird.setText(top_counties.get(2));
        btForth.setText(top_counties.get(3));

    }
}
