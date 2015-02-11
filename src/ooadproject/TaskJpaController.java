/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

//import frugallab.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class TaskJpaController implements Serializable {

    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private EntityManagerFactory emf;
    MemberPanel panel;
    MemberModel model;
    
    public TaskJpaController(MemberPanel panel) {
        this.panel = panel;
        model = new MemberModel(panel);
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createEntry(Task task) {
        EntityManager manager = getEntityManager();
        try{	
            manager.getTransaction().begin();
            manager.persist(task);
            manager.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void create(Task task) {
        if (task.getUserroletaskCollection()== null) {
            task.setUserroletaskCollection(new ArrayList<Userroletask>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userId = task.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                task.setUserId(userId);
            }
            Project projectId = task.getProjectId();
            if (projectId != null) {
                projectId = em.getReference(projectId.getClass(), projectId.getProjectId());
                task.setProjectId(projectId);
            }
            Collection<Userroletask> attachedUserRoleTaskCollection = new ArrayList<Userroletask>();
            for (Userroletask userRoleTaskCollectionUserRoleTaskToAttach : task.getUserroletaskCollection()) {
                userRoleTaskCollectionUserRoleTaskToAttach = em.getReference(userRoleTaskCollectionUserRoleTaskToAttach.getClass(), userRoleTaskCollectionUserRoleTaskToAttach.getId());
                attachedUserRoleTaskCollection.add(userRoleTaskCollectionUserRoleTaskToAttach);
            }
            task.setUserroletaskCollection(attachedUserRoleTaskCollection);
            em.persist(task);
            if (userId != null) {
                userId.getTaskCollection().add(task);
                userId = em.merge(userId);
            }
            if (projectId != null) {
                projectId.getTaskCollection().add(task);
                projectId = em.merge(projectId);
            }
            for (Userroletask userRoleTaskCollectionUserRoleTask : task.getUserroletaskCollection()) {
                Task oldTaskIdOfUserRoleTaskCollectionUserRoleTask = userRoleTaskCollectionUserRoleTask.getTaskId();
                userRoleTaskCollectionUserRoleTask.setTaskId(task);
                userRoleTaskCollectionUserRoleTask = em.merge(userRoleTaskCollectionUserRoleTask);
                if (oldTaskIdOfUserRoleTaskCollectionUserRoleTask != null) {
                    oldTaskIdOfUserRoleTaskCollectionUserRoleTask.getUserroletaskCollection().remove(userRoleTaskCollectionUserRoleTask);
                    oldTaskIdOfUserRoleTaskCollectionUserRoleTask = em.merge(oldTaskIdOfUserRoleTaskCollectionUserRoleTask);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Task task) throws  Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task persistentTask = em.find(Task.class, task.getTaskId());
            User userIdOld = persistentTask.getUserId();
            User userIdNew = task.getUserId();
            Project projectIdOld = persistentTask.getProjectId();
            Project projectIdNew = task.getProjectId();
            Collection<Userroletask> userRoleTaskCollectionOld = persistentTask.getUserroletaskCollection();
            Collection<Userroletask> userRoleTaskCollectionNew = task.getUserroletaskCollection();
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                task.setUserId(userIdNew);
            }
            if (projectIdNew != null) {
                projectIdNew = em.getReference(projectIdNew.getClass(), projectIdNew.getProjectId());
                task.setProjectId(projectIdNew);
            }
            Collection<Userroletask> attachedUserRoleTaskCollectionNew = new ArrayList<Userroletask>();
            for (Userroletask userRoleTaskCollectionNewUserRoleTaskToAttach : userRoleTaskCollectionNew) {
                userRoleTaskCollectionNewUserRoleTaskToAttach = em.getReference(userRoleTaskCollectionNewUserRoleTaskToAttach.getClass(), userRoleTaskCollectionNewUserRoleTaskToAttach.getId());
                attachedUserRoleTaskCollectionNew.add(userRoleTaskCollectionNewUserRoleTaskToAttach);
            }
            userRoleTaskCollectionNew = attachedUserRoleTaskCollectionNew;
            task.setUserroletaskCollection(userRoleTaskCollectionNew);
            task = em.merge(task);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getTaskCollection().remove(task);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getTaskCollection().add(task);
                userIdNew = em.merge(userIdNew);
            }
            if (projectIdOld != null && !projectIdOld.equals(projectIdNew)) {
                projectIdOld.getTaskCollection().remove(task);
                projectIdOld = em.merge(projectIdOld);
            }
            if (projectIdNew != null && !projectIdNew.equals(projectIdOld)) {
                projectIdNew.getTaskCollection().add(task);
                projectIdNew = em.merge(projectIdNew);
            }
            for (Userroletask userRoleTaskCollectionOldUserRoleTask : userRoleTaskCollectionOld) {
                if (!userRoleTaskCollectionNew.contains(userRoleTaskCollectionOldUserRoleTask)) {
                    userRoleTaskCollectionOldUserRoleTask.setTaskId(null);
                    userRoleTaskCollectionOldUserRoleTask = em.merge(userRoleTaskCollectionOldUserRoleTask);
                }
            }
            for (Userroletask userRoleTaskCollectionNewUserRoleTask : userRoleTaskCollectionNew) {
                if (!userRoleTaskCollectionOld.contains(userRoleTaskCollectionNewUserRoleTask)) {
                    Task oldTaskIdOfUserRoleTaskCollectionNewUserRoleTask = userRoleTaskCollectionNewUserRoleTask.getTaskId();
                    userRoleTaskCollectionNewUserRoleTask.setTaskId(task);
                    userRoleTaskCollectionNewUserRoleTask = em.merge(userRoleTaskCollectionNewUserRoleTask);
                    if (oldTaskIdOfUserRoleTaskCollectionNewUserRoleTask != null && !oldTaskIdOfUserRoleTaskCollectionNewUserRoleTask.equals(task)) {
                        oldTaskIdOfUserRoleTaskCollectionNewUserRoleTask.getUserroletaskCollection().remove(userRoleTaskCollectionNewUserRoleTask);
                        oldTaskIdOfUserRoleTaskCollectionNewUserRoleTask = em.merge(oldTaskIdOfUserRoleTaskCollectionNewUserRoleTask);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = task.getTaskId();
                if (findTask(id) == null) {
                    throw new Exception("The task with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /*public void destroy(Integer id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task task;
            try {
                task = em.getReference(Task.class, id);
                task.getTaskId();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("The task with id " + id + " no longer exists.", enfe);
            }
            User userId = task.getUserId();
            if (userId != null) {
                userId.getTaskCollection().remove(task);
                userId = em.merge(userId);
            }
            Project projectId = task.getProjectId();
            if (projectId != null) {
                projectId.getTaskCollection().remove(task);
                projectId = em.merge(projectId);
            }
            Collection<UserRoleTask> userRoleTaskCollection = task.getUserRoleTaskCollection();
            for (UserRoleTask userRoleTaskCollectionUserRoleTask : userRoleTaskCollection) {
                userRoleTaskCollectionUserRoleTask.setTaskId(null);
                userRoleTaskCollectionUserRoleTask = em.merge(userRoleTaskCollectionUserRoleTask);
            }
            em.remove(task);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/

    public List<Task> findTaskEntities() {
        return findTaskEntities(true, -1, -1);
    }

    public List<Task> findTaskEntities(int maxResults, int firstResult) {
        return findTaskEntities(false, maxResults, firstResult);
    }

    private List<Task> findTaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Task.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Task findTask(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaskCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Task> rt = cq.from(Task.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
