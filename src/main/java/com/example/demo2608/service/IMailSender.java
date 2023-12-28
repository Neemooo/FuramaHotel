package com.example.demo2608.service;

public interface IMailSender {
      void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment);
}
