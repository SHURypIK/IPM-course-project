package com.example.app.repositories;

import com.example.app.AppState;
import com.example.app.LocalDateAdapter;
import com.example.app.model.LeaseContract;
import com.example.app.model.MedicalReport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDate;

public class MedicalReportRepo {

    private final static String UpdateURL = "http://localhost:8080/medical_report/update/";
    private final static String UpdateWithIdURL = "http://localhost:8080/medical_report/withId/";
    private final static String DeleteURL = "http://localhost:8080/medical_report/delete/";
    private final static String AddURL = "http://localhost:8080/medical_report/add";


    public static MedicalReport add(MedicalReport report) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        report.setPatientFIO(AppState.getInstance().getResident().getFio());
        String jsonBody = gson.toJson(report);
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
                        MedicalReport report1 = gson.fromJson(jsonElement, MedicalReport.class);
                        return report1;
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

    public static MedicalReport update(MedicalReport report, String id) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        StringBuilder urlWithParams = new StringBuilder(UpdateURL);
        urlWithParams.append(id);
        String string = urlWithParams.toString();

        report.setPatientFIO(AppState.getInstance().getResident().getFio());
        String jsonBody = gson.toJson(report);
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
                        MedicalReport report1 = gson.fromJson(jsonElement, MedicalReport.class);
                        return report1;
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

    public static MedicalReport updateWithId(MedicalReport report, String id) throws Exception {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        StringBuilder urlWithParams = new StringBuilder(UpdateWithIdURL);
        urlWithParams.append(id);
        String string = urlWithParams.toString();

        report.setPatientFIO(AppState.getInstance().getResident().getFio());
        String jsonBody = gson.toJson(report);
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
                        MedicalReport report1 = gson.fromJson(jsonElement, MedicalReport.class);
                        return report1;
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

}
