package com.example.app.repositories;

import com.example.app.model.Admin;
import com.example.app.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SignInRepo {

    private static String userUrl = "http://localhost:8080/user/auth";
    private static String adminUrl = "http://localhost:8080/admin/auth";

    public static User enterUser(String login, String password) throws Exception {
        User user = new User(login, password);
        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(userUrl);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(new StringEntity(jsonBody));

            try (CloseableHttpResponse response = client.execute(httpPut)) {
                //int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
//                if (statusCode == HttpStatus.SC_OK) {
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonObject()) {
                        User responseObject = gson.fromJson(jsonElement, User.class);
                        return responseObject;
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isBoolean()) {
                        boolean responseBoolean = jsonElement.getAsBoolean();
                        throw new Exception("Не получилось войти, проверте входные данные");
                    } else {
                        throw new Exception("Unknown json");
                    }
//                } else {
//                   throw new Exception("Bad request");
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Admin enterAdmin(String login, String password) throws Exception {
        Admin admin = new Admin(login, password);
        Gson gson = new Gson();
        String jsonBody = gson.toJson(admin);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(adminUrl);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setEntity(new StringEntity(jsonBody));

            try (CloseableHttpResponse response = client.execute(httpPut)) {
                HttpEntity entity = response.getEntity();
                    JsonElement jsonElement = gson.fromJson(EntityUtils.toString(entity), JsonElement.class);
                    if (jsonElement.isJsonObject()) {
                        Admin responseObject = gson.fromJson(jsonElement, Admin.class);
                        String ak = responseObject.getAccessKey();
                        return responseObject;
                    } else if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isBoolean()) {
                        boolean responseBoolean = jsonElement.getAsBoolean();
                        throw new Exception("Не получилось войти, проверте входные данные");
                    } else {
                        throw new Exception("Unknown json");
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
