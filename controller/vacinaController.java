package br.edu.ifsuldeminas.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifsuldeminas.dao.DAO;

import br.edu.ifsuldeminas.dao.VacinaDAO;
import br.edu.ifsuldeminas.modelo.Ingredientes;

import br.edu.ifsuldeminas.modelo.Vacina;
import br.edu.ifsuldeminas.modelo.Tipo;

@ManagedBean
@ViewScoped

public class vacinaController {

    private Vacina vacina = new Vacina();
    private Integer tipoId;
    private Integer ingId;

//	public List<Ingredientes> getIngredientes(){
//		return vacina.getLing();
//	}
//	
//	public void remover(Ingredientes i){
//		this.vacina.getLing().remove(i);
//	}
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

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Vacina getVacina() {
        return vacina;
    }

//	public void gravarIngrediente(){
//		Ingredientes i = new DAO<Ingredientes>(Ingredientes.class).listaPorId(ingId);
//		vacina.addIngredientes(i);
//		ingId = null;
//		
//	}
    public void carregar(Vacina p) {
        this.tipoId = p.getTipo().getId();
        p = new VacinaDAO().listaPorId(p);
        vacina = p;
    }

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

    public List<Vacina> getTodasVacinas() {
        return new DAO<Vacina>(Vacina.class).listaTodos();
    }

    public List<Tipo> getTodosTipos() {
        return new DAO<Tipo>(Tipo.class).listaTodos();
    }

    public void remover(Vacina p) {
        try {

            new DAO<Vacina>(Vacina.class).remove(p.getId());

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("animal", new FacesMessage("Impossivel remover: A vacina consta em algum registro de animal"));
        }
    }

//        //
//	public List<Ingredientes>getIngredientesProduto(){
//		return vacina.getLing();
//	}
//	
//	public List<Ingredientes> getTodosIngredientes(){
//		return new DAO<Ingredientes>(Ingredientes.class).listaTodos();
//	}
//	
//	public List<Vacina> getTodasPizzas(){
//		return new VacinaDAO().listaPizza();
//	}
//	
//	public List<Vacina> getTodosRefri(){
//		return new VacinaDAO().listaRefri();
//	}
//        
    //
}
