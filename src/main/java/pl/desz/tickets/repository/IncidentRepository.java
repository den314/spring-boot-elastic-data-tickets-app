package pl.desz.tickets.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.TicketStatus;

@Repository
public interface IncidentRepository extends ElasticsearchRepository<Incident, Long> {

    Iterable<Incident> findAllByStatus(TicketStatus status);
}
