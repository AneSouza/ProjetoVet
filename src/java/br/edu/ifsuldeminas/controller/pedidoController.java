package br.edu.ifsuldeminas.controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.edu.ifsuldeminas.dao.CarrinhoDAO;
import br.edu.ifsuldeminas.dao.DAO;
import br.edu.ifsuldeminas.dao.PedidoDAO;
import br.edu.ifsuldeminas.modelo.Carrinho;
import br.edu.ifsuldeminas.modelo.Animal;
import br.edu.ifsuldeminas.modelo.Pedido;
import br.edu.ifsuldeminas.modelo.Pessoa;
import br.edu.ifsuldeminas.modelo.Vacina;

@ManagedBean
@ViewScoped
public class pedidoController {

    private Pedido pedido = new Pedido();
    private Integer qtde;
    private Integer endId;
    private Integer vacinaId;
    private Integer pagId;

    private String login, senha;

/////////////////////////////////// get e set
    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

///////////////////////////////////
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

///////////////////////////////////
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

/////////////////////////////////// get e set classe objeto
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

///////////////////////////////////
    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

///////////////////////////////////
    public Integer getVacinaId() {
        return vacinaId;
    }

    public void setVacinaId(Integer vacinaId) {
        this.vacinaId = vacinaId;
    }

/////////////////////////////////// gravar vacina
    public void gravarCartao() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
        if (this.pedido.getId() == null) {
            new DAO<Pedido>(Pedido.class).adiciona(pedido);
        } else {
            new DAO<Pedido>(Pedido.class).atualiza(pedido);
        }
        this.pedido = new Pedido();

    }

    public void inserir(Pedido pedido) {

        if (pedido.getId() == null) {
            new DAO<Pedido>(Pedido.class).adiciona(pedido);
        } else {
            new DAO<Pedido>(Pedido.class).atualiza(pedido);
        }
        this.pedido = new Pedido();
    }

///////////////////////////////////VERFIF atualiza
//    private void atualiza() {
//        List<Carrinho> listaItens = new CarrinhoDAO().listaPorPedido(pedido);
//        DAO<Carrinho> d = new DAO<Carrinho>(Carrinho.class);
//
//        for (Carrinho i : listaItens) {
//            d.remove(i.getId());
//        }
//        new DAO<Pedido>(Pedido.class).atualiza(pedido);
//
//    }
///////////////////////////////////lista os dados do cartao
    public List<Pedido> getTodosPedidos() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
        return new DAO<Pedido>(Pedido.class).listaTodos();
    }

///////////////////////////////////VERFIF
    public void remover(Pedido c) {
        new DAO<Pedido>(Pedido.class).remove(c.getId());
    }

///////////////////////////////////VERFIF
    public void carregar(Pedido c) {
        c = new PedidoDAO().listaPorId(c);
        pedido = c;

    }

///////////////////////////////////VERFIF
    public List<Animal> getTodosAnimais() {
        FacesContext context = FacesContext.getCurrentInstance();
        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");

        return p.getEnds();

    }
/////////////////////////////////// listar vacina

    public List<Vacina> getTodasVacinas() {
        return new DAO<Vacina>(Vacina.class).listaTodos();
    }

///////////////////////////////////verif
//    public List<Pedido> getTodosCartoes() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        Pessoa p = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");
//
//        return p.getPeds();
//
//    }
}
