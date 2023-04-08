import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FX extends Application {

	private ArrayList<Review> Reviews;
	public void start(Stage primaryStage) throws Exception{

		gen_review();

		Button btLogin = new Button("Login");
		StackPane pane = new StackPane();
		pane.getChildren().add(btLogin);
		Scene scene = new Scene(pane, 800, 800);
		primaryStage.setTitle("Trip Geeks");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void gen_review() {
		ReviewGen generator = new ReviewGen();
		this.Reviews = generator.generate_review();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
