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

    private static final int BUSINESS_ACCOUNT_SIZE = 10;

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
        Pageable pageable = PageRequest.of(0, BUSINESS_ACCOUNT_SIZE);
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

    public boolean isBSNFormatValid(String BSN){
        if(!BSN.matches("^[0-9]+$") || BSN.length() != BSN_LENGTH){
            return false;
        } else {
            // voer 11 proef voor BSN uit
            String firstNumbers = BSN.substring(0,BSN_LENGTH - 1);
            int lastNumber = Integer.parseInt(BSN.substring(BSN_LENGTH - 1)) * -1;

            int sum = 0;
            for (int i = 0; i < firstNumbers.length(); i++) {
                sum += firstNumbers.charAt(i) * (BSN.length() - i);
            }
            sum += lastNumber;

            return sum%11 == 0;
        }
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        phoneNumber = phoneNumber.replace(" ", "");
        return  // vast nummer zonder +31
                phoneNumber.matches("^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$")
                        // of mobiel nummber zonder +31
                        || phoneNumber.matches("^(((\\+31|0|0031)6)[1-9][0-9]{7})$");
    }

    public boolean isCityValid(String city){
        city = city.trim();
        return city.matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
    }

    public boolean isEmailValid(String email){
        email = email.trim();
        // volgt RFC 5322 Official Standard
        return email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
                        "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                        "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                        "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                        "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                        "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
    }

    public boolean isHouseNumberValid(String houseNumber){
        houseNumber = houseNumber.trim();
        return houseNumber.matches("^[0-9]{1,6}+([-]\\d{1,5})?$");
    }

    public boolean isPostalCodeValid(String postalCode) {
        postalCode = postalCode.replace(" ", "");
        postalCode = postalCode.toUpperCase();

        // Postcode bestaat uit 4 getallen en 2 letters. Begint nooit met een 0 en bevat nooit SS, SA of SD
        return postalCode.matches("^[1-9][0-9]{3} ?(?!SA|SD|SS)[A-Z]{2}$");
    }


}
