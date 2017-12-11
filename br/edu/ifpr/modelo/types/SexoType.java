package br.edu.ifpr.modelo.types;

public enum SexoType {
	MASCULINO (0){
		@Override
		public String toString() {
			return "Masculino";
		}
	},
	FEMININO (1){
		@Override
		public String toString() {
			return "Feminino";
		}
	};
	
	private final Integer value;
	
	SexoType (Integer sexoType){ this.value = sexoType; }
	
	public Integer getValue() { return value; }
}
