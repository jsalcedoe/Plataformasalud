package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

@Getter
@Setter
@Entity
@Table(name="coninfpac")

public class ConsentimientoPaciente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idconsinfpac;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatconsinfpac;
	
	@PrePersist
	public void datecreatconsinfpac () {
		datecreatconsinfpac = new Date ();
		
	}
	
	@Temporal(TemporalType.DATE)
	private Date dateeditconinfpac;
	
	@PreUpdate
	public void dateeditconinfpac () {
		dateeditconinfpac = new Date ();
		
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Consentimiento conspxpac_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario medpxpac_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventconsinfpac_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado statuconsinfpac_fk;
	
	private static final long serialVersionUID = 1L;

}
