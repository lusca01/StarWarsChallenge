package View;

import Auxiliar.CommandProducer;
import Auxiliar.Strategy;
import Control.Searchs;
import Model.Auxiliar;
import Model.Characters;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public class ViewParticipations extends CommandProducer implements Strategy {

    private TableView<Auxiliar> tableParticipations = new TableView<>();
    private Searchs searchs = new Searchs();

    private void criarTable() throws Exception {
        TableColumn<Auxiliar, String> col1 = new TableColumn<>("Name");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Auxiliar, Arrays> col2 = new TableColumn<>("Participations");
        col2.setCellValueFactory(new PropertyValueFactory<>("participations"));

        tableParticipations.getColumns().addAll(col1, col2);

        tableParticipations.setItems(searchs.getListCharacters());
    }


    @Override
    public Pane render() {
        BorderPane pane = new BorderPane();
        pane.setTop(new Label("   Characters and their participation in movies"));
        pane.setCenter(tableParticipations);
        try {
            this.criarTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pane;
    }
}
