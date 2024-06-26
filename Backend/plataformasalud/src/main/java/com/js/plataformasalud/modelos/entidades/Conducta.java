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
@Table(name = "condpac")

public class Conducta implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcondpac;
	
	@Column(unique = true, length = 10)
	@NotEmpty(message = "El campo del nombre para la conducta no puede ser vacio")
	private String nomcondpac;

	@Column(unique = true, length = 50)
	@NotEmpty(message = "El campo detalle para nombre de la conducta no puede ser vacio")
	private String detcondpac;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatcondpac;
	
	@PrePersist
	public void creatdate () {
		datecreatcondpac = new Date ();
		
	}
	
	@Temporal(TemporalType.DATE)
	private Date editcondpac;
	
	@PreUpdate
	public void actualizafecha () {
		editcondpac = new Date ();
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estcondpac_fk;
	
	
	private static final long serialVersionUID = 1L;

}
