package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;

/**
 *
 * @author tplevko
 */
public class RequestCommentDto {

    private Long id;

    private String text;

    private AccountDto commenter;

    private RequestDto request;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AccountDto getCommenter() {
        return commenter;
    }

    public void setCommenter(AccountDto commenter) {
        this.commenter = commenter;
    }

    public RequestDto getRequest() {
        return request;
    }

    public void setRequest(RequestDto request) {
        this.request = request;
    }
}
