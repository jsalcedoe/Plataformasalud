package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.OrdenesProcExam;

public interface IOrdenesProcExamServices {
	
	public List<OrdenesProcExam> findAll();
	
	public OrdenesProcExam findById(Long idordprocexam);
	
	public OrdenesProcExam save (OrdenesProcExam ordenes);
	
	public void delete (Long idordprocexam);

}
