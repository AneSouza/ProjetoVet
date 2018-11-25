package br.edu.ifsuldeminas.utils;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.edu.ifsuldeminas.modelo.Pessoa;

public class Autorizador implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
        // Obt�m contexto da aplica��o 
        FacesContext context = event.getFacesContext();
        // Obt�m o nome da p�gina que est� sendo chamada
        String nomePagina = context.getViewRoot().getViewId();

        // System.out.println(nomePagina);
        // se for a p�gina de login, o usu�rio pode acessar 
        if ("/conta2.xhtml".equals(nomePagina)) {
            return;
        }

        if (nomePagina.equals("/index.xhtml")) {
            return;
        } else if (nomePagina.equals("/alerta.xhtml")) {
            return;
        } else if (nomePagina.equals("/sobre.xhtml")) {
            return;
        } else if (nomePagina.equals("/informacao.xhtml")) {
            return;
        } else if (nomePagina.equals("/curiosidades.xhtml")) {
            return;
        } else if (nomePagina.equals("/conta2.xhtml")) {
            return;
        }

        // Obtem usuario da sessao
        Pessoa user
                = (Pessoa) context.getExternalContext().getSessionMap().get("usuariologado");

        // se ha usuario logado, ele pode acessar as seguintes paginas
        if (user != null) {

            if (nomePagina.equals("/animal.xhtml")) {
                return;
            } else if (nomePagina.equals("/pedido.xhtml")) {
                return;
            } else if (nomePagina.equals("/vacina.xhtml")) {
                return;
            } else if (nomePagina.equals("/alerta2.xhtml")) {
                return;
            } else if (nomePagina.equals("/cadVacina.xhtml")) {
                return;
            } else if (nomePagina.equals("/enderecos.xhtml")) {
                return;
            } else if (nomePagina.equals("/tipo.xhtml")) {
                return;
            } else if (nomePagina.equals("/minha-conta.xhtml")) {
                return;
            } else if (nomePagina.equals("/notificacao.xhtml")) {
                return;
            }

                Boolean permit = user.getPermissao();

                if (permit == true) {
                    return;
                }

                NavigationHandler handler = context.getApplication().getNavigationHandler();
                handler.handleNavigation(context, null, "/index?faces-redirect=true");
                context.renderResponse();
                return;
            }

            // se nao ha, o usuario e redirecionado para o login
            NavigationHandler handler = context.getApplication().getNavigationHandler();
            handler.handleNavigation(context, null, "/conta2?faces-redirect=true");
            context.renderResponse();
        }

        @Override
        public void beforePhase
        (PhaseEvent arg0
        
        
        ) {
    }

    @Override
        public PhaseId getPhaseId
        
            () {
        return PhaseId.RESTORE_VIEW; // o autorizador ser� executado na fase restore_view
        }

    }
