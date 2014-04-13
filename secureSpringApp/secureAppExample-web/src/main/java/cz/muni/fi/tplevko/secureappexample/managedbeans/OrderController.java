package cz.muni.fi.tplevko.secureappexample.managedbeans;

import cz.muni.fi.tplevko.secureappexample.entity.dto.InvoiceDto;
import cz.muni.fi.tplevko.secureappexample.services.InvoiceService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component
@ManagedBean
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request" )
public class OrderController {

    @Autowired
    private InvoiceService orderService;

    public List<InvoiceDto> getOrderList() {
        
        return orderService.getAllOrders();
    }

    
    public String finishOrder() {
        
        // TODO : asi novy parameter pre Invoice, kde bude oznacenie, ci sa jedna o uzavretu objednavku, alebo nie...
        
        return null;
    }
}
