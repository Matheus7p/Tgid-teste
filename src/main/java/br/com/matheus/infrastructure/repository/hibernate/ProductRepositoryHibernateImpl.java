package br.com.matheus.infrastructure.repository.hibernate;

import br.com.matheus.domain.entity.Product;
import br.com.matheus.domain.repository.ProductRepository;
import br.com.matheus.infrastructure.configuration.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryHibernateImpl implements ProductRepository {

    @Override
    public void save(Product product){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Product productToDelete = session.get(Product.class, id);

            if(productToDelete != null) {
                session.remove(productToDelete);
                System.out.println("Produto removido: " + productToDelete);
            }
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
         }
    }

    public Optional<Product> findById(Long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            return Optional.ofNullable(product);
        }
    }

    @Override
    public Optional<Product> findByName(String product_name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.createQuery("from Product product WHERE product.name =:product_name", Product.class)
                   .setParameter("product_name", product_name)
                   .uniqueResultOptional();
        }
    }

    @Override
    public List<Product> findAllProduct(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("From Product", Product.class).list();

        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
