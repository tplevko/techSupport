package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Objects;

/**
 *
 * @author tplevko
 */
public class ProductDto {

    private Long id;
    private String name;
    private Long defaultPriority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDefaultPriority() {
        return defaultPriority;
    }

    public void setDefaultPriority(Long defaultPriority) {
        this.defaultPriority = defaultPriority;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductDto other = (ProductDto) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ProductDto{" + "id=" + id + ", name=" + name + '}';
    }

}
