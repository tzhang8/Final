import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FX extends Application {

	private ArrayList<Review> Reviews;
	public void start(Stage primaryStage){

		gen_review();

		Button btOK = new Button("Hello World");
		Scene scene = new Scene(btOK, 200, 250);
		primaryStage.setTitle("MyJavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void gen_review() {
		ReviewGen generator = new ReviewGen();
		Reviews = generator.generate_review();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
