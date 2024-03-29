package com.js.plataformasalud.modelos.entidades;
/*observacion
 * recuperacion
 * hospitalizacion
 * examenes
 * consultorios
 * salas de quirofano
 * camillas de observacion
 * habitaciones*/

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

@Getter
@Setter
@Entity
@Table(name = "ubicaciones")

public class Ubicacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idubica;
	
	@Column(nullable = false, length = 6, unique = true)
	@NotEmpty(message = "El campo ubicacion no puede ser vacio, por favor ingrese la informacion de ubicacion")
	private String nomubicaciones;
	
	@NotEmpty(message = "El campo detalle de la ubicacion no puede ser vacio")
	private String detubica;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatubic;
	
	@PrePersist
	public void creaubi () {
		datecreatubic = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estubica_fk;
	
	private static final long serialVersionUID = 1L;

}
