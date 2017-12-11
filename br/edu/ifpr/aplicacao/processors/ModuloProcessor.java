package br.edu.ifpr.aplicacao.processors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpr.modelo.Modulo;
import br.edu.ifpr.utils.date.DateTimeUtils;

public class ModuloProcessor {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	private InstrutorProcessor ip = null;
	
	public ModuloProcessor() {
		
	}
	public ModuloProcessor(EntityManagerFactory emf, InstrutorProcessor ip) {
		this.emf = emf;
		this.ip = ip;
	}
	
	private void createEntityManager() {
		em = emf.createEntityManager();
	}
	private void closeEntityManager() {
		if(em != null) em.close();
	}
	
	private void print(Modulo dado) {
		System.out.println(dado);
	}
	
	private void print(List<Modulo> dados) {
		System.out.println("===> TOTAL [" + dados.size() + "] <===");
		for(Modulo dado : dados)
			print(dado);
	}
	
	private void populate(List<Modulo> dados) {
		Modulo dado = new Modulo("Banco de Dados", DateTimeUtils.parseDate("25/05/2017"), ip.select("05578965487"));
		dados.add(dado);
		
		dado = new Modulo("Metodologia de Pesquisa", DateTimeUtils.parseDate("26/06/2017"), ip.select("58634875857"));
		dados.add(dado);
		
		dado = new Modulo("Microbiologia", DateTimeUtils.parseDate("27/07/2017"), ip.select("72674557273"));
		dados.add(dado);
		
		dado = new Modulo("Química Orgânica", DateTimeUtils.parseDate("28/08/2017"), ip.select("21898508607"));
		dados.add(dado);
		
		dado = new Modulo("Anatomia Humana", DateTimeUtils.parseDate("29/09/2017"), ip.select("40053301935"));
		dados.add(dado);
		
		dado = new Modulo("Código Penal", DateTimeUtils.parseDate("30/10/2017"), ip.select("95624683804"));
		dados.add(dado);
		
		dado = new Modulo("Fotografia", DateTimeUtils.parseDate("01/11/2017"), ip.select("84954772206"));
		dados.add(dado);
		
		dado = new Modulo("Atletismo", DateTimeUtils.parseDate("02/12/2017"), ip.select("27857240621"));
		dados.add(dado);
		
		dado = new Modulo("Literatura Portuguesa", DateTimeUtils.parseDate("03/01/2018"), ip.select("15861284857"));
		dados.add(dado);
		
		dado = new Modulo("Ecologia", DateTimeUtils.parseDate("04/02/2018"), ip.select("21898508607"));
		dados.add(dado);
	}
	
	private int insert() {
		List<Modulo> dados = new ArrayList<>();
		populate(dados);
		int regs = 0;
		for(Modulo dado : dados)
			regs += insert(dado);
		
		return regs;
	}
	
	private Modulo get(String valorBusca) {
		TypedQuery<Modulo> query = em.createNamedQuery("Modulo.findByNome", Modulo.class);
		query.setParameter("nome", valorBusca);
		
		Modulo dado = null;
		try {
			dado = query.getSingleResult();
		}catch(NoResultException ex){}
		
		return dado;
	}
	
	public int insert(Modulo dado) {
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
		
		Modulo dado = get(valorBusca);
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
		Modulo dado = get(valorBusca);
		
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
		TypedQuery<Modulo> query = em.createNamedQuery("Modulo.findAll", Modulo.class);
		List<Modulo> dados = query.getResultList();
		
		em.getTransaction().begin();
		
		int regs = 0;
		
		for(Modulo dado : dados) {
			em.remove(dado);
			++regs;
		}
		em.getTransaction().commit();
		closeEntityManager();
		
		return regs;
	}
	
	public Modulo select(String valorBusca) {
		createEntityManager();
		
		Modulo dado = get(valorBusca);
		
		closeEntityManager();
		
		return dado;
	}
	
	public List<Modulo> select(){
		createEntityManager();
		TypedQuery<Modulo> query = em.createNamedQuery("Modulo.findAll", Modulo.class);
		List<Modulo> dados = query.getResultList();
		
		closeEntityManager();
		
		return dados;
	}
	
	private void processInsert(boolean varios) {
		if(varios) {
			System.out.println("===> INCLUIDOS [" + insert() + "] <===");
			return;
		}
		
		Modulo dado = new Modulo("Engenharia de Software", DateTimeUtils.parseDate("25/05/2017"), ip.select("03325885277"));
		
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
			List<Modulo> dados = select();
			print(dados);
		}else
			print(select(valorBusca));
	}
	
	public void processModulos() {
		  System.out.println("\n\n\n### PROCESSANDO MODULOS : INICIO ###");
		  
	      System.out.println("\n===> INCLUIR MODULOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR MODULOS <===");
	      processDelete();
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR MODULOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR MODULO <===");
	      processInsert(false);
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      String nome = "Banco de Dados";
	      System.out.println("\n===> BUSCAR MODULO EXISTENTE [" + nome + "] <===");
	      processSelect(nome);
	 
	      nome = "Ginastica Olimpica";
	      System.out.println("\n===> BUSCAR MODULO NÃO EXISTENTE [" + nome
	                        + "] <===");
	      processSelect(nome);
	 
	      nome = "Atletismo";
	      System.out.println("\n===> ALTERAR MODULO [" + nome + "] <===");
	      processUpdate(nome);
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      nome = "Historia da Arte";
	      System.out.println("\n===> EXCLUIR MODULO NÃO EXISTENTE [" + nome + "] <===");
	      processDelete(nome);
	 
	      nome = "Microbiologia";
	      System.out.println("\n===> EXCLUIR MODULO EXISTENTE [" + nome + "] <===");
	      processDelete(nome);
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR MODULOS <===");
	      processDelete();
	 
	      System.out.println("\n===> INCLUIR MODULOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR MODULOS <===");
	      processSelect();
	 
	      System.out.println("\n### PROCESSANDO MODULOS : FIM ###\n");
	}
}
