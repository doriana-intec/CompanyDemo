package be.intecbrussel.dao.implementations;

import be.intecbrussel.config.ConnectionProvider;
import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.interfaces.CompanyDao;
import be.intecbrussel.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {


    public Connection createConnection() throws SQLException {
        return ConnectionProvider.getInstance().getConnection();
    }

    @Override
    public Company createAndReturnCompany(Company company) throws CustomException {
        try (PreparedStatement preparedStatement = createConnection()
                .prepareStatement("INSERT INTO company_db.companies(name) VALUES (?)"
                        , PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.execute();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return new Company(id, company.getName());
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
    public void create(Company object) throws CustomException {
        try (PreparedStatement preparedStatement =
                     createConnection().prepareStatement("INSERT INTO company_db.companies(name) VALUES (?)")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.execute();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException("Something went wrong");
        }
    }

    @Override
    public Company readById(int id) throws CustomException {
        try (PreparedStatement preparedStatement =
                     createConnection().prepareStatement("SELECT * FROM companies where company_id=?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Company company = new Company();
                if (resultSet.next()) {
                    company.setId(resultSet.getInt("company_id"));
                    company.setName(resultSet.getString("name"));
                    return company;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CustomException();
        }
    }

    @Override
    public void update(Company object) throws CustomException {
        try (PreparedStatement preparedStatement =
                     createConnection().prepareStatement("UPDATE companies SET name= ? where company_id=?")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            throw new CustomException();
        }
    }

    @Override
    public void deleteById(int id) throws CustomException {
        try (PreparedStatement preparedStatement =
                     createConnection().prepareStatement("DELETE FROM companies where company_id=?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            throw new CustomException();
        }

    }

    @Override
    public List<Company> getList() throws CustomException {
        List<Company> companies = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     createConnection().prepareStatement("SELECT * FROM companies")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Company company = new Company();
                    company.setId(resultSet.getInt("id"));
                    company.setName(resultSet.getString("name"));
                    companies.add(company);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CustomException();
        }

        return companies;
    }
}
