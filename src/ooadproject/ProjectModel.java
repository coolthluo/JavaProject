/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;
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
class ProjectModel extends AbstractTableModel {
    
    private CreateProject gui;
    Project pr;
    	private List<Project> projectList;
    private int numcols, numrows; // number of rows and columns
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
	private static EntityManagerFactory factory; // JPA private EntityManager manager; // JPA
	private EntityManager manager;
	private ProjectService projectService;

    ProjectModel() {
        pr=new Project();
        this.gui = gui;   
    	projectList = new ArrayList<>();
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	manager = factory.createEntityManager();
    	projectService = new ProjectService(manager);
    	
    	//projectList = projectService.readAll();
        
    	numcols = pr.getNumberOfColumns();
    }
        
       // @Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Project project = projectList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return project.getProjectId();
		
		case 1:
			return project.getProjectName();

		case 2:
			return project.getProjectDescr();
			
		case 3:
			return project.getStatus();
			
		case 4:
			return project.getBudgetCollection().iterator().next();
			
		case 5:
			return project.getStartDate();
			
		case 6:
			return project.getEndDate();
		}
		
		return null;
	}

//    void addTableModelListener(ProjectJpaController aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    void addRow(String[] array) {
        
        projectService.createProject(array);
		//projectList.add(array);	
		this.fireTableDataChanged();
        
    }

//    void deleteRow(String projectId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public int getRowCount() {
        return projectList.size();
    }

    @Override
    public int getColumnCount() {
    return numcols;
    }
    
    public List<String> getProjectsForUser(String userName){
        return projectService.getProjectsForUser(userName);
        
    }
	

}
