package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import royalstacks.app.model.Customer;
import royalstacks.app.model.CustomerAndTotalBalance;
import royalstacks.app.model.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerByUserId(int userId){
        return customerRepository.findCustomerByUserId(userId);
    }

    public Optional<Customer> findCustomerByBSN(String BSN) {
        return customerRepository.findCustomerByBSN(BSN);
    }

    public List<CustomerAndTotalBalance> findTop10BusinessAccounts() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Object[]> results = customerRepository.findCustomersAndBusinessAccountBalance(pageable);
        List<CustomerAndTotalBalance> customersAndTotalBalance = new ArrayList<>();

        for (Object[] result : results) {
            customersAndTotalBalance.add(
                    new CustomerAndTotalBalance(
                            (String) result[0],
                            (String) result[1],
                            (double) result[2]
                    )
            );
        }

        return customersAndTotalBalance;
    }
}
