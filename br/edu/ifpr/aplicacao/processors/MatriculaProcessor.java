package br.edu.ifpr.aplicacao.processors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpr.modelo.Matricula;
import br.edu.ifpr.utils.date.DateTimeUtils;

public class MatriculaProcessor {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	private CursoProcessor cp = null;
	private AlunoProcessor ap = null;
	
	public MatriculaProcessor() {
		
	}
	
	public MatriculaProcessor(EntityManagerFactory emf, CursoProcessor cp, AlunoProcessor ap) {
		this.emf = emf;
		this.cp = cp;
		this.ap = ap;
	}
	
	private void createEntityManager() {
		em = emf.createEntityManager();
	}
	private void closeEntityManager() {
		if(em != null) em.close();
	}
	
	private void print(Matricula dado) {
		System.out.println(dado);
	}
	
	private void print(List<Matricula> dados) {
		System.out.println("===> TOTAL [" + dados.size() + "] <===");
		for(Matricula dado : dados)
			print(dado);
	}
	
	private void populate(List<Matricula> dados) {
		Matricula dado = new Matricula(cp.select("Analise de Sistemas"), ap.select("12971531031"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Letras"), ap.select("02122211123"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Publicidade"), ap.select("07663758382"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Educação Física"), ap.select("61166624552"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Direito"), ap.select("50322267501"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Biomedicina"), ap.select("62323040120"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Filosofia"), ap.select("32283259037"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Medicina"), ap.select("73877628052"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Biologia"), ap.select("85252310297"), DateTimeUtils.getDate());
		dados.add(dado);
		
		dado = new Matricula(cp.select("Química"), ap.select("67164916608"), DateTimeUtils.getDate());
		dados.add(dado);
	}
	
	private int insert() {
		List<Matricula> dados = new ArrayList<>();
		populate(dados);
		int regs = 0;
		for(Matricula dado : dados)
			regs += insert(dado);
		
		return regs;
	}
	
	private Matricula get(String valorBusca) {
		TypedQuery<Matricula> query = em.createNamedQuery("Matricula.findByAluno", Matricula.class);
		query.setParameter("aluno", valorBusca);
		
		Matricula dado = null;
		try {
			dado = query.getSingleResult();
		}catch(NoResultException ex){}
		
		return dado;
	}
	
	public int insert(Matricula dado) {
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
		
		Matricula dado = get(valorBusca);
		if(dado != null) {
			em.getTransaction().begin();
					
			dado.setDataMatricula(DateTimeUtils.nowPlusDays(20));
			
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
		Matricula dado = get(valorBusca);
		
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
		TypedQuery<Matricula> query = em.createNamedQuery("Matricula.findAll", Matricula.class);
		List<Matricula> dados = query.getResultList();
		
		em.getTransaction().begin();
		
		int regs = 0;
		
		for(Matricula dado : dados) {
			em.remove(dado);
			++regs;
		}
		em.getTransaction().commit();
		closeEntityManager();
		
		return regs;
	}
	
	public Matricula select(String valorBusca) {
		createEntityManager();
		
		Matricula dado = get(valorBusca);
		
		closeEntityManager();
		
		return dado;
	}
	
	public List<Matricula> select(){
		createEntityManager();
		TypedQuery<Matricula> query = em.createNamedQuery("Matricula.findAll", Matricula.class);
		List<Matricula> dados = query.getResultList();
		
		closeEntityManager();
		
		return dados;
	}
	
	private void processInsert(boolean varios) {
		if(varios) {
			System.out.println("===> INCLUIDOS [" + insert() + "] <===");
			return;
		}
		
		Matricula dado = new Matricula(cp.select("Letras"), ap.select("62323040120"), DateTimeUtils.getDate());
		
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
			List<Matricula> dados = select();
			print(dados);
		}else
			print(select(valorBusca));
	}
	
	public void processMatriculas() {
		  System.out.println("\n\n\n### PROCESSANDO MATRICULAS : INICIO ###");
		  
	      System.out.println("\n===> INCLUIR MATRICULAS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR MATRICULAS <===");
	      processDelete();
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR MATRICULAS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR MATRICULA <===");
	      processInsert(false);
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      String nome = "07663758382";
	      System.out.println("\n===> BUSCAR MATRICULA EXISTENTE [" + nome + "] <===");
	      processSelect(nome);
	 
	      nome = "00000000000";
	      System.out.println("\n===> BUSCAR MATRICULA NÃO EXISTENTE [" + nome
	                        + "] <===");
	      processSelect(nome);
	 
	      nome = "07663758382";
	      System.out.println("\n===> ALTERAR MATRICULA [" + nome + "] <===");
	      processUpdate(nome);
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      nome = "11111111111";
	      System.out.println("\n===> EXCLUIR MATRICULA NÃO EXISTENTE [" + nome + "] <===");
	      processDelete(nome);
	 
	      nome = "67164916608";
	      System.out.println("\n===> EXCLUIR MATRICULA EXISTENTE [" + nome + "] <===");
	      processDelete(nome);
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR MATRICULAS <===");
	      processDelete();
	 
	      System.out.println("\n===> INCLUIR MATRICULAS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR MATRICULAS <===");
	      processSelect();
	 
	      System.out.println("\n### PROCESSANDO MATRICULAS : FIM ###\n");
	
	}
}