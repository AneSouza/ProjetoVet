package br.edu.ifsuldeminas.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeAni, raca, sexo, cidade, estado;
    private float peso;

    @OneToOne
    private Pessoa pessoa;
    
    @ManyToMany
    private List<Vacina> ling = new LinkedList<Vacina>();

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date nascAni;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

   

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomeAni() {
        return nomeAni;
    }

    public void setNomeAni(String nomeAni) {
        this.nomeAni = nomeAni;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public java.util.Date getNasc() {
        return nascAni;
    }

    public void setNasc(java.util.Date nascAni) {
        this.nascAni = nascAni;
    }

}
