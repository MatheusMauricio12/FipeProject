package com.matheusiowa12.FipeProject.service;

import java.util.List;

public interface IConvertData {
    <T> T obtainData(String json, Class<T> objectClass);

    <T> List<T> obtainList(String json, Class<T> objectClass);
}
