package royalstacks.app.model.DataGenerator;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public abstract class Gen {

    public static char randomChar(char maxChar){
        return (char)('A' + Math.random() * (maxChar - 'A' + 1));
    }

    public static boolean generateRandomTrueFalse(int percentageTrue) {
        int MAX = 100;
        return (Math.random() * MAX < percentageTrue);
    }

    public static int randomInt(int min, int max){
        return (int)((Math.random()*(max-min+1))+min);
    }

    public static JSONArray createJsonArrayFromFile(String filePath) {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
            JSONArray jsonArray = (org.json.simple.JSONArray) jsonParser.parse(fileReader);
            return jsonArray;
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public static BigDecimal partiallyRandomAmount(int min, int maxCommon, int maxUncommon, int percentageCommon){
        double randomAmount;
        if(Gen.generateRandomTrueFalse(percentageCommon)){
            randomAmount =(Gen.randomInt(min, maxCommon *100))/100.0;
            return BigDecimal.valueOf(randomAmount);
        }
        else {
            randomAmount = (Gen.randomInt(min, maxUncommon * 100)) / 100.0;
            return BigDecimal.valueOf(randomAmount);
        }
    }


}
