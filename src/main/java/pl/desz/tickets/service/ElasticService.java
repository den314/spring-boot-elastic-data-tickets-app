package pl.desz.tickets.service;

import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.TicketStatus;

public interface ElasticService {

    Incident findById(Long id);

    Iterable<Incident> findAllByStatus(TicketStatus status);

    Iterable<Incident> findAllByIds(Iterable<Long> incidentIds);

    Incident save(Incident i);

    Iterable<Incident> saveAll(Iterable<Incident> incidents);

    void delete(Long id);

    void delete(Incident i);
}
