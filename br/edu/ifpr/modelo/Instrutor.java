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
@Table(name="INSTRUTOR")
@AssociationOverrides({
	@AssociationOverride(name="fones",joinColumns=@JoinColumn(name="INSTR_TEL_PES_CPF")),
	@AssociationOverride(name="endereco",
						 foreignKey=@ForeignKey(name="INSTR_PES_END_ID_FK"),
						 joinColumns=@JoinColumn(name="INSTR_PES_END_ID"))
})
public class Instrutor extends Pessoa {
	
	@Column(name="INS_CODIGO")
	private String codigo;
	
	@OneToMany(mappedBy="instrutor", fetch=FetchType.EAGER)
	private List<Modulo> modulos;
	
	public Instrutor() {
		super();
		this.tipo = PessoaType.INSTRUTOR;
		this.modulos = new ArrayList<>();
	}
	public Instrutor(String cpf, String nome, String rg, Date nascimento, SexoType sexo, String email,
			Endereco endereco, List<Telefone> fones, String codigo) {
		super(cpf, nome, rg, nascimento, sexo, email, endereco, fones, PessoaType.INSTRUTOR);
		this.codigo = codigo;
	}
	
	public Instrutor(String codigo) {
		super();
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Modulo> getModulos() {
		return modulos;
	}
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	@Override
	public String toString() {
		return "Instrutor [codigo=" + codigo + ", modulos=" + modulos + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((modulos == null) ? 0 : modulos.hashCode());
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
		Instrutor other = (Instrutor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (modulos == null) {
			if (other.modulos != null)
				return false;
		} else if (!modulos.equals(other.modulos))
			return false;
		return true;
	}
}
