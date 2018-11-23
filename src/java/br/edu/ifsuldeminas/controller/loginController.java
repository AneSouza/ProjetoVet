package br.edu.ifsuldeminas.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.edu.ifsuldeminas.dao.PessoaDAO;
import br.edu.ifsuldeminas.modelo.Pessoa;

@ManagedBean
@ViewScoped
public class loginController {

    private Pessoa user = new Pessoa();

///////////////////// get e set user objeto
    public Pessoa getUser() {
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

///////////////////// funcionalidade logaar
    public String logar() {

        user = new PessoaDAO().buscarPorEmailESenha(user.getLogin(), user.getSenha());
        FacesContext context = FacesContext.getCurrentInstance();
        if (user != null) {//logou
            context.getExternalContext().getSessionMap().put("usuariologado", user);
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage("Seja bem-vindo (a) " + user.getNome()));
            return "pedido?faces-redirect=true";
        } else {
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage("Login ou Senha Incorretos"));
            return "index?faces-redirect=true";
        }

    }

///////////////////// func. deslogar
    public String deslogar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Fique de olho nas vacinas!"));
        context.getExternalContext().getSessionMap().remove("usuariologado");
        return "index?faces-redirect=true";

    }

///////////////////// verifica acesso
    public Boolean temAcesso(String page) {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa user = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
        if (user == null) {
            return false;
        }
        Boolean permit = user.getPermissao();

        if (permit == true) {
            return true;
        } else {
            return false;
        }

    }

}
