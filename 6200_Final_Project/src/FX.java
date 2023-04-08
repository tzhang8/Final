import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FX extends Application {

	private ArrayList<Review> Reviews;
	private ArrayList<User> Users;
	public void start(Stage primaryStage){

		Users = new ArrayList<>();
		gen_review();

		GridPane pane = new GridPane ();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));

		Button btLogin = new Button("Login");
		HBox hbtLogin = new HBox(10);
		hbtLogin.setAlignment(Pos.BASELINE_RIGHT);
		hbtLogin.getChildren().add(btLogin);
		pane.add(hbtLogin, 1, 4);

		Text scene_title = new Text("Welcome! Please login.");
		scene_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		pane.add(scene_title, 0, 0, 2, 1);

		Label user_name = new Label("Username");
		pane.add(user_name, 0, 1);

		TextField user_name_text = new TextField();
		pane.add(user_name_text, 1, 1);

		Label user_pass = new Label("Password");
		pane.add(user_pass, 0, 2);

		TextField user_pass_text = new TextField();
		pane.add(user_pass_text, 1, 2);

		final Text message = new Text();
		pane.add(message, 1, 6);

		btLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				User t = new User(user_name_text.getText(), user_pass_text.getText());
				message.setFill(Color.RED);
				message.setText(check_login(t));
			}
		});

		Scene scene = new Scene(pane, 800, 800);
		primaryStage.setTitle("Trip Geeks");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void gen_review() {
		ReviewGen generator = new ReviewGen();
		this.Reviews = generator.generate_review();
	}

	private String check_login(User t) {
		for (User i : this.Users) {
			if (i.compareTo(t) == 0) {
				return "Login Successfully";
			}
			else if (i.compareTo(t) == -1) {
				return "Wrong Password";
			}
		}
		return "Wrong Username";
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
