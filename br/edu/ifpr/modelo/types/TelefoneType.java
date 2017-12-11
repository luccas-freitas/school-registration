package br.edu.ifpr.modelo.types;

public enum TelefoneType {
	CELULAR(0){
		@Override
		public String toString() {
			return "Celular";
		}
	},
	RESIDENCIAL (1){
		@Override
		public String toString() {
			return "Residencial";
		}
	},
	COMERCIAL (2){
		@Override
		public String toString() {
			return "Comercial";
		}
	},
	RECADO (3){
		@Override
		public String toString() {
			return "Recado";
		}
	},
	FAX (4){
		@Override
		public String toString() {
			return "Fax";
		}
	};
	
	private final Integer value;
	
	TelefoneType (Integer telefoneType){ this.value = telefoneType;	}
	
	public Integer getValue() { return value; }
	
}