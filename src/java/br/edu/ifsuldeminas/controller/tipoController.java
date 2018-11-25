package br.edu.ifsuldeminas.controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped
public class tipoController {

    private Tipo t = new Tipo();

    public Tipo getTipo() {
        return t;
    }

//////////////alterar está 
    public void gravar() {

        if (this.t.getId() == null) {
            new DAO<Tipo>(Tipo.class).adiciona(t);
        } else {
            new DAO<Tipo>(Tipo.class).atualiza(t);
        }

        System.out.println("Gravando Tipo " + t.getTipo());
    }
//     
////////////lista todos tipos  

    public List<Tipo> getTodosTipos() {
        return new DAO<Tipo>(Tipo.class).listaTodos();
    }

    ////////////remove  tipos  
    public void remover(Tipo t) {
        try {
            new DAO<Tipo>(Tipo.class).remove(t.getId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("tipo", new FacesMessage("Impossivel remover: o tipo consta em uma vacina."));

        }
    }

    ////////////atualiza tipos  
    public void atualizaTipo(Tipo t) {
        this.t = t;

    }

}
