package pl.desz.bootifulapp.service;

import pl.desz.bootifulapp.model.Incident;

public interface ElasticService {

    Incident findById(Long id);

    Iterable<Incident> findAllByIds(Iterable<Long> incidentIds);

    Incident save(Incident i);

    void delete(Long id);

    void delete(Incident i);
}
