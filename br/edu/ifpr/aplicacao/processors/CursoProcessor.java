package br.edu.ifpr.aplicacao.processors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpr.modelo.Curso;

public class CursoProcessor {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	private ModuloProcessor mop = null;
	
	public CursoProcessor() {
		
	}
	public CursoProcessor(EntityManagerFactory emf, ModuloProcessor mop) {
		this.emf = emf;
		this.mop = mop;
	}
	
	private void createEntityManager() {
		em = emf.createEntityManager();
	}
	private void closeEntityManager() {
		if(em != null) em.close();
	}
	
	private void print(Curso dado) {
		System.out.println(dado);
	}
	
	private void print(List<Curso> dados) {
		System.out.println("===> TOTAL [" + dados.size() + "] <===");
		for(Curso dado : dados)
			print(dado);
	}
	
	private void populate(List<Curso> dados) {
		Curso dado = new Curso("TADS", "Analise de Sistemas", mop.select("Banco de Dados"));
		dados.add(dado);
		
		dado = new Curso("LET", "Letras", mop.select("Literatura Portuguesa"));
		dados.add(dado);
		
		dado = new Curso("EDF", "Educação Física", mop.select("Atletismo"));
		dados.add(dado);
		
		dado = new Curso("BIO", "Biologia", mop.select("Ecologia"));
		dados.add(dado);
		
		dado = new Curso("DIR", "Direito", mop.select("Código Penal"));
		dados.add(dado);
		
		dado = new Curso("MED", "Medicina", mop.select("Anatomia Humana"));
		dados.add(dado);
		
		dado = new Curso("BMED", "Biomedicina", mop.select("Microbiologia"));
		dados.add(dado);
		
		dado = new Curso("QUI", "Química", mop.select("Química Orgânica"));
		dados.add(dado);
		
		dado = new Curso("PUB", "Publicidade", mop.select("Fotografia"));
		dados.add(dado);
		
		dado = new Curso("FIL", "Filosofia", mop.select("Metodologia de Pesquisa"));
		dados.add(dado);
	}
	
	private int insert() {
		List<Curso> dados = new ArrayList<>();
		populate(dados);
		int regs = 0;
		for(Curso dado : dados)
			regs += insert(dado);
		
		return regs;
	}
	
	private Curso get(String valorBusca) {
		TypedQuery<Curso> query = em.createNamedQuery("Curso.findByNome", Curso.class);
		query.setParameter("nome", valorBusca);
		
		Curso dado = null;
		try {
			dado = query.getSingleResult();
		}catch(NoResultException ex){}
		
		return dado;
	}
	
	public int insert(Curso dado) {
		createEntityManager();
		em.getTransaction().begin();
		em.persist(dado);
		em.getTransaction().commit();
		
		closeEntityManager();
		
		return 1;
	}
	
	public int update(String valorBusca) {
		int regs = 0;
		createEntityManager();
		
		Curso dado = get(valorBusca);
		if(dado != null) {
			em.getTransaction().begin();

			String nome = dado.getNome();
			dado.setNome(nome.toUpperCase());
			
			em.merge(dado);
			em.getTransaction().commit();
			
			regs = 1;
		}
		closeEntityManager();
		
		return regs;
	}
	
	public int delete(String valorBusca) {
		int regs =0 ;
		createEntityManager();
		Curso dado = get(valorBusca);
		
		if(dado!= null) {
			em.getTransaction().begin();
			em.remove(dado);
			em.getTransaction().commit();
			
			regs = 1;
		}
		closeEntityManager();
		
		return regs;
	}
	
	public int delete() {
		createEntityManager();
		TypedQuery<Curso> query = em.createNamedQuery("Curso.findAll", Curso.class);
		List<Curso> dados = query.getResultList();
		
		em.getTransaction().begin();
		
		int regs = 0;
		
		for(Curso dado : dados) {
			em.remove(dado);
			++regs;
		}
		em.getTransaction().commit();
		closeEntityManager();
		
		return regs;
	}
	
	public Curso select(String valorBusca) {
		createEntityManager();
		
		Curso dado = get(valorBusca);
		
		closeEntityManager();
		
		return dado;
	}
	
	public List<Curso> select(){
		createEntityManager();
		TypedQuery<Curso> query = em.createNamedQuery("Curso.findAll", Curso.class);
		List<Curso> dados = query.getResultList();
		
		closeEntityManager();
		
		return dados;
	}
	
	private void processInsert(boolean varios) {
		if(varios) {
			System.out.println("===> INCLUIDOS [" + insert() + "] <===");
			return;
		}
		
		Curso dado = new Curso("REP", "Relações Públicas", mop.select("Metodologia de Pesquisa"));
		
		System.out.println("===> INCLUIDO [" + insert(dado) + "] <===");
	}
	
	private void processUpdate(String valorBusca) {
		int updated = update(valorBusca);
		
		if(updated == 1)
			System.out.println("===> ALTERADO [" + valorBusca + "] <===");
		else
			System.out.println("==> [" + valorBusca + "] NÃO ENCONTRADO <===");
	}
	
	private void processDelete(String valorBusca) {
		int deleted = delete (valorBusca);
		
		if(deleted == 1)
			System.out.println("===> ALTERADO [" + valorBusca + "] <===");
		else
			System.out.println("===> [" + valorBusca + "] NÃO ENCONTRADO <===");
	}
	
	private void processDelete() {
		System.out.println("===> EXCLUIDOS [" + delete() + "] <===");
	}
	
	private void processSelect() {
		processSelect(null);
	}
	
	private void processSelect(String valorBusca) {
		if(valorBusca == null) {
			List<Curso> dados = select();
			print(dados);
		}else
			print(select(valorBusca));
	}
	
	public void processCursos() {
		  System.out.println("\n\n\n### PROCESSANDO CURSOS : INICIO ###");
		  
	      System.out.println("\n===> INCLUIR CURSOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR CURSOS <===");
	      processDelete();
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR CURSOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR CURSO <===");
	      processInsert(false);
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      String nome = "Analise de Sistemas";
	      System.out.println("\n===> BUSCAR CURSO EXISTENTE [" + nome + "] <===");
	      processSelect(nome);
	 
	      nome = "Sociologia";
	      System.out.println("\n===> BUSCAR CURSO NÃO EXISTENTE [" + nome
	                        + "] <===");
	      processSelect(nome);
	 
	      nome = "Medicina";
	      System.out.println("\n===> ALTERAR CURSO [" + nome + "] <===");
	      processUpdate(nome);
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      nome = "Engenharia Eletrica";
	      System.out.println("\n===> EXCLUIR CURSO NÃO EXISTENTE [" + nome + "] <===");
	      processDelete(nome);
	 
	      nome = "Filosofia";
	      System.out.println("\n===> EXCLUIR CURSO EXISTENTE [" + nome + "] <===");
	      processDelete(nome);
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR CURSOS <===");
	      processDelete();
	 
	      System.out.println("\n===> INCLUIR CURSOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR CURSOS <===");
	      processSelect();
	 
	      System.out.println("\n### PROCESSANDO CURSOS : FIM ###\n");
	}
}