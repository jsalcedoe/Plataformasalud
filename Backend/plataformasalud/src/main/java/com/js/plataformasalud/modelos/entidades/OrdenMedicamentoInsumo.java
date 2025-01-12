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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordmedins")

public class OrdenMedicamentoInsumo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idordmedins;
	
	@Column(length = 3)
	@NotNull(message = "El campo de cantidad no puede ser vacio")
	private Long cantmedins;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "La dosis no puede ser vacia")
	private String dosismedins;
	
	@Column(length = 200)
	private String obsordmedins;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreatordmedins;
	
	@PrePersist
	public void datecreatordmedins() {
		datecreatordmedins = new Date();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateeditordmedins;
	
	@PreUpdate
	public void dateeditordmedins() {
		dateeditordmedins = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private MedicamentoInsumo ordmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private PresentacionMedicamentoInsumo pordmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private UnidadMedida uniordmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Evento eventordmedins_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estordmedins_fk;
	
	private static final long serialVersionUID = 1L;

}
