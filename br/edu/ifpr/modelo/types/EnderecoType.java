package br.edu.ifpr.modelo.types;

public enum EnderecoType {
	RESIDENCIAL (0){
		@Override
		public String toString() {
			return "Residencial";
		}
	},
	COMERCIAL (1){
		@Override
		public String toString() {
			return "Comercial";
		}
	};
	
	private final Integer value;
	
	EnderecoType (Integer enderecoType){ this.value = enderecoType; }
	
	public Integer getValue() { return value; }
}
