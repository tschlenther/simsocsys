package network;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class NetworkReader {

    private final Network net;

    public NetworkReader(Network net) {
        this.net = net;
    }

    public void read(String file) {

        JSONObject obj = null;
        try {
            JSONParser parser = new JSONParser();
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            obj = (JSONObject) parser.parse(br);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONArray nodes = (JSONArray) obj.get("nodes");
        for (Object o : nodes.toArray()) {
            JSONObject jo = (JSONObject) o;
            long id = (long) jo.get("id");
            this.net.createNode((double) jo.get("x"), (double) jo.get("y"), (int) id);
        }

        JSONArray links = (JSONArray) obj.get("links");
        for (Object o : links.toArray()) {
            JSONObject jo = (JSONObject) o;

            long fr = (long) jo.get("from");
            Node from = net.getNode((int) fr);
            long t = (long) jo.get("to");
            Node to = net.getNode((int) t);

            long id = (long) jo.get("id");
            this.net.createLink(from, to, (int) id);
        }

    }
}
