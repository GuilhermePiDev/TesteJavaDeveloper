package com.transfers.system.tgid.tgidProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transfers.system.tgid.tgidProject.Models.CompanyModel;
import com.transfers.system.tgid.tgidProject.Repositories.CompanyRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepo companyRepository;
    
    public void createCompany(CompanyModel company) {
        companyRepository.save(company);
    }

    public List<CompanyModel> listCompanies(){
        return companyRepository.findAll();

    }

    public void existCompany(String cnpj) {
        if (!companyRepository.findByCnpj(cnpj)) {
            throw new EntityNotFoundException("Empresa com o cnpj: *" + cnpj + "* não encontrado");

        }

    }

}
