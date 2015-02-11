/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;


import javax.swing.table.TableModel;


/**
 *
 * @author Coolth
 */
public class AddUserInfoController {
        private AddUserInfoModel adduserModel;
	private AddUserInfoGUIJframe gui;
        public AddUserInfoController(AddUserInfoGUIJframe gui){
            this.gui = gui;
            adduserModel = new AddUserInfoModel();
        }
        
        public void addRow(String[] array) {
		adduserModel.addRow(array);
	}
        
        public AddUserInfoModel getTableModel() {
            return adduserModel;
	}
}
