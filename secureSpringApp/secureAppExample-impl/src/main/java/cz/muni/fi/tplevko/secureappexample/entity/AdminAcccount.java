package cz.muni.fi.tplevko.secureappexample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author tplevko
 */
@Entity
public class AdminAcccount {// extends Account {

    @Column
    @Id
    private int someNumber;
    
    
    // TODO : tu bude admin ucet ulozeny...
    // ako vlastne extendnut ten klasicky ucet?
    
    public int getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(int someNumber) {
        this.someNumber = someNumber;
    }
}
