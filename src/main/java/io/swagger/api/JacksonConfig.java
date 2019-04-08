package io.swagger.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
/**
 * Class used to handle the Object mapper which is neccessary for JSON Conversions
 * @author Ryan
 *
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

    private ObjectMapper objectMapper;

    public JacksonConfig() throws Exception {
        this.objectMapper = new ObjectMapper();

        this.objectMapper.registerModule(new JodaModule());

        // sample to convert any DateTime to readable timestamps
        // this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
        // true);
    }
    /**
     * return a refrence to the Instantiated ObjectMapper, used in Json packing and unpacking
     */
    public ObjectMapper getContext(Class<?> objectType) {
        return objectMapper;
    }
}