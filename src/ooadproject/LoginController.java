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
public class LoginController {
    
    public String[] validateInfo(String array)
    {
        LoginTableModel lmodel=new LoginTableModel();
        String[] result=new String[5];
         result=lmodel.validateInfo(array);
        return result;
    }
    
}
