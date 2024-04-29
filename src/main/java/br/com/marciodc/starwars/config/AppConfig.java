package br.com.marciodc.starwars.config;

import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.modelmapper.ModelMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.com.marciodc.starwars.domain.ports.inbound.MovieServicePort;
import br.com.marciodc.starwars.domain.ports.outbound.MovieRepositoryPort;
import br.com.marciodc.starwars.domain.service.MovieService;

import javax.net.ssl.SSLContext;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class AppConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Star Wars API")
                    .version("1.0.0")
                    .description("API to manage Star Wars movies")
        );
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate() throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
            .loadTrustMaterial(TrustAllStrategy.INSTANCE)
            .build();
    
        PoolingHttpClientConnectionManagerBuilder managerBuilder = PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE));
    
        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(managerBuilder.build())
            .build();
    
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }

    @Bean
    public MovieServicePort todoServiceImpl(MovieRepositoryPort movieRepositoryPort) {
        return new MovieService(movieRepositoryPort);
    }
}
