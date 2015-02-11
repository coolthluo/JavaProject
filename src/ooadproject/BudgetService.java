/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ooadproject.EntityTables.Budget;
import ooadproject.EntityTables.Userroletask;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.User;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.Role;

/**
 *
 * @author komalshirore
 */
class BudgetService {

    private EntityManager manager;

    public BudgetService(EntityManager manager) {
        this.manager = manager;
    }

    public void createProject(String[] array2) {
        
        //step 1: get id
        Query query = manager.createNativeQuery("select p.project_id from project p where p.project_name = '"+array2[4]+"'");
        List<Integer> list = query.getResultList();
//            for (Integer s : list) {
//                System.out.println("test :" + s);
//            }
        System.out.println("project id = " + list.get(0));
        
        
        //step 2: store budget
        try {
            manager.getTransaction().begin();
            Budget budgetData = new Budget();
            // projectData.setProjectId(1);
            budgetData.setProjectId(new Project(list.get(0)));
            budgetData.setEquipmentName(array2[0]);
            budgetData.setLink(array2[1]);
            budgetData.setProjectedCost(array2[2]);
            budgetData.setEstimatedCost(array2[3]);

            manager.persist(budgetData);
            manager.getTransaction().commit();
            //System.out.println("abc" +teamData.getTeamId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Duplicate Key");
        }
    }

    /*public List<Project> readAll() {
     TypedQuery<Project> query = manager.createQuery("SELECT t FROM Team t", Project.class);
     List<Project> result = query.getResultList();
     return result;
     }*/
    public int size() {
        //TODO
        return 0;
    }
}
