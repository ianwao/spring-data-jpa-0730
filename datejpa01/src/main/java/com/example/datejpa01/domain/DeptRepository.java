package com.example.datejpa01.domain;


import com.example.datejpa01.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeptRepository  extends JpaRepository<Dept, Integer>, JpaSpecificationExecutor<Dept> {
     @Query("select d from Dept d where d.id=:id")
    public Optional<Dept> findById(@Param("id")Integer id);
    @Modifying
    @Query("update Dept d set d.deptName=:deptName1 where d.id=:id")
    public void updateDeptNameById(@Param("deptName1")String deptName,@Param("id")Integer id);

    @Modifying
    @Query("update Dept d set d.flag=0  where d.id=:id")
    public void updateFlagById(@Param("id")Integer id);

    List<Dept> findByFlagAndParentIsNullOrderByDeptIndexAsc(@Param("flag") int i);

    Dept findFirstByOrderByIdDesc();
    List<Dept> findFirst10ByDeptName(String deptName);
}
