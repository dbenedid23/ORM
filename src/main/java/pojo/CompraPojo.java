/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import dao.CompraDAO;
import java.util.List;
import model.Compra;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

/**
 *
 * @author dev
 */
public class CompraPojo implements CompraDAO {

    @Override
    public List<Compra> mostrarAllCompra() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Compra> query = session.createQuery("FROM Compra", Compra.class);//nombre de la clase e indicamos que es una clase.
            return query.getResultList();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public void updateCompra(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(compra);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(e);
        }
    }

    @Override
    public int getCompraBySuministro(String suministro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Compra WHERE suministro LIKE :suministro", Long.class);
            query.setParameter("suministro", "%" + suministro + "%");
            return Math.toIntExact(query.getSingleResult());
        } catch (Exception e) {
            System.err.println(e);
            return 0;
        }
    }

    @Override
    public void addCompra(Compra compra) {
        Transaction ts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ts = session.beginTransaction(); //siempre hay que ponerle esto para iniciar las transacciones
            session.persist(compra);//esto es para el insert
            ts.commit();//y esto cuando queremos acabarlo
        } catch (HibernateException he) {
            if (ts != null) {
                ts.rollback(); //para volver atrás, se pone en el catch no en el finally
            }
            System.err.println(he);
        }
    }

//    @Override
//    public int getId(Compra compra) {
//       
//    }

    @Override
    public Compra getCompra2(String suministro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Compra> query = session.createQuery("SELECT COUNT(*) FROM Compra WHERE suministro LIKE :suministro", Compra.class);
           query.setParameter("suministro", "%" + suministro + "%");
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

}
