package royalstacks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import royalstacks.app.model.repository.EmployeeRepository;

public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
}
