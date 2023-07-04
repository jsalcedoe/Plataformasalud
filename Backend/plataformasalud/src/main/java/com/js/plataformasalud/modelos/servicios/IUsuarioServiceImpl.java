package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IUsuarioDao;
import com.js.plataformasalud.modelos.entidades.Usuario;

@Service
public class IUsuarioServiceImpl implements IUsuarioServices {
	@Autowired
	private IUsuarioDao userdao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		
		return (List<Usuario>)userdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long idusuario) {
		
		return userdao.findById(idusuario).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		
		return userdao.save(user);
	}

	@Override
	@Transactional
	public void delete(Long idusuario) {
		userdao.deleteById(idusuario);
		
	}

}
