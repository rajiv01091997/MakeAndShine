package com.stackroute.appointmentservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "db_sequence")
public class DbSequence {

	
      @Id
     private String id;
     private long seq;

    public long getSeq() {
        return seq;
    }
}
