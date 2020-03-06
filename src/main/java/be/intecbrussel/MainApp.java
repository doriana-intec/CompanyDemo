package be.intecbrussel;

import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.model.Company;
import be.intecbrussel.model.Employee;
import be.intecbrussel.service.service_implementation.CompanyServiceImpl;
import be.intecbrussel.service.service_implementation.EmployeeServiceImpl;
import be.intecbrussel.service.service_interfaces.CompanyService;

public class MainApp {
    public static void main(String[] args) {

        CompanyService companyService = new CompanyServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Company company;

        try {
            companyService.create(new Company("intec"));
            company = companyService.createAndReturnCompany(new Company("Company name"));

            employeeService.create(new Employee("smth", "smth", company));
            employeeService.getList().forEach(System.out::println);

        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
