package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Customer;
import royalstacks.app.model.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

/*    public Customer findBySocialSecurityNumber(String socialSecurityNumber) {
        Optional<Customer> c = customerRepository.findBySocialSecurityNumber(socialSecurityNumber);
        if (c.isPresent()) {
            return c.get();
        } else {
            return null;
        }
    }*/

    public Optional<Customer> findBySocialSecurityNumber(String socialSecurityNumber)  {
        return CustomerRepository.findBySocialSecurityNumber(socialSecurityNumber);
    }

}
