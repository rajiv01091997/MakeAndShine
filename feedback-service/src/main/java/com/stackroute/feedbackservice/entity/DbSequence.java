package com.stackroute.feedbackservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "db_sequence")
public class DbSequence {

	
      @Id
     private String id;
     private long seq;


	}
