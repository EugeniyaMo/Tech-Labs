package ru.itmo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.tools.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AbstractDao {

    private Object entity;

    public AbstractDao(Object entity) {
        this.entity = entity;
    }

    public Object findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Object object = session.get(entity.getClass(), id);
        session.close();
        return object;
    }

    public void save(Object object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(object);
        tx.commit();
        session.close();
    }

    public void update(Object object){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(object);
        tx.commit();
        session.close();
    }

    public void delete(Object object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(object);
        tx.commit();
        session.close();
    }

    public List<Object> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteria = builder.createQuery((Class<Object>) entity.getClass());
        criteria.from(entity.getClass());
        List<Object> objects = session.createQuery(criteria).getResultList();
        session.close();
        return objects;
    }
}
