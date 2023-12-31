package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="eapb")

public class Entidad implements Serializable {
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ideapb;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "El campo entidad no puede ser vacio")
	private String nomeapb;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "El campo direccion de la entidad no puede ser vacio")
	private String direapb;
	
	@Column(nullable = false, length = 15)
	@NotEmpty(message = "El campo número de contacto de la entidad no puede ser vacio")
	private String contaceapb;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "El campo email de la entidad no puede ser vacio")
	private String emaileapb;
	
	@Column(nullable = false, length = 45)
	@NotEmpty(message = "El campo gerente no puede ser vacio")
	private String gerenteapb;
	
	@NotEmpty(message = "Selecciones el tipo de entidad a la que pertenece ")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoEAPB tipent;
	
	private static final long serialVersionUID = 1L;

}
