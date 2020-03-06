package be.intecbrussel;

import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.implementations.EmployeeDaoImpl;
import be.intecbrussel.dao.interfaces.Dao;
import be.intecbrussel.model.Company;
import be.intecbrussel.model.Employee;
import be.intecbrussel.service.CompanyServiceImpl;

public class MainApp {
    public static void main(String[] args) {

        CompanyServiceImpl companyService = new CompanyServiceImpl();
        Dao<Employee> employeeDao = new EmployeeDaoImpl();

        try {
//            dao.create(new Company("intec"));
//            System.out.println(dao.createAndReturnCompany(new Company("moktok")));
        Company company = companyService.createAndReturnCompany(new Company("Company name"));
        employeeDao.create(new Employee("smth","smth",company));

        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
