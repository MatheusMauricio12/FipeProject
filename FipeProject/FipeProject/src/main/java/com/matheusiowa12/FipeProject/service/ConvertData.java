package com.matheusiowa12.FipeProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertData implements IConvertData{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obtainData(String json, Class<T> objectClass){
        try {
            return mapper.readValue(json, objectClass);
        } catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obtainList(String json, Class<T> objectClass) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, objectClass);
        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
