package ru.itmo.kotiki.dao.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.util.HibernateSessionFactoryUtil;

import java.util.List;

public class CatDaoImpl implements CatDao {
    @Override
    public Cat findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);
    }

    @Override
    public void save(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public Owner findOwnerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    @Override
    public List<Cat> findAll() {
        List<Cat> cats = (List<Cat>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From cat").list();
        return cats;
    }
}
