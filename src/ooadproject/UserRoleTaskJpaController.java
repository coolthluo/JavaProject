/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

//import frugallab.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class UserRoleTaskJpaController implements Serializable {

    public UserRoleTaskJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public void create(UserRoleTask userRoleTask) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role roleId = userRoleTask.getRoleId();
            if (roleId != null) {
                roleId = em.getReference(roleId.getClass(), roleId.getRoleId());
                userRoleTask.setRoleId(roleId);
            }
            Task taskId = userRoleTask.getTaskId();
            if (taskId != null) {
                taskId = em.getReference(taskId.getClass(), taskId.getTaskId());
                userRoleTask.setTaskId(taskId);
            }
            Project projectId = userRoleTask.getProjectId();
            if (projectId != null) {
                projectId = em.getReference(projectId.getClass(), projectId.getProjectId());
                userRoleTask.setProjectId(projectId);
            }
            User userId = userRoleTask.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                userRoleTask.setUserId(userId);
            }
            em.persist(userRoleTask);
            if (roleId != null) {
                roleId.getUserRoleTaskCollection().add(userRoleTask);
                roleId = em.merge(roleId);
            }
            if (taskId != null) {
                taskId.getUserRoleTaskCollection().add(userRoleTask);
                taskId = em.merge(taskId);
            }
            if (projectId != null) {
                projectId.getUserRoleTaskCollection().add(userRoleTask);
                projectId = em.merge(projectId);
            }
            if (userId != null) {
                userId.getUserRoleTaskCollection().add(userRoleTask);
                userId = em.merge(userId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserRoleTask userRoleTask) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserRoleTask persistentUserRoleTask = em.find(UserRoleTask.class, userRoleTask.getId());
            Role roleIdOld = persistentUserRoleTask.getRoleId();
            Role roleIdNew = userRoleTask.getRoleId();
            Task taskIdOld = persistentUserRoleTask.getTaskId();
            Task taskIdNew = userRoleTask.getTaskId();
            Project projectIdOld = persistentUserRoleTask.getProjectId();
            Project projectIdNew = userRoleTask.getProjectId();
            User userIdOld = persistentUserRoleTask.getUserId();
            User userIdNew = userRoleTask.getUserId();
            if (roleIdNew != null) {
                roleIdNew = em.getReference(roleIdNew.getClass(), roleIdNew.getRoleId());
                userRoleTask.setRoleId(roleIdNew);
            }
            if (taskIdNew != null) {
                taskIdNew = em.getReference(taskIdNew.getClass(), taskIdNew.getTaskId());
                userRoleTask.setTaskId(taskIdNew);
            }
            if (projectIdNew != null) {
                projectIdNew = em.getReference(projectIdNew.getClass(), projectIdNew.getProjectId());
                userRoleTask.setProjectId(projectIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                userRoleTask.setUserId(userIdNew);
            }
            userRoleTask = em.merge(userRoleTask);
            if (roleIdOld != null && !roleIdOld.equals(roleIdNew)) {
                roleIdOld.getUserRoleTaskCollection().remove(userRoleTask);
                roleIdOld = em.merge(roleIdOld);
            }
            if (roleIdNew != null && !roleIdNew.equals(roleIdOld)) {
                roleIdNew.getUserRoleTaskCollection().add(userRoleTask);
                roleIdNew = em.merge(roleIdNew);
            }
            if (taskIdOld != null && !taskIdOld.equals(taskIdNew)) {
                taskIdOld.getUserRoleTaskCollection().remove(userRoleTask);
                taskIdOld = em.merge(taskIdOld);
            }
            if (taskIdNew != null && !taskIdNew.equals(taskIdOld)) {
                taskIdNew.getUserRoleTaskCollection().add(userRoleTask);
                taskIdNew = em.merge(taskIdNew);
            }
            if (projectIdOld != null && !projectIdOld.equals(projectIdNew)) {
                projectIdOld.getUserRoleTaskCollection().remove(userRoleTask);
                projectIdOld = em.merge(projectIdOld);
            }
            if (projectIdNew != null && !projectIdNew.equals(projectIdOld)) {
                projectIdNew.getUserRoleTaskCollection().add(userRoleTask);
                projectIdNew = em.merge(projectIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getUserRoleTaskCollection().remove(userRoleTask);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getUserRoleTaskCollection().add(userRoleTask);
                userIdNew = em.merge(userIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userRoleTask.getId();
                if (findUserRoleTask(id) == null) {
                    throw new NonexistentEntityException("The userRoleTask with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserRoleTask userRoleTask;
            try {
                userRoleTask = em.getReference(UserRoleTask.class, id);
                userRoleTask.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userRoleTask with id " + id + " no longer exists.", enfe);
            }
            Role roleId = userRoleTask.getRoleId();
            if (roleId != null) {
                roleId.getUserRoleTaskCollection().remove(userRoleTask);
                roleId = em.merge(roleId);
            }
            Task taskId = userRoleTask.getTaskId();
            if (taskId != null) {
                taskId.getUserRoleTaskCollection().remove(userRoleTask);
                taskId = em.merge(taskId);
            }
            Project projectId = userRoleTask.getProjectId();
            if (projectId != null) {
                projectId.getUserRoleTaskCollection().remove(userRoleTask);
                projectId = em.merge(projectId);
            }
            User userId = userRoleTask.getUserId();
            if (userId != null) {
                userId.getUserRoleTaskCollection().remove(userRoleTask);
                userId = em.merge(userId);
            }
            em.remove(userRoleTask);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    */
/*
    public List<UserRoleTask> findUserRoleTaskEntities() {
        return findUserRoleTaskEntities(true, -1, -1);
    }

    public List<UserRoleTask> findUserRoleTaskEntities(int maxResults, int firstResult) {
        return findUserRoleTaskEntities(false, maxResults, firstResult);
    }

    private List<UserRoleTask> findUserRoleTaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRoleTask.class));
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

    public UserRoleTask findUserRoleTask(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserRoleTask.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserRoleTaskCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserRoleTask> rt = cq.from(UserRoleTask.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }*/
    
}
