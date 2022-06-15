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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService{
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Map<String, Integer> getStats(){
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(company -> stats.put(company.getName(), company.getEmployees().size()));
        return stats;
    }
}