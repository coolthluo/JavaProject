/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject.EntityTables;

import com.service.ProjectUtil;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manish
 */
@Entity(name = "user")
@Table(name = "user")
@XmlRootElement
/*@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})*/
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "userId")
    private Collection<Role> roleCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Task> taskCollection;
    @OneToMany(mappedBy = "senderId")
    private Collection<Communication> communicationCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Userroletask> userroletaskCollection;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Role> getRoleCollection() {
        return roleCollection;
    }

    public void setRoleCollection(Collection<Role> roleCollection) {
        this.roleCollection = roleCollection;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @XmlTransient
    public Collection<Communication> getCommunicationCollection() {
        return communicationCollection;
    }

    public void setCommunicationCollection(Collection<Communication> communicationCollection) {
        this.communicationCollection = communicationCollection;
    }

    @XmlTransient
    public Collection<Userroletask> getUserroletaskCollection() {
        return userroletaskCollection;
    }

    public void setUserroletaskCollection(Collection<Userroletask> userroletaskCollection) {
        this.userroletaskCollection = userroletaskCollection;
    }
        public int getNumberOfColumns() {
    return 4;
    }
        public String getColumnName(int i) throws Exception {
String colName = null;
if (i == 0)
colName = "userId";
else if (i == 1)
colName = "Name";
else if (i == 2)
colName = "email";
else if (i == 3)
colName = "password";
else
throw new Exception("Access to invalid column number in courselist table");
return colName;
}
    public void setColumnData(int i, Object value) throws Exception {
if (i == 0)
userId =value.toString();
else if (i == 1)
name = (String) value;
else if (i == 2)
email = (String) value;
else if (i == 3)
password = (String) value;

else
throw new Exception("Error: invalid column index in courselist table");
}
      public String getColumnData(int i) throws Exception {
        if (i == 0)
            return getName();
        else if(i == 1)
            return Integer.toString(getTaskCollection().iterator().next().getTaskId());
        else if (i == 2)
            return getTaskCollection().iterator().next().getTaskDescr();
        else if (i == 3)
            return  ProjectUtil.simpleDateFormat(getTaskCollection().iterator().next().getStartDate());
        else if (i == 4)
            return  ProjectUtil.simpleDateFormat(getTaskCollection().iterator().next().getEndDate());
        else 
            throw new Exception("Error: invalid column index in courselist table");
    }
         public String getUserColumnData(int i) throws Exception {
        if (i == 0)
            return getUserId();
        else if (i == 1)
            return getName();
        else if (i == 2)
            return getPassword();
	else if (i == 3)
            return getEmail();
        else 
            throw new Exception("Error: invalid column index in courselist table");
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooadproject.EntityTables.User[ userId=" + userId + " ]";
    }
    
}
