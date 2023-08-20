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
@Table(name = "unimedidasmedins")

public class UnidadMedidaMedIns implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long idmed;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo nombre de la unidad de medida no puede ser vacio, por favor digitelo")
	private String nommed;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo detalle del detalle para unidad de medida no puede ser vacio, por favor digitelo")
	private String detallnommed;
	
	@Temporal(TemporalType.DATE)
	private Date fechcreaunid;
	
	public void creaunimed() {
		fechcreaunid = new Date();
	}
	
	private String estado;
	
	private static final long serialVersionUID = 1L;

}
