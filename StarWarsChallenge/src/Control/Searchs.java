package Control;

import Model.Auxiliar;
import Model.Starship;
import Model.Characters;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Searchs {

    static String BaseURLChars = "https://swapi.dev/api/people/", json = "?format=json";
    static String BaseURL = "https://swapi.dev/api/";

    public List<Characters> searchParticipations() throws Exception{
        int sucess = 200, c = 1;
        List<Characters> allCharacters = new ArrayList<>();

        while(sucess == 200){
            String newUrl = BaseURLChars + String.valueOf(c) + json;
            try {
                URL url = new URL(newUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() != sucess){
                    sucess = connection.getResponseCode();
                    break;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String jsonToString = Useful.convertJsonToString(reader);

                Gson gson = new Gson();
                Characters Characters = gson.fromJson(jsonToString, Characters.class);

                allCharacters.add(Characters);

                c++;
                if(c == 17){
                    c++;
                }
            }catch (Exception e){
                throw new Exception("Erro: " + e);
            }
        }
        allCharacters.sort(Comparator.comparingInt(Characters::getLenght).reversed());
        return allCharacters;
    }

    public Starship searchPilotos() throws Exception {
        String newURL = BaseURL + "/starships/10/" + json;
        try {
            URL url = new URL(newURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != 200)
                throw new RuntimeException("Http error code: " + connection.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonToString = Useful.convertJsonToString(reader);

            Gson gson = new Gson();
            Starship pilots = gson.fromJson(jsonToString, Starship.class);

            return pilots;
        } catch (Exception e){
            throw new Exception("ERRO: " + e);
        }
    }

    public List<Characters> FalconPilots(Starship pilotos) throws Exception{
        List<Characters> ListPilots = new ArrayList<>();
        for (String pilots: pilotos.pilots) {
            String newURL = pilots + json;
            URL url = new URL(newURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != 200)
                throw new RuntimeException("Http error code: " + connection.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonToString = Useful.convertJsonToString(reader);

            Gson gson = new Gson();
            Characters Pilots = gson.fromJson(jsonToString, Characters.class);

            ListPilots.add(Pilots);
        }
        return ListPilots;
    }

    public ObservableList<Auxiliar> getListCharacters() throws Exception {
        List<Characters> allCharacters = searchParticipations();
        List<Auxiliar> allAuxiliar = new ArrayList<>();
        for (Characters x: allCharacters) {
            Auxiliar auxiliar = new Auxiliar(x.getName(), x.getLenght());
            allAuxiliar.add(auxiliar);
        }
        ObservableList<Auxiliar> listChar = FXCollections.observableArrayList();
        listChar.addAll(allAuxiliar);
        return listChar;
    }

    public ObservableList<Characters> GetListPilots() throws Exception {
        Starship ship = searchPilotos();
        List<Characters> list = FalconPilots(ship);
        ObservableList<Characters> listPilots = FXCollections.observableArrayList();
        listPilots.addAll(list);
        return listPilots;
    }
}
