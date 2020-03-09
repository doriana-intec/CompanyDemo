package be.intecbrussel.service.service_interfaces;

import be.intecbrussel.custom_exception.CustomException;
import be.intecbrussel.model.Company;

public interface CompanyService extends Service<Company>{

    Company createAndReturnCompany(Company company) throws CustomException;

}
