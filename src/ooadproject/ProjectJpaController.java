/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import com.toedter.calendar.JDateChooser;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
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
public class ProjectJpaController implements Serializable, ListSelectionListener, TableModelListener {

    private ProjectModel tableModel;
    private CreateProject gui;
    Project pr;
    
    public ProjectJpaController(CreateProject gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    	 
     //   tableModel.addTableModelListener(this);
        
	}	
    public ProjectJpaController() {
		
        
	}
	// new code
	public TableModel getTableModel() {
            
		return (TableModel) tableModel;
	}
        
         public void valueChanged(ListSelectionEvent e) {
    	
             System.out.println("Value changed");
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();	
		if (firstIndex < 0) {
			firstIndex = 0;
		}
		// read the data in each column using getValueAt and display it on corresponding textfield
		gui.setProjectNameValue((JTextField) tableModel.getValueAt(firstIndex, 0));
		gui.setDescriptionValue( (JTextField) tableModel.getValueAt(firstIndex, 1));
		gui.setOutcomeValue( (JTextField) tableModel.getValueAt(firstIndex, 2));
        gui.setProjStartDate( (JDateChooser) tableModel.getValueAt(firstIndex, 3));
        gui.setProjEndDate( (JDateChooser) tableModel.getValueAt(firstIndex, 4));
		
	}
         
      
    public void addRow(String[] array) {
      
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        tableModel = new ProjectModel();
        tableModel.addRow(array);
    }
//    public void deleteRow(String projectId){
//		tableModel.deleteRow(projectId);
//	}

//       public void addRow(String[] array, int[] i_array){
//           tableModel.addRow(array, i_array);
//       } 
    public ProjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   
    public List<Project> findProjectEntities() {
        return findProjectEntities(true, -1, -1);
    }

    public List<Project> findProjectEntities(int maxResults, int firstResult) {
        return findProjectEntities(false, maxResults, firstResult);
    }

    private List<Project> findProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Project.class));
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

    public Project findProject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Project> rt = cq.from(Project.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
