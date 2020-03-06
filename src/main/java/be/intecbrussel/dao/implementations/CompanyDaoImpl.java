package be.intecbrussel.dao.implementations;

import be.intecbrussel.config.ConnectionProvider;
import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.interfaces.CompanyDao;
import be.intecbrussel.model.Company;

import java.sql.*;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {


    public Connection createConnection() throws SQLException {
        return ConnectionProvider.getInstance().getConnection();
    }

    @Override
    public Company createAndReturnCompany(Company company) throws CustomException{
        try(PreparedStatement preparedStatement = createConnection()
                .prepareStatement("INSERT INTO company_db.companies(name) VALUES (?)"
                        ,PreparedStatement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,company.getName());
            preparedStatement.execute();

            try(ResultSet rs = preparedStatement.getGeneratedKeys()){
                if (rs.next()){
                    int id = rs.getInt(1);
                    return new Company(id,company.getName());
                }else{
                    return null;
                }
            }

        }catch (SQLException se){
            System.out.println(se.getMessage());
            throw new CustomException();
        }
    }

    @Override
    public void create(Company object) throws CustomException {
        try (PreparedStatement preparedStatement =
                     createConnection()
                             .prepareStatement("INSERT INTO company_db.companies(name) VALUES (?)")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.execute();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            throw new CustomException("Something went wrong");
        }
    }

    @Override
    public Company readById(int id) throws CustomException {

        return null;
    }

    @Override
    public void update(Company object) throws CustomException {

    }

    @Override
    public void deleteById(int id) throws CustomException {

    }

    @Override
    public List<Company> getList() throws CustomException {
        return null;
    }
}
