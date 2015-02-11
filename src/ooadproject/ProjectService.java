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
import javax.persistence.TypedQuery;
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
class ProjectService {

    private EntityManager manager;

    public ProjectService(EntityManager manager) {
        this.manager = manager;
    }

    public void createProject(String[] array) {
        try {
            manager.getTransaction().begin();
            Project projectData = new Project();
            // projectData.setProjectId(1);
            projectData.setProjectName(array[0]);
            projectData.setProjectDescr(array[1]);
            //status
            projectData.setStatus(array[2]);
            //catagory
            projectData.setCategory(array[3]);
            projectData.setDesiredOutcome(array[4]);
            DateFormat dt = new SimpleDateFormat("yyyy-MM-dd" + " " + "00:00:00");
            Date startDate = dt.parse(array[5]);
            Date endDate = dt.parse(array[6]);
            projectData.setStartDate(startDate);
            projectData.setEndDate(endDate);

            manager.persist(projectData);

            manager.getTransaction().commit();
            

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Duplicate Key");
        }
    }
    
    public List<String> getProjectsForUser(String userName){
        Query query = manager.createNativeQuery("select project_name from project where project_id in (select project_id from task where user_id = '"+userName+"')");
        List<String> list = query.getResultList();
        for (String s : list) {
            System.out.println("test :" + s);
        }
        return list;
    }


    public int size() {
        //TODO
        return 0;
    }
}
