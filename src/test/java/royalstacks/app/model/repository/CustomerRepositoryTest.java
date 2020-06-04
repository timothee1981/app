package royalstacks.app.model.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import royalstacks.app.model.Customer;
import royalstacks.app.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findCustomerByBSNTest() {
        //public Customer( String postalCode, String houseNumber, String suffix, String city, String phoneNumber, String BSN, Employee accountManager, boolean isBusinessAccountHolder) {
        Customer customer = new Customer("username", "password", "Fiep", "Bakker",
                "emailaddress", "AAAA12", "12", "A", "Hoorn" , "0612345678",
                "753861489", null, false);

/*        customer.setUsername("username1");
        customer.setBSN("302110501");*/

        this.entityManager.persist(customer);
        Optional<Customer> expected = Optional.of(customer);

        Optional<Customer> actual = customerRepository.findCustomerByBSN("753861489");

        assertThat(actual).isEqualTo(expected);
    }


}