package ca.bc.gov.open.Scss.Controllers;

import ca.bc.gov.open.Scss.Configuration.SoapConfig;
import com.example.demp.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HealthController {

    @Value("${scss.host}")
    private String host = "https://127.0.0.1/";

    private final RestTemplate restTemplate;

    @Autowired
    public HealthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getHealth")
    @ResponsePayload
    public GetHealthResponse getHealth(@RequestPayload GetHealth empty) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "health");

        HttpEntity<GetHealthResponse> resp =
                restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        GetHealthResponse.class);

        return resp.getBody();
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getPing")
    @ResponsePayload
    public GetPingResponse getHealth(@RequestPayload GetPing empty) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "ping");

        HttpEntity<GetPingResponse> resp =
                restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        GetPingResponse.class);

        return resp.getBody();
    }
}
