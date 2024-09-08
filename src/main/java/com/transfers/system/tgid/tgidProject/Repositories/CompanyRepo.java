package com.transfers.system.tgid.tgidProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transfers.system.tgid.tgidProject.Models.CompanyModel;


public interface CompanyRepo extends JpaRepository<CompanyModel, Integer> {
	boolean findByCnpj(String cnpj);
	
}
