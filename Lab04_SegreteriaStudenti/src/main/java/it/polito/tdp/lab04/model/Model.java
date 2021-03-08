package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO daoC;
	private StudenteDAO daoS;
	
	public Model() {
		this.daoC = new CorsoDAO();
		this.daoS = new StudenteDAO();
	}
	
	public List<Corso> getTuttiCorsi() {
		return daoC.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(int matricola) {
		return daoS.getStudenteByMatricola(matricola);
	}

	public List<Studente> getStudentiIscritti(Corso c) {
		return daoC.getStudentiIscrittiAlCorso(c);
	}
	
	public List<Corso> getCorsiByStudente(int matricola){
		return daoS.getCorsiByStudente(matricola);
	}

}
