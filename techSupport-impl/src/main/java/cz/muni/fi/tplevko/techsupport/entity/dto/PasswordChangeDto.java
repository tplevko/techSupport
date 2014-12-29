package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.requester);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PasswordChangeDto other = (PasswordChangeDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.requester, other.requester)) {
            return false;
        }
        return true;
    }
}
