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

    public void start(Stage primaryStage) {

        Users = new ArrayList<>();
        gen_review();

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        Button btSignup = new Button("Sign up");
        HBox hbtSignup = new HBox(10);
        hbtSignup.setAlignment(Pos.BASELINE_LEFT);
        hbtSignup.getChildren().add(btSignup);
        pane.add(hbtSignup, 0, 4);

        Button btLogin = new Button("Login");
        HBox hbtLogin = new HBox(10);
        hbtLogin.setAlignment(Pos.BASELINE_RIGHT);
        hbtLogin.getChildren().add(btLogin);
        pane.add(hbtLogin, 1, 4);

        Text scene_title = new Text("Welcome! Please login or sign up.");
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
		message.setFill(Color.RED);
        pane.add(message, 1, 6);

        Button btLogout = new Button("Logout");
        Scene home_scene = new Scene(btLogout, 800, 800);
        Scene scene = new Scene(pane, 800, 800);

        btLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });

        btSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
				User t = new User(user_name_text.getText(), user_pass_text.getText());
				if (!add_user(t)) {
					message.setText("User already exsit");
				}
				else
					message.setText("Sign up successful");
                user_name_text.clear();
                user_pass_text.clear();
            }
        });
        btLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User t = new User(user_name_text.getText(), user_pass_text.getText());
				int check = check_user(t);
                message.setText(gen_text(check));
				if (check == 0)
                	primaryStage.setScene(home_scene);
                user_name_text.clear();
                user_pass_text.clear();
            }
        });

        primaryStage.setTitle("Trip Geeks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	private boolean add_user(User t) {
		int i = check_user(t);
		if (i == 0 || i == -1)
			return false;
		else
			Users.add(t);
		return true;
	}
	private int check_user(User t) {
		for (User i : Users) {
			if (i.compareTo(t) == 0)
				return 0;
			else if (i.compareTo(t) == -1)
				return -1;
		}
		return 1;
	}
    private void gen_review() {
        ReviewGen generator = new ReviewGen();
        this.Reviews = generator.generate_review();
    }

    private String gen_text(int i) {
        if (i == 0) {
            return "Login Successfully";
        } else if (i == -1) {
            return "Wrong Password";
        }
    	else
			return"Wrong Username";
}

    public static void main(String[] args) {
        Application.launch(args);
    }
}
