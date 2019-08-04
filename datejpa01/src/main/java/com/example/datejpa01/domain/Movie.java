package com.example.datejpa01.domain;

import javax.persistence.*;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name="movie_name",length=30)
	private String MovieName;
	@Column(scale=2)
	private Float price;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="director_id")
	private Director director;
	@Column(name="date")
	private String date;
	@Column(name = "movie_index")
	private String movieIndex;
	private Integer flag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMovieName() {
		return MovieName;
	}

	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMovieIndex() {
		return movieIndex;
	}

	public void setMovieIndex(String movieIndex) {
		this.movieIndex = movieIndex;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public void Director(Director director) {
	}
}
