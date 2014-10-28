package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;

/**
 *
 * @author tplevko
 */
public class PasswordChangeDto {

    private String id;
    private AccountDto requester;
    private boolean executed = false;
    private Date created;
    private Date finished;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountDto getRequester() {
        return requester;
    }

    public void setRequester(AccountDto requester) {
        this.requester = requester;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

}
