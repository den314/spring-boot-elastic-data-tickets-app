package pl.desz.bootifulapp.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import pl.desz.bootifulapp.model.Incident;
import pl.desz.bootifulapp.service.ElasticService;

@Service
@Profile("elastic-template")
public class IncidentServiceElasticTemplateImpl implements ElasticService {

    private ElasticsearchTemplate elasticTemplate;

    public IncidentServiceElasticTemplateImpl(ElasticsearchTemplate elasticTemplate) {
        this.elasticTemplate = elasticTemplate;
    }

    @Override
    public Incident findById(Long id) {
        return null;
    }

    @Override
    public Iterable<Incident> findAllByIds(Iterable<Long> incidentIds) {
        return null;
    }

    @Override
    public Incident save(Incident i) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Incident i) {

    }
}
