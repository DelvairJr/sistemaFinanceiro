package br.com.sistemafinanceiro.dao;

import br.com.sistemafinanceiro.model.Despesas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Junior
 */
public class DespesasDaoImpl implements DespesasDAO {

    private static EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("SistemaFinanceiroPU");
        }

        return emf.createEntityManager();
    }
    
    @Override
    public void delete(Despesas despesas) {
        
        EntityManager em = Conexao.getEntityManager();

        em.getTransaction().begin();

        despesas = em.merge(despesas);
        em.remove(despesas);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public List<Despesas> search(Despesas despesas) {
         EntityManager em = Conexao.getEntityManager();

        StringBuilder sql = new StringBuilder("from Despesas as d where 1 = 1 ");

        if (despesas.getId()!= null) {
            sql.append("and d.id = :id ");
        }
        if (despesas.getDescricao()!= null && !despesas.getDescricao().equals("")) {
            sql.append("and d.descricao like :descricao");
        }

        Query query = em.createQuery(sql.toString());

        if (despesas.getId() != null) {
            query.setParameter("id", despesas.getId());
        }

        if (despesas.getDescricao()!= null && !despesas.getDescricao().equals("")) {
            query.setParameter("descricao", "%" + despesas.getDescricao()+ "%");
        }

        return query.getResultList();
    }

    @Override
    public void createAndUpdate(Despesas despesas) {
        EntityManager em = Conexao.getEntityManager();

        em.getTransaction().begin();

        if (despesas.getId() != null) {
            em.merge(despesas);
        }

        em.persist(despesas);
        em.getTransaction().commit();
        em.close();
    }

}
