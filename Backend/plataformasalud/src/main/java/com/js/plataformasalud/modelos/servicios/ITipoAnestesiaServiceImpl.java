package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoAnestesiaDao;
import com.js.plataformasalud.modelos.entidades.TipoAnestesia;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ITipoAnestesiaServiceImpl implements ITipoAnestesiaService {
	
	private ITipoAnestesiaDao anestdao;

	@Override
	@Transactional(readOnly = true)
	public List<TipoAnestesia> findAll() {
		
		return (List<TipoAnestesia>)anestdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoAnestesia findById(Long idtipanest) {
		
		return anestdao.findById(idtipanest).orElse(null);
	}

	@Override
	@Transactional
	public TipoAnestesia save(TipoAnestesia anestesia) {
		
		return anestdao.save(anestesia);
	}

	@Override
	@Transactional
	public void delete(Long idtipanest) {
		
		anestdao.deleteById(idtipanest);
	}

}
