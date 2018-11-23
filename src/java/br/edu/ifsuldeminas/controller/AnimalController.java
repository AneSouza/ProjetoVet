package br.edu.ifsuldeminas.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.modelo.Animal;

@ManagedBean
@ViewScoped
public class AnimalController {

    private Animal end = new Animal();

////////////////////////inserir - atualiza animal	
    public void inserir(Animal END) {

        if (END.getId() == null) {
            new DAO<Animal>(Animal.class).adiciona(END);
        } else {
            new DAO<Animal>(Animal.class).atualiza(END);
        }
        this.end = new Animal();
    }

    	
////////////////////////lista todos animal
    public List<Animal> getTodosAnimais() {
        return new DAO<Animal>(Animal.class).listaTodos();
    }
	
////////////////////////iremove animal
    public void remover(Animal p) {
        new DAO<Animal>(Animal.class).remove(p.getId());
    }

    	
////////////////////////carrega animal
    public void carregar(Animal p) {
        this.end = p;

    }
	
////////////////////////get e set End objeto
    public Animal getEnd() {
        return end;
    }

    public void setEnd(Animal end) {
        this.end = end;
    }

}
