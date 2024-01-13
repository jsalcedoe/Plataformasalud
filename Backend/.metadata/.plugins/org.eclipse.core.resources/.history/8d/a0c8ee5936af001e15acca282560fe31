package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "status")

public class Estado implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idstatus;
	
	@Column(nullable = false, length = 15)
	@NotEmpty(message = "El campo estado no puede ser vacio")
	private String nomstatus;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatstatus;

	
	private static final long serialVersionUID = 1L;

}
