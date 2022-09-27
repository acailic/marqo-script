package org.marqo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ClientUtils {

    // http client for making requests
    HttpClient httpClient = HttpClient.newBuilder()
        .version(Version.HTTP_1_1) // v2 not allowed
        .build();

    static HttpResponse<String> sendRequest(HttpRequest httpRequest)
        throws IOException, InterruptedException {
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    static BodyPublisher createBody(String file) {
        return BodyPublishers.ofString(file);
    }

    static String readFileFromResources(String filename) throws URISyntaxException, IOException {
        URL resource = ClientUtils.class.getClassLoader().getResource(filename);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.toURI()));
        return new String(bytes);
    }

    static void processRequest(HttpRequest httpRequest) {
        try {
            var httpResponse = sendRequest(httpRequest);
            if (httpResponse.statusCode() == 200) {
                log.info("Succeed response {} {}", httpResponse.uri(), httpResponse.statusCode());
                log.info("Response: " + httpResponse.body());
            } else {
                log.info("Failed response {} {}", httpResponse.uri(), httpResponse.statusCode());
                log.info("Response: " + httpResponse.body());
            }
        } catch (IOException e) {
            log.error("exception {}", e);
        } catch (InterruptedException e) {
            log.error("exception {}", e);
        }
    }

}
