package cn.com.taiji.jparepository;

import cn.com.taiji.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQuery;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer>,JpaSpecificationExecutor<Teacher> {
    @Modifying
    @Query("update Teacher t set t.name='惠佳' where t.id=:id")
    void updateTeacher(@Param("id") int i);

    @Modifying
    @Query("update Teacher t set t.flag=0 where t.id=:id")
    void delTeacher(@Param("id") int i);

    List<Teacher> finAllTeacher();
}
