package br.edu.ifpr.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MODULO")
public class Modulo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MOD_ID", nullable=false)
	private Long id;
	
	@Column(name="MOD_NOME", length=100, nullable=false)
	private String nome;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="MOD_DT_INICIO", nullable=false)
	private Date inicio;
	
	@OneToOne(optional=true)
	@JoinColumn(name="MOD_INSTR_CPF", referencedColumnName="PES_CPF",
				foreignKey=@ForeignKey(name="MOD_INSTR_CPF_FK"))
	private Instrutor instrutor;
	
	public Modulo() {
		this.inicio = new Date();
	}
	public Modulo(String nome, Date inicio, Instrutor instrutor) {
		this();
		this.nome = nome;
		this.inicio = inicio;
		this.instrutor = instrutor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Instrutor getInstrutor() {
		return instrutor;
	}
	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
	@Override
	public String toString() {
		return "Modulo [nome=" + nome + ", inicio=" + inicio + ", instrutor=" + instrutor + ", getNome()=" + getNome()
				+ ", getInicio()=" + getInicio() + ", getInstrutor()=" + getInstrutor() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((instrutor == null) ? 0 : instrutor.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Modulo other = (Modulo) obj;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (instrutor == null) {
			if (other.instrutor != null)
				return false;
		} else if (!instrutor.equals(other.instrutor))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
