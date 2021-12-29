package View;

import Auxiliar.CommandExecution;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class Main extends Application implements CommandExecution {

    private BorderPane panePrincipal = new BorderPane();

    private ViewParticipations viewParticipations = new ViewParticipations();
    private ViewPilots viewPilots = new ViewPilots();


    public Main() {
        viewParticipations.addExecution(this);
        viewPilots.addExecution(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        Button btnParticipations = new Button("Participations");
        Button btnPilots = new Button("Pilots Falconn");
        grid.add(btnParticipations, 0,0);
        grid.add(btnPilots, 1,0);

        btnParticipations.setOnAction((e)->{
            execute("ViewParticipations");
        });

        btnPilots.setOnAction((e)->{
            execute("ViewPilots");
        });

        panePrincipal.setTop(grid);
        Scene scn = new Scene(panePrincipal, 300,600);
        stage.setScene(scn);
        stage.setTitle("Star Wars");
        stage.show();
    }


    public void execute(String command) {
        if ("ViewParticipations".equals(command)) {
            panePrincipal.setCenter(viewParticipations.render());
        } else if ("ViewPilots".equals(command)) {
            panePrincipal.setCenter(viewPilots.render());
        } else if ("SAIR".equals(command)) {
            Platform.exit();
            System.exit(0);
        }
    }

    public static void main(String[]args) throws Exception{
        Application.launch(Main.class, args);
    }
}


