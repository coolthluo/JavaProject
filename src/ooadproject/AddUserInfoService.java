/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import ooadproject.EntityTables.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ooadproject.EntityTables.User;

/**
 *
 * @author Coolth
 */
public class AddUserInfoService {
    private EntityManager manager;
    public AddUserInfoService(EntityManager manager) {
        this.manager = manager;
    }
     public void addUser(String[] array) {
            
         try{
             manager.getTransaction().begin();
             User user = new User();         
             user.setUserId(array[0]);
             user.setPassword(array[2]);
             user.setName(array[1]);
             user.setEmail(array[3]);
             Role role = new Role();
             role.setUserId(user);
             role.setRole(array[4]);
//             user.getRoleCollection().iterator().next().setRole(array[4]);
             manager.persist(user);
             manager.persist(role);
             manager.getTransaction().commit();
         } catch(Exception e){
             e.printStackTrace();
             System.out.println("Duplicate key");
         }
    }
    public List<User> readAll() {
	TypedQuery<User> query = manager.createQuery("SELECT e FROM user e", User.class);
	List<User> result = query.getResultList();
	return result;
    }

}
