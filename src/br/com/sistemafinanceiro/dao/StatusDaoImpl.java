package br.com.sistemafinanceiro.dao;

import br.com.sistemafinanceiro.model.Status;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Junior
 */
public class StatusDaoImpl implements StatusDAO {

    @Override
    public void delete(Status status) {
        EntityManager em = Conexao.getEntityManager();

        em.getTransaction().begin();
        status = em.merge(status);
        em.remove(status);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Status> search(Status status) {
        EntityManager em = Conexao.getEntityManager();

        StringBuilder sql = new StringBuilder("from Status as s where 1 = 1 ");

        if (status.getId() != null) {
            sql.append("and s.id = :id ");
        }
        if (status.getTipo() != null && !status.getTipo().equals("")) {
            sql.append("and s.tipo like :tipo");
        }

        Query query = em.createQuery(sql.toString());

        if (status.getId() != null) {
            query.setParameter("id", status.getId());
        }

        if (status.getTipo() != null && !status.getTipo().equals("")) {
            query.setParameter("tipo", "%" + status.getTipo() + "%");
        }

        return query.getResultList();
    }

    @Override
    public void createAndUpdate(Status status) {
        EntityManager em = Conexao.getEntityManager();

        em.getTransaction().begin();

        if (status.getId() != null) {
            em.merge(status);
        }

        em.persist(status);
        em.getTransaction().commit();
        em.close();

    }

}
