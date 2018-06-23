package br.com.sistemafinanceiro.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Junior
 */
public class Conexao {

    private static EntityManagerFactory emf;
    private static Conexao connection;

    private Conexao() {
        emf = Persistence.createEntityManagerFactory("SistemaFinanceiroPU");
    }

    public static EntityManager getEntityManager() {
        if (connection == null) {
            connection = new Conexao();
        }

        return emf.createEntityManager();
    }
}
