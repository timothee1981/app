package royalstacks.app.controller;

import royalstacks.app.model.Customer;

import java.util.Optional;

public interface GetCustomer {


    Customer getCustomerByUserId(int userId);

}
