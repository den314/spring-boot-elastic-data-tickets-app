package pl.desz.bootifulapp.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.desz.bootifulapp.model.Incident;

@Repository
public interface IncidentRepository extends ElasticsearchRepository<Incident, Long> {
}
