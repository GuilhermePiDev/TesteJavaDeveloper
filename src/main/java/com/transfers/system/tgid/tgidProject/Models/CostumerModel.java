package com.transfers.system.tgid.tgidProject.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="costumers")
public class CostumerModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String name;
	private Double balance;
	@Column(nullable = false, unique = true)
	private String cpf;



}
