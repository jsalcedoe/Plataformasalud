package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IFabricanteMedInsDao;
import com.js.plataformasalud.modelos.entidades.FabricanteMedIns;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IFabricanteMedInsServiceImpl implements IFabricanteMedInsService {
	
	private IFabricanteMedInsDao fabmedinsdao;

	@Override
	@Transactional(readOnly = true)
	public List<FabricanteMedIns> findAll() {
		
		return fabmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public FabricanteMedIns findById(Long nitfabmedins) {
		
		return fabmedinsdao.findById(nitfabmedins).orElse(null);
	}

	@Override
	@Transactional
	public FabricanteMedIns save(FabricanteMedIns fabmedins) {
	
		return fabmedinsdao.save(fabmedins);
	}

	@Override
	@Transactional
	public void delete(Long nitfabmedins) {
		fabmedinsdao.deleteById(nitfabmedins);
		
	}

}
