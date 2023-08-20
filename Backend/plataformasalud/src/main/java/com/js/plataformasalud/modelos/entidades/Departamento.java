package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departamentos")
@Getter
@Setter

public class Departamento implements Serializable {
	
	@Id
	@Column (name = "id_departamento",nullable = false, length = 2, unique = true )
	private Long coddep;
	
	@Column (nullable = false, length = 30 )
	private String departamento;
	
	
	private static final long serialVersionUID = 1L;

}
