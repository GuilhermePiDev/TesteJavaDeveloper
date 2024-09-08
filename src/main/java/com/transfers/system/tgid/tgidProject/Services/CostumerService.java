package com.transfers.system.tgid.tgidProject.Services;


import com.transfers.system.tgid.tgidProject.Exceptions.InsufficientBalanceException;
import com.transfers.system.tgid.tgidProject.Models.CompanyModel;
import com.transfers.system.tgid.tgidProject.Models.CostumerModel;
import com.transfers.system.tgid.tgidProject.Repositories.CostumerRepo;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostumerService {
    CostumerModel costumer;
    CompanyModel company;

    @Autowired
    private CostumerRepo costumerRepository;

    public CostumerModel saque(Double valorS) {
        Double valorComTaxa = valorS + company.getRate();
    
        insufficientBalanceVerify(valorComTaxa);
        
        costumer.setBalance(costumer.getBalance() - valorComTaxa);
        company.setBalance(company.getBalance() - valorComTaxa);

        createCostumer(costumer);
    
        return costumer;
    }

    public CostumerModel deposito(Double valorS) {
        
        Double valorComTaxa = valorS + company.getRate();
    
        costumer.setBalance(costumer.getBalance() + valorComTaxa);
        company.setBalance(company.getBalance() + valorComTaxa);
        createCostumer(costumer);
    
        return costumer;
    }
    


    
    public void createCostumer(CostumerModel costumer) {
        costumerRepository.save(costumer);
    }

    public List<CostumerModel> listCostumers(){
        return costumerRepository.findAll();
    }

    public Double rate(Double balanceCostumer, Double rateCompany) {
        return balanceCostumer - rateCompany;
    }
    

    public void insufficientBalanceVerify(Double valor){
        if (costumer.getBalance() < valor || company.getBalance() < valor) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação.");
        }
    }
    public void existCostumer(String cpf) {
        if (!costumerRepository.findByCpf(cpf)) {
            throw new EntityNotFoundException("Cliente com o cpf: *" + cpf + "* não encontrado");

        }

    }
}


