/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_fastfood;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author semih
 */
public class SqlProductProcess {

    private Product product;
    private Transaction tx;
    private Session session;
    private SessionFactory sFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    public SqlProductProcess() {

    }

    public SqlProductProcess(Product product) {
        this.product = product;
    }

    public void list(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            List<Product> products = session.createQuery("from Product", Product.class).getResultList();

            for (Product product : products) {
                model.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getContant()});
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Product getProduct(int id) {
        Product product = new Product();

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            product = session.createQuery("from Product WHERE id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return product;
    }

    public boolean isThereProduct(int id) {
        boolean isThere = false;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            long count = session.createQuery("SELECT COUNT(id) FROM Product WHERE id=:id", Long.class)
                    .setParameter("id", id)
                    .getSingleResult();

            tx.commit();

            if (count > 0) {
                isThere = true;
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isThere;
    }

    public boolean add() {
        boolean isComplate = false;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            session.persist(product);

            tx.commit();

            isComplate = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }

    public boolean update() {
        boolean isComplate = false;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            session.merge(product);

            tx.commit();

            isComplate = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }

    public boolean delete() {
        boolean isComplate = false;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            session.remove(product);

            tx.commit();

            isComplate = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }
}
