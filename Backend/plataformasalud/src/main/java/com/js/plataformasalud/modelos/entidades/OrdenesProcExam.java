package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
@Table(name ="ordprocexam")

public class OrdenesProcExam implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idordprocexam;
	
	@Column(nullable = false, length = 12)
	@Temporal(TemporalType.DATE)
	private Date fechaordprocexam;
	
	@PrePersist
	public void fechaordprocexam() {
		fechaordprocexam = new Date();
	}
	
	@Column(nullable = false, length = 12)
	@Temporal(TemporalType.DATE)
	private Date fechaeditordprocexam;
	
	@PreUpdate
	public void fechaeditordprocexam() {
		fechaeditordprocexam = new Date();
	}
	
	@Column(nullable = false, length = 3)
	@NotEmpty(message = "El campo cantidad no puede ser vacio")
	private long cantprocexam;
	
	@Column(length = 500)
	private String obsprocexam;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoProcedimiento tprocexamord_fk;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private ProcedimientosExamenes procexamord;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventprocexamord_fk;
	
	
	private static final long serialVersionUID = 1L;

}
