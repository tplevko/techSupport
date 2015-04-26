package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.managedbeans.utils.UserProfileHandler;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "userRequestsController")
@ManagedBean
@Scope("session")
public class UserRequestsController implements Serializable {

    private Long requestId;
    private static final Logger LOG = Logger.getLogger(UserRequestsController.class.getName());

    private static final long serialVersionUID = 1L;
    private List<RequestDto> userRequestList;
    private RequestDto selectedRequestDto;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserProfileHandler profileHandler;

    @Autowired
    private RequestService requestService;
    private AccountDto currentUser;

    @PostConstruct
    public void init() {

        currentUser = profileHandler.getUserProfile();
        userRequestList = new ArrayList<RequestDto>();
    }

    public List<RequestDto> getUserRequestList() {
//        currentUser.isAuthenticated();
        userRequestList = currentUser.getRequestsOwned();

        LOG.info("**********");
        LOG.info("**********");
        LOG.info(currentUser.getEmail());
        LOG.info("**********");
        LOG.info("**********");
        
        
        LOG.info("********************************");
        LOG.info("********************************");

        for (RequestDto req : userRequestList) { 
        
            LOG.info(req.getRequestTitle());
        }
        
        LOG.info("********************************");
        LOG.info("********************************");

        return userRequestList;
    }

    public void editRequestBefore() {

//        currentUser.isAuthenticated();
        selectedRequestDto = requestService.findRequestById(requestId);
        if (selectedRequestDto.getOwner().equals(currentUser)) {
        } else {
            throw new RuntimeException("403 : not accessible");
        }
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public RequestDto getSelectedRequestDto() {
        return selectedRequestDto;
    }

    public void setSelectedRequestDto(RequestDto selectedRequestDto) {
        this.selectedRequestDto = selectedRequestDto;
    }

    public void deselect() {
        selectedRequestDto = null;
    }

    public void rowKeyListener(Object rowKey) {

        Long id = Long.valueOf((String) rowKey);

        selectedRequestDto = requestService.findRequestById(id);

    }

}
