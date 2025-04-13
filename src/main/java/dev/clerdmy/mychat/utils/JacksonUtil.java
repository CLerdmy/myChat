package dev.clerdmy.mychat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.clerdmy.mychat.storage.Data;

import java.io.File;
import java.io.IOException;

public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final File dataFile = new File("src/main/resources/data.json");

    public static Data loadData() throws IOException {
        if (!dataFile.exists() || dataFile.length() == 0) {
            Data emptyData = new Data();
            saveData(emptyData);
            return emptyData;
        } else {
            return objectMapper.readValue(dataFile, Data.class);
        }
    }

    public static void saveData(Data data) throws IOException {
        objectMapper.writeValue(dataFile, data);
    }

}
