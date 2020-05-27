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

        Customer testCustomer = cbb.customer();

        Assert.assertEquals(cbb, testCustomer);

/*        String expected = testCustomer.getUsername();
        String actual =*/
    }
}