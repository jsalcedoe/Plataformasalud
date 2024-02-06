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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/*la entidad de tipo de nota aplica para seleccionar que tipo de nota se va a realizar, puede ser:
 * nota aclaratoria
 * nota adicional
 * nota de registro de examenes
 * nota de efermeria*/

@Getter
@Setter
@Entity
@Table(name = "tynote")

public class TipoNota implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtypnot;
	
	@Column(nullable = false, unique = true, length = 6)
	@NotEmpty(message = "El campo tipo de nota no puede ser vacio")
	private String nametypnot;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo detalle del tipo de nota no puede ser vacio")
	private String dettypnot;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatypnot;
	
	@PrePersist
	public void datecreat() {
		datecreatypnot = new Date ();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estypnot_fk;
	
	
	private static final long serialVersionUID = 1L;

}
