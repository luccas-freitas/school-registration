package br.edu.ifpr.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifpr.modelo.types.TelefoneType;
import br.edu.ifpr.utils.formatter.TelefoneFormatter;

@Entity
@Table(name="TELEFONE")
public class Telefone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEL_ID", nullable=false)
	private Long id;
	
	@Column(name="TEL_DDD", length=2, nullable=false)
	private int ddd;
	
	@Column(name="TEL_NUMERO", length=9, nullable=true)
	private String numero;
	
	@Enumerated(value=EnumType.ORDINAL)
	@Column(name="TEL_TIPO", nullable=false)
	private TelefoneType tipo;
	
	public Telefone() {
		
	}
	public Telefone(int ddd, String numero, TelefoneType tipo) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
	}
	public int getDdd() {
		return ddd;
	}
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public TelefoneType getTipo() {
		return tipo;
	}
	public void setTipo(TelefoneType tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Telefone [ddd=" + ddd + ", numero=" + TelefoneFormatter.formatNumero(numero) + ", tipo=" + tipo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ddd;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (ddd != other.ddd)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
