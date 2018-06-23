package br.com.sistemafinanceiro.service;

import br.com.sistemafinanceiro.dao.DespesasDAO;
import br.com.sistemafinanceiro.dao.DespesasDaoImpl;
import br.com.sistemafinanceiro.dao.GrupoFinanceiroDAO;
import br.com.sistemafinanceiro.dao.GrupoFinanceiroDaoImpl;
import br.com.sistemafinanceiro.dao.StatusDAO;
import br.com.sistemafinanceiro.dao.StatusDaoImpl;

/**
 *
 * @author Junior
 */
public class ServiceLocator {

    public static StatusDAO getStatusDao() {
        return new StatusDaoImpl();
    }

    public static GrupoFinanceiroDAO getGrupoFinanceiroDao() {
        return new GrupoFinanceiroDaoImpl();
    }

    public static DespesasDAO getDespesasDao() {
        return new DespesasDaoImpl();
    }
}
