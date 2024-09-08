package com.transfers.system.tgid.tgidProject.Models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="company")
public class CompanyModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String name;
	private Double balance;
	private Double rate;
	@Column(nullable = false, unique = true)
	private String cnpj;


}
