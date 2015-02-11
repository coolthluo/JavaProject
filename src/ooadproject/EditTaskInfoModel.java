/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.service;
import com.dao.ProjectInfoService;
import com.dao.TaskInfoService;
import com.dao.UserInfoService;
import com.model.Budget;
import com.model.Project;
import com.model.Task;
import com.model.User;*/
package ooadproject;
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
public class EditTaskInfoModel extends AbstractTableModel{
    List<User> usertaskInfoResultList;// stores the model data in a List
//    List<Budget> budgetInfoResultList;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    
    private User usertaskinfo;// represents the entity
    private Task taskinfo;
    
    // This field contains additional information about the results
    private TaskInfoService usertaskinfoservice;
    
    private int numcols, numrows;
    
    public EditTaskInfoModel(){
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            manager = factory.createEntityManager();
            usertaskinfo = new User();
            // read all the records from courselist
            usertaskinfoservice = new TaskInfoService(manager);

            // update the number of rows and columns in the model
            usertaskInfoResultList = usertaskinfoservice.readAll();
//            budgetInfoResultList = projectinfoservice.readBudgetAll();
            numrows = usertaskInfoResultList.size();
            numcols = usertaskinfo.getNumberOfColumns();
    }
    public EditTaskInfoModel(List<User> list, EntityManager em){
        usertaskInfoResultList = list;
        numrows = usertaskInfoResultList.size();
        usertaskinfo = new User();
        numcols = usertaskinfo.getNumberOfColumns();
        manager = em;
        usertaskinfoservice = new TaskInfoService(manager);
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
    public String getColumnName(int col) {
	try {
            return usertaskinfo.getColumnName(col);
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
                return usertaskInfoResultList.get(row).getColumnData(col);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    
    public void updateRow(Object obj, Object[] array) {
        try {
                int row = Integer.parseInt((String) obj);
                usertaskinfo = usertaskInfoResultList.get(row);

                EntityTransaction userTransaction = manager.getTransaction();
                userTransaction.begin();
                Task newRecord = usertaskinfoservice.updateUserTask(Integer.parseInt((String) array[0]),(String)array[1],(String)array[2],(String) array[3]);
                userTransaction.commit();
                taskinfo = newRecord;
                fireTableRowsUpdated(row, row);
        } catch (Exception e) {
                e.printStackTrace();
        }

    }
}
