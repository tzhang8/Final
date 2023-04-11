import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ChartController implements Initializable {

    private Stage stage;
    private Scene scene;
    private ArrayList<Review> Reviews;
    private HashMap<String, Integer> neg_data, pos_data;
    @FXML
    private CategoryAxis xAxis ;
    @FXML
    private NumberAxis yAxis ;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private PieChart negPieChart;
    @FXML
    private PieChart posPieChart;

    @FXML
    void switchToHomeSence(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }


    private void creat_pie_chart() {
        ObservableList<PieChart.Data> negPieChartData = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> posPieChartData = FXCollections.observableArrayList();

        for (HashMap.Entry<String, Integer> i : neg_data.entrySet())
            negPieChartData.add(new PieChart.Data(i.getKey(), i.getValue()));

        negPieChart.setData(negPieChartData);
        negPieChart.setTitle("Negative Reviews");

        for (HashMap.Entry<String, Integer> i : pos_data.entrySet())
            posPieChartData.add(new PieChart.Data(i.getKey(), i.getValue()));

        posPieChart.setData(posPieChartData);
        posPieChart.setTitle("Positive Reviews");
    }

    private void creat_bar_chart() {

        barChart.setTitle("Country Summary");
        xAxis.setLabel("Countries");
        yAxis.setLabel("Reviews");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Negative");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Positive");


        for (HashMap.Entry<String, Integer> i : neg_data.entrySet())
            series1.getData().add(new XYChart.Data(i.getKey(), i.getValue()));

        for (HashMap.Entry<String, Integer> i : pos_data.entrySet())
            series2.getData().add(new XYChart.Data(i.getKey(), i.getValue()));


        barChart.setLegendSide(Side.LEFT);
        barChart.getData().addAll(series1, series2);
    }

    private void getData() {

        neg_data = new HashMap<>();
        pos_data = new HashMap<>();

        for (Review i : Reviews) {
            String t = i.get_country();
            if (i instanceof Neg_Review) {
                if (neg_data.containsKey(t))
                    neg_data.put(t, neg_data.get(t) + 1);
                else
                    neg_data.put(t, 0);
            }
            else {
                if (pos_data.containsKey(t))
                    pos_data.put(t, pos_data.get(t) + 1);
                else
                    pos_data.put(t, 0);
            }
        }
        //System.out.printf("" + pos_data);
        //System.out.printf(Reviews.get(0).get_country());
    }
    private void gen_review() {
        ReviewGen generator = new ReviewGen();
        this.Reviews = generator.generate_review();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gen_review();
        getData();
        creat_bar_chart();
        creat_pie_chart();
    }
}
