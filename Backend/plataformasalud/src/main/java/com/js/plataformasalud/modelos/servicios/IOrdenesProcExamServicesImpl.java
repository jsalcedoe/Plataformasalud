package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IOrdenesProcExamDao;
import com.js.plataformasalud.modelos.entidades.OrdenesProcExam;

@Service
public class IOrdenesProcExamServicesImpl implements IOrdenesProcExamServices {
	
	@Autowired
	private IOrdenesProcExamDao ordenesdao; 

	@Override
	@Transactional(readOnly = true)
	public List<OrdenesProcExam> findAll() {
		
		return ordenesdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenesProcExam findById(Long idordprocexam) {
		
		return ordenesdao.findById(idordprocexam).orElse(null);
	}

	@Override
	@Transactional
	public OrdenesProcExam save(OrdenesProcExam ordenes) {
	
		return ordenesdao.save(ordenes);
	}

	@Override
	@Transactional
	public void delete(Long idordprocexam) {
		
		ordenesdao.deleteById(idordprocexam);
	}

}
