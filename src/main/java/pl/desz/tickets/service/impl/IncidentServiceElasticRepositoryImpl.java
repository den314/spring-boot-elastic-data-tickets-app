package pl.desz.tickets.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.TicketStatus;
import pl.desz.tickets.repository.IncidentRepository;
import pl.desz.tickets.service.ElasticService;

@Service
@Profile("elastic-repository")
public class IncidentServiceElasticRepositoryImpl implements ElasticService {

    private IncidentRepository incidentRepository;

    public IncidentServiceElasticRepositoryImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Incident findById(Long id) {
        return incidentRepository.findById(id).orElseThrow(() -> new RuntimeException("Incident of id: " + id + " not found"));
    }

    @Override
    public Iterable<Incident> findAllByStatus(TicketStatus status) {
        return incidentRepository.findAllByStatus(status);
    }

    @Override
    public Iterable<Incident> findAllByIds(Iterable<Long> incidentIds) {
        return incidentRepository.findAllById(incidentIds);
    }

    @Override
    public Incident save(Incident i) {
        return incidentRepository.save(i);
    }

    @Override
    public Iterable<Incident> saveAll(Iterable<Incident> incidents) {
        return incidentRepository.saveAll(incidents);
    }



    @Override
    public void delete(Long id) {
        incidentRepository.deleteById(id);
    }

    @Override
    public void delete(Incident i) {
        incidentRepository.delete(i);
    }
}
