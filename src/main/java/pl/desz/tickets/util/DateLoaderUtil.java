package pl.desz.tickets.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.desz.tickets.model.Incident;
import pl.desz.tickets.model.IncidentPriority;
import pl.desz.tickets.model.TicketStatus;
import pl.desz.tickets.model.Watcher;
import pl.desz.tickets.service.ElasticService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Saves sample data on application startup in dev profile.
 */
@Profile("dev")
@Component
class DateLoaderUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateLoaderUtil.class);

    private ElasticService elasticService;

    public DateLoaderUtil(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @PostConstruct
    private void init() {
        logger.debug("Processing sample incidents...");
        logger.debug("STEP: Create");
        List<Incident> incidents = createIncidents();

        logger.debug("STEP: Save");
        elasticService.saveAll(incidents);

        logger.debug("STEP: FIND");
        Iterable<Incident> foundIncidents = elasticService.findAllByStatus(TicketStatus.OPEN);

        logger.debug("STEP: PRINT");
        printIncidents(foundIncidents);
    }

    private List<Incident> createIncidents() {
        // first incident
        Incident i0 = new Incident(1L, "Our CEO cannot login to critical application", IncidentPriority.MINOR);
        i0.setWatchers(Arrays.asList(new Watcher("denis", "szczukocki"), new Watcher("denis", "kowalski")));
        i0.setStatus(TicketStatus.CLOSED);
        // second incident
        Incident i1 = new Incident(2L, "Something is wrong with XAS application, 404 when accessing /credits", IncidentPriority.CRITICAL);
        i1.setWatchers(Arrays.asList(new Watcher("maciej", "dworski")));

        return Arrays.asList(i0, i1);
    }

    private void printIncidents(Iterable<Incident> incidents) {
        for (Incident i : incidents) {
            System.out.println(i);
        }
    }
}
