package br.edu.ifsuldeminas.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.PessoaDAO;
import br.edu.ifsuldeminas.modelo.Animal;
import br.edu.ifsuldeminas.modelo.Ingredientes;
import br.edu.ifsuldeminas.modelo.Pessoa;
import br.edu.ifsuldeminas.modelo.Vacina;
import br.edu.ifsuldeminas.utils.Utils;

@ManagedBean
@ViewScoped
public class pessoaController {

    private Pessoa pessoa = new Pessoa();
    private Animal end = new Animal();
    private String busca = null;
    private Boolean mostar = false;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void inserir() {
        try {

            this.pessoa.setSenha(Utils.toMD5(this.pessoa.getSenha()));
            this.pessoa.getEnds().add(end);
            this.pessoa.setPermissao(false);
            this.end.setPessoa(this.pessoa);
            if (this.pessoa.getId() == null) {
                new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);

            } else {
                new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);
            }
            this.pessoa = new Pessoa();
            this.end = new Animal();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("conta2", new FacesMessage("Este login ja existe!"));
        }
    }

    public void addAnimal() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");

        if (this.end.getId() == null) {
            p.getEnds().add(end);
            end.setPessoa(p);
            new DAO<Animal>(Animal.class).adiciona(end);
            System.out.println("--------------------------> Add");
        } else {
            new DAO<Animal>(Animal.class).atualiza(this.end);
            System.out.println("--------------------------> Up");
        }
        this.end = new Animal();
    }

    public void removeAnimal(Animal end) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
            new DAO<Animal>(Animal.class).remove(end.getId());
            p.getEnds().remove(end);

        } catch (PersistenceException e) {
            FacesContext.getCurrentInstance().addMessage("animais", new FacesMessage("Impossivel remover, Animal ja consta!"));
            e.printStackTrace();
        }
    }

    public void atualizaAnimal(Animal animal) {
        this.end = animal;

    }

    public List<Pessoa> getTodosPessoa() {
        return new DAO<Pessoa>(Pessoa.class).listaTodos();
    }

    public void remover(Pessoa p) {
        new DAO<Pessoa>(Pessoa.class).remove(p.getId());
    }

    public void carregar(Pessoa p) {
        this.pessoa = p;

    }

    public List<Pessoa> getUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
        LinkedList<Pessoa> lista = new LinkedList<Pessoa>();
        lista.add(p);
        return lista;
    }

    public Animal getEnd() {
        return end;
    }

    public void setEnd(Animal end) {
        this.end = end;
    }

    public List<Animal> getAnimaisPessoa() {
        return pessoa.getEnds();
    }

    public List<Pessoa> getTodasPessoas() {
        return new DAO<Pessoa>(Pessoa.class).listaTodos();
    }

    public void buscar() {

        PessoaDAO pdao = new PessoaDAO();
        System.out.println("\n\n\n\nAQUI_>>>>>>>>>>>>>>>>>>>>>>>" + this.busca + "!!!!!!!!!!!!!!!!\n\n\n\n");
        Pessoa p = pdao.buscarPessoa(this.busca);

        System.out.println("@@@@@@@@@@@" + p.getEnds().get(0) + "!!!!!!!!!!!!!!!!!!!!!");

    }

    public List<Animal> getTodosAnimais() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");

        return p.getEnds();

    }

    public void atualizaPessoa() {
        this.setMostar(true);
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
        p.setSenha(null);
        this.pessoa = p;

    }

    public void alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
        String senha = Utils.toMD5(this.pessoa.getSenha());
        if (p.getSenha().equals(senha)) {
            new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);
        } else {
            this.pessoa.setSenha(Utils.toMD5(this.pessoa.getSenha()));
            new DAO<Pessoa>(Pessoa.class).atualiza(this.pessoa);

        }

    }

    public Boolean mostrar() {
        return this.mostar;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;

    }

    public Boolean getMostar() {
        return mostar;
    }

    public void setMostar(Boolean mostar) {
        this.mostar = mostar;
    }

}
