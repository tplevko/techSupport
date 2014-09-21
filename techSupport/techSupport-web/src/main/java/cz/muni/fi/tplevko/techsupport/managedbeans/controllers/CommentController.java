package cz.muni.fi.tplevko.techsupport.managedbeans.controllers;

import cz.muni.fi.tplevko.techsupport.entity.dto.RequestCommentDto;
import cz.muni.fi.tplevko.techsupport.services.RequestCommentService;
import javax.faces.bean.ManagedBean;
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
public class CommentController {

    @Autowired
    private RequestCommentService requestCommentService;

    private RequestCommentDto requestComment;

    public RequestCommentDto getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(RequestCommentDto requestComment) {
        this.requestComment = requestComment;
    }

}
