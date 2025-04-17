package dev.clerdmy.mychat.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequest<T> {

    private final ObjectMapper objectMapper;

    protected AbstractRequest() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    protected abstract File getFile();

    protected abstract TypeReference<List<T>> getTypeReference();

    protected List<T> loadAll() throws IOException {
        File file = getFile();
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, getTypeReference());
    }

    protected void saveAll(List<T> data) throws IOException {
        objectMapper.writeValue(getFile(), data);
    }

    protected void saveOne(T item) throws IOException {
        List<T> all = loadAll();
        all.add(item);
        saveAll(all);
    }

}
