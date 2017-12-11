package br.edu.ifpr.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpr.modelo.types.CursoSituacaoType;
@Entity
@Table(name="CURSO")
public class Curso {
	
	@Id
	@Column(name="CUR_ID", nullable=false)
	private String codigo;
	
	@Column(name="CUR_NOME", length=100, nullable=false)
	private String nome;
	
	@Enumerated(value=EnumType.ORDINAL)
	@Column(name="CUR_SITUACAO", nullable=false)
	private CursoSituacaoType situacao;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="CUR_DT_INICIO", nullable=false)
	private Date inicio;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="MOD_CUR_ID", referencedColumnName="CUR_ID",
				foreignKey=@ForeignKey(name="MOD_CUR_ID_FK"))
	private List<Modulo> modulos;
	
	@OneToMany(mappedBy="curso", orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Matricula> matriculas;
	
	
	public Curso() {
		this.modulos = new ArrayList<>();
		this.matriculas = new ArrayList<>();
		this.situacao = CursoSituacaoType.ABERTO;
		this.inicio = new Date();
	}
	public Curso(String codigo, String nome, Date inicio, List<Modulo> modulos) {
		this();
		this.codigo = codigo;
		this.nome = nome;
		this.inicio = inicio;
		this.modulos = modulos;
	}
	public Curso(String codigo, String nome, Date inicio, Modulo modulo) {
		this(codigo, nome, inicio, Arrays.asList(modulo));
	}
	public Curso(String codigo, String nome, Modulo modulo) {
		this(codigo, nome, new Date(), modulo);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public CursoSituacaoType getSituacao() {
		return situacao;
	}
	public void setSituacao(CursoSituacaoType situacao) {
		this.situacao = situacao;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public List<Modulo> getModulos() {
		return modulos;
	}
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	@Override
	public String toString() {
		return "Curso [nome=" + nome + ", situacao=" + situacao + ", inicio=" + inicio + ", modulos=" + modulos
				+ ", matriculas=" + matriculas + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((matriculas == null) ? 0 : matriculas.hashCode());
		result = prime * result + ((modulos == null) ? 0 : modulos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		Curso other = (Curso) obj;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (matriculas == null) {
			if (other.matriculas != null)
				return false;
		} else if (!matriculas.equals(other.matriculas))
			return false;
		if (modulos == null) {
			if (other.modulos != null)
				return false;
		} else if (!modulos.equals(other.modulos))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}
}
