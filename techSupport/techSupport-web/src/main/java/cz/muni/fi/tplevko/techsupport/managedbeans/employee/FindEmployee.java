package cz.muni.fi.tplevko.techsupport.managedbeans.employee;

import javax.faces.bean.ManagedBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "findEmployee")
@ManagedBean
@Scope("session")
public class FindEmployee {

}
