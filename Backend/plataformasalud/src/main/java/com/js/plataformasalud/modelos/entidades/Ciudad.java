package com.js.plataformasalud.modelos.entidades;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ciudades")
@Getter
@Setter

public class Ciudad implements Serializable {
		 
	@Id
	@Column (nullable = false, length = 5, unique = true )
	private Long codciudad;
	
	@Column (nullable = false, length = 30)
	private String nomciudad;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Departamento departamento;
	
	
	private static final long serialVersionUID = 1L;

}
