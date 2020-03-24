# This is a generic Dockerfile for adding wiremock mapping and file resources to a wiremock image.
# All that is required is that a 'mappings' directory and '__files' directory exist in 'src/main/resources/wiremock'.
# Alternatively, these directories can be placed in 'src/test/resources/wiremock', with the Maven Resource lugin configured to capture them.
FROM rodolpheche/wiremock:2.26.0-alpine

# Add our wiremock mapping and response files to wiremocks default run path.
# No other configuration is required!
ADD src/test/resources/wiremock/mappings /home/wiremock/mappings/
ADD src/test/resources/wiremock/__files/ /home/wiremock/__files/

EXPOSE 8080

CMD ["--global-response-templating","--port","8080","--verbose"]

# Run wget at a regular interval to check that the service is healthy.
HEALTHCHECK --interval=10s CMD ["sh", "-c", "wget http://localhost:8080/__admin/mappings -O /dev/null"]
