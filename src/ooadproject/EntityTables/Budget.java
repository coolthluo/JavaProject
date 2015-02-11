/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject.EntityTables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manish
 */
@Entity(name = "budget")
@Table(name = "budget")
@XmlRootElement
/*@NamedQueries({
    @NamedQuery(name = "Budget.findAll", query = "SELECT b FROM Budget b"),
    @NamedQuery(name = "Budget.findByBudgetId", query = "SELECT b FROM Budget b WHERE b.budgetId = :budgetId"),
    @NamedQuery(name = "Budget.findByEquipmentName", query = "SELECT b FROM Budget b WHERE b.equipmentName = :equipmentName"),
    @NamedQuery(name = "Budget.findByLink", query = "SELECT b FROM Budget b WHERE b.link = :link"),
    @NamedQuery(name = "Budget.findByEstimatedCost", query = "SELECT b FROM Budget b WHERE b.estimatedCost = :estimatedCost"),
    @NamedQuery(name = "Budget.findByProjectedCost", query = "SELECT b FROM Budget b WHERE b.projectedCost = :projectedCost")})*/
public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "budget_id")
    private Integer budgetId;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "link")
    private String link;
    @Column(name = "estimated_cost")
    private String estimatedCost;
    @Column(name = "projected_cost")
    private String projectedCost;
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    @ManyToOne
    private Project projectId;

    public Budget() {
    }

    public Budget(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getProjectedCost() {
        return projectedCost;
    }

    public void setProjectedCost(String projectedCost) {
        this.projectedCost = projectedCost;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }
      public int getNumberOfColumns() {
    return 4;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (budgetId != null ? budgetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Budget)) {
            return false;
        }
        Budget other = (Budget) object;
        if ((this.budgetId == null && other.budgetId != null) || (this.budgetId != null && !this.budgetId.equals(other.budgetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooadproject.EntityTables.Budget[ budgetId=" + budgetId + " ]";
    }
    
}
