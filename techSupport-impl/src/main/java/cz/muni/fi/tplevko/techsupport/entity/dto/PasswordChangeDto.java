package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;

/**
 *
 * @author tplevko
 */
public class PasswordChangeDto {

    private String id;
    private CustomerDto requester;
    private boolean executed;
    private Date createdAt;
    private Date finished;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerDto getRequester() {
        return requester;
    }

    public void setRequester(CustomerDto requester) {
        this.requester = requester;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }
}
