package test.genarateSql.List;

import test.genarateSql.entity.Client;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class ClientList extends BaseList{

    private ArrayList<Client> clients = new ArrayList<>();

    public ClientList(String path) {
        super(path);
    }

    public ArrayList<Client> getClientsList(){

        //read file into stream, try-with-resources
        try {
            File file = new File(this.fileName);
            BufferedReader b = new BufferedReader(new FileReader(file));

            String readLine = "";

            while ((readLine = b.readLine()) != null) {
               this.addClient(readLine);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.clients;
    }

    private void addClient(String row){
        Client client = new Client();
        Map<String, String> values = this.getValuesByQueryRow(row);

        for (Map.Entry<String, String> entry : values.entrySet())
        {
            switch (entry.getKey()) {
                case "id":
                    client.setId(Integer.parseInt(entry.getValue()));
                    break;
                case "name":
                    client.setName(entry.getValue());
                    break;
                case "family":
                    client.setFamily(entry.getValue());
                    break;
                case "passport":
                    client.setPassport(entry.getValue());
                    break;
            }
        }

        this.clients.add(client);
    }
}
