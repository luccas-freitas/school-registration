package br.edu.ifpr.modelo.types;

public enum UF {
	ACRE("AC", "Acre"){
		@Override
		public String toString() {
			return "Acre";
		}
	},
	ALAGOAS("AL", "Alagoas"){
		@Override
		public String toString() {
			return "Alagoas";
		}
	},
	AMAPA("AP", "Amapa"){
		@Override
		public String toString() {
			return "Amapa";
		}
	},
	AMAZONAS("AM", "Amazonas"){
		@Override
		public String toString() {
			return "Amazonas";
		}
	},
	BAHIA("BA","BAHIA"){
		@Override
		public String toString() {
			return "Bahia";
		}
	},
	CEARA("CE","Ceara"){
		@Override
		public String toString() {
			return "Ceara";
		}
	},
	DISTRITO_FEDERAL("DF","Distrito Federal"){
		@Override
		public String toString() {
			return "Distrito Federal";
		}
	},
	ESPIRITO_SANTO("ES","Espirito Santo"){
		@Override
		public String toString() {
			return "Espirito Santo";
		}
	},
	GOIAS("GO","Goias"){
		@Override
		public String toString() {
			return "Goias";
		}
	},
	MARANHAO("MA","Maranhão"){
		@Override
		public String toString() {
			return "Maranhão";
		}
	},
	MATO_GROSSO("MT","Mato Grosso"){
		@Override
		public String toString() {
			return "Mato Grosso";
		}
	},
	MATO_GROSSO_SUL("MS","Mato Grosso do Sul"){
		@Override
		public String toString() {
			return "Mato Grosso do Sul";
		}
	},
	MINAS_GERAIS("MG","Minas Gerais"){
		@Override
		public String toString() {
			return "Minas Gerais";
		}
	},
	PARA("PA","Pará"){
		@Override
		public String toString() {
			return "Pará";
		}
	},
	PARAIBA("PB","Paraiba"){
		@Override
		public String toString() {
			return "Paraíba";
		}
	},
	PARANA("PR","Paraná"){
		@Override
		public String toString() {
			return "Paraná";
		}
	},
	PERNAMBUCO("PE","Pernambuco"){
		@Override
		public String toString() {
			return "Pernambuco";
		}
	},
	PIAUI("PI","Piaui"){
		@Override
		public String toString() {
			return "Piaui";
		}
	},
	RIO_JANEIRO("RJ","Rio de Janeiro"){
		@Override
		public String toString() {
			return "Rio de Janeiro";
		}
	},
	RIO_GRANDE_NORTE("RN","Rio Grande do Norte"){
		@Override
		public String toString() {
			return "Rio Grande do Norte";
		}
	},
	RIO_GRANDE_SUL("RS","Rio Grande do Sul"){
		@Override
		public String toString() {
			return "Rio Grande do Sul";
		}
	},
	RONDONIA("RO","Rondonia"){
		@Override
		public String toString() {
			return "Rondonia";
		}
	},
	RORAIMA("RR","Roraima"){
		@Override
		public String toString() {
			return "Roraima";
		}
	},
	SANTA_CATARINA("SC","Santa Catarina"){
		@Override
		public String toString() {
			return "Santa Catarina";
		}
	},
	SAO_PAULO("SP","São Paulo"){
		@Override
		public String toString() {
			return "São Paulo";
		}
	},
	SERGIPE("SE","Sergipe"){
		@Override
		public String toString() {
			return "Sergipe";
		}
	},
	TOCANTINS("TO","Tocantins"){
		@Override
		public String toString() {
			return "Tocantins";
		}
	};
	
	private String sigla;
	private String nome;
	
	private UF(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}
	public String sigla() {
		return sigla;
	}
	public String nome() {
		return nome;
	}
}
