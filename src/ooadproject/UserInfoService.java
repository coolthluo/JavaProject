/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;
import com.service.ProjectUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.User;

/**
 *
 * @author Coolth
 */
public class UserInfoService {
    private EntityManager manager;
    public UserInfoService(EntityManager manager) {
       this.manager = manager;
    }
    public User updateUser(String projectId, String userName, String userPass, String userEmail) {
		 User userinfo = manager.find(User.class, projectId+"");
		if (userinfo != null) {                       
			userinfo.setName(userName);
			userinfo.setPassword(userPass);
			userinfo.setEmail(userEmail);
		}
		return userinfo;
    }
    public List<User> readAll() {
            TypedQuery<User> query = manager.createQuery("SELECT u FROM user u", User.class);
            List<User> result = query.getResultList();
            return result;
    }

    public Project updateUserTask(int parseInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
