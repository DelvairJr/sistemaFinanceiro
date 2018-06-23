package br.com.sistemafinanceiro.controller;

import br.com.sistemafinanceiro.dao.DespesasDAO;
import br.com.sistemafinanceiro.model.Despesas;
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
public class DespesasController {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Despesas despesasDigitado;
    private Despesas despesasSelecionado;
    private List<Despesas> despesasTabela;
    private DespesasDAO despesasDao;
    private GrupoFinanceiro gpFinanceiro;

    public DespesasController() {
        despesasDao = ServiceLocator.getDespesasDao();
        despesasTabela = ObservableCollections.observableList(new ArrayList<Despesas>());
        novo();
        pesquisar();
    }

    public void novo() {
        setDespesasDigitado(new Despesas());
    }

    public void pesquisar() {
        despesasTabela.clear();
        despesasTabela.addAll(despesasDao.search(despesasDigitado));
    }

    public void salvar() {
        despesasDao.createAndUpdate(despesasDigitado);
        novo();
        pesquisar();
    }

    public void excluir() {
        despesasDao.delete(despesasDigitado);
        novo();
        pesquisar();
    }

    public Despesas getDespesasDigitado() {
        return despesasDigitado;
    }

    public void setDespesasDigitado(Despesas despesasDigitado) {
        Despesas oldDespesaDigitado = this.despesasDigitado;
        this.despesasDigitado = despesasDigitado;
        propertyChangeSupport.firePropertyChange("despesasDigitado", oldDespesaDigitado, despesasDigitado);
    }

    public Despesas getDespesasSelecionado() {
        return despesasSelecionado;
    }

    public void setDespesasSelecionado(Despesas despesasSelecionado) {
        this.despesasSelecionado = despesasSelecionado;
        if (this.despesasSelecionado != null) {
            setDespesasDigitado(despesasSelecionado);
        }
    }

    public List<Despesas> getDespesasTabela() {
        return despesasTabela;
    }

    public void setDespesasTabela(List<Despesas> despesasTabela) {
        this.despesasTabela = despesasTabela;
    }

    public GrupoFinanceiro getGpFinanceiro() {
        return gpFinanceiro;
    }

    public void setGpFinanceiro(GrupoFinanceiro gpFinanceiro) {
        this.gpFinanceiro = gpFinanceiro;
    }

    
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        propertyChangeSupport.addPropertyChangeListener(pl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pl) {
        propertyChangeSupport.removePropertyChangeListener(pl);
    }

}
