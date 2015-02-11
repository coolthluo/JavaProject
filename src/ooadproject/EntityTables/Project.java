/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject.EntityTables;

import com.service.ProjectUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Manish
 */
@Entity(name = "project")
@Table(name = "project")
@XmlRootElement
/*@NamedQueries({
 @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
 @NamedQuery(name = "Project.findByProjectId", query = "SELECT p FROM Project p WHERE p.projectId = :projectId"),
 @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
 @NamedQuery(name = "Project.findByProjectDescr", query = "SELECT p FROM Project p WHERE p.projectDescr = :projectDescr"),
 @NamedQuery(name = "Project.findByStatus", query = "SELECT p FROM Project p WHERE p.status = :status"),
 @NamedQuery(name = "Project.findByCategory", query = "SELECT p FROM Project p WHERE p.category = :category"),
 @NamedQuery(name = "Project.findByDesiredOutcome", query = "SELECT p FROM Project p WHERE p.desiredOutcome = :desiredOutcome"),
 @NamedQuery(name = "Project.findByStartDate", query = "SELECT p FROM Project p WHERE p.startDate = :startDate"),
 @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate")})*/
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "project_descr")
    private String projectDescr;
    @Column(name = "status")
    private String status;
    @Column(name = "category")
    private String category;
    @Column(name = "desired_outcome")
    private String desiredOutcome;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany(mappedBy = "projectId")
    private Collection<Role> roleCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<Task> taskCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<Userroletask> userroletaskCollection;
    @OneToMany(mappedBy = "projectId")
    private Collection<Budget> budgetCollection;

    public Project() {
    }

    public Project(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescr() {
        return projectDescr;
    }

    public void setProjectDescr(String projectDescr) {
        this.projectDescr = projectDescr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesiredOutcome() {
        return desiredOutcome;
    }

    public void setDesiredOutcome(String desiredOutcome) {
        this.desiredOutcome = desiredOutcome;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    public Collection<Userroletask> getUserroletaskCollection() {
        return userroletaskCollection;
    }

    public void setUserroletaskCollection(Collection<Userroletask> userroletaskCollection) {
        this.userroletaskCollection = userroletaskCollection;
    }

    @XmlTransient
    public Collection<Budget> getBudgetCollection() {
        return budgetCollection;
    }

    public void setBudgetCollection(Collection<Budget> budgetCollection) {
        this.budgetCollection = budgetCollection;
    }

    public int getNumberOfColumns() {
        return 11;
    }
     /*public String getColumnData(int i) throws Exception {
	if (i == 0)
            return Integer.toString(getProjectId());
        else if (i == 1)
            return getProjectName();
        else if (i == 2)
            return getProjectDescr();
	else if (i == 3)
            return getStatus();
	else if (i == 4)
            return getCategory ();
        else if (i == 5)
            return getDesiredOutcome();
        else if (i == 6)
            return  ProjectUtil.simpleDateFormat(getStartDate());
        else if (i == 7)
            return  ProjectUtil.simpleDateFormat(getEndDate());
        else if (i == 8)
            return getBudgetCollection().iterator().next().getEquipmentName();
        else if (i == 9)
            return getBudgetCollection().iterator().next().getEstimatedCost();
        else if (i == 10)
            return getBudgetCollection().iterator().next().getProjectedCost();
        else if (i == 11)
            return getBudgetCollection().iterator().next().getLink();
        else 
            throw new Exception("Error: invalid column index in courselist table");
	}*/

    public String getColumnName(int i) throws Exception {
        String colName = null;
        if (i == 0) {
            colName = "projectId";
        } else if (i == 1) {
            colName = "projectName";
        } else if (i == 2) {
            colName = "projectDescr";
        } else if (i == 3) {
            colName = "status";
        } else if (i == 4) {
            colName = "category";
        } else if (i == 5) {
            colName = "desiredOutcome";
        } else if (i == 6) {
            colName = "startDate";
        } else if (i == 7) {
            colName = "endDate";
        } else if (i == 8) {
            colName = "equipmentName";
        } else if (i == 9) {
            colName = "estimatedCost";
        } else if (i == 10) {
            colName = "projectedCost";
        } else if (i == 11) {
            colName = "link";
        } else {
            throw new Exception("Access to invalid column number in courselist table");
        }
        return colName;
    }

    public void setColumnData(int i, Object value) throws Exception {
        if (i == 0) {
            projectId = (Integer) value;
        } else if (i == 1) {
            projectName = (String) value;
        } else if (i == 2) {
            projectDescr = (String) value;
        } else if (i == 3) {
            status = (String) value;
        } else if (i == 4) {
            category = (String) value;
        } else if (i == 5) {
            desiredOutcome = (String) value;
        } else if (i == 6) {
            startDate = (Date) value;
        } else if (i == 7) {
            endDate = (Date) value;
        } else {
            throw new Exception("Error: invalid column index in courselist table");
        }
    }

    public String getColumnData(int i) throws Exception {
        if (i == 0) {
            return getProjectId().toString();
        } else if (i == 1) {
            return getProjectName();
        } else if (i == 2) {
            return getProjectDescr();
        } else if (i == 3) {
            return getStatus();
        } else if (i == 4) {
            return getCategory();
        } else if (i == 5) {
            return getDesiredOutcome();
        } else if (i == 6) {
            return getStartDate().toString();
        } else if (i == 7) {
            return getEndDate().toString();
        }
            else if (i == 8)
            return getBudgetCollection().iterator().next().getEquipmentName();
        else if (i == 9)
            return getBudgetCollection().iterator().next().getEstimatedCost();
        else if (i == 10)
            return getBudgetCollection().iterator().next().getProjectedCost();
        else if (i == 11)
            return getBudgetCollection().iterator().next().getLink();
        else 
            throw new Exception("Error: invalid column index in courselist table");
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooadproject.EntityTables.Project[ projectId=" + projectId + " ]";
    }

}
