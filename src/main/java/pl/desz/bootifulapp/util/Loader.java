package pl.desz.bootifulapp.util;

import org.springframework.stereotype.Component;
import pl.desz.bootifulapp.model.Incident;
import pl.desz.bootifulapp.model.IncidentPriority;
import pl.desz.bootifulapp.model.Watcher;
import pl.desz.bootifulapp.service.ElasticService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
class Loader {

    private ElasticService elasticService;

    public Loader(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @PostConstruct
    private void init() {
        Incident i0 = new Incident(1L, "Our CEO cannot login to critical application", IncidentPriority.CRITICAL);
        i0.setWatchers(Arrays.asList(new Watcher("denis", "szczukocki"), new Watcher("denis", "kowalski")));

        elasticService.save(i0);
    }
}
