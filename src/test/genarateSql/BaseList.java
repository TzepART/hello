package test.genarateSql;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BaseList {

    protected String fileName;

    BaseList(String path) {
        fileName = path;
    }

    protected Map<String, String> getValuesByQueryRow(String row){
        Map<String, String> values = new HashMap<>();

        Pattern pattern = Pattern.compile("[(]([^()]*)[)]");
        Matcher matcher = pattern.matcher(row);
        int i = 0;
        String keysStr = "";
        String valuesStr = "";
        while(matcher.find())
        {
            if(i == 0){
                keysStr = matcher.group(1).replaceAll("['`]","");
            }else if(i == 1){
                valuesStr = matcher.group(1).replace("'","");
            }
            i++;
        }

        String[] keysArray = this.stringToArray(keysStr);
        String[] valuesArray = this.stringToArray(valuesStr);


        if(keysArray.length == valuesArray.length){
            for(i = 0; i< keysArray.length; i++){
                values.put(keysArray[i].trim(),valuesArray[i].trim());
            }
        }

        return values;
    }

    private String[] stringToArray(String s) {
        return s.split("[,]");
    }
}
