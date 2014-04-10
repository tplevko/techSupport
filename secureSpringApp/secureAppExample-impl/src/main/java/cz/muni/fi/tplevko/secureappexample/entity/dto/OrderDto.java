package cz.muni.fi.tplevko.secureappexample.entity.dto;

import cz.muni.fi.tplevko.secureappexample.entity.Account;
import cz.muni.fi.tplevko.secureappexample.entity.Item;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author tplevko
 */
public class OrderDto {

    private Long id;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar calendar;
    private List<ItemDto> items;
    private Account owner;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
