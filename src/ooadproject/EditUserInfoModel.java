/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class EditUserInfoModel extends AbstractTableModel {
    List<User> userInfoResultList;
    private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
    private static EntityManagerFactory factory; // JPA
    private EntityManager manager; // JPA
    
    private User userinfo;// represents the entity
    
    // This field contains additional information about the results
    private UserInfoService userinfoservice;
    
    private int numcols, numrows;
    
    public EditUserInfoModel(){
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            manager = factory.createEntityManager();
            userinfo = new User();
            // read all the records from courselist
            userinfoservice = new UserInfoService(manager);

            // update the number of rows and columns in the model
            userInfoResultList = userinfoservice.readAll();
//            budgetInfoResultList = projectinfoservice.readBudgetAll();
            numrows = userInfoResultList.size();
            numcols = userinfo.getNumberOfColumns();
    }
    
    public EditUserInfoModel(List<User> list, EntityManager em){
        userInfoResultList = list;
        numrows = userInfoResultList.size();
        userinfo = new User();
        numcols = userinfo.getNumberOfColumns();
        manager = em;
        userinfoservice = new UserInfoService(manager);
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
            return userinfo.getColumnName(col);
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
                return userInfoResultList.get(row).getUserColumnData(col);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    public void updateRow(Object obj, Object[] array) {
        try {
                int row = Integer.parseInt((String) obj);
                userinfo = userInfoResultList.get(row);

                EntityTransaction userTransaction = manager.getTransaction();
                userTransaction.begin();
                User newRecord = userinfoservice.updateUser(((String)array[0]), (String) array[1], (String)array[2], (String)array[3]);
                userTransaction.commit();
                userinfo = newRecord;
                fireTableRowsUpdated(row, row);
        } catch (Exception e) {
                e.printStackTrace();
      }
    }
    
//    public void updateTaskRow(Object obj, Object[] array) {
//        try {
//                int row = Integer.parseInt((String) obj);
//                userinfo = userInfoResultList.get(row);
//
//                EntityTransaction userTransaction = manager.getTransaction();
//                userTransaction.begin();
//                User newRecord = userinfoservice.updateUser(Integer.parseInt((String) array[0]), (String)array[1], (String)array[2],(String) array[3]);
//                userTransaction.commit();
//                userinfo = newRecord;
//                fireTableRowsUpdated(row, row);
//        } catch (Exception e) {
//                e.printStackTrace();
//      }
//    }
    
}
