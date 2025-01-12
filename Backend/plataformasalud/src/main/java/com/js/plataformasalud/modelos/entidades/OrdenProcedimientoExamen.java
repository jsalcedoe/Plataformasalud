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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordprocexam")

public class OrdenProcedimientoExamen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idordprocexam;
	
	@Column(length = 3)
	@NotNull(message = "El campo de cantidad no puede ser vacio")
	private Long canordprocexam;
	
	@Column(length = 200)
	private String obsordprocexam;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreatordprocexam;
	
	@PrePersist
	public void datecreatordprocexam() {
		datecreatordprocexam = new Date();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateeditordprocexam;
	
	@PreUpdate
	public void dateeditordprocexam() {
		dateeditordprocexam = new Date();
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoProcedimiento tordprocexam_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ProcedimientosExamenes pordprocexam_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventordprocexam_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estordprocexam_fk;
	
	
	
	
	
	private static final long serialVersionUID = 1L;

}
