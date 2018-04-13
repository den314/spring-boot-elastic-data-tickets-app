package pl.desz.tickets.config;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticConfiguration {

    @Value("${elasticsearch.host:localhost}")
    private String elasticHost;

    /**
     * Transport port of elastic server
     */
    @Value("${elasticsearch.port:9300}")
    private int elasticPort;

    /**
     * Interacts with Elastic Server.
     *
     * @return elasticTemplate bean
     * @throws UnknownHostException
     */
    @Bean("elasticsearchTemplate")
    public ElasticsearchTemplate elasticTemplate() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        return new ElasticsearchTemplate(new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticHost), elasticPort)));
    }
}
