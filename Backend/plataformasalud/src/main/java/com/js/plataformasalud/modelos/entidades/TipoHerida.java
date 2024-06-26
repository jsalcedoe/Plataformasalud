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
@Table(name="thx")

public class TipoHerida implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idthx;
	
	@Column(length = 6)
	@NotEmpty(message = "El campo de nombre del tipo de herida no puede ser vacio")
	private String nomthx;
	
	@Column(length = 10000)
	@NotEmpty(message = "El campo de detalle para el tipo de herida  no puede ser vacio")
	private String dethx;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreathx;
	
	@PrePersist
	public void datecreatevol() {
		datecreathx = new Date();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datedithx;
	
	@PreUpdate
	public void dateditevol() {
		datedithx = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estadothx_fk;
	
	
	
	
	private static final long serialVersionUID = 1L;

}
