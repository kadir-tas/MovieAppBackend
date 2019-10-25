package com.obss.spring.MovieApp.controller.request;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImdbRequest {
	@NotNull
	private String movieName;
}
