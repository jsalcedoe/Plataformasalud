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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tipac")

public class TipoPaciente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idtipac;
	
	@Column(nullable = false, length = 6, unique = true)
	@NotEmpty(message = "El campo tipo de paciente no puede ser vacio")
	private String nomtipac;
	
	@Column(nullable = false,length = 15)
	@NotEmpty(message = "El campo detalle del tipo de paciente no puede ser vacio")
	private String dettipac;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatipac;
	
	@PrePersist
	public void creatipac() {
		datecreatipac = new Date();
		
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esttippac;
	
	/*
	 * 	Los tipos de pacientes pueden ser:
		contizantes
		beneficiarios
		sin seguridad socia
	 */
	
	private static final long serialVersionUID = 1L;

}
