package br.edu.ifpr.aplicacao.view;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaService {
	private final String PERSISTENCE_UNIT_NAME = "treinamento";
	
	private EntityManagerFactory emf = null;
	
	public void openEntityManagerFactory() {
		 emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public void closeEntityManagerFactory() {
		if (emf != null)
			emf.close();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
}
