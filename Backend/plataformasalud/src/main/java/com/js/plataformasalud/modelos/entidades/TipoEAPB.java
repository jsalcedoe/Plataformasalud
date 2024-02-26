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


@Getter
@Setter
@Entity
@Table(name="tipeapb")

public class TipoEAPB implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtipeapb;
	
	@NotEmpty(message = "Digite el tipo de entidad administradora de plan de beneficios")
	@Column(nullable = false,unique = true)
	private String nomtipeapb;
	
	@NotEmpty(message = "El campo detalle del tipo de entidad responsable de pago no puede ser vacio")
	@Column(nullable = false, unique = true)
	private String detipeapb;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatypeapb;
	
	@PrePersist
	public void creatypeapb() {
		datecreatypeapb = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estyeapb_fk;
	
	private static final long serialVersionUID = 1L;

}
