package royalstacks.app.backingBean;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import royalstacks.app.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerBackingBeanTest {

    @Test
    void customer() {

        CustomerBackingBean cbb = new CustomerBackingBean("MiekeG!", "Oli3b@l",
                "Mieke", "Geelen", "miekegeelen@gmail.com", "Westerlaan 24",
                "Heerlen", "4578 DG", "06789512348", "954786327");

        //actual
        Customer testCustomer = cbb.customer();

        //expected
        Customer customer = new Customer("MiekeG!", "Oli3b@l",
                "Mieke", "Geelen", "miekegeelen@gmail.com", "Westerlaan 24",
                "Heerlen", "4578 DG", "06789512348", "954786327", null, false);

        Assert.assertEquals(customer, testCustomer);

        cbb.setLastName("Mieke");
        cbb.setFirstName("Geelen");
        Customer testCustomer2 = cbb.customer();

        Assert.assertNotEquals(customer, testCustomer2);

        cbb.setAddress("4578 DG");
        cbb.setPostalCode("Westerlaan 24");
        customer.setAddress("4578 DG");
        customer.setPostalCode("Westerlaan 24");
        Customer testCustomer3 = cbb.customer();

        Assert.assertEquals(customer, testCustomer3);
    }
}