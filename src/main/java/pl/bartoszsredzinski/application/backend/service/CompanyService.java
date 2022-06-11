package pl.bartoszsredzinski.application.backend.service;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 12.06.2022
 */

import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.application.backend.model.entity.Company;
import pl.bartoszsredzinski.application.backend.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService{
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }
}