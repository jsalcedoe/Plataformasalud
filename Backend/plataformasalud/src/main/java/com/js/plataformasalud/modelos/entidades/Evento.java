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
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "eventhcpac")

public class Evento implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idevent;
	
	@Column(nullable = false)
	private Long conseventpac;
	
	/*
	 * Lo que buscamos con este atributo es identificar mas detalladamente el evento, es decir
	 * CONSULTA
	 * PROCEDIMIENTO
	 * CONTROL
	 * Entre otros
	 * */
	
	@Column(nullable = false)
	private String detevent;
	
	@Temporal(TemporalType.DATE)
	private Date fechainievent;
	
	@PrePersist
	public void fechaini() {
		fechainievent = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date fechafinevent;
	
	@PreUpdate
	public void fechafin() {
		fechafinevent = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Paciente pacevent_fk;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estevent_fk;
	

	
	private static final long serialVersionUID = 1L;

}
