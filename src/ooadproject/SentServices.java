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
import javax.persistence.TypedQuery;
import ooadproject.EntityTables.Communication;
import ooadproject.EntityTables.User;

/**
 *
 * @author Manish
 */
public class SentServices {
      private static EntityManager manager;
    String[][] result1;
    
    public SentServices(EntityManager manager)
    {
        this.manager=manager;
    }
     public String[][] readSentData(String loginId)
    {
        result1=new String[SentItemPage.length][5];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     EntityManager manager = emf.createEntityManager();
     EntityTransaction transaction = manager.getTransaction();
     transaction.begin();
      TypedQuery<User> query = manager.createQuery("SELECT U FROM user U JOIN U.communicationCollection C on U=C.senderId where  U.userId=:cnum", User.class);
        
        query.setParameter("cnum", loginId);
        transaction.commit();
         List<User> result= query.getResultList();
     
        
        
        int x=0;
        for(User u:result)
        {
            List<Communication> res=(List<Communication>) u.getCommunicationCollection();
            for(Communication c:res)
            {
            result1[x][0]=c.getSenderId().getName();
            result1[x][1]=c.getSubject();
            result1[x][2]=c.getMessageBody();
            result1[x][3]=c.getDate().toString();
             TypedQuery<User> query1 = manager.createQuery("SELECT U.name FROM user U  where  U.userId=:cnum", User.class);
        
        query.setParameter("cnum", c.getReceiverId());
     transaction.begin();
        List<User> result2= query.getResultList();
        transaction.commit();
            result1[x][4]=result2.get(0).getName();
            x++;
            }
            SentItemPage.length=res.size();
        }
        
        return result1;
    }
}
