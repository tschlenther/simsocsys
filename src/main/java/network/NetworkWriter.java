package network;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NetworkWriter {

    private final Network net;

    public NetworkWriter(Network net) {
        this.net = net;
    }

    public void write(String file) {

        JSONObject jNet = new JSONObject();
        JSONArray nodeArr = new JSONArray();
        jNet.put("nodes",nodeArr);
        for (Node node : net.getNodes()) {
            JSONObject obj = new JSONObject();
            obj.put("id",node.getId());
            obj.put("x",node.getX());
            obj.put("y",node.getY());
            nodeArr.add(obj);
        }
        JSONArray linkArr = new JSONArray();
        jNet.put("links",linkArr);
        for (Link link : net.getLinks()) {

            JSONObject obj = new JSONObject();
            obj.put("id",link.getId());
            obj.put("from",link.getFrom().getId());
            obj.put("to",link.getTo().getId());
            linkArr.add(obj);
        }

        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File(file)));
            jNet.writeJSONString(bf);
            bf.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
