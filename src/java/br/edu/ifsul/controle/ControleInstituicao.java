/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleInstituicao")
@SessionScoped
public class ControleInstituicao implements Serializable {


    private InstituicaoDAO dao;
    private Instituicao objeto;

    public ControleInstituicao() {
        dao = new InstituicaoDAO();
    }

    public String listar() {
        return "/privado/instituicao/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Instituicao();
        return "formulario?faces-redirect=true";
    }

    public String salvar() {
        if (dao.salvar(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemInformacao(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public String editar(Integer id) {
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public InstituicaoDAO getDao() {
        return dao;
    }

    public void setDao(InstituicaoDAO dao) {
        this.dao = dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    }

}
