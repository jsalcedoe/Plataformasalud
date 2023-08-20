package com.js.plataformasalud.modelos.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "fabmedins")

public class FabricanteMedIns implements Serializable{
	
	
	@Id
	@Column(unique = true)
	private long nitfabmedins;
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "El campo nombre del fabricante no puede ser vacio, por favor digite el fabricante")
	private String nomfabmedins;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "El campo direccion del fabricante no puede ser vacio, por favor digite la dirección")
	private String dirfabmedins;
	
	private String contactfabmedins;
	
	@Temporal(TemporalType.DATE)
	private Date fechacreafabmedins;
	
	public void fechafabmedins() {
		fechacreafabmedins = new Date();
	}
	
	private static final long serialVersionUID = 1L;

}
