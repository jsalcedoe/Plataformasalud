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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idusuario;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60, nullable = false)
	private String password;
	
	@Column(length = 3, nullable = false)
	private String typiduser;
	
	@Column (length = 11, unique = true)
	private long docuser;
	
	@Column(nullable = false)
	private String nameuser;
	
	@Column (length = 30,unique = false)
	private String lastnameuser;
	
	@Column (length = 20, unique = true)
	private String tarjprofuser;
	
	@Column (length = 45)
	private String emailuser;
	
	@Column (nullable = false)
	private String firma;
	
	@Column (nullable = false)
	private Date creatdateuser;
	
	@Column (nullable = false)
	private Boolean statususer;;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "especialidades_idespecialidades")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cargo especialidad ;
	

	private static final long serialVersionUID = 1L;

}
