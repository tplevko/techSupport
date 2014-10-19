package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.CustomerDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestCommentDto;
import cz.muni.fi.tplevko.techsupport.entity.dto.RequestDto;
import cz.muni.fi.tplevko.techsupport.services.CustomerService;
import cz.muni.fi.tplevko.techsupport.services.RequestCommentService;
import cz.muni.fi.tplevko.techsupport.services.RequestService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "commentController")
@ManagedBean
@Scope("request")
public class CommentController implements Serializable {

    private Long requestId;

    @Autowired
    private RequestCommentService requestCommentService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CustomerService customerService;

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

//    public String editRequestBefore() {
//
//        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        Long requestId = Long.valueOf(parameterMap.get("requestId"));
//        selectedRequestDto = requestService.findRequestById(requestId);
//
//        return "/request/requestDetail?faces-redirect=true";
//    }
    public void newComment() {

        String currentUser = SecurityUtils.getSubject().getPrincipal().toString();

        CustomerDto commenter = customerService.findCustomerByEmail(currentUser);
        RequestDto request = requestService.findRequestById(requestId);

        requestComment.setCommenter(commenter);
        requestComment.setRequest(request);

        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.
                getCurrentInstance().getExternalContext().getRequest();

        requestCommentService.createRequestComment(requestComment);
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

//
//    public String addRequestBefore() {
//        request = new RequestDto();
//        return "/request/createRequest?faces-redirect=true";
//    }
//
//    public String deleteComment() {
//
//        RequestDto requestToDelete = requestService.findRequestById(selectedItemId);
//        requestService.deleteRequest(requestToDelete);
//        return "/employee/technician/requestList?faces-redirect=true";
//    }
//
//    public Collection<Object> getSelected() {
//        return selected;
//    }
//
//    public void setSelected(Collection<Object> selected) {
//        this.selected = selected;
//    }
//
//    public RequestDto getSelectedItem() {
//        return selectedItem;
//    }
}
