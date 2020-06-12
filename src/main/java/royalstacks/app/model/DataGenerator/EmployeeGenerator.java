package royalstacks.app.model.DataGenerator;

import royalstacks.app.model.Employee;



public class EmployeeGenerator {
    final private static String headBusUsername = "headbusiness";
    final private static String headBusName = "Anna";
    final private static String headBusLastname = "Werner";
    final private static String headBusposition = "headbusiness";

    final private static String headPrivUsername = "headprivate";
    final private static String headPrivName = "Peter";
    final private static String headPrivLastname = "Hamers";
    final private static String headPrivPosition = "headprivate";

    final private static String password = "Auto!12345";

    public static Employee headBusinessGenerator() {
        return new Employee(headBusUsername, password, headBusName, headBusLastname, headBusposition);
    }
    public static Employee headPrivateGenerator() {
        return  new Employee(headPrivUsername, password, headPrivName, headPrivLastname, headPrivPosition);
    }
}
