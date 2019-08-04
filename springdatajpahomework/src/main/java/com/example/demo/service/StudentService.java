package com.example.demo.service;

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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Student;
import com.example.demo.domain.Student;
import com.example.demo.domain.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)//开启事务，查询不需要
	public void saveStudent(Student student) {
		this.studentRepository.saveAndFlush(student);
	}
	
	public Student findStudent(Integer id) {
		return this.studentRepository.findOne(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteStudennt(Student student) {
		this.studentRepository.delete(student);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateStudent(Integer age,Integer id) {
		this.studentRepository.updateStudent(age, id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateFlagStudent(Integer flag,Integer id) {
		this.studentRepository.updateFlagStudent(flag, id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map getPage(final Map searchParameters) {
		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<Student> pageList;
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
			pageable =new PageRequest(page, pageSize,new Sort(orders));
		} else {
			Sort sort = new Sort(Direction.ASC, "studentIndex");
			pageable =new PageRequest(page, pageSize, sort);
		}
		Map filter = (Map) searchParameters.get("filter");
		if (filter != null) {
			final List<Map> filters = (List<Map>) filter.get("filters");
			//查询构造器，  filters是查询条件
			Specification<Student> spec = new Specification<Student>() {
				@Override
				public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (Map f : filters) {
						String field = f.get("field").toString().trim();
						String value = f.get("value").toString().trim();
						if (value != null && value.length() > 0) {
							if ("studentName".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							} else if ("age".equalsIgnoreCase(field)) {
								pl.add(cb.like(root.<String>get(field), value + "%"));
							}
						}

					}
					// 查询出未删除的
					pl.add(cb.equal(root.<Integer>get("flag"), 1));
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			

			pageList = studentRepository.findAll(spec, pageable);

		} else {
			Specification<Student> spec = new Specification<Student>() {
				public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> list = new ArrayList<Predicate>();
					// 查询出未删除的
					list.add(cb.equal(root.<Integer>get("flag"), 1));
					return cb.and(list.toArray(new Predicate[0]));
				}
			};
			pageList = studentRepository.findAll(spec, pageable);

		}
		map.put("total", pageList.getTotalElements());
		List<Student> list = pageList.getContent();
		 
		map.put("Students", list);
		return map;
	}
	
	/**
	 * 
	   * @return 
	 * @Description 没有查询条件的分页查询
	   * @Author 范彬慧
	   * @CreatDate 2019年8月4日
	   * @UpdateUser 范彬慧
	   * @UpdateDate 2019年8月4日
	   * @throws
	   * @param pageable  返回值void
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Student> PageStudent(Pageable pageable) {
		return this.studentRepository.findAll(pageable);
	}

	
	
	
}
