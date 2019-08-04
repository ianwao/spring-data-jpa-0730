package com.example.datejpa01.service;

import com.example.datejpa01.domain.Dept;
import com.example.datejpa01.domain.DeptRepository;
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
import java.beans.Transient;
import java.util.*;

/**
 * ClassName:
 *
 * @Description:
 * @Author wanghw
 * @CreatDate
 */
@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveDept(Dept dept) {
        this.deptRepository.saveAndFlush(dept);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dept> findAllDepts(){
        List<Dept> list = new ArrayList<>();
        Dept dept =new Dept();
        return list;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dept> findByFlag(int i){
        List<Dept> list = this.deptRepository.findByFlagAndParentIsNullOrderByDeptIndexAsc(i);
        return list;
    }
    //@Transactional(propagation = Propagation.REQUIRED)
   // public void updateDeptById(int i) {
      //  List<Dept> list = this.findByFlag()
   // }
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map getPage(final Map searchParameters) {
        Map map = new HashMap();
        int page = 0;
        int pageSize = 10;
        Page<Dept> pageList;
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
            pageable =  PageRequest.of(page, pageSize,  Sort.by(orders));
        } else {
            Sort sort = new Sort(Sort.Direction.ASC, "deptIndex");
            pageable = PageRequest.of(page, pageSize, sort);
        }
        Map filter = (Map) searchParameters.get("filter");
        if (filter != null) {
            final List<Map> filters = (List<Map>) filter.get("filters");
            Specification<Dept> spec = new Specification<Dept>() {
                @Override
                public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> pl = new ArrayList<Predicate>();
                    for (Map f : filters) {
                        String field = f.get("field").toString().trim();//动态实体
                        String value = f.get("value").toString().trim();//
                        if (value != null && value.length() > 0) {
                            if ("deptName".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            } else if ("deptType".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            } else if ("deptUrl".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            }
                        }

                    }
                    // 查询出未删除的
                    pl.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(pl.toArray(new Predicate[0]));
                }
            };


            pageList = deptRepository.findAll(spec, pageable);

        } else {
            Specification<Dept> spec = new Specification<Dept>() {
                public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    // 查询出未删除的
                    list.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(list.toArray(new Predicate[0]));
                }
            };
            pageList = deptRepository.findAll(spec, pageable);

        }
        map.put("total", pageList.getTotalElements());
        List<Dept> list = pageList.getContent();

        map.put("depts", list);
        return map;
    }
    public Optional<Dept> findById(int id) {
        return deptRepository.findById(id);

    }

    public void updateDeptNameById(String deptName, int id) {
    }

    public void updateFlagById(int id) {
    }


    public List<Dept> findByFlagAndParentIsNullOrderByDeptIndexAsc(int i) {
        return deptRepository.findByFlagAndParentIsNullOrderByDeptIndexAsc(i);
    }

    public Dept findFirstByOrderByIdDesc() {
        return deptRepository.findFirstByOrderByIdDesc();
    }


}
