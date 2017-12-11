package br.edu.ifpr.aplicacao.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpr.modelo.Aluno;
import br.edu.ifpr.modelo.Endereco;
import br.edu.ifpr.modelo.Telefone;
import br.edu.ifpr.modelo.types.EnderecoType;
import br.edu.ifpr.modelo.types.SexoType;
import br.edu.ifpr.modelo.types.TelefoneType;
import br.edu.ifpr.modelo.types.UF;
import br.edu.ifpr.utils.date.DateTimeUtils;

public class AlunoProcessor {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	public AlunoProcessor(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	private void createEntityManager() {
		em = emf.createEntityManager();
	}
	private void closeEntityManager() {
		if(em != null) em.close();
	}
	
	private void print(Aluno dado) {
		System.out.println(dado);
	}
	
	private void print(List<Aluno> dados) {
		System.out.println("===> TOTAL [" + dados.size() + "] <===");
		for(Aluno dado : dados)
			print(dado);
	}
	
	private void populate(List<Aluno> dados) {
		Aluno dado = new Aluno("12971531031", "Joao das Neves", "188121924",
					 DateTimeUtils.getDate(), SexoType.MASCULINO, "snow@jon.com",
					 new Endereco("Rua A", 100, "", "Bairro A", "86200000", EnderecoType.RESIDENCIAL, "Londrina", UF.PARANA),
					 Arrays.asList(new Telefone(43, "996440942", TelefoneType.CELULAR)), "121000");
		dados.add(dado);
		
		dado = new Aluno("02122211123", "Reginaldo Manzotti", "0441414414",
				 DateTimeUtils.getDate(), SexoType.MASCULINO, "padre@church.com",
				 new Endereco("Rua D", 55, "", "Bairro X", "86200000", EnderecoType.RESIDENCIAL, "Ibipora", UF.PARANA),
				 Arrays.asList(new Telefone(43, "99699966", TelefoneType.CELULAR)), "122000");
		
		dados.add(dado);
		
		dado = new Aluno("07663758382", "Stella Lavínia Marcela Souza", "447840939",
				 DateTimeUtils.parseDate("21/07/1999"), SexoType.FEMININO, "stella@ifpr.com",
				 new Endereco("Rua Engenheiro Paulo Frontim", 431, "", "Jardim Los Angeles", "86200000", EnderecoType.COMERCIAL, "Campo Grande", UF.MATO_GROSSO_SUL),
				 Arrays.asList(new Telefone(67, "25236173", TelefoneType.RECADO)), "123000");
		
		dados.add(dado);
		
		dado = new Aluno("61166624552", "Hadassa Yasmin Sabrina Gomes", "358027627",
				 DateTimeUtils.parseDate("25/02/1995"), SexoType.FEMININO, "hadassa@ifpr.com",
				 new Endereco("Rua Ibiúna", 176, "", "Lagoa Azul", "86200000", EnderecoType.COMERCIAL, "Natal", UF.RIO_GRANDE_NORTE),
				 Arrays.asList(new Telefone(84, "987424660", TelefoneType.RECADO)), "124000");
		
		dados.add(dado);
		
		dado = new Aluno("50322267501", "Gustavo André Rodrigues", "326702556",
				 DateTimeUtils.parseDate("02/06/1988"), SexoType.MASCULINO, "gustavo@ifpr.com",
				 new Endereco("Rua Pio XI", 698, "", "Jardim Belo Panorama", "86200000", EnderecoType.RESIDENCIAL, "Rondonópolis", UF.MATO_GROSSO),
				 Arrays.asList(new Telefone(66, "992742806", TelefoneType.CELULAR)), "125000");
		
		dados.add(dado);
		
		dado = new Aluno("62323040120", "Antonio Paulo Araújo", "190058614",
				 DateTimeUtils.parseDate("03/03/1960"), SexoType.MASCULINO, "tonin@ifpr.com",
				 new Endereco("Quadra QR 516 Conjunto I", 595, "", "Santa Maria", "86200000", EnderecoType.RESIDENCIAL, "Brasília", UF.DISTRITO_FEDERAL),
				 Arrays.asList(new Telefone(61, "37560110", TelefoneType.FAX)), "126000");
		
		dados.add(dado);
		
		dado = new Aluno("32283259037", "Laura Yasmin Emanuelly Oliveira", "474364069",
				 DateTimeUtils.parseDate("14/06/1976"), SexoType.MASCULINO, "laura@ifpr.com",
				 new Endereco("Rua Itainópolis", 438, "", "Parque de Exposição", "86200000", EnderecoType.RESIDENCIAL, "Picos", UF.PIAUI),
				 Arrays.asList(new Telefone(89, "997132184", TelefoneType.RESIDENCIAL)), "127000");
		
		dados.add(dado);
		
		dado = new Aluno("73877628052", "Gabrielly Bárbara Dias", "131693025",
				 DateTimeUtils.parseDate("04/07/1999"), SexoType.MASCULINO, "gabi@ifpr.com",
				 new Endereco("Rua Professor Bayar", 972, "", "Popular", "86200000", EnderecoType.RESIDENCIAL, "Santa Rita", UF.PARAIBA),
				 Arrays.asList(new Telefone(83, "995926723", TelefoneType.COMERCIAL)), "128000");
		
		dados.add(dado);
		
		dado = new Aluno("85252310297", "Fernando Bernardo Araújo", "467764311",
				 DateTimeUtils.parseDate("25/08/1994"), SexoType.MASCULINO, "fernando@ifpr.com",
				 new Endereco("Rua Antônio Carlos Dias Soares", 972, "", "Farolândia", "86200000", EnderecoType.RESIDENCIAL, "Aracaju", UF.SERGIPE),
				 Arrays.asList(new Telefone(79, "984871849", TelefoneType.CELULAR)), "129000");
		
		dados.add(dado);
		
		dado = new Aluno("67164916608", "Filipe Vinicius Pinto", "346454785",
				 DateTimeUtils.parseDate("20/07/1992"), SexoType.MASCULINO, "filipe@ifpr.com",
				 new Endereco("Rua das Alagoas", 973, "", "Nova Parnamirim", "86200000", EnderecoType.RESIDENCIAL, "Parnamirim", UF.RIO_GRANDE_NORTE),
				 Arrays.asList(new Telefone(84, "981821170", TelefoneType.CELULAR)), "130000");
		
		dados.add(dado);
	}
	
	private int insert() {
		List<Aluno> dados = new ArrayList<>();
		populate(dados);
		int regs = 0;
		for(Aluno dado : dados)
			regs += insert(dado);
		
		return regs;
	}
	
	private Aluno get(String valorBusca) {
		TypedQuery<Aluno> query = em.createNamedQuery("Aluno.findByCpf", Aluno.class);
		query.setParameter("cpf", valorBusca);
		
		Aluno dado = null;
		try {
			dado = query.getSingleResult();
		}catch(NoResultException ex){}
		
		return dado;
	}
	
	public int insert(Aluno dado) {
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
		
		Aluno dado = get(valorBusca);
		if(dado != null) {
			em.getTransaction().begin();
			
//			Endereco endereco = dado.getEndereco();
//			endereco.setComplemento("@@@ COMPLEMENTO COMPLEMENTO @@@");
			
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
		Aluno dado = get(valorBusca);
		
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
		TypedQuery<Aluno> query = em.createNamedQuery("Aluno.findAll", Aluno.class);
		List<Aluno> dados = query.getResultList();
		
		em.getTransaction().begin();
		
		int regs = 0;
		
		for(Aluno dado : dados) {
			em.remove(dado);
			++regs;
		}
		em.getTransaction().commit();
		closeEntityManager();
		
		return regs;
	}
	
	public Aluno select(String valorBusca) {
		createEntityManager();
		
		Aluno dado = get(valorBusca);
		
		closeEntityManager();
		
		return dado;
	}
	
	public List<Aluno> select(){
		createEntityManager();
		TypedQuery<Aluno> query = em.createNamedQuery("Aluno.findAll", Aluno.class);
		List<Aluno> dados = query.getResultList();
		
		closeEntityManager();
		
		return dados;
	}
	
	private void processInsert(boolean varios) {
		if(varios) {
			System.out.println("===> INCLUIDOS [" + insert() + "] <===");
			return;
		}
		
		Aluno dado = new Aluno("10519732871", "Danyela da Tormenta", "377622679",
				 DateTimeUtils.getDate(), SexoType.FEMININO, "dany@drogon.com",
				 new Endereco("Rua do Dragao", 300, "", "Bairro A", "86200000", EnderecoType.RESIDENCIAL, "Cambe", UF.PARANA),
				 Arrays.asList(new Telefone(43, "96078282", TelefoneType.CELULAR)), "654321");
		
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
			List<Aluno> dados = select();
			print(dados);
		}else
			print(select(valorBusca));
	}
	
	public void processAlunos() {
		  System.out.println("\n\n\n### PROCESSANDO ALUNOS : INICIO ###");
		  
	      System.out.println("\n===> INCLUIR ALUNOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR ALUNOS <===");
	      processDelete();
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR ALUNOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR ALUNO <===");
	      processInsert(false);
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      String cpf = "62323040120";
	      System.out.println("\n===> BUSCAR ALUNO EXISTENTE [" + cpf + "] <===");
	      processSelect(cpf);
	 
	      cpf = "11111111111";
	      System.out.println("\n===> BUSCAR ALUNO NÃO EXISTENTE [" + cpf
	                        + "] <===");
	      processSelect(cpf);
	 
	      cpf = "73877628052";
	      System.out.println("\n===> ALTERAR ALUNO [" + cpf + "] <===");
	      processUpdate(cpf);
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      cpf = "22222222222";
	      System.out.println("\n===> EXCLUIR ALUNO NÃO EXISTENTE [" + cpf + "] <===");
	      processDelete(cpf);
	 
	      cpf = "61166624552";
	      System.out.println("\n===> EXCLUIR ALUNO EXISTENTE [" + cpf + "] <===");
	      processDelete(cpf);
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR ALUNOS <===");
	      processDelete();
	 
	      System.out.println("\n===> INCLUIR ALUNOS <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR ALUNOS <===");
	      processSelect();
	 
	      System.out.println("\n### PROCESSANDO ALUNOS : FIM ###\n");
	}
}
