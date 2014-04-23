package cz.muni.fi.tplevko.secureappexample.entity.dto;

/**
 *
 * @author tplevko
 */
public class ItemOrderDto {

    private Long itemId;
    private Integer quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
