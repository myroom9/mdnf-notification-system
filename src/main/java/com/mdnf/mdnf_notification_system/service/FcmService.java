package com.mdnf.mdnf_notification_system.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FcmService {
    public void sendMessage(String email) {

        // token 가져오고

        Message message = Message.builder()
                .putData("title", "제목입니다")
                .putData("content", "내용입니다")
                .setToken("BEqNFkjIKo1bkhEngEilo8OLB0jWAC42SKe9Pv1a8uyq4wPbdaD30ksWkd67325-BKkqUT64BNDGmJgZT1pLMN4").build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
