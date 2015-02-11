/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.service;
package ooadproject;
//import com.model.Budget;
//import com.model.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.User;

/**
 *
 * @author Coolth
 */
public class EditProjectInfoModel extends AbstractTableModel{
    List<Project> projectInfoResultList;// stores the model data in a List
//    List<Budget> budgetInfoResultList;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    
    private Project projectinfo;// represents the entity
    
    // This field contains additional information about the results
    private EditProjectInfoService projectinfoservice;
    
    private int numcols, numrows;
    
    public EditProjectInfoModel(){
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            manager = factory.createEntityManager();
            projectinfo = new Project();
            // read all the records from Project
            projectinfoservice = new EditProjectInfoService(manager);

            // update the number of rows and columns in the model
            projectInfoResultList = projectinfoservice.readAll();
//            budgetInfoResultList = projectinfoservice.readBudgetAll();
            fireTableDataChanged();
            numrows = projectInfoResultList.size();
            numcols = projectinfo.getNumberOfColumns();
    }
    public EditProjectInfoModel(List<Project> list, EntityManager em){
        projectInfoResultList = list;
        numrows = projectInfoResultList.size();
        projectinfo = new Project();
        numcols = projectinfo.getNumberOfColumns();
        manager = em;
        projectinfoservice = new EditProjectInfoService(manager);
    }
    
    @Override
    public int getRowCount() {
        return numrows; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return numcols; //To change body of generated methods, choose Tools | Templates.
    }
    // returns the name of the column
    //为表格设置表项名
    public String getColumnName(int col) {
	try {
            return projectinfo.getColumnName(col);
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    public EntityManager getEntityManager() {
		return manager;
    }
    @Override
    public Object getValueAt(int row, int col) {
        try {
                return projectInfoResultList.get(row).getColumnData(col);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    
    public void updateRow(Object obj, Object[] array) {
        try {
                int row = Integer.parseInt((String) obj);
                projectinfo = projectInfoResultList.get(row);

                EntityTransaction userTransaction = manager.getTransaction();
                userTransaction.begin();
                Project newRecord = projectinfoservice.updateProject(Integer.parseInt((String) array[0]),(String)array[1], (String)array[2],(String) array[3], (String) array[4],(String) array[5],(String) array[6],(String) array[7],(String) array[8],(String) array[9],(String) array[10],(String) array[11]);
                userTransaction.commit();
                projectinfo = newRecord;
                fireTableRowsUpdated(row, row);
                fireTableDataChanged();
        } catch (Exception e) {
                e.printStackTrace();
        }

    }
}
