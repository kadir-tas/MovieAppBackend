package com.obss.spring.MovieApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obss.spring.MovieApp.model.Lists;
import com.obss.spring.MovieApp.model.pk.ListsKey;

@Repository
public interface ListRepository extends JpaRepository<Lists, ListsKey> {
	public List<Lists> findByListsKeyNameAndListsKeyType(String name, String type);
}
