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
@Table(name = "tipanest")

public class TipoAnestesia implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idtipanest;
	
	@Column(length = 30)
	@NotEmpty(message = "El campo nombre del tipo de anestesia no puede ser vacio")
	private String nomtipanest;
	
	private String desctipanest;
	
	private static final long serialVersionUID = 1L;

}
