package com.obss.spring.MovieApp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddListRequest {

	private Long movieId;
	
	private String name;
	
}
