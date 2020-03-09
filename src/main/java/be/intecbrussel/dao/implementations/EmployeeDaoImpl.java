package be.intecbrussel.dao.implementations;

import be.intecbrussel.config.ConnectionProvider;
import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.interfaces.CompanyDao;
import be.intecbrussel.dao.interfaces.Dao;
import be.intecbrussel.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements Dao<Employee> {


    private CompanyDao companyDao = new CompanyDaoImpl();

    public Connection createConnection() throws SQLException {
        return ConnectionProvider.getInstance().getConnection();
    }

    @Override
    public void create(Employee object) throws CustomException {
        try (PreparedStatement preparedStatement = createConnection()
                .prepareStatement("INSERT INTO company_db.employees(name, job_title, company_id) " +
                        "VALUES (?,?,?)")) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getJobTitle());
            preparedStatement.setInt(3, object.getCompany().getId());

            preparedStatement.execute();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException();
        }
    }

    @Override
    public Employee readById(int id) throws CustomException {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("SELECT * FROM employees where employee_id=?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Employee employee = new Employee();

                if (resultSet.next()) {

                    employee.setId(resultSet.getInt("employee_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setJobTitle(resultSet.getString("job_title"));
                    employee.setCompany(companyDao.readById(resultSet.getInt("company_id")));
                    return employee;

                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException();
        }
    }

    @Override
    public void update(Employee object) throws CustomException {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("UPDATE employees SET job_title = ? WHERE employee_id = ?")) {

            preparedStatement.setString(1, object.getJobTitle());
            preparedStatement.setInt(2, object.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException();
        }
    }

    @Override
    public void deleteById(int id) throws CustomException {
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("DELETE FROM employees where employee_id=?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException();
        }
    }

    @Override
    public List<Employee> getList() throws CustomException {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = createConnection().prepareStatement("SELECT * FROM employees")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("employee_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setJobTitle(resultSet.getString("job_title"));
                    employee.setCompany(companyDao.readById(resultSet.getInt("company_id")));
                    employees.add(employee);
                }
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException();
        }
        return employees;
    }
}
