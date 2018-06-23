package br.com.sistemafinanceiro.controller;

import br.com.sistemafinanceiro.dao.StatusDAO;
import br.com.sistemafinanceiro.model.Status;
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
public class StatusController {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Status status;
    private Status statusDigitado;
    private Status statusSelecionado;
    private List<Status> statusTabela;
    private StatusDAO statusDao;

    public StatusController() {
        statusDao = ServiceLocator.getStatusDao();
        statusTabela = ObservableCollections.observableList(new ArrayList<Status>());
        novo();
        pesquisar();
    }

    public void novo() {
        setStatusDigitado(new Status());
    }

    public void pesquisar() {
        statusTabela.clear();
        statusTabela.addAll(statusDao.search(statusDigitado));
    }

    public void salvar() {
        statusDao.createAndUpdate(statusDigitado);
        novo();
        pesquisar();
    }

    public void excluir() {
        statusDao.delete(statusDigitado);
        novo();
        pesquisar();
    }

    public Status getStatusDigitado() {
        return statusDigitado;
    }

    public void setStatusDigitado(Status statusDigitado) {
        Status oldStatusDigitado = this.statusDigitado;
        this.statusDigitado = statusDigitado;
        propertyChangeSupport.firePropertyChange("statusDigitado", oldStatusDigitado, statusDigitado);
    }

    public Status getStatusSelecionado() {
        return statusSelecionado;
    }

    public void setStatusSelecionado(Status statusSelecionado) {
        this.statusSelecionado = statusSelecionado;
        if (this.statusSelecionado != null) {
            setStatusDigitado(statusSelecionado);
        }
    }

    public List<Status> getStatusTabela() {
        return statusTabela;
    }

    public void setStatusTabela(List<Status> statusTabela) {
        this.statusTabela = statusTabela;
    }

    public void addPropertyChangeListener(PropertyChangeListener pl) {
        propertyChangeSupport.addPropertyChangeListener(pl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pl) {
        propertyChangeSupport.removePropertyChangeListener(pl);
    }

}
