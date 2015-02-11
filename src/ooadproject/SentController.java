/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Manish
 */
public class SentController implements ListSelectionListener {
    String[][] result;
    SentItemPage gui1;
    SentModel tableModel;

    public SentController(SentItemPage gui) {
        this.gui1 = gui;
        tableModel = new SentModel();
    }
    public String[][] getInbox(String loginId)
    {
        result=new String[SentItemPage.length][5];
        result=tableModel.getInbox(loginId);
        return result;
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
        int firstIndex = selectModel.getMinSelectionIndex();
        CommunicationMain.sent.jPanel3.setVisible(true);
        CommunicationMain.sent.jLabel3.setText(SentItemPage.result2[firstIndex][4]);
        CommunicationMain.sent.jLabel4.setText(SentItemPage.result2[firstIndex][1]);
        CommunicationMain.sent.jTextArea1.setText(SentItemPage.result2[firstIndex][2]);
        
    }

}
