/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ooadproject.EntityTables.User;
import ooadproject.EntityTables.Communication;

/**
 *
 * @author Manish
 */
public class CreateServices {
    private static EntityManager manager;
    Communication comm;
    
    public CreateServices(EntityManager manager)
    {
        this.manager=manager;
        
    }
    
    public String[] getuserName(String userName)
    {
        String[] userList=new String[5];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     EntityManager manager = emf.createEntityManager();
     EntityTransaction transaction = manager.getTransaction();
     transaction.begin();
     TypedQuery<User> query = manager.createQuery("SELECT U FROM user U where U.name LIKE :cnum ", User.class);
        String name="%"+userName+"%";
        query.setParameter("cnum", name);
        transaction.commit();
         List<User> result= query.getResultList();
         int x=0;
         for(User u:result)
         {
             userList[x]=u.getName();
             x++;
             
         }
         return userList;
    }
    public void sendMessage(String[] array)
    {
        try
        {
        User u=new User();
        comm=new Communication();
        
      String[] userList=new String[5];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     EntityManager manager = emf.createEntityManager();
     EntityTransaction transaction = manager.getTransaction();
     transaction.begin();
     TypedQuery<User> query = manager.createQuery("SELECT U FROM user U where U.userId=:cnum ", User.class);
     query.setParameter("cnum", array[0]);
          TypedQuery<User> query1 = manager.createQuery("SELECT U FROM user U where U.name=:cnum ", User.class);
     query1.setParameter("cnum", array[2]);
    
         List<User> result= query.getResultList();
         List<User> result1= query1.getResultList();
         for(User u1:result)
         {
             comm.setSenderId(u1);
         }
        comm.setSubject(array[1]);
         for(User u2:result1)
         {
             comm.setReceiverId(u2.getUserId());
         }
        
        comm.setMessageBody(array[3]);
          DateFormat dt = new SimpleDateFormat("yyyy-MM-dd" + " " + "00:00:00");
        Date dt1 = dt.parse(array[4]);
        comm.setDate(dt1);
        
        manager.persist(comm);
         transaction.commit();
        }
        catch(ParseException ex)
        {
            ex.getMessage();
        }
        
    }
    
}
