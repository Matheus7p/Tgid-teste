package br.com.matheus.infrastructure.repository.hibernate;

import br.com.matheus.domain.entity.User;
import br.com.matheus.domain.repository.UserRepository;
import br.com.matheus.infrastructure.configuration.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserRepositoryHibernateImpl implements UserRepository {

    @Override
    public User save(User user){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return user;
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            User userToDelete = session.get(User.class, id);
            if (userToDelete != null) {
                session.remove(userToDelete);
                System.out.println("Usuario deletado: " + userToDelete.getName());
            }
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> findByCpf(String cpf){
       try( Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.createQuery("from User user where user.cpf = :cpf", User.class)
                   .setParameter("cpf", cpf)
                   .uniqueResultOptional();
       }
    }

}
