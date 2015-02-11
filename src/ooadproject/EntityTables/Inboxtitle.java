/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject.EntityTables;

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
@Entity
@Table(name = "inboxtitle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inboxtitle.findAll", query = "SELECT i FROM Inboxtitle i"),
    @NamedQuery(name = "Inboxtitle.findById", query = "SELECT i FROM Inboxtitle i WHERE i.id = :id"),
    @NamedQuery(name = "Inboxtitle.findBySenderid", query = "SELECT i FROM Inboxtitle i WHERE i.senderid = :senderid"),
    @NamedQuery(name = "Inboxtitle.findBySubject", query = "SELECT i FROM Inboxtitle i WHERE i.subject = :subject"),
    @NamedQuery(name = "Inboxtitle.findByReceiverId", query = "SELECT i FROM Inboxtitle i WHERE i.receiverId = :receiverId"),
    @NamedQuery(name = "Inboxtitle.findByDate", query = "SELECT i FROM Inboxtitle i WHERE i.date = :date")})
public class Inboxtitle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Sender_id")
    private String senderid;
    @Column(name = "Subject")
    private String subject;
    @Column(name = "receiver_id")
    private String receiverId;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "id")
    private Collection<Messagebody> messagebodyCollection;

    public Inboxtitle() {
    }

    public Inboxtitle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<Messagebody> getMessagebodyCollection() {
        return messagebodyCollection;
    }

    public void setMessagebodyCollection(Collection<Messagebody> messagebodyCollection) {
        this.messagebodyCollection = messagebodyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inboxtitle)) {
            return false;
        }
        Inboxtitle other = (Inboxtitle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooadproject.EntityTables.Inboxtitle[ id=" + id + " ]";
    }
    
}
