package com.example.app.repositories;

import com.example.app.model.AdditionalCondition;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConditionRepo {



    private final static String UpdateURL = "http://localhost:8080/additional_condition/update/";
    private final static String GetURL = "http://localhost:8080/additional_condition/all";
    private final static String DeleteURL = "http://localhost:8080/additional_condition/delete/";
    private final static String AddURL = "http://localhost:8080/additional_condition/add";


    public static AdditionalCondition add(AdditionalCondition condition) throws Exception {
        Gson gson = new Gson();

        String jsonBody = gson.toJson(condition);
        StringEntity stringEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(AddURL);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(stringEntity);
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (statusCode == HttpStatus.SC_OK) {
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonObject()) {
                        AdditionalCondition condition1 = gson.fromJson(jsonElement, AdditionalCondition.class);
                        return condition1;
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                        String responseString = jsonElement.getAsString();
                        throw new Exception(responseString);
                    } else {
                        throw new Exception("Unknown json");
                    }
                } else {
                    throw new Exception(response.getEntity().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AdditionalCondition update(AdditionalCondition condition, int id) throws Exception {
        Gson gson = new Gson();

        StringBuilder urlWithParams = new StringBuilder(UpdateURL);
        urlWithParams.append(id);
        String string = urlWithParams.toString();

        String jsonBody = gson.toJson(condition);
        StringEntity stringEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(string);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(stringEntity);
            try (CloseableHttpResponse response = client.execute(httpPut)) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (statusCode == HttpStatus.SC_OK) {
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonObject()) {
                        AdditionalCondition condition1 = gson.fromJson(jsonElement, AdditionalCondition.class);
                        return condition1;
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                        String responseString = jsonElement.getAsString();
                        throw new Exception(responseString);
                    } else {
                        throw new Exception("Unknown json");
                    }
                } else {
                    throw new Exception(response.getEntity().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean delete(int id) throws Exception {
        Gson gson = new Gson();


        StringBuilder urlWithParams = new StringBuilder(DeleteURL);
        urlWithParams.append(id);
        String string = urlWithParams.toString();


        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(string);
            httpDelete.setHeader("Accept", "application/json");
            httpDelete.setHeader("Content-Type", "application/json");
            try (CloseableHttpResponse response = client.execute(httpDelete)) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (statusCode == HttpStatus.SC_OK) {
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isBoolean()) {
                        boolean result = jsonElement.getAsBoolean();
                        return result;
                    } else {
                        throw new Exception("Unknown json");
                    }
                } else {
                    throw new Exception(response.getEntity().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<AdditionalCondition> get() throws Exception {
        Gson gson = new Gson();
        Type conditionListType = new TypeToken<ArrayList<AdditionalCondition>>(){}.getType();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(GetURL);
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Content-Type", "application/json");
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                if (statusCode == HttpStatus.SC_OK) {
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonArray()) {
                        ArrayList<AdditionalCondition> conditions = gson.fromJson(jsonElement, conditionListType);
                        return conditions;
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()) {
                        String responseString = jsonElement.getAsString();
                        throw new Exception(responseString);
                    } else {
                        throw new Exception("Unknown json");
                    }
                } else {
                    throw new Exception(response.getEntity().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
