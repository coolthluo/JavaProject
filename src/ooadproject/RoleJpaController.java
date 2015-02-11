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
public class RoleJpaController implements Serializable {

    public RoleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
/*
    public void create(Role role) {
        if (role.getUserRoleTaskCollection() == null) {
            role.setUserRoleTaskCollection(new ArrayList<UserRoleTask>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project projectId = role.getProjectId();
            if (projectId != null) {
                projectId = em.getReference(projectId.getClass(), projectId.getProjectId());
                role.setProjectId(projectId);
            }
            User userId = role.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                role.setUserId(userId);
            }
            Collection<UserRoleTask> attachedUserRoleTaskCollection = new ArrayList<UserRoleTask>();
            for (UserRoleTask userRoleTaskCollectionUserRoleTaskToAttach : role.getUserRoleTaskCollection()) {
                userRoleTaskCollectionUserRoleTaskToAttach = em.getReference(userRoleTaskCollectionUserRoleTaskToAttach.getClass(), userRoleTaskCollectionUserRoleTaskToAttach.getId());
                attachedUserRoleTaskCollection.add(userRoleTaskCollectionUserRoleTaskToAttach);
            }
            role.setUserRoleTaskCollection(attachedUserRoleTaskCollection);
            em.persist(role);
            if (projectId != null) {
                projectId.getRoleCollection().add(role);
                projectId = em.merge(projectId);
            }
            if (userId != null) {
                userId.getRoleCollection().add(role);
                userId = em.merge(userId);
            }
            for (UserRoleTask userRoleTaskCollectionUserRoleTask : role.getUserRoleTaskCollection()) {
                Role oldRoleIdOfUserRoleTaskCollectionUserRoleTask = userRoleTaskCollectionUserRoleTask.getRoleId();
                userRoleTaskCollectionUserRoleTask.setRoleId(role);
                userRoleTaskCollectionUserRoleTask = em.merge(userRoleTaskCollectionUserRoleTask);
                if (oldRoleIdOfUserRoleTaskCollectionUserRoleTask != null) {
                    oldRoleIdOfUserRoleTaskCollectionUserRoleTask.getUserRoleTaskCollection().remove(userRoleTaskCollectionUserRoleTask);
                    oldRoleIdOfUserRoleTaskCollectionUserRoleTask = em.merge(oldRoleIdOfUserRoleTaskCollectionUserRoleTask);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Role role) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role persistentRole = em.find(Role.class, role.getRoleId());
            Project projectIdOld = persistentRole.getProjectId();
            Project projectIdNew = role.getProjectId();
            User userIdOld = persistentRole.getUserId();
            User userIdNew = role.getUserId();
            Collection<UserRoleTask> userRoleTaskCollectionOld = persistentRole.getUserRoleTaskCollection();
            Collection<UserRoleTask> userRoleTaskCollectionNew = role.getUserRoleTaskCollection();
            if (projectIdNew != null) {
                projectIdNew = em.getReference(projectIdNew.getClass(), projectIdNew.getProjectId());
                role.setProjectId(projectIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                role.setUserId(userIdNew);
            }
            Collection<UserRoleTask> attachedUserRoleTaskCollectionNew = new ArrayList<UserRoleTask>();
            for (UserRoleTask userRoleTaskCollectionNewUserRoleTaskToAttach : userRoleTaskCollectionNew) {
                userRoleTaskCollectionNewUserRoleTaskToAttach = em.getReference(userRoleTaskCollectionNewUserRoleTaskToAttach.getClass(), userRoleTaskCollectionNewUserRoleTaskToAttach.getId());
                attachedUserRoleTaskCollectionNew.add(userRoleTaskCollectionNewUserRoleTaskToAttach);
            }
            userRoleTaskCollectionNew = attachedUserRoleTaskCollectionNew;
            role.setUserRoleTaskCollection(userRoleTaskCollectionNew);
            role = em.merge(role);
            if (projectIdOld != null && !projectIdOld.equals(projectIdNew)) {
                projectIdOld.getRoleCollection().remove(role);
                projectIdOld = em.merge(projectIdOld);
            }
            if (projectIdNew != null && !projectIdNew.equals(projectIdOld)) {
                projectIdNew.getRoleCollection().add(role);
                projectIdNew = em.merge(projectIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getRoleCollection().remove(role);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getRoleCollection().add(role);
                userIdNew = em.merge(userIdNew);
            }
            for (UserRoleTask userRoleTaskCollectionOldUserRoleTask : userRoleTaskCollectionOld) {
                if (!userRoleTaskCollectionNew.contains(userRoleTaskCollectionOldUserRoleTask)) {
                    userRoleTaskCollectionOldUserRoleTask.setRoleId(null);
                    userRoleTaskCollectionOldUserRoleTask = em.merge(userRoleTaskCollectionOldUserRoleTask);
                }
            }
            for (UserRoleTask userRoleTaskCollectionNewUserRoleTask : userRoleTaskCollectionNew) {
                if (!userRoleTaskCollectionOld.contains(userRoleTaskCollectionNewUserRoleTask)) {
                    Role oldRoleIdOfUserRoleTaskCollectionNewUserRoleTask = userRoleTaskCollectionNewUserRoleTask.getRoleId();
                    userRoleTaskCollectionNewUserRoleTask.setRoleId(role);
                    userRoleTaskCollectionNewUserRoleTask = em.merge(userRoleTaskCollectionNewUserRoleTask);
                    if (oldRoleIdOfUserRoleTaskCollectionNewUserRoleTask != null && !oldRoleIdOfUserRoleTaskCollectionNewUserRoleTask.equals(role)) {
                        oldRoleIdOfUserRoleTaskCollectionNewUserRoleTask.getUserRoleTaskCollection().remove(userRoleTaskCollectionNewUserRoleTask);
                        oldRoleIdOfUserRoleTaskCollectionNewUserRoleTask = em.merge(oldRoleIdOfUserRoleTaskCollectionNewUserRoleTask);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = role.getRoleId();
                if (findRole(id) == null) {
                    throw new NonexistentEntityException("The role with id " + id + " no longer exists.");
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
            Role role;
            try {
                role = em.getReference(Role.class, id);
                role.getRoleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The role with id " + id + " no longer exists.", enfe);
            }
            Project projectId = role.getProjectId();
            if (projectId != null) {
                projectId.getRoleCollection().remove(role);
                projectId = em.merge(projectId);
            }
            User userId = role.getUserId();
            if (userId != null) {
                userId.getRoleCollection().remove(role);
                userId = em.merge(userId);
            }
            Collection<UserRoleTask> userRoleTaskCollection = role.getUserRoleTaskCollection();
            for (UserRoleTask userRoleTaskCollectionUserRoleTask : userRoleTaskCollection) {
                userRoleTaskCollectionUserRoleTask.setRoleId(null);
                userRoleTaskCollectionUserRoleTask = em.merge(userRoleTaskCollectionUserRoleTask);
            }
            em.remove(role);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    */

    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Role> findRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Role.class));
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

    public Role findRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Role> rt = cq.from(Role.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
