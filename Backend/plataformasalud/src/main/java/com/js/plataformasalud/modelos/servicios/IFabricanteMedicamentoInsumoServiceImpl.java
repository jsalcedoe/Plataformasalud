package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IFabricanteMedicamentoInsumoDao;
import com.js.plataformasalud.modelos.entidades.FabricanteMedicamentoInsumo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IFabricanteMedicamentoInsumoServiceImpl implements IFabricanteMedicamentoInsumoService {
	
	private IFabricanteMedicamentoInsumoDao fabmedinsdao;

	@Override
	@Transactional(readOnly = true)
	public List<FabricanteMedicamentoInsumo> findAll() {
		
		return (List<FabricanteMedicamentoInsumo>) fabmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public FabricanteMedicamentoInsumo findById(Long idfabmedins) {
		
		return fabmedinsdao.findById(idfabmedins).orElse(null);
	}

	@Override
	@Transactional
	public FabricanteMedicamentoInsumo save(FabricanteMedicamentoInsumo fabmedins) {
		
		return fabmedinsdao.save(fabmedins);
	}

}
