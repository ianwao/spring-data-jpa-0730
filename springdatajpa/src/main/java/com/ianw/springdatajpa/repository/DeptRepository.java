package com.ianw.springdatajpa.repository;

import com.ianw.springdatajpa.domain.Dept;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: DeptRepository <br/>
 * Description: <br/>
 * date: 2019/7/31 10:10<br/>
 *
 * @author 72733<br />
 * @since JDK 1.8
 */

public interface DeptRepository  extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept> {

    @Override
    @Modifying
    @Query("select d from Dept d where d.flag = 1 ")
    List<Dept> findAll();

    List<Dept> findByFlagAndParentIsNullOrderByDeptIndexAsc(@Param("flag") int i);

}
