package br.edu.ifpr.modelo.id;

import java.io.Serializable;

public class MatriculaId implements Serializable{
	private static final long serialVersionUID = 1L;

	//o tipo de dado é o mesmo usado na declaração do atributo na classe Curso
	private String curso; //código do curso
	
	//o tipo de dado é o mesmo usado na declaração do atributo na classe Aluno
	//neste caso, Pessoa, uma vez que a primeira herda da segunda
	private String aluno; //CPF, herdado da pessoa

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
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
		MatriculaId other = (MatriculaId) obj;
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
		return true;
	}

	public MatriculaId() {
		super();
	}
}
