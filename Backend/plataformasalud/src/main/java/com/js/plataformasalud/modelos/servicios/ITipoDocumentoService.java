package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoDocumento;

public interface ITipoDocumentoService {
	
	public List<TipoDocumento> findAll();
	
	public TipoDocumento FindById(Long idtipdoc);
	
	public TipoDocumento save (TipoDocumento tipodoc);
	
	public void delete (Long idtipdoc);

}
