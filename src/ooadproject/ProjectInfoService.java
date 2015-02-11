/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.User;
import java.lang.*;
import java.util.ArrayList;
import java.util.*;
import ooadproject.EntityTables.Budget;
import ooadproject.EntityTables.Userroletask;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.User;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.Role;

/**
 *
 * @author Manish
 */
public class ProjectInfoService {

    private static EntityManager manager;
    //List<Team> result;
    Object[][] a;
    List<Task> task;
    User team;
    List<User> result;
    static String[][] data;
    static String[][] projectDetails;

    //Object[][] result1;

    public ProjectInfoService(EntityManager manager) {
        this.manager = manager;

    }

    public List<Project> readData(String catg, String Status, String strtDt, String endDate) throws ParseException {
        DateFormat dt = new SimpleDateFormat("yyyy-MM-dd" + " " + "00:00:00");
        Date dt1 = dt.parse(strtDt);
        //TypedQuery<Project> query = manager.createQuery("SELECT T FROM " + Project.class.getName() + " T where T.startDate=:cnum ", Project.class);
      // TypedQuery<Project> query=manager.createQuery("select p from project p join p.budgetCollection b ON p=b.projectId where p.projectId=:cnum and p.startDate=:dnum", Project.class);
       TypedQuery<Project> query=manager.createQuery("select p from project p join p.budgetCollection b ON p=b.projectId where p.startDate=:dnum", Project.class);
       //query.setParameter("cnum", 2);
       query.setParameter("dnum", dt1);
        /*query.setParameter("enum",Status);
         query.setParameter("dnum",strtDt);
         query.setParameter("fnum",endDate);*/

       int y=0;
       
        List<Project> result = query.getResultList();
        projectDetails=new String[result.size()][14];
        for(Project p:result)
        {
            projectDetails[y][0]=p.getProjectName();
            projectDetails[y][1]=p.getCategory();
            projectDetails[y][2]=p.getProjectDescr();
            projectDetails[y][3]=p.getStatus();
            projectDetails[y][4]=p.getDesiredOutcome();
            projectDetails[y][5]=p.getStartDate().toString();
            projectDetails[y][6]=p.getEndDate().toString();
            List<Budget> bd= (List<Budget>)p.getBudgetCollection();
            for(Budget b:bd)
            {
                projectDetails[y][7]=b.getEquipmentName();
                projectDetails[y][8]=b.getLink();
                  projectDetails[y][9]=b.getEstimatedCost();
            projectDetails[y][10]=b.getProjectedCost();
               
            }
          
            y++;
        }
        
        return result;
    }

    public String[][] readTeamData(String projectid) {
        int a = Integer.parseInt(projectid);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager manager = emf.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
         //TypedQuery<User> query = manager.createQuery("SELECT T from user T JOIN T.taskCollection T1 on T=T1.userId JOIN userroletask UT on T=UT.userId",User.class);
        TypedQuery<User> query = manager.createQuery("SELECT T from user T JOIN T.taskCollection T1 on T=T1.userId JOIN project p on p=T1.projectId where P.projectId=:cnum",User.class);
        
        // TypedQuery<Task> query = manager.createQuery("SELECT T from task T JOIN T.userId U on T.userId=U.userId",Task.class);// JOIN userroletask UT on T=UT.userId JOIN  where project.projectId=:cnum",User.class);//TypedQuery<Userroletask> query = manager.createQuery("select ut.userId from userroletask ut,project p where ut.projectId:cnum", Userroletask.class);
       query.setParameter("cnum",a);
        /*query.setParameter("enum",Status);
         query.setParameter("dnum",strtDt);
         query.setParameter("fnum",endDate);*/
        transaction.commit();
        result = query.getResultList();
        List<User> res = (List<User>) result;
         //task=(List<Task>)result.get(0);
        //List<String> a=result;
        String[] columnName = {"cid", "cname", "enrollment"};
         data = new String[result.size()][4];
        int x = 0;
        for (User c : res) {

            data[x][0] = ((User) c).getName();
            // data[x][1] = Integer.toString(c.getTeamId());
            List<Task> q = (List<Task>) c.getTaskCollection();
            for (Task t : q) {
                data[x][1] = t.getTaskDescr();
                data[x][2] = t.getStartDate().toString();
                data[x][3] = t.getEndDate().toString();
            }
         SearchProject.length=result.size();
            x++;

        }
        return data;

    }

    public static void main(String[] args)  throws ParseException{
        ProjectInfoService pr = new ProjectInfoService(manager);
       // pr.readData("1");

    }

}
