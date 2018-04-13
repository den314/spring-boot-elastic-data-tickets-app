package pl.desz.tickets.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.IncidentPriority;
import pl.desz.tickets.model.TicketStatus;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class IncidentServiceElasticTemplateImplTest {

    @Mock
    private ElasticsearchTemplate elasticTemplate;

    @InjectMocks
    private IncidentServiceElasticTemplateImpl elasticService;

    private Incident inc_1_desc_minor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        inc_1_desc_minor = new Incident(1L, "desc", IncidentPriority.MINOR);
    }

    @Test
    public void shouldFindById() {
        // given
        BDDMockito.given(elasticTemplate.queryForObject(Mockito.any(GetQuery.class), Mockito.eq(Incident.class)))
                .willReturn(inc_1_desc_minor);
        // when
        Incident incById = elasticService.findById(1L);
        //then
        assertNotNull(incById);
    }

    @Test
    public void shouldFindByStatus() {
        BDDMockito.given(elasticTemplate.queryForList(Mockito.any(SearchQuery.class), Mockito.eq(Incident.class)))
                .willReturn(Arrays.asList(inc_1_desc_minor, inc_1_desc_minor));

        Iterable<Incident> incidentsByStatus = elasticService.findAllByStatus(TicketStatus.OPEN);
        assertNotNull(incidentsByStatus);
        assertThat((Collection<Incident>) incidentsByStatus, hasSize(2));
    }
}