package com.stackroute.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "db_sequence")
public class DbSequence {

	 @Id
	    private String id;

	    private Long seq;



	}
