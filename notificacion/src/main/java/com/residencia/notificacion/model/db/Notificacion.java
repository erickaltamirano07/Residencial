package com.residencia.notificacion.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
