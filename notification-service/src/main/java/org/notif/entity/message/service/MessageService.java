package org.notif.entity.message.service;

import org.notif.entity.message.Message;

public interface MessageService {

    Message createMessage(String message, String email);
}
