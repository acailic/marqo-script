# marqo-script
Script to use marqo ai https://github.com/marqo-ai/marqo


# Tutorial M Series mac
1. Docker run : colima start --cpu 4 --memory 8
2. docker rm -f marqo-os; docker run -p 9200:9200 -p 9600:9600 -e "discovery.type=single-node" marqoai/marqo-os:0.0.2-arm
3. docker rm -f marqo; docker run --name marqo --privileged \
   -p 8882:8882 --add-host host.docker.internal:host-gateway \
   -e "OPENSEARCH_URL=https://localhost:9200" \
   marqoai/marqo:0.0.3
4. Run script
5. Enjoy
6. Docker stop : colima stop

# Script
- Create a test index
- Add a document
- Refresh index
- Search for the document

# Example run

```
[main] INFO org.marqo.MarqoPlayground - Script start
[main] INFO org.marqo.ClientUtils - Succeed response http://0.0.0.0:8882/indexes/test-index-example 200
[main] INFO org.marqo.ClientUtils - Response: {"acknowledged":true,"shards_acknowledged":true,"index":"test-index-example"}
[main] INFO org.marqo.ClientUtils - Succeed response http://0.0.0.0:8882/indexes/test-index-example/documents 200
[main] INFO org.marqo.ClientUtils - Response: {"errors":false,"items":[{"_id":"866b1eaa-7284-4bb5-ab48-2944a531aff2","result":"created","status":201},{"_id":"article_591","result":"created","status":201},{"_id":"ae7f5291-7dea-4e54-86cf-0fdf5e24a1cf","result":"created","status":201}],"processingTimeMs":82,"index_name":"test-index-example"}
[main] INFO org.marqo.ClientUtils - Succeed response http://0.0.0.0:8882/indexes/test-index-example/refresh 200
[main] INFO org.marqo.ClientUtils - Response: {"_shards":{"total":10,"successful":5,"failed":0}}
[main] INFO org.marqo.MarqoPlayground - Query to find document
[main] INFO org.marqo.ClientUtils - Succeed response http://0.0.0.0:8882/indexes/test-index-example/search 200
[main] INFO org.marqo.ClientUtils - Response: {"hits":[{"Description":"Serbia striker Aleksandar Mitrovic netted his 50th international goal as his side outclassed Norway with a 2-0 win to top Nations League B Group 4 on Tuesday and gain promotion to League A.","Title":"Mitro is on fire","_id":"ae7f5291-7dea-4e54-86cf-0fdf5e24a1cf","_highlights":{"Description":"Serbia striker Aleksandar Mitrovic netted his 50th international goal as his side outclassed Norway with a 2-0 win to top Nations League B Group 4 on Tuesday and gain promotion to League A."},"_score":0.7562105},{"Description":"A 13th-century travelogue describing Polo's travels","Title":"The Travels of Marco Polo","_id":"866b1eaa-7284-4bb5-ab48-2944a531aff2","_highlights":{"Description":"A 13th-century travelogue describing Polo's travels"},"_score":0.53588057},{"Description":"The EMU is a spacesuit that provides environmental protection,mobility, life support, and communications for astronauts","Title":"Extravehicular Mobility Unit (EMU)","_id":"article_591","_highlights":{"Description":"The EMU is a spacesuit that provides environmental protection,mobility, life support, and communications for astronauts"},"_score":0.49444443}],"processingTimeMs":203,"query":"Mitrovic scores","limit":10}
[main] INFO org.marqo.MarqoPlayground - end of script
```