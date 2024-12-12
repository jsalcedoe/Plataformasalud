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
@Table(name="fabmedins")

public class FabricanteMedicamentoInsumo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idfabmedins;
	
	@Column(unique = true, length = 100)
	@NotEmpty(message = "El campo fabricante del  medicamento o insumo no puede ser vacio")
	private String nomfabmedins;
	
	@Column(length = 100)
	@NotEmpty(message = "El campo dirección del fabricante no puede ser vacio")
	private String dirfabmedins;
	
	@Temporal(TemporalType.DATE)
	private Date datecreatfabmedins;
	
	@PrePersist
	public void datecreatfabmedins() {
		datecreatfabmedins = new Date();
	}
	
	@Temporal(TemporalType.DATE)
	private Date dateditfabmedins;
	
	@PreUpdate
	public void dateditfabmedins() {
		dateditfabmedins = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Ciudad ciufabmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estfabmedins_fk;
	
	private static final long serialVersionUID = 1L;

}
