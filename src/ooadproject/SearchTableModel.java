/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.sql.Connection;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.User;

/**
 *
 * @author Manish
 */
public class SearchTableModel extends AbstractTableModel implements TableModel  {
    private static final String PERSISTENCE_UNIT_NAME ="PersistenceUnit"; // Used in persistence.xml
private static EntityManagerFactory factory; // JPA
private EntityManager manager; 
Project projectInfo;
private ProjectInfoService projectInfoService;
private int numcols, numrows;
private SearchController searchController;
List<Project> projectinfoResultList;
List<User> teaminfoResulList;
CachedRowSet projectinfoRowSet;
Connection connection;

public SearchTableModel(String catg,String Status,String strtDt,String endDate) throws ParseException
{
       factory =Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
projectInfo = new Project();
// read all the records from courselist
projectInfoService = new ProjectInfoService(manager);
// update the number of rows and columns in the model
projectinfoResultList =projectInfoService.readData(catg,Status,strtDt,endDate);
numrows = projectinfoResultList.size();
//numcols=1;
numcols = projectInfo.getNumberOfColumns();
}
public SearchTableModel(List<Project> list, EntityManager em)  {
	    projectinfoResultList = list;
	    numrows = projectinfoResultList.size();
	    projectInfo = new Project();
	   	numcols = projectInfo.getNumberOfColumns();     
		manager = em;  
		projectInfoService = new ProjectInfoService(manager);
	 }
 public List<Project> getList() {
		 return projectinfoResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }
         public RowSet getRowSet() {
		return (RowSet) projectinfoRowSet;
	}
          public Connection getConnection() {
    	return connection;
    }
/*public SearchTableModel(String projectid)
{
     factory =Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
projectInfo = new Project();
// read all the records from courselist
projectInfoService = new ProjectInfoService(manager);
// update the number of rows and columns in the model
teaminfoResulList =projectInfoService.readTeamData(projectid);
numrows = teaminfoResulList.size();
//numcols=1;
numcols = projectInfo.getNumberOfColumns();
}*/
public int getRowCount() {
return numrows;
}
public int getColumnCount() {
return numcols;
}
public Object getValueAt(int row, int col) {
try {
return projectinfoResultList.get(row).getColumnData(col);
} catch (Exception e) {
e.getMessage();
return null;
}
}
public boolean isCellEditable(int rowIndex, int colIndex) {
return false;
}
public Class<?> getColumnClass(int col){
//return getValueAt(0, col).getClass();
    return String.class;
}
public String getColumnName(int col) {
try {
return projectInfo.getColumnName(col);
} catch (Exception err) {
return err.toString();
}
    }
public static void main(String[] args)
{
    
}
    
    
}
