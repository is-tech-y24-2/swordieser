package ru.itmo.kotiki.dao.implementation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import ru.itmo.kotiki.util.HibernateSessionFactoryUtil;

import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public Owner findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    @Override
    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public Cat findCatById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Cat.class, id);

    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = (List<Owner>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From cat").list();
        return owners;
    }
}
