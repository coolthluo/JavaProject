/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Manish
 */
public class InboxModel {
    InboxServices inboxServices;
    EntityManagerFactory emf;
    EntityTransaction transaction;
    private EntityManager manager;
    String[][] result;
    
    public InboxModel()
    {
       inboxServices=new InboxServices(manager);
    }
    
    public String[][] getInbox(String loginId)
    {
         emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     manager = emf.createEntityManager();
      transaction = manager.getTransaction();
     transaction.begin();
     result=new String[InboxPage.length][5];
        result=inboxServices.readInboxData(loginId);
        transaction.commit();
        return result;
        
    }
    
}
