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
@Entity
@Table(name = "messagebody")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messagebody.findAll", query = "SELECT m FROM Messagebody m"),
    @NamedQuery(name = "Messagebody.findByMessageId", query = "SELECT m FROM Messagebody m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "Messagebody.findBySenderid", query = "SELECT m FROM Messagebody m WHERE m.senderid = :senderid"),
    @NamedQuery(name = "Messagebody.findByMsgBody", query = "SELECT m FROM Messagebody m WHERE m.msgBody = :msgBody"),
    @NamedQuery(name = "Messagebody.findByReceiverid", query = "SELECT m FROM Messagebody m WHERE m.receiverid = :receiverid")})
public class Messagebody implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Column(name = "Sender_id")
    private String senderid;
    @Column(name = "msgBody")
    private String msgBody;
    @Column(name = "Receiver_id")
    private String receiverid;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne
    private Inboxtitle id;

    public Messagebody() {
    }

    public Messagebody(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public Inboxtitle getId() {
        return id;
    }

    public void setId(Inboxtitle id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagebody)) {
            return false;
        }
        Messagebody other = (Messagebody) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooadproject.EntityTables.Messagebody[ messageId=" + messageId + " ]";
    }
    
}
