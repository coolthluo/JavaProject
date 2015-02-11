/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import ooadproject.EntityTables.User;

/**
 *
 * @author Manish
 */
public class LoginTableModel {
    private EntityManager manager;
    Loginservice service;
    EntityManagerFactory emf;
    EntityTransaction transaction;
    public LoginTableModel()
    {
    service=new Loginservice(manager);
    }
      public LoginTableModel(List<User> list, EntityManager em) {
     manager = em;
     service = new Loginservice(manager);
}
    public String[] validateInfo(String array)
    {
         emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     manager = emf.createEntityManager();
      transaction = manager.getTransaction();
     transaction.begin();
         String[] result=new String[5];
        result=service.readInfo(array);
         transaction.commit();
         
         return result;
    }
    
}
