package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "procexam")

public class ProcedimientosExamenes implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpxex;
	
	@Column(nullable = false, unique = true, length = 6)
	@NotEmpty(message = "El campo codigo del procedimiento no puede ser vacio")
	private String codpxex;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo nombre del procedimiento o examen no puede ser vacio")
	private String nompxex;
	
	@Column(nullable = false)
	@NotEmpty(message = "Por favor seleccione el sexo al que se aplica el procedimiento o examen")
	private String sexopxex;//el sexo puede ser 1. Femenino 2. masculino 3. ambos
	
	@Temporal(TemporalType.DATE)
	private Date datecreatpxex;
	
	@PrePersist
	public void creadate() {
		datecreatpxex = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date dateeditpxex;
	
	@PreUpdate
	public void editdate() {
		dateeditpxex = new Date ();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estadopxex_fk;// el estado puede ser: creado, modificado o eliminado
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoProcedimiento tpxex;
	
	private static final long serialVersionUID = 1L;

}
