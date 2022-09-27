package org.marqo;

import static org.marqo.ClientUtils.createBody;
import static org.marqo.ClientUtils.processRequest;
import static org.marqo.MarqoURLs.baseUrl;
import static org.marqo.MarqoURLs.postDocuments;
import static org.marqo.MarqoURLs.postIndex;
import static org.marqo.MarqoURLs.refreshDocuments;
import static org.marqo.MarqoURLs.searchDocuments;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MarqoPlayground {

    // load json resources
    public static final String EXAMPLE_DOCUMENTS_JSON = "example-documents.json";
    public static final String INDEX_SETTINGS = "index-settings.json";
    public static final String REFRESH_INDEX = "refresh-index.json";
    public static final String FIND_DOCUMENT = "find-document.json";

    public static final String APPLICATION_JSON = "application/json";
    static final String[] headers = new String[]{"User-Agent", "Java 17 HttpClient Marqo",
        "Content-Type", APPLICATION_JSON,
        "Accept", APPLICATION_JSON};

    // test script for marqo ai
    public static void main(String[] args) throws URISyntaxException, IOException {
        log.info("Script start");
        final var indexName = "test-index"; // has to be lowercase

        var settingsFile = ClientUtils.readFileFromResources(INDEX_SETTINGS);
        var createIndexRequest = HttpRequest.newBuilder()
            .POST(createBody(settingsFile))
            .uri(URI.create(baseUrl + String.format(postIndex, indexName)))
            .headers(headers)
            .build();

        processRequest(createIndexRequest);

        var testFile = ClientUtils.readFileFromResources(EXAMPLE_DOCUMENTS_JSON);
        // add document to marqo ai
        var addDocumentsRequest = HttpRequest.newBuilder()
            .POST(createBody(testFile))
            .uri(URI.create(baseUrl+ String.format(postDocuments, indexName)))
            .headers(headers)
            .build();

        processRequest(addDocumentsRequest);

        // refresh index
        var refreshBody = ClientUtils.readFileFromResources(REFRESH_INDEX);
        var refreshDocument = HttpRequest.newBuilder()
            .POST(createBody(refreshBody))
            .uri(URI.create(baseUrl+ String.format(refreshDocuments, indexName)))
            .headers(headers)
            .build();
        processRequest(refreshDocument);

        log.info("Query to find document");
        // find document
        var searchBody = ClientUtils.readFileFromResources(FIND_DOCUMENT);
        var findDocumentRequest = HttpRequest.newBuilder()
            .POST(createBody(searchBody))
            .uri(URI.create(baseUrl+ String.format(searchDocuments, indexName)))
            .headers(headers)
            .build();
        processRequest(findDocumentRequest);


        log.info("end of script");
    }



}
