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