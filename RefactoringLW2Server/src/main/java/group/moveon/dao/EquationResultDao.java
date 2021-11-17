package group.moveon.dao;

import group.moveon.models.EquationResult;
import group.moveon.models.User;
import group.moveon.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EquationResultDao {

    public EquationResultDao() {
    }

    public void addEquationResult(EquationResult equationResult) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(equationResult);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public EquationResult getEquationResultById(int id) {
        Transaction transaction = null;
        EquationResult result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.find(EquationResult.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public List<Integer> getEquationResultByUserName(User user) {
        Transaction transaction = null;
        List equationIds = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select id from EquationResult E where E.user = :user");
            query.setParameter("user", user);
            equationIds = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return equationIds;
    }

}
