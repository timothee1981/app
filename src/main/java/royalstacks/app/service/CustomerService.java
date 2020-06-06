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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private static final int BUSINESS_ACCOUNT_SIZE = 10;
    private static final int BSN_LENGTH = 9;

    private static final Pattern phoneHomeRegex = Pattern.compile("^(((0)[1-9]{2}[0-9][-]?[1-9][0-9]{5})|((\\+31|0|0031)[1-9][0-9][-]?[1-9][0-9]{6}))$");
    private static final Pattern phoneMobileRegex = Pattern.compile("^(((\\\\+31|0|0031)6)[19][0-9]{7})$");
    private static final Pattern cityRegex = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
    private static final Pattern houseNumberRegex = Pattern.compile("^[0-9]{1,6}+([-]\\d{1,5})?$");
    private static final Pattern postalCodeRegex = Pattern.compile("^[1-9][0-9]{3} ?(?!SA|SD|SS)[A-Z]{2}$");
    private static final Pattern BSNRegex = Pattern.compile("^[0-9]+$");
    private static final Pattern emailRegex = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
                    "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\" +
                    "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                    "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");


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


    public boolean isPhoneNumberValid(String phoneNumber){
        Matcher home = phoneHomeRegex.matcher(phoneNumber);
        Matcher mobile = phoneMobileRegex.matcher(phoneNumber);
        return  home.matches() || mobile.matches();
    }

    public boolean isCityValid(String city){
        Matcher m = cityRegex.matcher(city);
        return m.matches();
    }

    public boolean isEmailValid(String email){
        Matcher m = emailRegex.matcher(email);
        return m.matches();
    }

    public boolean isHouseNumberValid(String houseNumber){
        Matcher m = houseNumberRegex.matcher(houseNumber);
        return m.matches();
    }

    public boolean isPostalCodeValid(String postalCode) {
        postalCode = postalCode.replace(" ", "").toUpperCase();
        Matcher m = postalCodeRegex.matcher(postalCode);
        return m.matches();
    }

    public boolean isBSNFormatValid(String BSN){
        Matcher m = BSNRegex.matcher(BSN);
        if(!m.matches() || BSN.length() != BSN_LENGTH){
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
}
