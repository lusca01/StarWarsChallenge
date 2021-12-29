package View;

import Auxiliar.CommandProducer;
import Auxiliar.Strategy;
import Control.Searchs;
import Model.Characters;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ViewPilots extends CommandProducer implements Strategy {

    private static Searchs searchs = new Searchs();
    public static TableView<Characters> tablePilots = new TableView<>();

    private void criarTable() throws Exception {
        TableColumn<Characters, String> col1 = new TableColumn<>("Pilots Name");
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));

        tablePilots.getColumns().add(col1);

        tablePilots.setItems(searchs.GetListPilots());
    }

    @Override
    public Pane render() {
        BorderPane pane = new BorderPane();
        pane.setCenter(tablePilots);
        try {
            criarTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (pane);
    }
}
