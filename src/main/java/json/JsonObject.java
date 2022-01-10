package json;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Map<String, Json> values = new HashMap<String, Json>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            values.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder MainPart = new StringBuilder();

        for (String Key: values.keySet()) {
            if (!MainPart.equals("")) {
                MainPart.append(" ,");
            }
            MainPart.append("'" + Key + "': ");
            MainPart.append(values.get(Key).toJson());
        }
        return "{" + MainPart + "}";
    }

    public void add(JsonPair jsonPair) {
        values.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return values.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject proj = new JsonObject();

        for (String name: names) {
            if (values.containsKey(name)) {
                proj.add(new JsonPair(name, values.get(name)));
            }
        }
        return proj;
    }
}
