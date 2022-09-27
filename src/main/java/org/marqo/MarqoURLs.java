package org.marqo;


import lombok.Getter;
import lombok.experimental.UtilityClass;

@Getter
@UtilityClass
public final class MarqoURLs {


    /**
     * base URL
     */
    public static String baseUrl="http://0.0.0.0:8882";
    /**
     * post index
     */
    public static String postIndex="/indexes/%s";
    /**
     * post documents
     * POST /indexes/{index_name}/documents
     *
     */
    public static String postDocuments="/indexes/%s/documents";
    /**
     * refresh index
     * POST /indexes/{index_name}/refresh
     */
    public static String refreshDocuments="/indexes/%s/refresh";
    /**
     * search index
     * POST /indexes/{index_name}/search
     */
    public static String searchDocuments="/indexes/%s/search";
}
