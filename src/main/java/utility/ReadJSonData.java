package utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadJSonData {

    String n;
    String j;
    String k;

    public String Read_the_value_from_json(String path,String keyword) throws IOException, ParseException {

        Reader reader = new FileReader(path);
        JSONParser parser = new JSONParser();
        JSONArray jsonArr = (JSONArray) parser.parse(reader);
        for (Object obj : jsonArr) {

            JSONObject jo = (JSONObject) obj;
            k = (String) jo.get(keyword);
        }
        return k;

    }
}
