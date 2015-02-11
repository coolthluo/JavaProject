/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.User;

/**
 *
 * @author Manish
 */
public class SearchTeamTablemodel  extends AbstractTableModel implements TableModel{
     private static final String PERSISTENCE_UNIT_NAME ="PersistenceUnit"; // Used in persistence.xml
private static EntityManagerFactory factory; // JPA
private EntityManager manager; 
User teamInfo;
private ProjectInfoService projectInfoService;
private int numcols, numrows;
private SearchController searchController;
List<Project> projectinfoResultList;
List<User> teaminfoResulList;
static String[][] result;

/*public SearchTeamTablemodel(String catg,String Status,String strtDt,String endDate)
{
       factory =Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
teamInfo = new Team();
// read all the records from courselist
projectInfoService = new ProjectInfoService(manager);
// update the number of rows and columns in the model
projectinfoResultList =projectInfoService.readData(catg,Status,strtDt,endDate);
numrows = projectinfoResultList.size();
//numcols=1;
numcols = teamInfo.getNumberOfColumns();
}*/
public SearchTeamTablemodel(String projectid)
{
     factory =Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
teamInfo = new User();
// read all the records from courselist
projectInfoService = new ProjectInfoService(manager);
// update the number of rows and columns in the model

//numrows = teaminfoResulList.size();
//numcols=1;
numcols = teamInfo.getNumberOfColumns();
}
public String[][] getData(String projectid)
{
    result=new String[SearchProject.length][4];
    result =projectInfoService.readTeamData(projectid);
    fireTableDataChanged();
    return result;
}
public int getRowCount() {
return numrows;
}
public int getColumnCount() {
return numcols;
}
public Object getValueAt(int row, int col) {
try {
return teamInfo.getColumnData(col);
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
 /*   for(int rowIndex = 0; rowIndex < teaminfoResulList.size(); rowIndex++){

    //List<Object> row =(List<Object>) teaminfoResulList.get(rowIndex);

    if (teaminfoResulList.get(rowIndex) != null) {
        return getValueAt(rowIndex, col).getClass();
    }
  }*/
  return String.class;
}
public String getColumnName(int col) {
try {
return teamInfo.getColumnName(col);
} catch (Exception err) {
return err.toString();
}
    }
public static void main(String[] args)
{
    
}
    
}
