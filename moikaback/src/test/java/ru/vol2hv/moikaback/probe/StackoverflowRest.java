package ru.vol2hv.moikaback.probe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Data
class SiteDTO {
    private String favicon_url;
    private String audience;
    private String site_url;
    private String name;
}
@Data
class SitesDTO {
    private List<SiteDTO> items;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stack_over_flow_website")
class StackoverflowWebsite {
    @Id
    private String id;
    private String website;
    private String iconImageUrl;
    private String title;
    private String description;
}

public class StackoverflowRest {
    HttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    RestTemplate restTemplate = new RestTemplate(requestFactory);

    public static void main(String[] args) {
        StackoverflowRest stackoverflowRest = new StackoverflowRest();
        List<SiteDTO> list = stackoverflowRest.stackoverflow();
    }
    List<SiteDTO> stackoverflow(){
        String url = "http://api.stackexchange.com/2.2/sites?page=1&pagesize=9999&filter=!Fn4IB7S7T4v-QOAVmFyqlc(HdV";
        try {
            SitesDTO response = restTemplate.getForObject(new URI(url), SitesDTO.class);
            return response.getItems();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
