/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;

/**
 *
 * @author Manish
 */
public class CreateController {
    CreateModel tableModel;
    
    public CreateController()
    {
        tableModel=new CreateModel();
    }
    public String[] getuserName(String userName)
    {
        String[] userList=new String[5];
        userList=tableModel.getuserName(userName);
        return userList;
    }
    public void sendMessage(String[] array)
    {
        tableModel.sendMessage(array);
    }
    
}
