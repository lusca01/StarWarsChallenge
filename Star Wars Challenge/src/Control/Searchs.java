package Control;

import Model.Starship;
import Model.Character;
import View.Main;
import com.google.gson.Gson;

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

    public static List<Character> searchParticipations() throws Exception{
        int sucess = 200, c = 1;
        List<Character> AllCharacters = new ArrayList<>();

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
                Character Character = gson.fromJson(jsonToString, Character.class);

                AllCharacters.add(Character);
                c++;
                if(c == 17){
                    c++;
                }
            }catch (Exception e){
                throw new Exception("Erro: " + e);
            }
        }
        AllCharacters.sort(Comparator.comparingInt(Character::getLenght).reversed());
        return AllCharacters;
    }

    public static Starship searchPilotos() throws Exception {
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

    public static List<Character> FalconPilots(Starship pilotos) throws Exception{
        List<Character> Character = new ArrayList<>();
        for (String pilots: pilotos.pilots) {
            String newURL = pilots + json;
            URL url = new URL(newURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != 200)
                throw new RuntimeException("Http error code: " + connection.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonToString = Useful.convertJsonToString(reader);

            Gson gson = new Gson();
            Character Pilots = gson.fromJson(jsonToString, Character.class);

            Character.add(Pilots);
        }

        return Character;
    }
}
