package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Customer;
import royalstacks.app.model.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
