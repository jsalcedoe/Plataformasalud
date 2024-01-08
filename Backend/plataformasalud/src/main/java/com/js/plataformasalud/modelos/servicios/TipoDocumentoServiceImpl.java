package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoDocumentoDao;
import com.js.plataformasalud.modelos.entidades.TipoDocumento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class TipoDocumentoServiceImpl implements ITipoDocumentoService {
	
	private ITipoDocumentoDao itipdocdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoDocumento> findAll() {
		
		return (List<TipoDocumento>) itipdocdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoDocumento FindById(Long idtipdoc) {
		
		return itipdocdao.findById(idtipdoc).orElse(null);
	}

	@Override
	public TipoDocumento save(TipoDocumento tipodoc) {
		
		return itipdocdao.save(tipodoc);
	}

	@Override
	public void delete(Long idtipdoc) {
		
		itipdocdao.deleteById(idtipdoc);
		
	}

}
