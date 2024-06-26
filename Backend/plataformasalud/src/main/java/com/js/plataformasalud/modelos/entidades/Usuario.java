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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iduser;
	
	@Column(unique = true, length = 20)
	@NotEmpty(message = "El campo username no puede ser vacio")
	private String username;
	
	@Column(length = 60, nullable = false)
	@NotEmpty(message = "El campo pasword no puede ser vacio")
	private String password;
	
	@Column (length = 11, unique = true)
	@NotNull(message = "El documento del usuario no puede ser vacio")
	private Long docuser;
	
	@Column(nullable = false)
	@NotEmpty(message = "El campo nombres del usuario no puede ser vacio")
	private String firstname;
	
	@Column (length = 30,unique = false)
	@NotEmpty(message = "El campo apellidos del usuario no puede ser vacio")
	private String lastname;
		
	@Column (length = 45)
	@Email(message = "Registre un email correcto")
	private String emailuser;
	

	private String firma;
	
	@Temporal(TemporalType.DATE)
	private Date creatdateuser;
	
	@PrePersist
	@PreUpdate
	public void creausuario() {
		creatdateuser = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoDocumento typeiduser_fk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cargo carguser_fk ;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estado estuser_fk;
	

	private static final long serialVersionUID = 1L;

}
