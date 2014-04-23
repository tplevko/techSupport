package cz.muni.fi.tplevko.secureappexample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tplevko
 */
@Entity
public class Invoice implements Serializable {

    // TODO : popridavat integritne obmedzenia...
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "placingTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date placingTime;

    // auto generate timestamp on entity create time
    @PrePersist
    void placingTime() {
        this.placingTime = new Date();
    }
    
    // TODO : mozno by bola lepsia mapa, ktora by mala item a jeho mnozstvo..
    // itemy sa menia v case, mozno namiesto toho spravit entitu, ktora by reprezentovala
    // polozku invoicu...
    @ManyToMany
    private List<Item> items;

    @JoinColumn(name = "contractor", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Account contractor;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Account getOwner() {
        return contractor;
    }

    public void setOwner(Account owner) {
        this.contractor = owner;
    }

    public Date getPlacingTime() {
        return placingTime;
    }

    public void setPlacingTime(Date placingTime) {
        this.placingTime = placingTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.muni.fi.tplevko.secureappexample.entity.Form[ id=" + id + " ]";
    }
}
