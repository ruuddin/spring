
package com.example.cassandra;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

/**
 * A simple Json codec.
 */
public class JsonCodec<T> extends TypeCodec<T> {

    private final ObjectMapper objectMapper = jsonObjectMapper();

    public JsonCodec(
            Class<T> javaType){
        super(DataType.varchar(), javaType);
    }

    @Override
    public ByteBuffer serialize(T value, ProtocolVersion protocolVersion)
            throws InvalidTypeException {
        if (value == null)
            return null;
        try {
            return ByteBuffer.wrap(objectMapper.writeValueAsBytes(value));
        }
        catch (JsonProcessingException e) {
            throw new InvalidTypeException(e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion)
            throws InvalidTypeException {
        if (bytes == null)
            return null;
        try {
            byte[] b = new byte[bytes.remaining()];
            // always duplicate the ByteBuffer instance before consuming it!
            bytes.duplicate().get(b);
            return (T)objectMapper.readValue(b, toJacksonJavaType());
        }
        catch (IOException e) {
            throw new InvalidTypeException(e.getMessage(), e);
        }
    }

    @Override
    public String format(T value) throws InvalidTypeException {
        if (value == null)
            return "NULL";
        String json;
        try {
            json = objectMapper.writeValueAsString(value);
        }
        catch (IOException e) {
            throw new InvalidTypeException(e.getMessage(), e);
        }
        return '\'' + json.replace("\'", "''") + '\'';
    }

    @Override
    @SuppressWarnings("unchecked")
    public T parse(String value) throws InvalidTypeException {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("NULL"))
            return null;
        if (value.charAt(0) != '\'' || value.charAt(value.length() - 1) != '\'')
            throw new InvalidTypeException("JSON strings must be enclosed by single quotes");
        String json = value.substring(1, value.length() - 1).replace("''", "'");
        try {
            return (T)objectMapper.readValue(json, toJacksonJavaType());
        }
        catch (IOException e) {
            throw new InvalidTypeException(e.getMessage(), e);
        }
    }

    protected JavaType toJacksonJavaType() {
        return TypeFactory.defaultInstance().constructType(getJavaType().getType());
    }
    
    private ObjectMapper jsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // ISODate
                .modules(new JSR310Module()).build();
    }
}
