package cz.muni.fi.tplevko.techsupport.entity.dto;

import java.util.Date;

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

}
