package pl.desz.tickets.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.IncidentPriority;
import pl.desz.tickets.model.TicketStatus;
import pl.desz.tickets.repository.IncidentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class IncidentServiceElasticRepositoryImplTest {

    @Mock
    private IncidentRepository incidentRepository;

    @InjectMocks
    private IncidentServiceElasticRepositoryImpl elasticService;

    private Incident incident_1_desc_major, incident_2_critical_inc_critical;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        incident_1_desc_major = new Incident(1L, "desc", IncidentPriority.MAJOR);
        incident_2_critical_inc_critical = new Incident(2L, "critical incident", IncidentPriority.CRITICAL);
    }

    @Test
    public void shouldFindById() {
        Mockito.when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident_1_desc_major));

        Incident incidentById = elasticService.findById(1L);
        assertNotNull(incidentById);
        assertEquals("desc", incidentById.getDescription());
        assertEquals(IncidentPriority.MAJOR, incidentById.getPriority());
    }

    @Test
    public void shouldFindByStatus() {
        // given
        BDDMockito.given(incidentRepository.findAllByStatus(TicketStatus.CLOSED))
                .willReturn((Collections.singletonList(Mockito.any(Incident.class))));
        // when
        Iterable<Incident> incByStatus = elasticService.findAllByStatus(TicketStatus.CLOSED);
        // then
        assertNotNull(incByStatus);
        assertThat((Collection<Incident>)incByStatus, hasSize(1));
    }
}