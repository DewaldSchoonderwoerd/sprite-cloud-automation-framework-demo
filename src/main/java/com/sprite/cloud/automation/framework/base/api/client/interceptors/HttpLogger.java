package com.sprite.cloud.automation.framework.base.api.client.interceptors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HttpLogger implements ClientRequestFilter, ClientResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(HttpLogger.class);
    private long startTime;

    @Override
    public void filter(ClientRequestContext requestContext) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String reqHeaders = gson.toJson(toMapConverter(requestContext.getHeaders()));

        String path = requestContext.getUri().toString();

        String method = requestContext.getMethod();
        LOG.info("========================== Request =========================================");
        LOG.info("Request: " + method + " ->" + path);
        LOG.info("RequestHeader: \n" + reqHeaders);
        Object entity = requestContext.getEntity();

        if (null != entity) {
            if (entity instanceof MetadataMap || entity instanceof String) {
                LOG.info("RequestBody: \n" + prettyString(entity.toString()));
            } else {
                LOG.info("RequestBody: \n" + prettyString(new Gson().toJson(entity)));
            }
        }
        startTime = System.currentTimeMillis();
    }

    private static Map<String, String> toMapConverter(final MultivaluedMap<String, Object> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        long endTime = System.currentTimeMillis();
        LOG.info("========================= Response ===========================================");
        double inSec = (double) (endTime - startTime) / 1000.00;
        String output = "Response time: " + String.format("%.2f", inSec) + " sec";
        int statusCode = responseContext.getStatus();
        if (statusCode == 200 || statusCode == 201) {
            LOG.info("Status code : " + statusCode);
        } else {
            LOG.warn("Status code : " + statusCode);
        }
        if (inSec >= 10) {
            LOG.error(output);
        } else if (inSec >= 3) {
            LOG.warn(output);
        } else {
            LOG.info(output);
        }
        if (Objects.nonNull(responseContext.getEntityStream())) {
            String response = getBody(responseContext);
            LOG.info("ResponseBody: \n" + prettyString(response));
        }
    }

    private String getBody(final ClientResponseContext responseContext) throws IOException {
        final InputStream stream = responseContext.getEntityStream();
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            final byte[] buffer = new byte[1024];
            int length = stream.read(buffer);

            while (length != -1) {
                result.write(buffer, 0, length);
                length = stream.read(buffer);
            }
            responseContext.setEntityStream(new ByteArrayInputStream(result.toByteArray()));
            return result.toString(StandardCharsets.UTF_8.toString());
        }
    }

    private static String prettyString(String uglyJSONString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            JsonElement jsonElement = JsonParser.parseString(uglyJSONString);
            return gson.toJson(jsonElement);
        } catch (Exception e) {
            return uglyJSONString;
        }
    }
}
