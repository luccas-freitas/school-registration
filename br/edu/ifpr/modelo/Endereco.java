package br.edu.ifpr.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifpr.modelo.types.EnderecoType;
import br.edu.ifpr.modelo.types.UF;
import br.edu.ifpr.utils.formatter.CepFormatter;

@Entity
@Table(name="ENDERECO")
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="END_ID", nullable=false)
	private Long id;
	
	@Column(name="END_LOGRADOURO", length=100, nullable=false)
	private String logradouro;
	
	@Column(name="END_NUMERO", nullable=false)
	private int numero;
	
	@Column(name="END_COMPLEMENTO", nullable=false)
	private String complemento;
	
	@Column(name="END_BAIRRO", nullable=false)
	private String bairro;
	
	@Column(name="END_CEP", nullable=false)
	private String cep;
	
	@Enumerated(value=EnumType.ORDINAL)
	@Column(name="END_TIPO", nullable=false)
	private EnderecoType tipo;
	
	@Column(name="END_CIDADE", nullable=false)
	private String cidade;
	
	@Enumerated(value=EnumType.ORDINAL)
	@Column(name="END_UF", nullable=false)
	private UF estado;
	
	public Endereco() {
		
	}
	public Endereco(String logradouro, int numero, String complemento, String bairro, String cep, EnderecoType tipo,
			String cidade, UF estado) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.tipo = tipo;
		this.cidade = cidade;
		this.estado = estado;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public EnderecoType getTipo() {
		return tipo;
	}
	public void setTipo(EnderecoType tipo) {
		this.tipo = tipo;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public UF getEstado() {
		return estado;
	}
	public void setEstado(UF estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", cep=" + CepFormatter.formatCep(cep) + ", tipo=" + tipo + ", cidade=" + cidade + ", estado=" + estado
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + numero;
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
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (estado != other.estado)
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numero != other.numero)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}