package cn.com.taiji.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
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

import cn.com.taiji.domain.Author;
import cn.com.taiji.domain.Book;
import cn.com.taiji.domain.BookRepository;
import cn.com.taiji.domain.Dept;

@Service
public class BookService  {
	@Autowired
	BookRepository bookRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Book book) {
		this.bookRepository.saveAndFlush(book);
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteBook(int id) {
		this.bookRepository.deleteBook(id);
		
		
	}

	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return this.bookRepository.findAll();
	}

	public List<Book> findAllNamedQuery() {
		// TODO Auto-generated method stub
		return bookRepository.findAllNamedQuery();
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateByAuthorId(String bookName,int id) {
		this.bookRepository.updateByAuthorId(bookName,id);
		
	}

	public Map getPage(Map searchParameters) {
		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<Book> pageList;
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
			Specification<Book> spec = new Specification<Book>() {
				@Override
				public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (Map f : filters) {
						String field = f.get("field").toString().trim();
						String value = f.get("value").toString().trim();
						if (value != null && value.length() > 0) {
							if ("bookName".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} else if ("bookIndex".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} else if ("bookUrl".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} 
						}

					}
					// 查询出未删除的
					pl.add(cb.equal(root.<Integer>get("flag"), 1));
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			

			pageList = bookRepository.findAll(spec, pageable);

		} else {
			Specification<Book> spec = new Specification<Book>() {
				public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();
					// 查询出未删除的
					list.add(cb.equal(root.<Integer>get("flag"), 1));
					return cb.and(list.toArray(new Predicate[0]));
				}
			};
			pageList = bookRepository.findAll(spec, pageable);

		}
		map.put("total", pageList.getTotalElements());
		List<Book> list = pageList.getContent();
		 
		map.put("depts", list);
		return map;
		
	}

	
	
	


}
