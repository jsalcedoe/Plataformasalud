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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name ="ubievent")

public class UbicacionEvent implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idubieventpac;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "ubicacion_idubic")
	@NotNull(message = "El campo ubicacion no puede ser vacio, seleccione la ubicacion del paciente")
	private Ubicacion ubicacion;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "event_ideventpac")
	private Evento evento;
	
	@Column (nullable = false, length = 12 )
	@Temporal(TemporalType.DATE)
	@NotNull (message = "El campo fecha de ingreso no puede ser vacio, seleccione fecha de ingreso")
	private Date fechainiubieventpac;
	
	@PrePersist
	public void prePersis() {
		fechainiubieventpac = new Date();
	}
		
	private Date fechafinubieventpac;
	
	private String estado;
	// el estado puede ser: creado, modificado o eliminado
		
	private static final long serialVersionUID = 1L;

}
