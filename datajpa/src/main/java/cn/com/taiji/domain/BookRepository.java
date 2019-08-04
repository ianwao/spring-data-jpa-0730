package cn.com.taiji.domain;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer>,JpaSpecificationExecutor<Book>{
	
	
	@Modifying
	@Query("update  Book b  set flag=0 where b.id=:id")
	public void deleteBook(@Param("id") int id);
	
	@Query("select b from Book b where flag=1")
	public List<Book> findAllNamedQuery();

	@Modifying
	@Query("update  Book b  set bookName=:bookName where b.id=:id")
	public void updateByAuthorId(@Param("bookName")String bookName,@Param("id") int id);

}
