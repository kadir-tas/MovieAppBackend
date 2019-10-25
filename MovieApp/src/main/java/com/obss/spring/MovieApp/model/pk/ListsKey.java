package com.obss.spring.MovieApp.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ListsKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@NotNull
	private String name;

	@NotNull
	private Long movieId;

	@NotNull
	private String type;

	@Override
	public boolean equals(Object o) {
		// general check
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;

		ListsKey that = (ListsKey) o;

		// property check
		if (!this.name.equals(that.name) 
				|| !this.type.equals(that.type))
			return false;
		return movieId.equals(that.movieId);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + movieId.hashCode();
		result = 31 * result + type.hashCode();
		return result;
	}
}
