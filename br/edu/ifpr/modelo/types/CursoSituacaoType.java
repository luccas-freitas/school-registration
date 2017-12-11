package br.edu.ifpr.modelo.types;

public enum CursoSituacaoType {
	ABERTO (0){
		@Override
		public String toString() {
			return "Aberto";
		}
	},
	ANDAMENTO (1){
		@Override
		public String toString() {
			return "Andamento";
		}
	},
	ENCERRADO (2){
		@Override
		public String toString() {
			return "Encerrado";
		}
	},
	CANCELADO (3){
		@Override
		public String toString() {
			return "Cancelado";
		}
	};

	private final Integer value;
	
	CursoSituacaoType (Integer cursoSituacaoType){ this.value = cursoSituacaoType; }
	
	public Integer getValue() { return value; }
}
