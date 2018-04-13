package pl.desz.tickets.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

/**
 * Stores information about particular Incident ticket.
 */
@Document(indexName = "ticket", type = "incident")
public class Incident implements Ticket {

    @Id
    private Long id;
    private String assignee;
    private String description;
    private IncidentPriority priority;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date)
    private LocalDate resolutionDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date)
    private final LocalDate createdDate = LocalDate.now();

    private List<Watcher> watchers;

    private TicketStatus status = TicketStatus.OPEN;

    @JsonCreator
    public Incident(@JsonProperty("id") Long id,
                    @JsonProperty("description") String description,
                    @JsonProperty("priority") IncidentPriority priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;

        this.resolutionDate = this.createdDate.plusDays(priority.getDaysToResolve());
    }

    public Long getId() {
        return id;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
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

    public LocalDate getResolutionDate() {
        return resolutionDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setResolutionDate(LocalDate resolutionDate) {
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
        sb.append("id=").append(id);
        sb.append(", assignee='").append(assignee).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", resolutionDate=").append(resolutionDate);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", watchers=").append(watchers);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
