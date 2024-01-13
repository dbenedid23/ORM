/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author dev
 */
public class HibernateUtil {
        private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
