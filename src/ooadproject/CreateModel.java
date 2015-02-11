/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Manish
 */
public class CreateModel {
    CreateServices createServices;
    EntityManagerFactory emf;
    EntityTransaction transaction;
    private EntityManager manager;
    
    public CreateModel()
    {
        createServices=new CreateServices(manager);
    }
    
    public String[] getuserName(String userName)
    {
        String[] userList=new String[5];
        userList=createServices.getuserName(userName);
        return userList;
    }
    public void sendMessage(String[] array)
    {
        createServices.sendMessage(array);
    }
    
}
