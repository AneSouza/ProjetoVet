package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.VacinaDAO;
import br.edu.ifsuldeminas.modelo.Vacina;
import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped
public class vacinaController {

    private Vacina vacina = new Vacina();
    private Integer tipoId;
    private Integer ingId;



/////////////////////////////////// getset private VERIFI
    public Integer getIngId() {
        return ingId;
    }

    public void setIngId(Integer ingId) {
        this.ingId = ingId;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

/////////////////////////////////// getset private classe obj
    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Vacina getVacina() {
        return vacina;
    }


/////////////////////////////////// carregar vacina
    public void carregar(Vacina p) {
        this.tipoId = p.getTipo().getId();
        p = new VacinaDAO().listaPorId(p);
        vacina = p;
    }

/////////////////////////////////// gravar vacina
    public void gravar() {
        Tipo t = new DAO<Tipo>(Tipo.class).listaPorId(this.tipoId);
        vacina.setTipo(t);
        if (this.vacina.getId() == null) {
            new DAO<Vacina>(Vacina.class).adiciona(vacina);
        } else {
            new DAO<Vacina>(Vacina.class).atualiza(vacina);
        }
        this.vacina = new Vacina();
        this.tipoId = 0;
        System.out.println("Gravando Vacina " + vacina.getNome());
    }

/////////////////////////////////// listar vacina
    public List<Vacina> getTodasVacinas() {
        return new DAO<Vacina>(Vacina.class).listaTodos();
    }

/////////////////////////////////// listar tipos vacina
    public List<Tipo> getTodosTipos() {
        return new DAO<Tipo>(Tipo.class).listaTodos();
    }

/////////////////////////////////// remover vacina
    public void remover(Vacina vacina) {
        try {

            new DAO<Vacina>(Vacina.class).remove(vacina.getId());

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("animal", new FacesMessage("Impossivel remover: A vacina consta em algum registro de animal"));
        }
    }
 
/////////////////////////////////// atualiza vacina   
    public void atualizaVacina(Vacina vacina) {
        this.vacina = vacina;

    }


}
