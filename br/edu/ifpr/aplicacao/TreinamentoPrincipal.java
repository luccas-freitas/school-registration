package br.edu.ifpr.aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.ifpr.aplicacao.processors.AlunoProcessor;
import br.edu.ifpr.aplicacao.processors.CursoProcessor;
import br.edu.ifpr.aplicacao.processors.InstrutorProcessor;
import br.edu.ifpr.aplicacao.processors.MatriculaProcessor;
import br.edu.ifpr.aplicacao.processors.ModuloProcessor;

public class TreinamentoPrincipal {
	private final String PERSISTENCE_UNIT_NAME = "treinamento";
	
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	private AlunoProcessor ap;
	private InstrutorProcessor ip;
	private CursoProcessor cp;
	private ModuloProcessor mop;
	private MatriculaProcessor map;
	
	private void openEntityManagerFactory() {
		 emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	private void closeEntityManagerFactory() {
		if (emf != null)
			emf.close();
	}
	
	private void openEntityManager() {
		em = emf.createEntityManager();
	}
	
	private void closeEntityManager() {
		if (em != null) em.close();
	}
	
	private void deleteAll() {
		System.out.println("====>  MATRÍCULAS EXCLUÍDAS [" + map.delete() + "] <====");
		System.out.println("====>     MODULOS EXCLUÍDAS [" + mop.delete() + "] <====");
		System.out.println("====>      CURSOS EXCLUÍDOS [" + cp.delete() + "] <====");
		System.out.println("====>      ALUNOS EXCLUÍDOS [" + ap.delete() + "] <====");
		System.out.println("====> INSTRUTORES EXCLUÍDOS [" + ip.delete() + "] <====");
	}
	
	private void initProcessors() {
		ap = new AlunoProcessor(emf);
		ip = new InstrutorProcessor(emf);
		mop = new ModuloProcessor(emf, ip);
		cp = new CursoProcessor(emf, mop);
		map = new MatriculaProcessor(emf, cp, ap);
	}
	
	TreinamentoPrincipal(){
		System.out.println("@@@ INICIALIZANDO O JPA @@@");
		openEntityManagerFactory();
		
		//depos de 'openEntityManagerFactory()' pois os obejtos 'emf' ja
		//terão sido inicializado
		initProcessors();
		
		//apaga todos os registros de todos as tabelas da base dados
		System.out.println("====>    LIMPAR BASE DE DADOS    <====");
		//NÃO executar para PostgreSQL
		deleteAll();
		
		ap.processAlunos();
		ip.processInstrutores();
		mop.processModulos();
		cp.processCursos();
		map.processMatriculas();
		
		System.out.println("@@@ ENCERRANDO JPA @@@");
		closeEntityManagerFactory();
	}

	public static void main(String[] args) {
		new TreinamentoPrincipal();
	}
}