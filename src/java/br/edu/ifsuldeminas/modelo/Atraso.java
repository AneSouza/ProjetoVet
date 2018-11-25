package br.edu.ifsuldeminas.modelo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atraso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String obs;
    private String nomeAnim;
    private String nomeVac;
    private String doseVac;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data = Calendar.getInstance();


//	
    @OneToOne
    private Pessoa pessoa;

    @OneToOne
    private Animal end;

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Animal getEnd() {
        return end;
    }

    public void setEnd(Animal end) {
        this.end = end;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getNomeAnim() {
        return nomeAnim;
    }

    public void setNomeAnim(String nomeAnim) {
        this.nomeAnim = nomeAnim;
    }

    public String getNomeVac() {
        return nomeVac;
    }

    public void setNomeVac(String nomeVac) {
        this.nomeVac = nomeVac;
    }

    public String getDoseVac() {
        return doseVac;
    }

    public void setDoseVac(String doseVac) {
        this.doseVac = doseVac;
    }

}
