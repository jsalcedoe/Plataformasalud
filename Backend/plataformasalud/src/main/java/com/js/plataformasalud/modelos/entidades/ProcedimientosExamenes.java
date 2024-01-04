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
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "procexam")

public class ProcedimientosExamenes implements Serializable {
	
	@Id
	@Column(nullable = false, length = 6, unique = true)
	@NotEmpty(message = "El campo codigo del examen o procedimiento no puede ser vacio")
	private String codprocexam;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo nombre del procedimiento o examen no puede ser vacio")
	private String nomprocexam;
	
	@Column(nullable = false)
	@NotEmpty(message = "Por favor seleccione el sexo al que se aplica el procedimiento o examen")
	private String sexoprocexam;//el sexo puede ser 1. Femenino 2. masculino 3. ambos
	
	@Column(nullable = false)
	private String estado;// el estado puede ser: creado, modificado o eliminado
	
	private Long precio;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoProcedimiento tprocexam;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Tarifa tarifprocexam;
	
	private static final long serialVersionUID = 1L;

}
