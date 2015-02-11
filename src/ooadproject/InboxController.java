/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static ooadproject.SearchController.projectId;

/**
 *
 * @author Manish
 */
public class InboxController implements ListSelectionListener {

    String[][] result;
    InboxModel tableModel;
    InboxPage gui;
    SentItemPage gui1;

    public InboxController() {
        tableModel = new InboxModel();
    }

    public InboxController(InboxPage gui) {
        this.gui = gui;
    }

    public String[][] getInbox(String loginId) {
        result = new String[InboxPage.length][5];
        result = tableModel.getInbox(loginId);
        return result;
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
        int firstIndex = selectModel.getMinSelectionIndex();
 //String SenderId=(String)gui.jTable2.getModel().getValueAt(firstIndex, 0);
//String messageBody=InboxPage.result2[firstIndex][1];
        CommunicationMain.inbox.jPanel3.setVisible(true);
        CommunicationMain.inbox.jLabel3.setText(InboxPage.result2[firstIndex][0]);
        CommunicationMain.inbox.jLabel4.setText(InboxPage.result2[firstIndex][1]);
        CommunicationMain.inbox.jTextArea1.setText(InboxPage.result2[firstIndex][2]);
      
        //System.out.println(messageBody);

    }

}
