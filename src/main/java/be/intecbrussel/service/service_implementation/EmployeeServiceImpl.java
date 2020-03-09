package be.intecbrussel.service.service_implementation;

import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.implementations.EmployeeDaoImpl;
import be.intecbrussel.dao.interfaces.Dao;
import be.intecbrussel.model.Employee;
import be.intecbrussel.service.service_interfaces.Service;

import java.util.List;

public class EmployeeServiceImpl implements Service<Employee> {

    private Dao<Employee> employeeDao = new EmployeeDaoImpl();

    @Override
    public void create(Employee object) throws CustomException {
        employeeDao.create(object);
    }

    @Override
    public Employee readById(int id) throws CustomException {
        return employeeDao.readById(id);
    }

    @Override
    public void update(Employee object) throws CustomException {
        employeeDao.update(object);
    }

    @Override
    public void deleteById(int id) throws CustomException {
        employeeDao.deleteById(id);
    }

    @Override
    public List<Employee> getList() throws CustomException {
        return employeeDao.getList();
    }
}
