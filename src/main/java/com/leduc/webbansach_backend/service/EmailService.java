package com.leduc.webbansach_backend.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendMessage(String to, String subject, String from, String text);
}
