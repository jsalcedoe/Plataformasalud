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
@Table(name="unimed")

// Esta entidad hace referencia a los diferentes tipos de medida que puedan existir para los medicamentos e insumos

public class UnidadMedida implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idunimed;
	
	@Column(unique = true, length = 8)
	@NotEmpty(message = "El campo unidad de medida no puede ser vacio")
	private String unimedi;
	
	@Column(unique = true, length = 50)
	@NotEmpty(message = "El campo detalle del tipo de medicamento no puede ser vacio")
	private String detunimedi;
	
	@Temporal(TemporalType.DATE)
	private Date datecreaunimedi;
	
	@PrePersist
	public void datecreaunimedi() {
		datecreaunimedi = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date datediunimedi;
	
	@PreUpdate
	public void datediunimedi() {
		datediunimedi = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estunimed_fk;

	
	private static final long serialVersionUID = 1L;

}
