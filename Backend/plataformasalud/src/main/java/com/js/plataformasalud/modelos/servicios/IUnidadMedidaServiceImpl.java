package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.js.plataformasalud.modelos.dao.IUnidadMedidaDao;
import com.js.plataformasalud.modelos.entidades.UnidadMedidaMedIns;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IUnidadMedidaServiceImpl implements IUnidadMedidaService {
	
	private IUnidadMedidaDao unimedao;

	@Override
	public List<UnidadMedidaMedIns> findAll() {
		
		return (List<UnidadMedidaMedIns>)unimedao.findAll();
	}

	@Override
	public UnidadMedidaMedIns findById(Long idmed) {
		
		return unimedao.findById(idmed).orElse(null);
	}

	@Override
	public UnidadMedidaMedIns save(UnidadMedidaMedIns unimed) {
		
		return unimedao.save(unimed);
	}

	@Override
	public void delete(Long idmed) {
		
		unimedao.deleteById(idmed);
				
	}

}
