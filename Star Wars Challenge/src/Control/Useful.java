package Control;

import java.io.BufferedReader;
import java.io.IOException;

public class Useful {

    public static String convertJsonToString(BufferedReader reader) throws IOException {
        String answer, jsonToString = "";
        while ((answer = reader.readLine()) != null){
            jsonToString += answer;
        }
        return jsonToString;
    }
}
