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
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dxevent")

public class DiagnosticoEvent implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long iddxpac;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "event_idevent")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@NotEmpty(message = "El campo de evento no puede ser vacio, seleccione el evento")
	private Evento eventpac;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "dx_iddx")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@NotEmpty(message = "El campo diagnostico no puede ser nulo, seleccione el diagnostico")
	private Diagnostico dxpac;
	
	@Column(nullable = false, length = 10)
	@NotEmpty(message = "El campo tipo de diagnostico no puede ser estar vacio, por favor seleccione el tipo de diagnostico")
	private String tipdxpac;
	//El tipo de diagnostico puede ser: principal, secundario, relacionado, confirmado
	
	@Column(nullable = false, length = 12)
	@Temporal(TemporalType.DATE)
	private Date fechadxpac;
	
	@PrePersist
	public void prePersis() {
		fechadxpac = new Date();
	}
	
	
	private static final long serialVersionUID = 1L;

}
