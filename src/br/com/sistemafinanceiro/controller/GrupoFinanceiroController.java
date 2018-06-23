package br.com.sistemafinanceiro.controller;

import br.com.sistemafinanceiro.dao.GrupoFinanceiroDAO;
import br.com.sistemafinanceiro.model.GrupoFinanceiro;
import br.com.sistemafinanceiro.service.ServiceLocator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Junior
 */
public class GrupoFinanceiroController {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private GrupoFinanceiro grupo;
    private GrupoFinanceiro grupoDigitado;
    private GrupoFinanceiro grupoSelecionado;
    private List<GrupoFinanceiro> grupoTabela;
    private GrupoFinanceiroDAO grupoDao;

    public GrupoFinanceiroController() {
        grupoDao = ServiceLocator.getGrupoFinanceiroDao();
        grupoTabela = ObservableCollections.observableList(new ArrayList<GrupoFinanceiro>());
        novo();
        pesquisar();
    }

    public void novo() {
        setGrupoDigitado(new GrupoFinanceiro());
    }

    public void pesquisar() {
        grupoTabela.clear();
        grupoTabela.addAll(grupoDao.search(grupoDigitado));
    }

    public void salvar() {
        grupoDao.createAndUpdate(grupoDigitado);
        novo();
        pesquisar();
    }

    public void excluir() {
        grupoDao.delete(grupoDigitado);
        novo();
        pesquisar();
    }

    public GrupoFinanceiro getGrupoDigitado() {
        return grupoDigitado;
    }

    public void setGrupoDigitado(GrupoFinanceiro grupoDigitado) {
        GrupoFinanceiro oldGrupoDigitado = this.grupoDigitado;
        this.grupoDigitado = grupoDigitado;
        propertyChangeSupport.firePropertyChange("grupoDigitado", oldGrupoDigitado, grupoDigitado);
    }

    public GrupoFinanceiro getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(GrupoFinanceiro grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
        if (this.grupoSelecionado != null) {
            setGrupoDigitado(grupoSelecionado);
        }
    }

    public List<GrupoFinanceiro> getGrupoTabela() {
        return grupoTabela;
    }

    public void setGrupoTabela(List<GrupoFinanceiro> grupoTabela) {
        this.grupoTabela = grupoTabela;
    }

    public void addPropertyChangeListener(PropertyChangeListener pl) {
        propertyChangeSupport.addPropertyChangeListener(pl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pl) {
        propertyChangeSupport.removePropertyChangeListener(pl);
    }

}
