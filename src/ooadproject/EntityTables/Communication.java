/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject.EntityTables;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manish
 */
@Entity(name = "communication")
@Table(name = "communication")
@XmlRootElement
/*@NamedQueries({
    @NamedQuery(name = "Communication.findAll", query = "SELECT c FROM Communication c"),
    @NamedQuery(name = "Communication.findById", query = "SELECT c FROM Communication c WHERE c.id = :id"),
    @NamedQuery(name = "Communication.findBySubject", query = "SELECT c FROM Communication c WHERE c.subject = :subject"),
    @NamedQuery(name = "Communication.findByReceiverId", query = "SELECT c FROM Communication c WHERE c.receiverId = :receiverId"),
    @NamedQuery(name = "Communication.findByMessageBody", query = "SELECT c FROM Communication c WHERE c.messageBody = :messageBody"),
    @NamedQuery(name = "Communication.findByDate", query = "SELECT c FROM Communication c WHERE c.date = :date")})*/
public class Communication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "subject")
    private String subject;
    @Column(name = "receiver_id")
    private String receiverId;
    @Column(name = "message_body")
    private String messageBody;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    @ManyToOne
    private User senderId;

    public Communication() {
    }

    public Communication(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
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
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooadproject.EntityTables.Communication[ id=" + id + " ]";
    }
    
}
