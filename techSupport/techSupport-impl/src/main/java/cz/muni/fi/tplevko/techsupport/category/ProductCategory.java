package cz.muni.fi.tplevko.techsupport.category;

/**
 *
 * @author tplevko
 */
public enum ProductCategory {

    TELEVISION("television"), FRIDGE("fridge"), OTHER("other");

    private final String label;

    private ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
    // TODO : not used, cause this would be better as a entity...
}
