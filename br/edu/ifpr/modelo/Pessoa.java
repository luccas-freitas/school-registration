package br.edu.ifpr.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpr.modelo.types.PessoaType;
import br.edu.ifpr.modelo.types.SexoType;
import br.edu.ifpr.utils.formatter.CpfFormatter;
import br.edu.ifpr.utils.formatter.RgFormatter;


@MappedSuperclass
public abstract class Pessoa {
	
	@Id
	@Column(name="PES_CPF", length=11, nullable=false)
	protected String cpf;
	
	@Column(name="PES_NOME", length=100, nullable=false)
	protected String nome;
	
	@Column(name="PES_RG", nullable=false)
	protected String rg;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="PES_DT_NASC", nullable=false)
	protected Date nascimento;
	
	@Enumerated(value=EnumType.ORDINAL)
	@Column(name="PES_SEXO", nullable=false)
	protected SexoType sexo;
	 
	@Enumerated(value=EnumType.ORDINAL)
	@Column(name="PES_TIPO", nullable=false)
	protected PessoaType tipo;
	
	@Column(name="PES_EMAIL", length=100)
	protected String email;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, optional=false)
	@JoinColumn(name="PES_END_ID", referencedColumnName="END_ID")
	protected Endereco endereco;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinColumn(name="TEL_PES_CPF", referencedColumnName="PES_CPF")
	protected List<Telefone> fones;
	
	public Pessoa() {
		this.fones = new ArrayList<>();
	}
	
	public Pessoa(String cpf, String nome, String rg, Date nascimento, SexoType sexo, String email,
			Endereco endereco, List<Telefone> fones, PessoaType tipo) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.rg = rg;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.email = email;
		this.endereco = endereco;
		this.fones = fones;
		this.tipo = tipo;
	}
	public Pessoa(String cpf, String nome, String rg, Date nascimento, SexoType sexo, String email, Endereco endereco, Telefone fone, PessoaType tipo) {
		this(cpf, nome, rg, nascimento, sexo, email, endereco, Arrays.asList(fone),tipo);
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public SexoType getSexo() {
		return sexo;
	}
	public void setSexo(SexoType sexo) {
		this.sexo = sexo;
	}
	public PessoaType getTipo() {
		return tipo;
	}
	public void setTipo(PessoaType tipo) {
		this.tipo = tipo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Telefone> getFones() {
		return fones;
	}
	public void setFones(List<Telefone> fones) {
		this.fones = fones;
	}
	@Override
	public String toString() {
		return "Pessoa [cpf=" + CpfFormatter.formatCpf(cpf) + ", nome=" + nome + ", rg=" + RgFormatter.formatRg(rg) + ", nascimento=" + nascimento + ", sexo=" + sexo
				+ ", tipo=" + tipo + ", email=" + email + ", endereco=" + endereco + ", fones=" + fones + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((fones == null) ? 0 : fones.hashCode());
		result = prime * result + ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco != other.endereco)
			return false;
		if (fones == null) {
			if (other.fones != null)
				return false;
		} else if (!fones.equals(other.fones))
			return false;
		if (nascimento == null) {
			if (other.nascimento != null)
				return false;
		} else if (!nascimento.equals(other.nascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (sexo != other.sexo)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
