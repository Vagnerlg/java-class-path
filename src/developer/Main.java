package developer;
import org.json.JSONObject;

import developer.entity.Developer;

public class Main {
    public static void main(String[] args) {
        var dev = new Developer(
                "John Doe",
                30,
                "New York",
                new String[]{"Java", "Python", "JavaScript"}
        );
        
        JSONObject jsonObject = new JSONObject(dev);
        
        System.out.println(jsonObject.toString(4));
    }
}