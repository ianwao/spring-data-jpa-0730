package com.example.datejpa01.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>,JpaSpecificationExecutor<Movie>{


	@Modifying
	@Query("update  Movie b  set flag=0 where b.id=:id")
	public void deleteMovie(@Param("id") int id);

	@Query("select b from Movie b where flag=1")
	public List<Movie> findAllNamedQuery();

	@Modifying
	@Query("update  movie b  set movieName=:movieName where b.id=:id")
	public void updateByDirectorId(@Param("movieName") String movieName, @Param("id") int id);

}
