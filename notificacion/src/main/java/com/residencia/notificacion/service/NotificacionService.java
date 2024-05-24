package com.residencia.notificacion.service;

import com.residencia.notificacion.model.db.Notificacion;

public interface NotificacionService {
    String sendSimpleMail(Notificacion notificacionRequest);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(Notificacion notificacionRequest);
}
