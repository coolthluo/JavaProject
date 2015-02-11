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
public class InboxServices {
    
    private static EntityManager manager;
    String[][] result1;
    
    public InboxServices(EntityManager manager)
    {
        this.manager=manager;
    }
    public String[][] readInboxData(String loginId)
    {
        result1=new String[InboxPage.length][5];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     EntityManager manager = emf.createEntityManager();
     EntityTransaction transaction = manager.getTransaction();
     transaction.begin();
     
    
        TypedQuery<Communication> query = manager.createQuery("SELECT C FROM communication C where C.receiverId=:cnum ", Communication.class);
        
        query.setParameter("cnum", loginId);
        transaction.commit();
         List<Communication> result= query.getResultList();
     
        
        
        int x=0;
        for(Communication c:result)
        {
            result1[x][0]=c.getSenderId().getName();
            result1[x][1]=c.getSubject();
            result1[x][2]=c.getMessageBody();
            result1[x][3]=c.getDate().toString();
            result1[x][4]=c.getReceiverId();
            x++;
        }
        InboxPage.length=result.size();
    
        return result1;
        
    }
    public static void main(String args[])
    {
        InboxServices inbox=new InboxServices(manager);
        //inbox.readInboxData("W1147112");
    }
    
}
