package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IUnidadMedidaDao;
import com.js.plataformasalud.modelos.entidades.UnidadMedida;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IUnidadMedidaServiceImpl implements IUnidadMedidaService {
	
	private IUnidadMedidaDao unimedidao;

	@Override
	@Transactional(readOnly = true)
	public List<UnidadMedida> findAll() {
		
		return (List<UnidadMedida>) unimedidao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UnidadMedida FindById(Long idunimedi) {
		
		return unimedidao.findById(idunimedi).orElse(null);
	}

	@Override
	@Transactional
	public UnidadMedida save(UnidadMedida unimedi) {
		
		return unimedidao.save(unimedi);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UnidadMedida> findByUmedins(String detunimedi) {
		
		return (List<UnidadMedida>)unimedidao.findByUmedins(detunimedi);
	}

	

}
