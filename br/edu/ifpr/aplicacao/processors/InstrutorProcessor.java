package br.edu.ifpr.aplicacao.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpr.modelo.Instrutor;
import br.edu.ifpr.modelo.Endereco;
import br.edu.ifpr.modelo.Telefone;
import br.edu.ifpr.modelo.types.EnderecoType;
import br.edu.ifpr.modelo.types.SexoType;
import br.edu.ifpr.modelo.types.TelefoneType;
import br.edu.ifpr.modelo.types.UF;
import br.edu.ifpr.utils.date.DateTimeUtils;

public class InstrutorProcessor {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	InstrutorProcessor(){
		
	}
	public InstrutorProcessor(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	private void createEntityManager() {
		em = emf.createEntityManager();
	}
	private void closeEntityManager() {
		if(em != null) em.close();
	}
	
	private void print(Instrutor dado) {
		System.out.println(dado);
	}
	
	private void print(List<Instrutor> dados) {
		System.out.println("===> TOTAL [" + dados.size() + "] <===");
		for(Instrutor dado : dados)
			print(dado);
	}
	
	private void populate(List<Instrutor> dados) {
		Instrutor dado = new Instrutor("05578965487", "Professor Romualdo", "3665445689",
					 DateTimeUtils.getDate(), SexoType.MASCULINO, "romauldo@ifpr.com",
					 new Endereco("Rua da Web", 30, "", "Bairro C", "666", EnderecoType.COMERCIAL, "Macapa", UF.AMAPA),
					 Arrays.asList(new Telefone(96, "96078244", TelefoneType.CELULAR)), "000001");
		dados.add(dado);
		
		dado = new Instrutor("40053301935", "Miguel Joaquim Alves", "350920382",
				 DateTimeUtils.parseDate("06/02/1977"), SexoType.MASCULINO, "miguel@ifpr.com",
				 new Endereco("Quadra QI 5 Conjunto F", 497, "", "Guará I", "86200000", EnderecoType.RESIDENCIAL, "Brasília", UF.DISTRITO_FEDERAL),
				 Arrays.asList(new Telefone(61, "26243997", TelefoneType.COMERCIAL)), "000002");
		dados.add(dado);
		
		dado = new Instrutor("15861284857", "Diogo Anthony Costa", "245661372",
				 DateTimeUtils.parseDate("06/12/1947"), SexoType.MASCULINO, "diogo@ifpr.com",
				 new Endereco("Rua José Fernandes", 978, "", "Jardim Paulista", "86200000", EnderecoType.RESIDENCIAL, "Bauru", UF.SAO_PAULO),
				 Arrays.asList(new Telefone(14, "26167007", TelefoneType.RESIDENCIAL)), "000003");
		dados.add(dado);
		
		dado = new Instrutor("58634875857", "Natália Brenda Souza", "214154087",
				 DateTimeUtils.parseDate("02/12/1964"), SexoType.FEMININO, "natalia@ifpr.com",
				 new Endereco("Rua Girardino Copozzoli", 179, "", "Jardim Tavares", "86200000", EnderecoType.COMERCIAL, "Campina Grande", UF.PARAIBA),
				 Arrays.asList(new Telefone(83, "26889772", TelefoneType.CELULAR)), "000004");
		dados.add(dado);
		
		dado = new Instrutor("72674557273", "Emanuelly Caroline Ribeiro", "298946257",
				 DateTimeUtils.parseDate("01/04/1987"), SexoType.FEMININO, "emanuelly@ifpr.com",
				 new Endereco("Rua Lauro Medeiros", 226, "", "Lagoa Nova", "86200000", EnderecoType.RESIDENCIAL, "Natal", UF.RIO_GRANDE_NORTE),
				 Arrays.asList(new Telefone(84, "37600000", TelefoneType.CELULAR)), "000005");
		dados.add(dado);
		
		dado = new Instrutor("95624683804", "Joana Sara de Paula", "236444396",
				 DateTimeUtils.parseDate("25/10/1982"), SexoType.FEMININO, "joana@ifpr.com",
				 new Endereco("Praça Bandeiras", 715, "", "Setor Central", "86200000", EnderecoType.COMERCIAL, "Araguaína", UF.TOCANTINS),
				 Arrays.asList(new Telefone(63, "38297644", TelefoneType.RECADO)), "000006");
		dados.add(dado);
		
		dado = new Instrutor("84954772206", "Elias Francisco de Paula", "146434043",
				 DateTimeUtils.parseDate("19/12/1937"), SexoType.MASCULINO, "elias@ifpr.com",
				 new Endereco("Rua Terezinha Pasquini", 596, "", "Jardim Flamboyant", "86200000", EnderecoType.COMERCIAL, "São João da Boa Vista", UF.SAO_PAULO),
				 Arrays.asList(new Telefone(19, "29043727", TelefoneType.FAX)), "000007");
		dados.add(dado);
		
		dado = new Instrutor("15887019646", "Olivia Maitê Yasmin Lima", "273738549",
				 DateTimeUtils.parseDate("23/07/1972"), SexoType.FEMININO, "olivia@ifpr.com",
				 new Endereco("Comunidade Canudal", 380, "", "Distrito de Gruta", "86200000", EnderecoType.RESIDENCIAL, "Cachoeiro de Itapemirim", UF.ESPIRITO_SANTO),
				 Arrays.asList(new Telefone(28, "39690739", TelefoneType.CELULAR)), "000008");
		dados.add(dado);
		
		dado = new Instrutor("21898508607", "Luccas Erick Fernandes", "325791703",
				 DateTimeUtils.parseDate("26/01/1987"), SexoType.MASCULINO, "luccas@ifpr.com",
				 new Endereco("Rua José de Brito", 740, "", "Jardim Lilah", "86200000", EnderecoType.RESIDENCIAL, "São Paulo", UF.SAO_PAULO),
				 Arrays.asList(new Telefone(11, "36230099", TelefoneType.CELULAR)), "000009");
		dados.add(dado);
		
		dado = new Instrutor("27857240621", "Joaquim Campos", "298052003",
				 DateTimeUtils.parseDate("23/05/1985"), SexoType.MASCULINO, "joaquim@ifpr.com",
				 new Endereco("Caminho 10-Quadra C", 428, "", "Cajazeiras", "86200000", EnderecoType.COMERCIAL, "Salvador", UF.BAHIA),
				 Arrays.asList(new Telefone(71, "39864706", TelefoneType.CELULAR)), "000010");
		dados.add(dado);
	}
	
	private int insert() {
		List<Instrutor> dados = new ArrayList<>();
		populate(dados);
		int regs = 0;
		for(Instrutor dado : dados)
			regs += insert(dado);
		
		return regs;
	}
	
	private Instrutor get(String valorBusca) {
		TypedQuery<Instrutor> query = em.createNamedQuery("Instrutor.findByCpf", Instrutor.class);
		query.setParameter("cpf", valorBusca);
		
		Instrutor dado = null;
		try {
			dado = query.getSingleResult();
		}catch(NoResultException ex){}
		
		return dado;
	}
	
	public int insert(Instrutor dado) {
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
		
		Instrutor dado = get(valorBusca);
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
		Instrutor dado = get(valorBusca);
		
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
		TypedQuery<Instrutor> query = em.createNamedQuery("Instrutor.findAll", Instrutor.class);
		List<Instrutor> dados = query.getResultList();
		
		em.getTransaction().begin();
		
		int regs = 0;
		
		for(Instrutor dado : dados) {
			em.remove(dado);
			++regs;
		}
		em.getTransaction().commit();
		closeEntityManager();
		
		return regs;
	}
	
	public Instrutor select(String valorBusca) {
		createEntityManager();
		
		Instrutor dado = get(valorBusca);
		
		closeEntityManager();
		
		return dado;
	}
	
	public List<Instrutor> select(){
		createEntityManager();
		TypedQuery<Instrutor> query = em.createNamedQuery("Instrutor.findAll", Instrutor.class);
		List<Instrutor> dados = query.getResultList();
		
		closeEntityManager();
		
		return dados;
	}
	
	private void processInsert(boolean varios) {
		if(varios) {
			System.out.println("===> INCLUIDOS [" + insert() + "] <===");
			return;
		}
		
		Instrutor dado = new Instrutor("03325885277", "Goncalo", "7896325844",
				 DateTimeUtils.getDate(), SexoType.MASCULINO, "goncalo@ifpr.com",
				 new Endereco("Rua das Flores", 33, "", "Bairro D", "069", EnderecoType.RESIDENCIAL, "Sao Paulo", UF.SAO_PAULO),
				 Arrays.asList(new Telefone(11, "32585448", TelefoneType.RESIDENCIAL)), "000002");
		
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
			List<Instrutor> dados = select();
			print(dados);
		}else
			print(select(valorBusca));
	}
	
