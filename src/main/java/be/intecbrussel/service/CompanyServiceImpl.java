package be.intecbrussel.service;

import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.dao.implementations.CompanyDaoImpl;
import be.intecbrussel.dao.interfaces.CompanyDao;
import be.intecbrussel.model.Company;

public class CompanyServiceImpl {

    private CompanyDao companyDao = new CompanyDaoImpl();

    public Company createAndReturnCompany(Company company) throws CustomException {
        return companyDao.createAndReturnCompany(company);
    }
}
