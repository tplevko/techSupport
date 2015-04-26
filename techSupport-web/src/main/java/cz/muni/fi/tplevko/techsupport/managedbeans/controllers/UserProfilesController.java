package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "userProfilesController")
@ManagedBean
@Scope("session")
public class UserProfilesController {

    private static final Logger LOG = Logger.getLogger(UserProfilesController.class.getName());

    @Autowired
    private AccountService accountService;

    private AccountDto account;
    private List<AccountDto> accountList;
    private AccountDto selectedItem;

    @PostConstruct
    public void init() {

        account = new AccountDto();
        accountList = new ArrayList<AccountDto>();
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public List<AccountDto> getAccountList() {
        accountList = accountService.getAllAccounts();
        return accountList;
    }

    public void setAccountList(List<AccountDto> accountList) {
        this.accountList = accountList;
    }

    public String listAccounts() {
//        currentUser.checkRole("ROLE_ADMIN");
        accountList = accountService.getAllAccounts();
        return "/employee/admin/users/accountList?faces-redirect=true";
    }

    public String editAccountBefore() {
//        currentUser.checkRole("ROLE_ADMIN");
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long accountId = Long.valueOf(parameterMap.get("accountId"));
        account = accountService.findAccountById(accountId);

        return "/employee/admin/users/editAccount?faces-redirect=true";
    }

    public String editAccountInfo() {

//        currentUser.checkRole("ROLE_ADMIN");
        accountService.updateAccount(account);
        return "/employee/admin/users/accountList?faces-redirect=true";
    }
    
    public AccountDto getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(AccountDto selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void deselect() {
        selectedItem = null;
    }

    public void rowKeyListener(Object rowKey) {

        Long id = Long.valueOf((String) rowKey);
        selectedItem = accountService.findAccountById(id);
    }
}
