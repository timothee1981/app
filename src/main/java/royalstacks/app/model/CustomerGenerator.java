package royalstacks.app.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CustomerGenerator {

    public static JSONArray createJsonArrayFromFile(String filePath) {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
            JSONArray jsonArray = (org.json.simple.JSONArray) jsonParser.parse(fileReader);
            System.out.println(jsonArray);
            return jsonArray;

        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public static List<Customer> printJsonArrayValues(JSONArray jsonArray){

        Iterator<JSONObject> iterator = jsonArray.iterator();
        List<Customer> customers = new ArrayList<>();
        while(iterator.hasNext()) {
            JSONObject person = iterator.next();

            String username = person.get("username").toString();
            String firstName = person.get("firstName").toString();
            String lastName = person.get("lastName").toString();
            String email = person.get("email").toString();
            String houseNumber = person.get("houseNumber").toString();
            String city = person.get("city").toString();
            String street = person.get("street").toString();
            String phoneNumber = person.get("phoneNumber").toString();
            String postcode = "2431BH";
            String password = "Password!12345";
            String bsn = "686353705";
            CustomerAddress address = new CustomerAddress(postcode, houseNumber, "", city, street);
            Customer customer = new Customer(username, password, firstName, lastName, email, address, phoneNumber, bsn, null,  false);
            System.out.println(customer);
        }
        return customers;
    }

    public static String postalCodeGenerator(){
        final int MAX = 10000;
        final int MIN = 1111;
        int number = (int)(Math.random()*(MAX-MIN))+MIN;
        char letter1 = (char)('A' + Math.random() * ('H' - 'A' + 1));
        char letter2 = (char)('A' + Math.random() * ('H' - 'A' + 1));
        return String.format("%d%c%c", number, letter1, letter2);
    }
    public static String bsnGenerator(){
        int MAX =1000000000;
        return String.valueOf((int)(Math.random() * MAX));
    }

    public static String generateSuffix(){
        return "";

    }
    public static boolean generateRandomTrueFalse(int percentageTrue){
        int MAX = 100;
        return(Math.random()*MAX < percentageTrue);
    }




}
