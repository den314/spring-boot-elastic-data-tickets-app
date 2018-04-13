package pl.desz.tickets.service.impl;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.TicketStatus;
import pl.desz.tickets.service.ElasticService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("elastic-template")
public class IncidentServiceElasticTemplateImpl implements ElasticService {

    private ElasticsearchTemplate elasticTemplate;

    public IncidentServiceElasticTemplateImpl(ElasticsearchTemplate elasticTemplate) {
        this.elasticTemplate = elasticTemplate;
    }

    @Override
    public Incident findById(Long id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId("1");

        return elasticTemplate.queryForObject(getQuery, Incident.class);
    }

    @Override
    public Iterable<Incident> findAllByStatus(TicketStatus status) {

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery = builder.withIndices("ticket")
                .withTypes("incident")
                .withQuery(QueryBuilders.matchQuery("status", status.toString())).build();

        return elasticTemplate.queryForList(searchQuery, Incident.class);
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
    public Iterable<Incident> saveAll(Iterable<Incident> incidents) {

        final List<IndexQuery> indexQueries = new ArrayList<>();

        incidents.forEach(c -> {
            IndexQuery query = new IndexQueryBuilder().withIndexName("ticket")
                    .withType("incident")
                    .withObject(c).build();

            indexQueries.add(query);
        });

        elasticTemplate.bulkIndex(indexQueries);
        return incidents;

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Incident i) {

    }
}
