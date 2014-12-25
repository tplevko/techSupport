package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.AccountDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestCommentDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.AccountService;
import cz.muni.fi.tplevko.techsupport.services.RequestCommentService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Comment controller is used for creating of new comments for requests
 *
 * In the future, there will be also editing functionality, but not now.
 * 
 * @author tplevko
 */
@Component(value = "commentController")
@ManagedBean
@Scope("request")
public class CommentController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CommentController.class.getName());

    private Long requestId;

    @Autowired
    private RequestCommentService requestCommentService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AccountService accountService;

    private RequestCommentDto requestComment;

    private List<RequestCommentDto> requestCommentsList;

    @PostConstruct
    public void init() {

        requestComment = new RequestCommentDto();
        requestCommentsList = new ArrayList<>();
    }

    public void initRequestCommentList() {

        selectedItem = requestService.findRequestById(requestId).getComments();
    }

    public List<RequestCommentDto> getRequestCommentsList() {
        return requestCommentsList;
    }

    public void setRequestCommentsList(List<RequestCommentDto> requestCommentsList) {
        this.requestCommentsList = requestCommentsList;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public RequestCommentDto getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(RequestCommentDto requestComment) {
        this.requestComment = requestComment;
    }
    
    /**
     * creates new comment for specified request (the request ID)
     * 
     * @return 
     */
    public String newComment() {

        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();

        AccountDto commenter = accountService.findAccountByEmail(currentUser);

        RequestDto request = requestService.findRequestById(requestId);

        LOG.info("**************************");
        LOG.info("current user, creating comment : " + commenter.getEmail());
        LOG.info("**************************");

        requestComment.setCommenter(commenter);
        requestComment.setRequest(request);

        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();

        requestCommentService.createRequestComment(requestComment);
        requestComment = new RequestCommentDto();

        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&includeViewParams=true";
    }

    private List<RequestCommentDto> selectedItem;

    public List<RequestCommentDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RequestCommentDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    private void deselect() {
        selectedItem = null;
    }
}
