package br.com.sistemafinanceiro.dao;

import br.com.sistemafinanceiro.model.Status;
import java.util.List;

/**
 *
 * @author Junior
 */
public interface StatusDAO {

    void delete(Status status);

    List<Status> search(Status status);

    void createAndUpdate(Status status);
}
