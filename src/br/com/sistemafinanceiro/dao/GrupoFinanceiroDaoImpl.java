package br.com.sistemafinanceiro.dao;

import br.com.sistemafinanceiro.model.GrupoFinanceiro;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Junior
 */
public class GrupoFinanceiroDaoImpl implements GrupoFinanceiroDAO {

    private static EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SistemaFinanceiroPU");
        }

        return emf.createEntityManager();
    }
    
    @Override
    public void delete(GrupoFinanceiro grupo) {
        EntityManager em = Conexao.getEntityManager();

        em.getTransaction().begin();
        grupo = em.merge(grupo);
        em.remove(grupo);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<GrupoFinanceiro> search(GrupoFinanceiro grupo) {
        EntityManager em = Conexao.getEntityManager();

        StringBuilder sql = new StringBuilder("from GrupoFinanceiro as g where 1 = 1 ");

        if (grupo.getId()!= null) {
            sql.append("and g.id = :id ");
        }
        if (grupo.getNome() != null && !grupo.getNome().equals("")) {
            sql.append("and g.nome like :nome");
        }

        Query query = em.createQuery(sql.toString());

        if (grupo.getId() != null) {
            query.setParameter("id", grupo.getId());
        }

        if (grupo.getNome() != null && !grupo.getNome().equals("")) {
            query.setParameter("nome", "%" + grupo.getNome() + "%");
        }

        return query.getResultList();

    }

    @Override
    public void createAndUpdate(GrupoFinanceiro grupo) {
        EntityManager em = Conexao.getEntityManager();

        em.getTransaction().begin();

        if (grupo.getId() != null) {
            em.merge(grupo);
        }

        em.persist(grupo);
        em.getTransaction().commit();
        em.close();

    }   

}
