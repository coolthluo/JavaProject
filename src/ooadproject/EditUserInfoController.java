/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;
//import com.service.EditUserInfoModel;
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
public class EditUserInfoController implements ListSelectionListener, TableModelListener, ListDataListener {
    private EditUserInfoModel edituserinfoModel;
    private EditUserInfoGUI edituserinfogui;
    public EditUserInfoController (EditUserInfoGUI edituserInfoGUI){
        this.edituserinfogui = edituserInfoGUI;
        //create the tableModel using the data in the cachedRowSet
        edituserinfoModel = new EditUserInfoModel();
    }
    
    public EditUserInfoModel getTableModel(){
        return edituserinfoModel;
    }
    
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel)e.getSource();
        int firstIndex  = selectModel.getMinSelectionIndex() ;
        edituserinfogui.setUserIdTextField((String)edituserinfoModel.getValueAt(firstIndex, 0));
        edituserinfogui.setUserNameTextField((String)edituserinfoModel.getValueAt(firstIndex, 1));
        edituserinfogui.setUserPasswordTextField((String)edituserinfoModel.getValueAt(firstIndex, 2));
        edituserinfogui.setUserEmailTextField((String)edituserinfoModel.getValueAt(firstIndex, 3));

//        editprojectgui.setProjectNameTextField((String)editprojectinfoModel.getValueAt(firstIndex, 0));
//        editprojectgui.setProjectDescriptionTextArea1((String)editprojectinfoModel.getValueAt(firstIndex, 21));
//        editprojectgui.setStatusTextField((String)editprojectinfoModel.getValueAt(firstIndex, 2));
//        editprojectgui.setProjectCategoryTextField((String)editprojectinfoModel.getValueAt(firstIndex, 3));
//        editprojectgui.setProjectOutcomeTextField((String)editprojectinfoModel.getValueAt(firstIndex, 4));
//        editprojectgui.setProjectStartDateTextField((String)editprojectinfoModel.getValueAt(firstIndex, 5));
//        editprojectgui.setProjectEndDateTextField((String)editprojectinfoModel.getValueAt(firstIndex, 6));
//        editprojectgui.setEquipmentNameTextField((String)editprojectinfoModel.getValueAt(firstIndex, 7));
//        editprojectgui.setEstimatedCostTextField((String)editprojectinfoModel.getValueAt(firstIndex,8));
//        editprojectgui.setProjectedCostTextField((String)editprojectinfoModel.getValueAt(firstIndex, 9));
//        editprojectgui.setEquipmentLinkTextField((String)editprojectinfoModel.getValueAt(firstIndex, 10));
    }
    
        public void updateRow(String row, String[] array) {
        edituserinfoModel.updateRow(row, array);
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
