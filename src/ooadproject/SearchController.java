/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.jdesktop.el.impl.parser.ParseException;

/**
 *
 * @author Manish
 */
public class SearchController implements ListSelectionListener, TableModelListener{
    SearchProject gui;
    SearchTableModel tableModel;
    static String projectId;
    static int firstIndex;
       public SearchController(String catg,String Status,String strtDt,String endDate, SearchProject gui) throws java.text.ParseException {
this.gui = gui;
// create the tableModel using the data in the cachedRowSet
 tableModel = new SearchTableModel(catg,Status,strtDt,endDate);

//tableModel.getRowSet().addRowSetListener(this);

}
        public TableModel getTableModel() {
     return tableModel;
     
    }
        public void tableChanged(TableModelEvent e) {
        System.out.println("In Table Changed ");
          
        try
        {
	    	// get the index of the inserted row
	        tableModel.getRowSet().moveToCurrentRow();
	    	int firstIndex =  e.getFirstRow();
	    	
	    	// create a new table model with the new data
	        tableModel = new SearchTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateJTable();
           }
        catch(Exception exp) {
		exp.getMessage();
		exp.printStackTrace();
	}
        }
         
        
      
    
    public void valueChanged(ListSelectionEvent e) {
ListSelectionModel selectModel = (ListSelectionModel)e.getSource();
firstIndex = selectModel.getMinSelectionIndex();
 projectId=(String)gui.jTable1.getModel().getValueAt(firstIndex, 0);

 //gui.addJTable2(projectId);

    }
    
}
