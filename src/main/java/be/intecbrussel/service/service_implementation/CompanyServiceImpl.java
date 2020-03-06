package be.intecbrussel.service.service_implementation;

import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.implementations.CompanyDaoImpl;
import be.intecbrussel.dao.interfaces.CompanyDao;
import be.intecbrussel.model.Company;
import be.intecbrussel.service.service_interfaces.CompanyService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public Company createAndReturnCompany(Company company) throws CustomException {
        return companyDao.createAndReturnCompany(company);
    }

    @Override
    public void create(Company object) throws CustomException {
        companyDao.create(object);
    }

    @Override
    public Company readById(int id) throws CustomException {
        return companyDao.readById(id);
    }

    @Override
    public void update(Company object) throws CustomException {
        companyDao.update(object);
    }

    @Override
    public void deleteById(int id) throws CustomException {
        companyDao.deleteById(id);
    }

    @Override
    public List<Company> getList() throws CustomException {
        return companyDao.getList();
    }
}
