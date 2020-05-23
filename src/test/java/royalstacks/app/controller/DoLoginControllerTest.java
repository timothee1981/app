package royalstacks.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import royalstacks.app.model.Customer;
import royalstacks.app.model.Employee;
import royalstacks.app.model.User;
import royalstacks.app.service.LogInService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(DoLoginController.class)
class DoLoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LogInService service;

    @Test
    void doLoginHandlerTestUsername() throws Exception {
        //ARRANGE
        String username = "nonexistent";
        String password = "password";
        given(service.findByUsername(username)).willReturn(null);

        //ACT & ASSERT
        mvc.perform(
                post("/doLogin")
                        .param("inputUsername", username)
                        .param("inputPassword", password)
        ).andExpect(status().isOk()).andExpect(view().name("homepage"));
    }

    @Test
    void doLoginHandlerTestPassword() throws Exception {
        //ARRANGE
        String username = "user";
        String password = "notCorrect";
        Customer customer = new Customer();
        customer.setPassword("correct");
        given(service.findByUsername(username)).willReturn(customer);

        //ACT & ASSERT
        mvc.perform(
                post("/doLogin")
                        .param("inputUsername", username)
                        .param("inputPassword", password)
        ).andExpect(status().isOk()).andExpect(view().name("homepage"));
    }

    @Test
    void doLoginHandlerTestEmployee() throws Exception {
        //ARRANGE
        String username = "employee";
        String password = "correct";
        Employee employee = new Employee();
        employee.setPassword("correct");
        given(service.findByUsername(username)).willReturn(employee);

        //ACT & ASSERT
        mvc.perform(
                post("/doLogin")
                        .param("inputUsername", username)
                        .param("inputPassword", password)
        ).andExpect(status().isOk()).andExpect(view().name("headprivateoverview"));
    }

    @Test
    void doLoginHandlerTestCustomer() throws Exception {
        //ARRANGE
        String username = "customer";
        String password = "correct";
        Customer customer = new Customer();
        customer.setPassword("correct");
        given(service.findByUsername(username)).willReturn(customer);

        //ACT & ASSERT
        mvc.perform(
                post("/doLogin")
                        .param("inputUsername", username)
                        .param("inputPassword", password)
        ).andExpect(status().isOk()).andExpect(view().name("myaccounts"));
    }

    @Test
    void doLoginHandlerTestNotACustomerOrEmployee() throws Exception {
        //ARRANGE
        String username = "supervisor";
        String password = "correct";

        class Supervisor extends User {
        }

        Supervisor supervisor = new Supervisor();
        supervisor.setPassword("correct");
        given(service.findByUsername(username)).willReturn(supervisor);

        //ACT & ASSERT
        mvc.perform(
                post("/doLogin")
                        .param("inputUsername", username)
                        .param("inputPassword", password)
        ).andExpect(status().isOk()).andExpect(view().name("homepage"));
    }
}