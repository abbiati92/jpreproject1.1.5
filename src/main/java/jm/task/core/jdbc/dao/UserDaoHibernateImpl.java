package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }
    SessionFactory sessionFactory = getSessionFactory();

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(45), " +
                    "lastName VARCHAR(45), " +
                    "age TINYINT)";
            transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }


    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()){
            String sql = "DROP TABLE IF EXISTS users";
            transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery(sql, User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").getResultList();
            transaction.commit();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM User").getResultList();

            for(User user : users) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }
}
