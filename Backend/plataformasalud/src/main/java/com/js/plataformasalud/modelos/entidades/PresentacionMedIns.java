package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pmedins")

public class PresentacionMedIns implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idpmedins;
	
	private String presmedins;
	/*
	las presentaciones pueden ser:
	Ampolla
	Tableta
	Capsula
	entre otros
	 */
	
	private static final long serialVersionUID = 1L;

}
