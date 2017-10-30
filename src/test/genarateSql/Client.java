package test.genarateSql;

import java.util.ArrayList;

public class Client {

    /**
     * @param count
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getClientIdsArray(Integer count){
        ArrayList<Integer> ids = new ArrayList<Integer>(count);
        for (int i = 1; i<= count; i++){
            ids.add(i);
        }
        return ids;
    }

}
