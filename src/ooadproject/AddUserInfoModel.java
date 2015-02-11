/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;


import ooadproject.EntityTables.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author Coolth
 */
public class AddUserInfoModel {
    List<User> adduserInfoResultList;// stores the model data in a List
//    List<Budget> budgetInfoResultList;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA 
    private User userinfo;
    private AddUserInfoService adduserinfoservice;
    
    private int numcols, numrows;
    
    public AddUserInfoModel(){
            userinfo = new User();    
            adduserInfoResultList = new ArrayList<>();
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            manager = factory.createEntityManager();
            adduserinfoservice = new AddUserInfoService(manager);
            numcols = userinfo.getNumberOfColumns();
    }
    public AddUserInfoModel(List<User> list, EntityManager em){
        adduserInfoResultList = list;
        numrows = adduserInfoResultList.size();
        userinfo = new User();
        numcols = userinfo.getNumberOfColumns();
        manager = em;
        adduserinfoservice = new AddUserInfoService(manager);
    }
    
    public void addRow(String[] array) {
            adduserinfoservice.addUser(array);
	}
    

}
