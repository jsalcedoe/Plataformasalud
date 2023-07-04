package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Usuario;

public interface IUsuarioServices {
	public List<Usuario> findAll();
	
	public Usuario findById(Long idusuario);
	
	public Usuario save (Usuario user);
	
	public void delete (Long idusuario);

}
