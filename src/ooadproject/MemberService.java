/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
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
class MemberService {

    private EntityManager manager;

    public MemberService(EntityManager manager) {
        this.manager = manager;
    }
    
    public List<User> getAllUser() {
        try {
            CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = manager.createQuery(cq);
            return q.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Project> getAllProjects() {
        try {
            CriteriaQuery cq = manager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Project.class));
            Query q = manager.createQuery(cq);
            return q.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void createEntry(List<String> entry) {
        
    }
}
