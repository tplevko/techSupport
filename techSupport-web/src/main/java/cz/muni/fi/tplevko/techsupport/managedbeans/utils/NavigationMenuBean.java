package cz.muni.fi.tplevko.techsupport.managedbeans.utils;

import java.io.Serializable;
import javax.faces.event.ActionEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */

@Component(value = "navigationMenuBean")
@Scope("session")
public class NavigationMenuBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String selectedMenuItem;

    /**
     * Creates a new instance of NavigationMenuBean
     */
    public NavigationMenuBean() {
    }

    public String getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public void setSelectedMenuItem(String selectedMenuItem) {
        this.selectedMenuItem = selectedMenuItem;
    }
    
    public void saveSelectedItem(ActionEvent event) {
        selectedMenuItem = (String) event.getComponent().getAttributes().get("name");
    }    
}
