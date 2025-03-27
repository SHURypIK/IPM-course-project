package com.example.app.repositories;

import com.example.app.LocalDateAdapter;
import com.example.app.model.Resident;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class ResidentRepo {

    private final static String UpdateURL = "http://localhost:8080/resident/update/";
    private final static String UpdateWithIdURL = "http://localhost:8080/resident/withId/";
    private final static String DeleteURL = "http://localhost:8080/resident/delete/";
    private final static String AddURL = "http://localhost:8080/resident/add";
    private final static String GetURL = "http://localhost:8080/resident/all";

    public static Resident add(Resident resident) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        String jsonBody = gson.toJson(resident);
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
                        Resident resident1 = gson.fromJson(jsonElement, Resident.class);
                        return resident1;
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

    public static Resident update(Resident resident, String id) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        StringBuilder urlWithParams = new StringBuilder(UpdateURL);

        String encodedId = id.replace(" ", "%20");

        urlWithParams.append(encodedId);
        String string = urlWithParams.toString();

        String jsonBody = gson.toJson(resident);
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
                        Resident resident1 = gson.fromJson(jsonElement, Resident.class);
                        return resident1;
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

    public static Resident updateWithId(Resident resident, String id) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        String encodedId = id.replace(" ", "%20");

        StringBuilder urlWithParams = new StringBuilder(UpdateWithIdURL);
        urlWithParams.append(encodedId);
        String string = urlWithParams.toString();

        String jsonBody = gson.toJson(resident);
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
                        Resident resident1 = gson.fromJson(jsonElement, Resident.class);
                        return resident1;
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

    public static Boolean delete(String id) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        String encodedId = id.replace(" ", "%20");

        StringBuilder urlWithParams = new StringBuilder(DeleteURL);
        urlWithParams.append(encodedId);
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

    public static ArrayList<Resident> get() throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        Type reaidentListType = new TypeToken<ArrayList<Resident>>(){}.getType();

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
                        ArrayList<Resident> residents = gson.fromJson(jsonElement, reaidentListType);
                        return residents;
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
