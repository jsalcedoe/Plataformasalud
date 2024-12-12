package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agmed")

public class Agenda implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idagmed;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "El campo fecha no puede ser vacio")
	private Date fechacreatagmed;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora no puede ser vacio")
	private LocalTime horainicioagmed;
	
	@Column(nullable = false, length = 5)
	@NotNull(message = "El campo hora no puede ser vacio")
	private LocalTime horafinagmed;
	
	@Column(length = 2)
	@NotEmpty(message = "El campo intervalo de tiempo para la agenda no puede ser vacio")
	private Long interagmed;
	
	@Column(length = 100)
	@NotEmpty(message = "El campo total de citas a asignar no puede ser vacio")
	private Long totalagmed;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Ubicacion ubicaagmed_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario useragmed_fk;
	
	@OneToMany(mappedBy = "citagcit_fk", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<AsignaCita> citasAsignadas = new ArrayList<>();

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esthab_fk;

	
	private static final long serialVersionUID = 1L;

}
