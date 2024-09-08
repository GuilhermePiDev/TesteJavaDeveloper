package com.transfers.system.tgid.tgidProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transfers.system.tgid.tgidProject.Models.CostumerModel;

public interface CostumerRepo extends JpaRepository<CostumerModel, Integer>{
	boolean findByCpf(String cpf);
}
