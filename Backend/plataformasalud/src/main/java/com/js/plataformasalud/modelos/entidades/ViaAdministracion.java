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
@Table(name="viadm")

// Esta entidad hace referencia a las vias de administracion de los medicamentos o insumos 

public class ViaAdministracion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idviadm;
	
	@Column(unique = true, length = 8)
	@NotEmpty(message = "El campo nombre de la  via de administracion para el medicamento no puede ser vacio")
	private String nomviadm;
	
	@Column(unique = true, length = 50)
	@NotEmpty(message = "El campo detalle de la via de administracion para el medicamento no puede ser vacio")
	private String detviadm;
	
	
	@Temporal(TemporalType.DATE)
	private Date datecreatviadm;
	
	@PrePersist
	public void datecreatviadm() {
		datecreatviadm = new Date();
	}
		
	@Temporal(TemporalType.DATE)
	private Date dateditregmedins;
	
	@PreUpdate
	public void dateditregmedins() {
		dateditregmedins = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estviadm_fk;

	
	private static final long serialVersionUID = 1L;

}
