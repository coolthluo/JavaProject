/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author komalshirore
 */
import ooadproject.EntityTables.Budget;
import ooadproject.EntityTables.Userroletask;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.User;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.Role;
public class MemberModel {
    private MemberPanel ui;
    private EntityManager manager;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private static EntityManagerFactory factory; // JPA private EntityManager manager; // JPA

    private MemberService service;
    

    public MemberModel(MemberPanel ui) {
        this.ui = ui;   
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
        service = new MemberService(manager);
    }
    
    public List<User> getAllUsers() {
        return service.getAllUser();
    }
    
    public List<Project> getAllProjects() {
        return service.getAllProjects();
    }
    
    public void createEntry(List<String> entry) {
        service.createEntry(entry);
    }
        
}
