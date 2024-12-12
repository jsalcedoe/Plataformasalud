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
@Table(name="tymedins")

public class TipoMedicamentoInsumo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtmedins;
	
	@Column(unique = true, length = 8)
	@NotEmpty(message = "El campo Tipo de medicamento no puede ser vacio")
	private String typmedins;
	
	@Column(unique = true, length = 50)
	@NotEmpty(message = "El campo detalle del tipo de medicamento no puede ser vacio")
	private String detypmedins;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatymedins;
	
	@PrePersist
	public void datecreattypmedins() {
		datecreatymedins = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date datedittymedins;
	
	@PreUpdate
	public void editdatetypmedins() {
		datedittymedins = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado esttymedins_fk;

	
	private static final long serialVersionUID = 1L;

}
