package com.transfers.system.tgid.tgidProject.Controllers;

import com.transfers.system.tgid.tgidProject.Exceptions.InsufficientBalanceException;
import com.transfers.system.tgid.tgidProject.Models.CostumerModel;
import com.transfers.system.tgid.tgidProject.Services.CompanyService;
import com.transfers.system.tgid.tgidProject.Services.CostumerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costumers")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/saque")
    public ResponseEntity<String> saque(@RequestParam String cpf, @RequestParam String cnpj, @RequestParam Double valor) {
        try {
            // Verifica se o cliente e a empresa existem
            costumerService.existCostumer(cpf);
            companyService.existCompany(cnpj); // Implementar este método conforme necessário
            
            // Realiza o saque
            CostumerModel costumer = costumerService.saque(valor);
            return new ResponseEntity<>("Saque realizado com sucesso. Novo saldo: " + costumer.getBalance(), HttpStatus.OK);
        } catch (InsufficientBalanceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao realizar saque: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> deposito(@RequestParam String cpf,@RequestParam String cnpj , @RequestParam Double valor) {
        try {
            // Verifica se o cliente e a empresa existem
            costumerService.existCostumer(cpf);
            companyService.existCompany(cnpj); // Implementar este método conforme necessário
            
            // Realiza o depósito
            CostumerModel costumer = costumerService.deposito(valor);
            return new ResponseEntity<>("Depósito realizado com sucesso. Novo saldo: " + costumer.getBalance(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao realizar depósito: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCostumer(@RequestBody CostumerModel costumer) {
        try {
            costumerService.createCostumer(costumer);
            return new ResponseEntity<>("Cliente criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar cliente: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<CostumerModel>> listCostumers() {
        try {
            List<CostumerModel> costumers = costumerService.listCostumers();
            return new ResponseEntity<>(costumers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exists/{cpf}")
    public ResponseEntity<String> existCostumer(@PathVariable String cpf) {
        try {
            costumerService.existCostumer(cpf);
            return new ResponseEntity<>("Cliente encontrado", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao verificar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
