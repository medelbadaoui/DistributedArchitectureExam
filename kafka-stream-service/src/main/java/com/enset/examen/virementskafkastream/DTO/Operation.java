package com.enset.examen.virementskafkastream.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Operation {
    private Long id;
    private Double montant;
    private Date date;
    private String type;
}
