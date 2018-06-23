package br.com.sistemafinanceiro.dao;

import br.com.sistemafinanceiro.model.GrupoFinanceiro;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface GrupoFinanceiroDAO {
    
    void delete(GrupoFinanceiro grupo);
    
    List<GrupoFinanceiro> search(GrupoFinanceiro grupo);
    
    void createAndUpdate(GrupoFinanceiro grupo);
}
