package br.edu.ifpr.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpr.modelo.id.MatriculaId;

@Entity
@Table(name="MATRICULA")
@IdClass(MatriculaId.class)
public class Matricula {
	@Id
	@ManyToOne
	@JoinColumn(name="MATR_CUR_ID", referencedColumnName="CUR_ID", 
				nullable=false, foreignKey=@ForeignKey(name="MATR_CUR_ID_FK"))
	private Curso curso;
	
	@Id
	@ManyToOne
	@JoinColumn(name="MATR_PES_CPF", referencedColumnName="PES_CPF",
				nullable=false, foreignKey=@ForeignKey(name="MATR_PES_CPF_FK"))
	private Aluno aluno;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="MATR_DT_MATR", nullable=false)
	private Date dataMatricula;
	
	public Matricula() {
		
	}
	public Matricula(Curso curso, Aluno aluno, Date dataMatricula) {
		super();
		this.curso = curso;
		this.aluno = aluno;
		this.dataMatricula = dataMatricula;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	@Override
	public String toString() {
		return "Matricula [curso=" + curso + ", aluno=" + aluno + ", dataMatricula=" + dataMatricula + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((dataMatricula == null) ? 0 : dataMatricula.hashCode());
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
		Matricula other = (Matricula) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (dataMatricula == null) {
			if (other.dataMatricula != null)
				return false;
		} else if (!dataMatricula.equals(other.dataMatricula))
			return false;
		return true;
	}
}