/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

//import frugallab.exceptions.NonexistentEntityException;
//import frugallab.exceptions.PreexistingEntityException;
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
public class UserJpaController implements Serializable {

    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
    private EntityManagerFactory emf;
    MemberModel model;
    MemberPanel panel;
    
    public UserJpaController(MemberPanel panel) {
        this.panel = panel;
        model = new MemberModel(panel);
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<User> getAllUsers() {
        return model.getAllUsers();
    }
    
    public List<Project> getAllProjects() {
        return model.getAllProjects();
    }
    
    public void insertEntry(List<String> entry) {
        model.createEntry(entry);
    }
/*
    public void create(User user) throws PreexistingEntityException, Exception {
        if (user.getTaskCollection() == null) {
            user.setTaskCollection(new ArrayList<Task>());
        }
        if (user.getUserRoleTaskCollection() == null) {
            user.setUserRoleTaskCollection(new ArrayList<UserRoleTask>());
        }
        if (user.getRoleCollection() == null) {
            user.setRoleCollection(new ArrayList<Role>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Task> attachedTaskCollection = new ArrayList<Task>();
            for (Task taskCollectionTaskToAttach : user.getTaskCollection()) {
                taskCollectionTaskToAttach = em.getReference(taskCollectionTaskToAttach.getClass(), taskCollectionTaskToAttach.getTaskId());
                attachedTaskCollection.add(taskCollectionTaskToAttach);
            }
            user.setTaskCollection(attachedTaskCollection);
            Collection<UserRoleTask> attachedUserRoleTaskCollection = new ArrayList<UserRoleTask>();
            for (UserRoleTask userRoleTaskCollectionUserRoleTaskToAttach : user.getUserRoleTaskCollection()) {
                userRoleTaskCollectionUserRoleTaskToAttach = em.getReference(userRoleTaskCollectionUserRoleTaskToAttach.getClass(), userRoleTaskCollectionUserRoleTaskToAttach.getId());
                attachedUserRoleTaskCollection.add(userRoleTaskCollectionUserRoleTaskToAttach);
            }
            user.setUserRoleTaskCollection(attachedUserRoleTaskCollection);
            Collection<Role> attachedRoleCollection = new ArrayList<Role>();
            for (Role roleCollectionRoleToAttach : user.getRoleCollection()) {
                roleCollectionRoleToAttach = em.getReference(roleCollectionRoleToAttach.getClass(), roleCollectionRoleToAttach.getRoleId());
                attachedRoleCollection.add(roleCollectionRoleToAttach);
            }
            user.setRoleCollection(attachedRoleCollection);
            em.persist(user);
            for (Task taskCollectionTask : user.getTaskCollection()) {
                User oldUserIdOfTaskCollectionTask = taskCollectionTask.getUserId();
                taskCollectionTask.setUserId(user);
                taskCollectionTask = em.merge(taskCollectionTask);
                if (oldUserIdOfTaskCollectionTask != null) {
                    oldUserIdOfTaskCollectionTask.getTaskCollection().remove(taskCollectionTask);
                    oldUserIdOfTaskCollectionTask = em.merge(oldUserIdOfTaskCollectionTask);
                }
            }
            for (UserRoleTask userRoleTaskCollectionUserRoleTask : user.getUserRoleTaskCollection()) {
                User oldUserIdOfUserRoleTaskCollectionUserRoleTask = userRoleTaskCollectionUserRoleTask.getUserId();
                userRoleTaskCollectionUserRoleTask.setUserId(user);
                userRoleTaskCollectionUserRoleTask = em.merge(userRoleTaskCollectionUserRoleTask);
                if (oldUserIdOfUserRoleTaskCollectionUserRoleTask != null) {
                    oldUserIdOfUserRoleTaskCollectionUserRoleTask.getUserRoleTaskCollection().remove(userRoleTaskCollectionUserRoleTask);
                    oldUserIdOfUserRoleTaskCollectionUserRoleTask = em.merge(oldUserIdOfUserRoleTaskCollectionUserRoleTask);
                }
            }
            for (Role roleCollectionRole : user.getRoleCollection()) {
                User oldUserIdOfRoleCollectionRole = roleCollectionRole.getUserId();
                roleCollectionRole.setUserId(user);
                roleCollectionRole = em.merge(roleCollectionRole);
                if (oldUserIdOfRoleCollectionRole != null) {
                    oldUserIdOfRoleCollectionRole.getRoleCollection().remove(roleCollectionRole);
                    oldUserIdOfRoleCollectionRole = em.merge(oldUserIdOfRoleCollectionRole);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUser(user.getUserId()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            Collection<Task> taskCollectionOld = persistentUser.getTaskCollection();
            Collection<Task> taskCollectionNew = user.getTaskCollection();
            Collection<UserRoleTask> userRoleTaskCollectionOld = persistentUser.getUserRoleTaskCollection();
            Collection<UserRoleTask> userRoleTaskCollectionNew = user.getUserRoleTaskCollection();
            Collection<Role> roleCollectionOld = persistentUser.getRoleCollection();
            Collection<Role> roleCollectionNew = user.getRoleCollection();
            Collection<Task> attachedTaskCollectionNew = new ArrayList<Task>();
            for (Task taskCollectionNewTaskToAttach : taskCollectionNew) {
                taskCollectionNewTaskToAttach = em.getReference(taskCollectionNewTaskToAttach.getClass(), taskCollectionNewTaskToAttach.getTaskId());
                attachedTaskCollectionNew.add(taskCollectionNewTaskToAttach);
            }
            taskCollectionNew = attachedTaskCollectionNew;
            user.setTaskCollection(taskCollectionNew);
            Collection<UserRoleTask> attachedUserRoleTaskCollectionNew = new ArrayList<UserRoleTask>();
            for (UserRoleTask userRoleTaskCollectionNewUserRoleTaskToAttach : userRoleTaskCollectionNew) {
                userRoleTaskCollectionNewUserRoleTaskToAttach = em.getReference(userRoleTaskCollectionNewUserRoleTaskToAttach.getClass(), userRoleTaskCollectionNewUserRoleTaskToAttach.getId());
                attachedUserRoleTaskCollectionNew.add(userRoleTaskCollectionNewUserRoleTaskToAttach);
            }
            userRoleTaskCollectionNew = attachedUserRoleTaskCollectionNew;
            user.setUserRoleTaskCollection(userRoleTaskCollectionNew);
            Collection<Role> attachedRoleCollectionNew = new ArrayList<Role>();
            for (Role roleCollectionNewRoleToAttach : roleCollectionNew) {
                roleCollectionNewRoleToAttach = em.getReference(roleCollectionNewRoleToAttach.getClass(), roleCollectionNewRoleToAttach.getRoleId());
                attachedRoleCollectionNew.add(roleCollectionNewRoleToAttach);
            }
            roleCollectionNew = attachedRoleCollectionNew;
            user.setRoleCollection(roleCollectionNew);
            user = em.merge(user);
            for (Task taskCollectionOldTask : taskCollectionOld) {
                if (!taskCollectionNew.contains(taskCollectionOldTask)) {
                    taskCollectionOldTask.setUserId(null);
                    taskCollectionOldTask = em.merge(taskCollectionOldTask);
                }
            }
            for (Task taskCollectionNewTask : taskCollectionNew) {
                if (!taskCollectionOld.contains(taskCollectionNewTask)) {
                    User oldUserIdOfTaskCollectionNewTask = taskCollectionNewTask.getUserId();
                    taskCollectionNewTask.setUserId(user);
                    taskCollectionNewTask = em.merge(taskCollectionNewTask);
                    if (oldUserIdOfTaskCollectionNewTask != null && !oldUserIdOfTaskCollectionNewTask.equals(user)) {
                        oldUserIdOfTaskCollectionNewTask.getTaskCollection().remove(taskCollectionNewTask);
                        oldUserIdOfTaskCollectionNewTask = em.merge(oldUserIdOfTaskCollectionNewTask);
                    }
                }
            }
            for (UserRoleTask userRoleTaskCollectionOldUserRoleTask : userRoleTaskCollectionOld) {
                if (!userRoleTaskCollectionNew.contains(userRoleTaskCollectionOldUserRoleTask)) {
                    userRoleTaskCollectionOldUserRoleTask.setUserId(null);
                    userRoleTaskCollectionOldUserRoleTask = em.merge(userRoleTaskCollectionOldUserRoleTask);
                }
            }
            for (UserRoleTask userRoleTaskCollectionNewUserRoleTask : userRoleTaskCollectionNew) {
                if (!userRoleTaskCollectionOld.contains(userRoleTaskCollectionNewUserRoleTask)) {
                    User oldUserIdOfUserRoleTaskCollectionNewUserRoleTask = userRoleTaskCollectionNewUserRoleTask.getUserId();
                    userRoleTaskCollectionNewUserRoleTask.setUserId(user);
                    userRoleTaskCollectionNewUserRoleTask = em.merge(userRoleTaskCollectionNewUserRoleTask);
                    if (oldUserIdOfUserRoleTaskCollectionNewUserRoleTask != null && !oldUserIdOfUserRoleTaskCollectionNewUserRoleTask.equals(user)) {
                        oldUserIdOfUserRoleTaskCollectionNewUserRoleTask.getUserRoleTaskCollection().remove(userRoleTaskCollectionNewUserRoleTask);
                        oldUserIdOfUserRoleTaskCollectionNewUserRoleTask = em.merge(oldUserIdOfUserRoleTaskCollectionNewUserRoleTask);
                    }
                }
            }
            for (Role roleCollectionOldRole : roleCollectionOld) {
                if (!roleCollectionNew.contains(roleCollectionOldRole)) {
                    roleCollectionOldRole.setUserId(null);
                    roleCollectionOldRole = em.merge(roleCollectionOldRole);
                }
            }
            for (Role roleCollectionNewRole : roleCollectionNew) {
                if (!roleCollectionOld.contains(roleCollectionNewRole)) {
                    User oldUserIdOfRoleCollectionNewRole = roleCollectionNewRole.getUserId();
                    roleCollectionNewRole.setUserId(user);
                    roleCollectionNewRole = em.merge(roleCollectionNewRole);
                    if (oldUserIdOfRoleCollectionNewRole != null && !oldUserIdOfRoleCollectionNewRole.equals(user)) {
                        oldUserIdOfRoleCollectionNewRole.getRoleCollection().remove(roleCollectionNewRole);
                        oldUserIdOfRoleCollectionNewRole = em.merge(oldUserIdOfRoleCollectionNewRole);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = user.getUserId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            Collection<Task> taskCollection = user.getTaskCollection();
            for (Task taskCollectionTask : taskCollection) {
                taskCollectionTask.setUserId(null);
                taskCollectionTask = em.merge(taskCollectionTask);
            }
            Collection<UserRoleTask> userRoleTaskCollection = user.getUserRoleTaskCollection();
            for (UserRoleTask userRoleTaskCollectionUserRoleTask : userRoleTaskCollection) {
                userRoleTaskCollectionUserRoleTask.setUserId(null);
                userRoleTaskCollectionUserRoleTask = em.merge(userRoleTaskCollectionUserRoleTask);
            }
            Collection<Role> roleCollection = user.getRoleCollection();
            for (Role roleCollectionRole : roleCollection) {
                roleCollectionRole.setUserId(null);
                roleCollectionRole = em.merge(roleCollectionRole);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    */

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
