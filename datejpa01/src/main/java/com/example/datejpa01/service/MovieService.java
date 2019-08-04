package com.example.datejpa01.service;


import com.example.datejpa01.domain.Movie;
import com.example.datejpa01.domain.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Movie movie) {
		this.movieRepository.saveAndFlush(movie);
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMovie(int id) {
		this.movieRepository.deleteMovie(id);
		
		
	}

	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return this.movieRepository.findAll();
	}

	public List<Movie> findAllNamedQuery() {
		// TODO Auto-generated method stub
		return movieRepository.findAllNamedQuery();
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateByDirectorId(String movieName,int id) {
		this.movieRepository.updateByDirectorId(movieName,id);
		
	}

	public Map getPage(Map searchParameters) {
		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<Movie> pageList;
		if (searchParameters != null && searchParameters.size() > 0 && searchParameters.get("page") != null) {
			page = Integer.parseInt(searchParameters.get("page").toString()) - 1;
		}
		if (searchParameters != null && searchParameters.size() > 0 && searchParameters.get("pageSize") != null) {
			pageSize = Integer.parseInt(searchParameters.get("pageSize").toString());
		}
		if (pageSize < 1)
			pageSize = 1;
		if (pageSize > 100)
			pageSize = 100;
		List<Map> orderMaps = (List<Map>) searchParameters.get("sort");
		List<Order> orders = new ArrayList<Order>();
		if (orderMaps != null) {
			for (Map m : orderMaps) {
				if (m.get("field") == null)
					continue;
				String field = m.get("field").toString();
				if (!StringUtils.isEmpty(field)) {
					String dir = m.get("dir").toString();
					if ("DESC".equalsIgnoreCase(dir)) {
						orders.add(new Order(Direction.DESC, field));
					} else {
						orders.add(new Order(Direction.ASC, field));
					}
				}
			}
		}
		PageRequest pageable;
		if (orders.size() > 0) {
			pageable =  PageRequest.of(page, pageSize, Sort.by(orders));
		} else {
			Sort sort = new Sort(Direction.ASC, "bookIndex");
			pageable = PageRequest.of(page, pageSize, sort);
		}
		Map filter = (Map) searchParameters.get("filter");
		if (filter != null) {
			final List<Map> filters = (List<Map>) filter.get("filters");
			Specification<Movie> spec = new Specification<Movie>() {
				@Override
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (Map f : filters) {
						String field = f.get("field").toString().trim();
						String value = f.get("value").toString().trim();
						if (value != null && value.length() > 0) {
							if ("movieName".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} else if ("movieIndex".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} else if ("movieUrl".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} 
						}

					}
					// 查询出未删除的
					pl.add(cb.equal(root.<Integer>get("flag"), 1));
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			

			pageList = movieRepository.findAll(spec, pageable);

		} else {
			Specification<Movie> spec = new Specification<Movie>() {
				public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();
					// 查询出未删除的
					list.add(cb.equal(root.<Integer>get("flag"), 1));
					return cb.and(list.toArray(new Predicate[0]));
				}
			};
			pageList = movieRepository.findAll(spec, pageable);

		}
		map.put("total", pageList.getTotalElements());
		List<Movie> list = pageList.getContent();
		 
		map.put("depts", list);
		return map;
		
	}

	
	
	


}
