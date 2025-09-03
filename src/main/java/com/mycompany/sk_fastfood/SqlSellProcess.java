/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_fastfood;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author semih
 */
public class SqlSellProcess {

    private Transaction tx;
    private Session session;
    private SessionFactory sFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClasses(SellProduct.class, SellProductInfo.class, SellProductInfoId.class, Product.class)
            .buildSessionFactory();

    public void list(JTable table, LocalDate date) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            List<Object[]> obj = session.createQuery("SELECT s.id, s.date, s.payType, SUM(i.productPrice * i.amount) AS TotalPrice, COUNT(i.amount), s.complate  FROM SellProductInfo i JOIN i.sell s WHERE s.complate = :bool AND s.date = :date GROUP BY s.id, s.date, s.payType, s.complate ORDER BY s.id")
                    .setParameter("bool", false)
                    .setParameter("date", date)
                    .getResultList();

            for (Object[] o : obj) {
                model.addRow(new Object[]{o[0], o[1], o[2], o[3], o[4], o[5]});
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public void listHistory(JTable table, LocalDate date) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            List<Object[]> obj = session.createQuery("SELECT s.id, s.date, s.payType, SUM(i.productPrice * i.amount) AS TotalPrice, COUNT(i.amount), s.complate  FROM SellProductInfo i JOIN i.sell s WHERE s.date = :date GROUP BY s.id, s.date, s.payType, s.complate ORDER BY s.id")
                    .setParameter("date", date)
                    .getResultList();

            for (Object[] o : obj) {
                model.addRow(new Object[]{o[0], o[1], o[2], o[3], o[4], o[5]});
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Object[]> getPayInfo(int id) {
        List<Object[]> objects = null;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            objects = session.createQuery("SELECT s.id,s.takeMoney, s.giveMoney, SUM(i.productPrice * i.amount), s.payType FROM SellProductInfo i JOIN i.sell s WHERE s.id = :id GROUP BY s.giveMoney, s.takeMoney, s.payType, s.id")
                    .setParameter("id", id)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return objects;
    }

    public List<Object[]> getProductList(int id) {
        List<Object[]> objects = null;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            objects = session.createQuery("SELECT p.id, p.name, i.amount, i.comment FROM SellProductInfo i JOIN i.product p JOIN i.sell s WHERE s.id = :id")
                    .setParameter("id", id)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return objects;
    }

    public List<Object[]> getProduct(int sId, int pId) {
        List<Object[]> objects = null;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            objects = session.createQuery("SELECT p.id, p.name, p.contant, i.amount, i.comment, i.productPrice FROM SellProductInfo i JOIN i.product p JOIN i.sell s WHERE s.id = :sId AND p.id = :pId")
                    .setParameter("sId", sId)
                    .setParameter("pId", pId)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return objects;
    }

    public boolean add(float takeMoney, float giveMoney, String payType, ArrayList<SellProductInfo> productList) {
        boolean isComplate = false;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            SellProduct sellProdut = new SellProduct();
            sellProdut.setDate(LocalDate.now());
            sellProdut.setGiveMoney(giveMoney);
            sellProdut.setTakeMoney(takeMoney);
            sellProdut.setPayType(payType);

            session.persist(sellProdut);
            int sellID = sellProdut.getId();

            for (SellProductInfo sellInfo : productList) {
                sellInfo.setId(new SellProductInfoId(sellID, sellInfo.getProduct().getId()));
                sellInfo.setSell(sellProdut);
                sellInfo.setProductPrice(sellInfo.getProduct().getPrice());
                session.persist(sellInfo);
            }

            tx.commit();
            isComplate = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }

    public boolean updateComplate(int id) {
        boolean isComplate = false;

        try {
            session = sFactory.openSession();
            tx = session.beginTransaction();

            SellProduct product = session.get(SellProduct.class, id);
            product.setComplate(true);

            session.merge(product);

            tx.commit();

            isComplate = true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }
}
