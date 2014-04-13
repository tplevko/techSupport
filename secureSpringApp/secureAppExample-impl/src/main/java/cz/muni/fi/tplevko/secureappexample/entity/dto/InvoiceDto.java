package cz.muni.fi.tplevko.secureappexample.entity.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tplevko
 */
public class InvoiceDto {

    private Long id;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date placingTime;
    private List<ItemDto> items;
    private AccountDto contractor;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacingTime() {
        return placingTime;
    }

    public void setPlacingTime(Date placingTime) {
        this.placingTime = placingTime;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public AccountDto getOwner() {
        return contractor;
    }

    public void setOwner(AccountDto owner) {
        this.contractor = owner;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
