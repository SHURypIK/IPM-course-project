package com.example.app.repositories;

import com.example.app.model.Page;
import com.example.app.model.ResidentShort;
import com.example.app.model.Sort;
import com.example.app.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainRepo {

    private final static String URL = "http://localhost:8080/resident/pagingShort";
    private final static String URLSearch = "http://localhost:8080/resident/find_in_pagingShort";
    public static final Map<String, Integer> PARAMS = new HashMap<>();

    static {
        PARAMS.put("page", 0);
        PARAMS.put("size", 20);
    }

    public static Page<ResidentShort> pagingShort(int page) throws Exception {
        PARAMS.put("page", page);
        Sort sort = new Sort(new ArrayList<>(Arrays.asList("FIO")),"ASC");
        Gson gson = new Gson();
        String jsonBody = gson.toJson(sort);

        StringBuilder urlWithParams = new StringBuilder(URL);
        urlWithParams.append("?");
        for (Map.Entry<String, Integer> entry : PARAMS.entrySet()) {
            urlWithParams.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        if (urlWithParams.charAt(urlWithParams.length() - 1) == '&') {
            urlWithParams.deleteCharAt(urlWithParams.length() - 1);
        }

        String string = urlWithParams.toString();



        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(urlWithParams.toString());
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(new StringEntity(jsonBody));
            try (CloseableHttpResponse response = client.execute(httpPut)) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (statusCode == HttpStatus.SC_OK) {
                JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                if (jsonElement.isJsonObject()) {
                    Type pageType = new TypeToken<Page<ResidentShort>>() {}.getType();
                    Page<ResidentShort> residentShortPage = gson.fromJson(jsonElement, pageType);
                    return residentShortPage;
                } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                    String responseString = jsonElement.getAsString();
                    throw new Exception(responseString);
                } else {
                    throw new Exception("Unknown json");
                }
                } else {
                   throw new Exception("Bad request");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Page<ResidentShort> pagingShort(int page, String string) throws Exception {
        PARAMS.put("page", page);
        Sort sort = new Sort(new ArrayList<>(Arrays.asList("FIO")),"ASC");
        Gson gson = new Gson();
        String jsonBody = gson.toJson(sort);

        StringBuilder urlWithParams = new StringBuilder(URLSearch);
        urlWithParams.append("?");
        for (Map.Entry<String, Integer> entry : PARAMS.entrySet()) {
            urlWithParams.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        urlWithParams.append("text=");
        urlWithParams.append(string);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(urlWithParams.toString());
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(new StringEntity(jsonBody));
            try (CloseableHttpResponse response = client.execute(httpPut)) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (statusCode == HttpStatus.SC_OK) {
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonObject()) {
                        Type pageType = new TypeToken<Page<ResidentShort>>() {}.getType();
                        Page<ResidentShort> residentShortPage = gson.fromJson(jsonElement, pageType);
                        return residentShortPage;
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                        String responseString = jsonElement.getAsString();
                    } else {
                        throw new Exception("Unknown json");
                    }
                } else {
                    throw new Exception("Bad request");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

