package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "editRequestController")
@Scope("session")
public class EditRequestController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(EditRequestController.class.getName());

    @Autowired
    private AccountService accountService;

    @Autowired
    private RequestService requestService;

    private Long requestId;
    private RequestDto selectedRequest;
    private List<AccountDto> accountList;
    private AccountDto selectedAccount;
//    private Subject currentUser;
//
//    @PostConstruct
//    public void init() {
//
////        currentUser = SecurityUtils.getSubject();
//    }
//
    public void initRequest() {
        accountList = accountService.getAllAccounts();
        selectedRequest = requestService.findRequestById(requestId);
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public RequestDto getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(RequestDto selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public AccountDto getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(AccountDto selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public List<AccountDto> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountDto> accountList) {
        this.accountList = accountList;
    }

    public String editRequest() {
//        currentUser.isAuthenticated();
//        String currentAccount = SecurityUtils.getSubject().getPrincipal().toString();

//        AccountDto account= accountService.findAccountByEmail(currentAccount);
        if (selectedRequest.isExecuted() == true) {

            selectedRequest.setFinished(new Date());
//            selectedRequest.setAssignee(account);

        } else {
            selectedRequest.setFinished(null);
        }

        requestService.updateRequest(selectedRequest);
        // the changed state is written into LOG, so it can be reviewed afterwards.
//        LOG.info("request update made by : " + currentAccount);

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";

    }

}
