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
@Table(name="evoenf")

public class EvolucionEnfermeria implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idevoenf;
	
	@Column(length = 10000)
	@NotEmpty(message = "El campo de detalle para la evolucion no puede ser vacio")
	private String detevoenf;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreatevoenf;
	
	@PrePersist
	public void datecreatevol() {
		datecreatevoenf = new Date();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateditevoenf;
	
	@PreUpdate
	public void dateditevol() {
		dateditevoenf = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventevoenf_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estevoenf_fk;
	
	private static final long serialVersionUID = 1L;

}
