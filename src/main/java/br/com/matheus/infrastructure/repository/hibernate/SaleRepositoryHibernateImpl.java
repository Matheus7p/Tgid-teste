package br.com.matheus.infrastructure.repository.hibernate;

import br.com.matheus.domain.entity.Sale;
import br.com.matheus.domain.repository.SaleRepository;
import br.com.matheus.infrastructure.configuration.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SaleRepositoryHibernateImpl implements SaleRepository {

    @Override
    public Sale save(Sale sale){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(sale);
            transaction.commit();
            return sale;
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Sale> findById(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Sale sale = session.get(Sale.class, id);
            return Optional.ofNullable(sale);
        }
    }

    @Override
    public List<Sale> findByUser(Long user_id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("From Sale sale WHERE sale.user.id = :user_id", Sale.class)
                    .setParameter("user_id", user_id)
                    .list();
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
