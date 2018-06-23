package br.com.sistemafinanceiro.dao;

import br.com.sistemafinanceiro.model.Despesas;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface DespesasDAO {
    
    void delete(Despesas despesas);
    
    List<Despesas> search(Despesas despesas);
    
    void createAndUpdate(Despesas despesas);
}
