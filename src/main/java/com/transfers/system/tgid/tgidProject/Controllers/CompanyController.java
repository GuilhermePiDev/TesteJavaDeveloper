package com.transfers.system.tgid.tgidProject.Controllers;

import com.transfers.system.tgid.tgidProject.Models.CompanyModel;
import com.transfers.system.tgid.tgidProject.Services.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody CompanyModel company) {
        try {
            companyService.createCompany(company);
            return new ResponseEntity<>("Empresa criada com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar empresa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<CompanyModel>> listCompanies() {
        try {
            List<CompanyModel> companies = companyService.listCompanies();
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exists/{cnpj}")
    public ResponseEntity<String> existCompany(@PathVariable String cnpj) {
        try {
            companyService.existCompany(cnpj);
            return new ResponseEntity<>("Empresa encontrada", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao verificar empresa: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
