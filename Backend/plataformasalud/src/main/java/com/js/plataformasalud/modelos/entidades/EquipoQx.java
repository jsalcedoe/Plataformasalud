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

@Setter
@Getter
@Entity
@Table(name = "eqqx")

public class EquipoQx implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ideqqx;
	
	@Temporal(TemporalType.DATE)
	private Date datecreateqqx;
	
	@PrePersist
	public void creadateeq () {
		datecreateqqx = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date dateediteqqx;
	
	@PreUpdate
	public void editdateeqqx() {
		dateediteqqx = new Date ();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario inteqqx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ProcedimientoQuirurgico pxqx_fk;
	
	private static final long serialVersionUID = 1L;

}
