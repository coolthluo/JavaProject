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
class BudgetModel extends AbstractTableModel {
private CreateProject gui;
    Budget bg;
    	private List<Budget> budgetList;
    private int numcols, numrows; // number of rows and columns
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit"; // Used in persistence.xml
	private static EntityManagerFactory factory; // JPA private EntityManager manager; // JPA
	private EntityManager manager;
	private BudgetService budgetService;

    BudgetModel() {
        this.gui = gui;   
    	budgetList = new ArrayList<>();
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	manager = factory.createEntityManager();
    	budgetService = new BudgetService(manager);
    	bg=new Budget();
    	//projectList = projectService.readAll();
    	numcols = bg.getNumberOfColumns();
    }
        
       // @Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Budget budget = budgetList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return budget.getBudgetId();
		
		case 1:
			return budget.getProjectId();

		case 2:
			return budget.getEquipmentName();
			
		case 3:
			return budget.getLink();
			
		case 4:
			return budget.getEstimatedCost();
			
		case 5:
			return budget.getProjectedCost();
			
		
		}
		
		return null;
	}

//    void addTableModelListener(ProjectJpaController aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    void addRow(String[] array2) {
        
        budgetService.createProject(array2);
		//projectList.add(array);	
		this.fireTableDataChanged();
        
    }

//    void deleteRow(String projectId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public int getRowCount() {
        return budgetList.size();
    }

    @Override
    public int getColumnCount() {
    return numcols;
    }
    
}
