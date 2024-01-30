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
@Table(name = "tipanest")

public class TipoAnestesia implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idtipanest;
	
	@Column(length = 6, unique = true)
	@NotEmpty(message = "El campo nombre del tipo de anestesia no puede ser vacio")
	private String nomtipanest;
	
	@Column(length = 30, unique = true)
	private String desctipanest;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreatanest;
	
	@PrePersist
	public void creadate () {
		datecreatanest = new Date();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateeditanest;
	
	@PreUpdate
	public void editdate() {
		dateeditanest = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esttypanest;
	
	
	
	private static final long serialVersionUID = 1L;

}
