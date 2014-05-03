package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author tplevko
 */
public class RequestDto {

    private Long id;
    private String text;
    private boolean executed = false;
    private CustomerDto owner;
    private EmployeeDto assignee;
    private Long priority;
    private Date created;
    private Date finished;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public CustomerDto getOwner() {
        return owner;
    }

    public void setOwner(CustomerDto owner) {
        this.owner = owner;
    }

    public EmployeeDto getAssignee() {
        return assignee;
    }

    public void setAssignee(EmployeeDto assignee) {
        this.assignee = assignee;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final RequestDto other = (RequestDto) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "RequestDto{" + "id=" + id + ", text=" + text + '}';
    }
}
