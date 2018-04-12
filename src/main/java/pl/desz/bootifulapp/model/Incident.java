package pl.desz.bootifulapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Document(indexName = "ticket", type = "incident")
public class Incident {

    @Id
    private final Long id;
    private String assignee;
    private String description;
    private IncidentPriority priority;

    @Field(type = FieldType.Date)
    private Date resolutionDate;

    @Field(type = FieldType.Date)
    private final Date createdDate = new Date();

    @Field(type = FieldType.Nested)
    private List<Watcher> watchers;

    public Incident(Long id, String description, IncidentPriority priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;

        Calendar c = Calendar.getInstance(); // add days in old style
        c.setTime(new Date());
        c.add(Calendar.DATE, priority.getDaysToResolve());
        this.resolutionDate = c.getTime();
    }

    public Long getId() {
        return id;
    }

    public IncidentPriority getPriority() {
        return priority;
    }

    public void setPriority(IncidentPriority priority) {
        this.priority = priority;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Watcher> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<Watcher> watchers) {
        this.watchers = watchers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Incident{");
        sb.append("id='").append(id).append('\'');
        sb.append(", watchers=").append(watchers);
        sb.append('}');
        return sb.toString();
    }
}
