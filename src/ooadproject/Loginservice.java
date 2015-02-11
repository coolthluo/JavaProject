/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import ooadproject.EntityTables.User;
import ooadproject.EntityTables.Role;

/**
 *
 * @author Manish
 */
public class Loginservice {
    private EntityManager manager;
    public Loginservice()
    {
        
    }
    public Loginservice(EntityManager manager) {
this.manager = manager;
}
    
    public String[] readInfo(String array)
    {
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
     EntityManager manager = emf.createEntityManager();
     EntityTransaction transaction = manager.getTransaction();
     transaction.begin();
     String[] result1=new String[5];
        TypedQuery<User> query = manager.createQuery("SELECT T FROM user T where T.userId=:cnum ", User.class);
        
        query.setParameter("cnum",array );
        
        transaction.commit();
        List<User> result = query.getResultList();
        int x=0;
        for(User u:result)
        {
            
            result1[x]=u.getPassword();
            List<Role> role=(List<Role>)u.getRoleCollection();
            for(Role r:role)
            {
                x++;
                result1[x]=r.getRole();
            }
            x++;
            result1[x]=u.getName();
        }
        //Userinfo info1=(Userinfo)info;
        
        return result1;
    }
    public static void main(String[] args)
    {
        Loginservice log=new Loginservice();
    // Object a=log.readInfo("W1147112");
    }
    
    
}
