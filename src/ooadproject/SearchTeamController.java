/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.util.*;

/**
 *
 * @author Manish
 */
public class SearchTeamController implements ListSelectionListener {
    private SearchProject gui;
    private SearchTeamTablemodel tableModel;
    static String[][] result1;
     public SearchTeamController(String projectid,SearchProject gui)
       {
           this.gui = gui;
         tableModel = new SearchTeamTablemodel(projectid);
           //result=new Object[3][4];
       }
    public TableModel getTableModel() {
     return tableModel;
     
    }
    public String[][] getData(String projectid)
        {
           result1=new String[SearchProject.length][4];
             result1=tableModel.getData(projectid);
            return result1;
        }
       public void valueChanged(ListSelectionEvent e) {
ListSelectionModel selectModel = (ListSelectionModel)e.getSource();
int firstIndex = selectModel.getMinSelectionIndex();
String projectId="1";
 gui.addJTable2(projectId);

    }
    
}
