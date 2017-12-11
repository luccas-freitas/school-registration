package br.edu.ifpr.modelo.types;

public enum PessoaType {
	INSTRUTOR (0){
		@Override
		public String toString() {
			return "Instrutor";
		}
	},
	ALUNO (1){
		@Override
		public String toString() {
			return "Aluno";
		}
	};
	
	private final Integer value;
	
	PessoaType (Integer pessoaType){ this.value = pessoaType; }
	
	public Integer getValue() { return value; }
}
