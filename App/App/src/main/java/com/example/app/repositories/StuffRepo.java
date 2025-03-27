package com.example.app.repositories;

import com.example.app.AppState;
import com.example.app.LocalDateAdapter;
import com.example.app.model.MedicalReport;
import com.example.app.model.Position;
import com.example.app.model.ResponsiblePerson;
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
import java.util.ArrayList;

public class StuffRepo {



    private final static String UpdateURL = "http://localhost:8080/responsible_person/update/";
    private final static String GetURL = "http://localhost:8080/responsible_person/all";
    private final static String DeleteURL = "http://localhost:8080/responsible_person/delete/";
    private final static String AddURL = "http://localhost:8080/responsible_person/add";
    private final static String UpdateWithIdURL = "http://localhost:8080/responsible_person/withId/";


    public static ResponsiblePerson add(ResponsiblePerson person) throws Exception {
        Gson gson = new Gson();

        String jsonBody = gson.toJson(person);
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
                        ResponsiblePerson person1 = gson.fromJson(jsonElement, ResponsiblePerson.class);
                        return person1;
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

    public static ResponsiblePerson update(ResponsiblePerson person, String id) throws Exception {
        Gson gson = new Gson();

        String encodedId = id.replace(" ", "%20");

        StringBuilder urlWithParams = new StringBuilder(UpdateURL);
        urlWithParams.append(encodedId);
        String string = urlWithParams.toString();

        String jsonBody = gson.toJson(person);
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
                        ResponsiblePerson person1 = gson.fromJson(jsonElement, ResponsiblePerson.class);
                        return person1;
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
        Gson gson = new Gson();

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

    public static ArrayList<ResponsiblePerson> get() throws Exception {
        Gson gson = new Gson();
        Type stuffListType = new TypeToken<ArrayList<ResponsiblePerson>>(){}.getType();

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
                        ArrayList<ResponsiblePerson> stuff = gson.fromJson(jsonElement, stuffListType);
                        return stuff;
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

    public static ResponsiblePerson updateWithId(ResponsiblePerson person, String id) throws Exception {
        Gson gson = new Gson();

        String encodedId = id.replace(" ", "%20");

        StringBuilder urlWithParams = new StringBuilder(UpdateWithIdURL);
        urlWithParams.append(encodedId);
        String string = urlWithParams.toString();

        String jsonBody = gson.toJson(person);
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
                        ResponsiblePerson person1 = gson.fromJson(jsonElement, ResponsiblePerson.class);
                        return person1;
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