	public void processInstrutores() {
		  System.out.println("\n\n\n### PROCESSANDO INSTRUTORES : INICIO ###");
		  
	      System.out.println("\n===> INCLUIR INSTRUTORES <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR INSTRUTORES <===");
	      processDelete();
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR INSTRUTORES <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      System.out.println("\n===> INCLUIR INSTRUTOR <===");
	      processInsert(false);
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      String cpf = "58634875857";
	      System.out.println("\n===> BUSCAR INSTRUTOR EXISTENTE [" + cpf + "] <===");
	      processSelect(cpf);
	 
	      cpf = "11111111111";
	      System.out.println("\n===> BUSCAR INSTRUTOR NÃO EXISTENTE [" + cpf
	                        + "] <===");
	      processSelect(cpf);
	 
	      cpf = "95624683804";
	      System.out.println("\n===> ALTERAR INSTRUTOR [" + cpf + "] <===");
	      processUpdate(cpf);
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      cpf = "22222222222";
	      System.out.println("\n===> EXCLUIR INSTRUTOR NÃO EXISTENTE [" + cpf + "] <===");
	      processDelete(cpf);
	 
	      cpf = "15887019646";
	      System.out.println("\n===> EXCLUIR INSTRUTOR EXISTENTE [" + cpf + "] <===");
	      processDelete(cpf);
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      System.out.println("\n===> EXCLUIR INSTRUTORES <===");
	      processDelete();
	 
	      System.out.println("\n===> INCLUIR INSTRUTORES <===");
	      processInsert(true);
	 
	      System.out.println("\n===> BUSCAR INSTRUTORES <===");
	      processSelect();
	 
	      System.out.println("\n### PROCESSANDO INSTRUTORES : FIM ###\n");
	}
}