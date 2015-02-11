/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;


//import com.service.EditUserInfoModel;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Coolth
 */
public class EditTaskInfoController implements ListSelectionListener, TableModelListener, ListDataListener {
//    private EditTaskInfoModel edittaskinfoModel;
//    private EditBudgetInfoModel editbudgerinfoModel;
    private EditTaskInfoModel usertaskinfoModel;
    //private EditTaskInfoGUI edittaskinfogui;
    private EditTaskInfoGUIJFRAME edittaskinfoguiframe;
    public EditTaskInfoController (EditTaskInfoGUIJFRAME edittaskinfoguiframe){
        this.edittaskinfoguiframe = edittaskinfoguiframe;
        //create the tableModel using the data in the cachedRowSet
        usertaskinfoModel = new EditTaskInfoModel();
        usertaskinfoModel.addTableModelListener(this);
    }
    public EditTaskInfoModel getTableModel(){
        return usertaskinfoModel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel)e.getSource();
        int firstIndex  = selectModel.getMinSelectionIndex() ;
        edittaskinfoguiframe.setUserNameTextField((String)usertaskinfoModel.getValueAt(firstIndex, 0));
        edittaskinfoguiframe.setTaskIdTexefield((String)usertaskinfoModel.getValueAt(firstIndex, 1));
        edittaskinfoguiframe.setTaskDescriptionTexefield((String)usertaskinfoModel.getValueAt(firstIndex, 2));
        edittaskinfoguiframe.setStartDateTextField((String)usertaskinfoModel.getValueAt(firstIndex, 3));
        edittaskinfoguiframe.setEndDateTextField((String)usertaskinfoModel.getValueAt(firstIndex, 4));
    
  
    }
    
    public void updateRow(String row, String[] array) {
        usertaskinfoModel.updateRow(row, array);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
