package br.edu.ifpr.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifpr.modelo.types.PessoaType;
import br.edu.ifpr.modelo.types.SexoType;

@Entity
@Table(name="ALUNO")
@AssociationOverrides({
	@AssociationOverride(name="fones", joinColumns=@JoinColumn(name="ALU_TEL_PES_CPF")),
	@AssociationOverride(name="endereco",
						 foreignKey=@ForeignKey(name="ALU_PES_END_ID_FK"),
						 joinColumns=@JoinColumn(name="ALU_PES_END_ID"))
})
public class Aluno extends Pessoa {
	@Column(name="ALU_REGISTRO")
	private String registro;
	
	@OneToMany(mappedBy="aluno", orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Matricula> matriculas;
	
	public Aluno() {
		super();
		this.tipo = PessoaType.ALUNO;
		this.matriculas = new ArrayList<>();
	}

	public Aluno(String cpf, String nome, String rg, Date nascimento, SexoType sexo, String email,
			Endereco endereco, List<Telefone> fones, String registro) {
		super(cpf, nome, rg, nascimento, sexo, email, endereco, fones, PessoaType.ALUNO);
		this.registro = registro;
	}

	public Aluno(String registro) {
		super();
		this.registro = registro;
	}

	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	@Override
	public String toString() {
		return "Aluno [registro=" + registro + ", matriculas=" + matriculas + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((matriculas == null) ? 0 : matriculas.hashCode());
		result = prime * result + ((registro == null) ? 0 : registro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matriculas == null) {
			if (other.matriculas != null)
				return false;
		} else if (!matriculas.equals(other.matriculas))
			return false;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		return true;
	}
}
