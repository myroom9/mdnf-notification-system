package com.mdnf.mdnf_notification_system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.gson.JsonParseException;
import com.mdnf.mdnf_notification_system.dto.FcmMessage;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FcmService {

    @Value("${fcm.certification}")
    private String googleApplicationCredential;

    private final ObjectMapper objectMapper;
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/mdnf-notification-system/messages:send";

    public void sendMessage(String fcmTitle, String fcmMessage, String token) throws IOException {
        String message = makeMessage(token, fcmTitle, fcmMessage);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());

/*        Message message = Message.builder()
                .putData("title", fcmTitle)
                .putData("content", fcmMessage)
                .setToken(token).build();

        FirebaseMessaging.getInstance().sendAsync(message);*/
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
        String googleApplicationCredential1 = googleApplicationCredential;
        System.out.println("googleApplicationCredential1 = " + googleApplicationCredential1);

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(googleApplicationCredential).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/firebase.messaging"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
