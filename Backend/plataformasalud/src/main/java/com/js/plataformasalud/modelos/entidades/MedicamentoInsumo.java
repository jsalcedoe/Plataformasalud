package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "medins")

public class MedicamentoInsumo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private long idmedins;
	
	@Column(nullable = true)
	@NotEmpty(message = "El campo del medicamento no puede ser vacio, por favor digitelo")
	private String nominsmed;
	
	@Column(nullable = true)
	@NotEmpty(message = "El campo para el registro INVIMA no puede ser vacio, por favor digitelo")
	private String reginvimamedins;
	
	@Column(nullable = true)
	@NotEmpty(message = "El campo lote no puede ser vacio, por favor digitelo")
	private String lotemedins;
	
	@Temporal(TemporalType.DATE)
	private Date fechacreamedins;
	
	public void fcreamedins () {
		fechacreamedins = new Date();
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private PresentacionMedIns presmedins;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private GrupoMedIns grupo;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private UnidadMedidaMedIns unidades;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private FabricanteMedIns fabricante;
	
	private static final long serialVersionUID = 1L;

}
