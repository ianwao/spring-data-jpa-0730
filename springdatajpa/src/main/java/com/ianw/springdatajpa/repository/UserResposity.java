package com.ianw.springdatajpa.repository;

import com.ianw.springdatajpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ClassName: UserResposity <br/>
 * Description: <br/>
 * date: 2019/7/31 10:08<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */
public interface UserResposity  extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /*根据名称查询*/
    List<User> findByUserNameAndLoginName(@Param("username") String userName,@Param("loginName") String loginName);

    /*查询前10条数据我们可以写作：*/
    List<User> findFirst10ByUserName(String lastName);

    /*有时候我们只需要查询第一个实体。*/
    User findFirstByOrderByUserNameAsc();

    /*查询排序之后的第一根数据*/
    User findTopByOrderByUserNameDesc();


    Page<User> queryFirst10ByUserName(String lastName, Pageable pageable);

    List<User> findFirst10ByUserName(String lastName, Sort sort);

    List< User > findTop10ByUserName(String lastname, Pageable pageable);

    @Override
    Page<User> findAll(Specification<User> specification, Pageable pageable);
}
