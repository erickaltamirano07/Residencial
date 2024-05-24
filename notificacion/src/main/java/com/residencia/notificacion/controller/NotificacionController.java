package com.residencia.notificacion.controller;

import com.residencia.notificacion.model.db.Notificacion;
import com.residencia.notificacion.service.NotificacionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class NotificacionController {

    @Autowired
    private NotificacionService emailService;

    // Sending a simple Email
    @PostMapping("/notificacion")
    public String
    notificacion(@RequestBody Notificacion notificacionRequest)
    {
        String status= emailService.sendSimpleMail(notificacionRequest);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/notificacionWithAttachment")
    public String notificacionWithAttachment(
            @RequestBody Notificacion notificacionRequest)
    {
        String status = emailService.sendMailWithAttachment(notificacionRequest);
        return status;
    }

}
