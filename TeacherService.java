package cn.com.taiji.service;

import cn.com.taiji.domain.Teacher;
import cn.com.taiji.jparepository.TeacherRepository;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTeacher(Teacher teacher) {
        this.teacherRepository.saveAndFlush(teacher);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTeacher(int i) {
        this.teacherRepository.updateTeacher(i);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void delTeacher(int i) {
        this.teacherRepository.delTeacher(i);
    }

    public List<Teacher> selectTeacher() {
        List<Teacher> list = this.teacherRepository.findAll();
        return list;
    }

    public List<Teacher> selectTeacherByFlag() {
        List<Teacher> list = this.teacherRepository.finAllTeacher();
        return list;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map getPage(final Map searchParameters) {
        Map map = new HashMap();
        int page = 0;
        int pageSize = 0;
        Page<Teacher> pageList;
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
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if (orderMaps != null) {
            for (Map m : orderMaps) {
                if (m.get("field") == null)
                    continue;
                String field = m.get("field").toString();
                if (!StringUtils.isEmpty(field)) {
                    String dir = m.get("dir").toString();
                    if ("DESC".equalsIgnoreCase(dir)) {
                        orders.add(new Sort.Order(Sort.Direction.DESC, field));
                    } else {
                        orders.add(new Sort.Order(Sort.Direction.ASC, field));
                    }
                }
            }
        }
        PageRequest pageable;
        if (orders.size() > 0) {
            pageable = PageRequest.of(page, pageSize, Sort.by(orders));
        } else {
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            pageable = PageRequest.of(page, pageSize, sort);
        }
        Map filter = (Map) searchParameters.get("filter");
        if (filter != null) {
            final List<Map> filters = (List<Map>) filter.get("filters");
            Specification<Teacher> spec = new Specification<Teacher>() {
                @Override
                public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> pl = new ArrayList<Predicate>();
                    for (Map f : filters) {
                        String field = f.get("field").toString().trim();
                        String value = f.get("value").toString().trim();
                        if (value != null && value.length() > 0) {
                            if ("course".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));}
//                            } else if ("deptType".equalsIgnoreCase(field)) {
//                                pl.add(cb.like(root.<String>get(field), value + "%"));
//                            } else if ("deptUrl".equalsIgnoreCase(field)) {
//                                pl.add(cb.like(root.<String>get(field), value + "%"));
//                            }
                        }

                    }
                    // 查询出未删除的
                    pl.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(pl.toArray(new Predicate[0]));
                }
            };


            pageList = teacherRepository.findAll(spec, pageable);

        } else {
            Specification<Teacher> spec = new Specification<Teacher>() {
                public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    // 查询出未删除的
                    list.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(list.toArray(new Predicate[0]));
                }
            };
            pageList = teacherRepository.findAll(spec, pageable);

        }
        map.put("total", pageList.getTotalElements());
        List<Teacher> list = pageList.getContent();

        map.put("teachers", list);
        return map;
    }
}
