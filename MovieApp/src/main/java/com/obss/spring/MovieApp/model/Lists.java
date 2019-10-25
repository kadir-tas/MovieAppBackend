package com.obss.spring.MovieApp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.obss.spring.MovieApp.model.pk.ListsKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lists {
	
	@EmbeddedId
    private ListsKey listsKey;
	
}
