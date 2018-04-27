/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "controleAluno")
@SessionScoped
public class ControleAluno implements Serializable {


    private AlunoDAO dao;
    private Aluno objeto;

    public ControleAluno() {
        dao = new AlunoDAO();
    }

    public String listar() {
        return "/privado/aluno/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Aluno();
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

    public AlunoDAO getDao() {
        return dao;
    }

    public void setDao(AlunoDAO dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

}
