/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.controller;

//import com.gui.EditProjectInfoGUI;
package ooadproject;
import com.mysql.jdbc.Connection;
//import com.service.EditProjectInfoModel;
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
public class EditProjectInfoController implements ListSelectionListener, TableModelListener, ListDataListener {
    private EditProjectInfoModel editprojectinfoModel;
//    private EditBudgetInfoModel editbudgerinfoModel;
    private EditProjectInfoGUI editprojectgui;
    public EditProjectInfoController (EditProjectInfoGUI editrojectgui){
        this.editprojectgui = editrojectgui;
        //create the tableModel using the data in the cachedRowSet
        editprojectinfoModel = new EditProjectInfoModel();
        editprojectinfoModel.addTableModelListener(this);
    }
    public EditProjectInfoModel getTableModel(){
        return editprojectinfoModel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel)e.getSource();
        int firstIndex  = selectModel.getMinSelectionIndex() ;
        editprojectgui.setProjectIdTextField((String)editprojectinfoModel.getValueAt(firstIndex, 0));
        editprojectgui.setProjectNameTextField((String)editprojectinfoModel.getValueAt(firstIndex, 1));
        editprojectgui.setProjectDescriptionTextArea1((String)editprojectinfoModel.getValueAt(firstIndex, 2));
        editprojectgui.setStatusTextField((String)editprojectinfoModel.getValueAt(firstIndex, 3));
        editprojectgui.setProjectCategoryTextField((String)editprojectinfoModel.getValueAt(firstIndex, 4));
        editprojectgui.setProjectOutcomeTextField((String)editprojectinfoModel.getValueAt(firstIndex, 5));
        editprojectgui.setProjectStartDateTextField((String)editprojectinfoModel.getValueAt(firstIndex, 6));
        editprojectgui.setProjectEndDateTextField((String)editprojectinfoModel.getValueAt(firstIndex, 7));
        editprojectgui.setEquipmentNameTextField((String)editprojectinfoModel.getValueAt(firstIndex, 8));
        editprojectgui.setEstimatedCostTextField((String)editprojectinfoModel.getValueAt(firstIndex,9));
        editprojectgui.setProjectedCostTextField((String)editprojectinfoModel.getValueAt(firstIndex, 10));
        editprojectgui.setEquipmentLinkTextField((String)editprojectinfoModel.getValueAt(firstIndex, 11));
    }
    
    public void updateRow(String row, String[] array) {
        editprojectinfoModel.updateRow(row, array);
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
