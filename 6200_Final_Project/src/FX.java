import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

import java.io.IOException;
import java.util.ArrayList;

public class FX extends Application {
    private ArrayList<User> Users;
    private Button btSignup, btLogin;
    private Text scene_title, message;
    private TextField user_name_text, user_pass_text;
    private GridPane login_pane;
    private Scene home_scene, login_scene;
    private Parent root;
    private FXMLLoader loader;

    public void start(Stage primaryStage) throws IOException {

        Users = new ArrayList<>();

        loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();

        set_login_pane();
        setBtLogout();
        setBtLogin(primaryStage);
        setBtSignup();

        primaryStage.setTitle("Trip Geeks");
        primaryStage.setScene(login_scene);
        primaryStage.show();
    }

    private void set_login_pane() {

        login_pane = new GridPane();
        login_pane.setAlignment(Pos.CENTER);
        login_pane.setHgap(10);
        login_pane.setVgap(10);
        login_pane.setPadding(new Insets(25, 25, 25, 25));

        btSignup = new Button("Sign up");
        HBox hbtSignup = new HBox(10);
        hbtSignup.setAlignment(Pos.BASELINE_LEFT);
        hbtSignup.getChildren().add(btSignup);
        login_pane.add(hbtSignup, 0, 4);

        btLogin = new Button("Login");
        HBox hbtLogin = new HBox(10);
        hbtLogin.setAlignment(Pos.BASELINE_RIGHT);
        hbtLogin.getChildren().add(btLogin);
        login_pane.add(hbtLogin, 1, 4);

        scene_title = new Text("Welcome! Please login or sign up.");
        scene_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        login_pane.add(scene_title, 0, 0, 2, 1);

        Label user_name = new Label("Username");
        login_pane.add(user_name, 0, 1);

        user_name_text = new TextField();
        login_pane.add(user_name_text, 1, 1);

        Label user_pass = new Label("Password");
        login_pane.add(user_pass, 0, 2);

        user_pass_text = new TextField();
        login_pane.add(user_pass_text, 1, 2);

        message = new Text();
		message.setFill(Color.RED);
        login_pane.add(message, 1, 6);

        home_scene = new Scene(root, 800, 800);
        login_scene = new Scene(login_pane, 800, 800);
    }

    private void setBtLogout() {
        HomeController HomeControler = loader.getController();
        HomeControler.loadLoginScene(login_scene);
    }

    private void setBtSignup() {
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
    }
    private void setBtLogin(Stage primaryStage) {
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
