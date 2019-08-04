package com.ianw.springdatajpa.service;/**
 * ClassName: UserServiceImpl <br/>
 * Description: <br/>
 * date: 2019/7/31 10:12<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.ianw.springdatajpa.domain.Dept;
import com.ianw.springdatajpa.domain.User;
import com.ianw.springdatajpa.repository.DeptRepository;
import com.ianw.springdatajpa.repository.UserResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@program: springdatajpa
 *@description: 服务层
 *@author: tao xujie
 *@create: 2019-07-31 10:12
 */

@Service
public class UserService {

    @Autowired
    UserResposity userResposity;


    public void saveUser(User user) {
        userResposity.saveAndFlush(user);
    }

    public List<User> findByUsernameAndLoginname(String userName, String loginName) {
        List<User> list = userResposity.findByUserNameAndLoginName(userName,loginName);
        return list;
    }

    public User findFirstByOrderByUserNameAsc(){
        User user = userResposity.findTopByOrderByUserNameDesc();
        return user;
    }

    public Page<User>  queryFirst10ByUserName(){
        Pageable pageable = new QPageRequest(1,5);
        Page<User> userPage = userResposity.queryFirst10ByUserName("小明0",pageable);
        return userPage;
    }

    public List<User>  findFirst10ByUserName(){
        //Specification
        Sort sort = new Sort(Sort.Direction.ASC,"UserIndex");
        List<User> userPage = userResposity.findFirst10ByUserName("小明0",sort);
        return userPage;
    }

    public Map getPage(Map searchParameters) {
        Map map = new HashMap();
        int page = 0;
        int pageSize = 10;
        Page<User> pageList;
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
            Sort sort = new Sort(Sort.Direction.ASC, "userIndex");
            pageable = PageRequest.of(page, pageSize, sort);
        }
        Map filter = (Map) searchParameters.get("filter");
        if (filter != null) {
            final List<Map> filters = (List<Map>) filter.get("filters");
            Specification<User> spec = new Specification<User>() {
                @Override
                public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> pl = new ArrayList<Predicate>();
                    for (Map f : filters) {
                        String field = f.get("field").toString().trim();
                        String value = f.get("value").toString().trim();
                        if (value != null && value.length() > 0) {
                            if ("userName".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            } else if ("loadName".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            } else if ("name".equalsIgnoreCase(field)) {
                                pl.add(cb.like(root.<String>get(field), value + "%"));
                            }
                        }
                    }
                    // 查询出未删除的
                    pl.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(pl.toArray(new Predicate[0]));
                }
            };
            pageList = userResposity.findAll(spec, pageable);
        } else {
            Specification<User> spec = new Specification<User>() {
                public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    // 查询出未删除的
                    list.add(cb.equal(root.<Integer>get("flag"), 1));
                    return cb.and(list.toArray(new Predicate[0]));
                }
            };
            pageList = userResposity.findAll(spec, pageable);
        }
        map.put("total", pageList.getTotalElements());
        List<User> list = pageList.getContent();

        map.put("users", list);
        return map;
    }




}