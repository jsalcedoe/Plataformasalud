package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="tipeapb")

public class TipoEAPB implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true )
	private long idtipeapb;
	
	@NotEmpty(message = "Digite el tipo de entidad administradora de plan de beneficios")
	@Column(nullable = false)
	private String nomtipeapb;
	
	private static final long serialVersionUID = 1L;

}
