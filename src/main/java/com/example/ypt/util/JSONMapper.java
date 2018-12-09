package com.example.ypt.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.elasticsearch.common.Strings;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author wencheng wang
 */
public final class JSONMapper {
    private final static ObjectMapper MAPPER = init();

    private static ObjectMapper init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.addMixIn(Object.class, ObjectFilterMixIn.class);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //java 8 date
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        mapper.registerModule(javaTimeModule);
        return mapper;
    }

    public static ObjectMapper objectMapper() {
        return MAPPER.copy().setMixIns(null);
    }

    private JSONMapper() {

    }

    public static void writeValue(Writer w, Object value) throws IOException {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept();
        SimpleFilterProvider ignore = new SimpleFilterProvider().addFilter("objectFilter", simpleBeanPropertyFilter);
        MAPPER.writer(ignore).writeValue(w, value);
    }

    public static <T> T binding(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T binding(String json, Type type) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructType(type));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T bindingParametrized(String json, Class<T> tClass, Class<?>... parameterClasses) {
        try {
            if (Strings.isNullOrEmpty(json)) {
                return null;
            }
            if (ObjectUtils.isEmpty(parameterClasses)) {
                return binding(json, tClass);
            }
            if (parameterClasses.length == 1) {
                return MAPPER.readValue(json, MAPPER.getTypeFactory().constructParametricType(tClass, parameterClasses));
            }
            int length = parameterClasses.length;
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(parameterClasses[length - 2], parameterClasses[length - 1]);
            int index = length - 3;
            while (index >= 0) {
                javaType = MAPPER.getTypeFactory().constructParametricType(parameterClasses[index], javaType);
                index--;
            }
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructParametricType(tClass, javaType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> list(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructParametricType(List.class, clazz));
        } catch (JsonParseException | JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> map(String json, Class<K> k, Class<V> v) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructParametricType(Map.class, k, v));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String json(Object object, String... ignoreProperties) {
        try {

            SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(ignoreProperties);
            SimpleFilterProvider ignore = new SimpleFilterProvider().addFilter("objectFilter", simpleBeanPropertyFilter);
            ObjectWriter writer = MAPPER.writer(ignore);

            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String jsonWithin(Object object, String... withinProperties) {
        try {
            SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(withinProperties);
            SimpleFilterProvider within = new SimpleFilterProvider().addFilter("objectFilter", simpleBeanPropertyFilter);
            ObjectWriter writer = MAPPER.writer(within);

            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonFilter("objectFilter")
    interface ObjectFilterMixIn {
    }

}
