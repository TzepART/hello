package test.genarateSql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class ClientList {

    private Integer count;
    private ArrayList<Client> clients = new ArrayList<Client>();
    private String fileName = "src/test/genarateSql/client.sql";

    ClientList() {

    }

    ArrayList<Client> getClientsList(){

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
        client.setName(row);
        this.clients.add(client);
    }
}
