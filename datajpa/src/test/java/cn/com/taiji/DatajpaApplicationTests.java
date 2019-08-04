package cn.com.taiji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.domain.Author;
import cn.com.taiji.domain.Book;
import cn.com.taiji.domain.Dept;
import cn.com.taiji.domain.DeptUser;
import cn.com.taiji.domain.DeptUserPK;
import cn.com.taiji.domain.User;
import cn.com.taiji.service.BookService;
import cn.com.taiji.service.DeptService;
import cn.com.taiji.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatajpaApplicationTests {

	private final Logger log = LoggerFactory.getLogger(DatajpaApplicationTests.class);
	@Autowired
	UserService userService;
	@Autowired
	DeptService deptService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	BookService bookService;
	
	
	//以下为课堂练习内容，作业测试内容从288行开始，作业所用实体Book(书籍),Author(作者)，关系为1-n
	
/**
 * 
   * @Description 保存Dept
   * @Author hdj
   * @CreatDate 2019年7月31日
   * @UpdateUser 方法更新者
   * @UpdateDate 2019年7月31日
   * @throws  返回值void
 */
	@Ignore
	@Test
	public void test1() {
		for(int i=0;i<9;i++) {
		Dept dept = new Dept();
		dept.setDeptName("机构"+i);
		dept.setFlag(1);
		deptService.saveDept(dept);
		}
	}
	
	/**
	 * 
	   * @Description 查询所有dept
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @UpdateUser 方法更新者
	   * @UpdateDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test2() {	
		List<Dept> dlist=deptService.finsAllDept();
		log.info(dlist.toString());
	}
	
	/**
	 * 
	   * @Description 根据id查询部门名称（deptName）
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @UpdateUser 方法更新者
	   * @UpdateDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test3() {	
		
		Optional<Dept> name=deptService.findById(2);
		log.info(name+"");
	}

	/**
	 * 
	   * @Description 根据id将部门名称修改为新传入的部门名称
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @UpdateUser 方法更新者
	   * @UpdateDate 2019年7月31日
	   * @throws
	   * @param id
	   * @return  返回值String
	 */
	@Ignore
	@Test
	public void test4() {
		String deptName="非机构";
		int id=2;		
		deptService.updateDeptNameById(deptName, id);
	}
	/**
	 * 
	   * @Description 根据id删除（逻辑删除），即将flag=0
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @UpdateUser 方法更新者
	   * @UpdateDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test5() {
		
		int id=2;		
		deptService.updateFlagById(id);
	}
	/**
	 * 
	   * @Description 插入数据到user_info表中
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test6() {
		for(int i=0;i<9;i++) {
		User user=new User();
		user.setUserName("用户"+i);
		user.setFlag(1);
		userService.saveUser(user);
		}
	}
	/**
	 * 
	   * @Description 查找flag=1的,且通过deptIndex升序排序后
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test 
	public void test7() {
		
		List<Dept> dlist=deptService.findByFlagAndParentIsNullOrderByDeptIndexAsc(1);
		Iterator<Dept> it=dlist.iterator();
		while(it.hasNext()) {
			Dept dept=it.next();
			System.out.println(dept.getDeptName());
		}
	
	}
	/**
	 * 
	   * @Description 按照id降序排序后查找第一个Dept对象
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test8() {
		Dept dept=deptService.findFirstByOrderByIdDesc();
		System.out.println(dept.getDeptName());
	}
	/**
	 * 
	   * @Description 查询与传入参数相同名称的数据库中前10条数据
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test9() {
		List<Dept> dlist=deptService.findFirst10ByDeptName("机构0");
		Iterator<Dept> it=dlist.iterator();
		while(it.hasNext()) {
			Dept dept=it.next();
			System.out.println(dept.getDeptName()+"-----");
		}
		
	}
	/**
	 * 
	   * @Description 按条件分页查询
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testPageDept() {
		String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"deptName\", \"value\":\"机构8\"}]}}";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			log.error("IOException{}:", e.getMessage());
		}

		Map mapDept = deptService.getPage(searchParameters);

		 
		System.out.println(mapDept.get("total"));

		System.out.println(mapDept.get("depts"));
	}
/**
 * 
   * @Description 按条件分页查询
   * @Author hdj
   * @CreatDate 2019年7月31日
   * @throws  返回值void
 */
	@Ignore
	@Test
	public void testPageUser() {
		String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"userName\", \"value\":\"用户09\"}]}}";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			log.error("IOException{}:", e.getMessage());
		}

		Map mapDept = userService.getPage(searchParameters);

		 
		System.out.println(mapDept.get("total"));

		System.out.println(mapDept.get("users"));
	}
	/**
	 * 
	   * @Description 维护用户和机构的关系
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void test10() {
//		Dept dept =new Dept();
		List<Dept> ld=new ArrayList<Dept>();
//		dept.setDeptName("机构测试");
//		dept.setFlag(1);
//		ld.add(dept);
		Optional<Dept> odept=deptService.findById(2);
		Dept dept=odept.get();
		dept.setUsers(null);
		ld.add(dept);
		User user=new User();
		user.setDepts(ld);
		user.setFlag(1);
		user.setUserName("用户测试");
		user.setId(2);
		userService.saveUser(user);
		
//		DeptUserPK dupk=new DeptUserPK();
//		dupk.setDeptId(2);
//		dupk.setUserId(3);
//		DeptUser du=new DeptUser();
//		
//		du.setId(dupk);
			
	}
	
	//以下为作业内容
	
	/**
	 * 
	   * @Description 增加书籍与作者
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testSaveBook() {
		for(int i=0;i<9;i++) {
			Author author=new Author();
			author.setName("作者"+i);
			author.setFlag(1);
			
			Book book = new Book();
			book.setBookIndex("索引"+i);
			book.setBookName("书籍"+i);
			book.setDate("2019-07-31");
			book.setAuthor(author);
			book.setFlag(1);
			bookService.save(book);
			
			}
	}
	
	/**
	 * 
	   * @Description 删除id为3的书籍（逻辑删除）
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testDeleteBook() {
		int id=3;
		bookService.deleteBook(id);
		
	}
	/**
	 * 
	   * @Description 根据书籍id更改书籍名字
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testupdateById() {
		int id=2;
		String bookName="更改书籍";
		bookService.updateByAuthorId(bookName,id);
	}
	
	/**
	 * 
	   * @Description 查询所有书籍
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testFindAll() {
		List<Book> list=bookService.findAll();
		Iterator<Book> it=list.iterator();
		while(it.hasNext()) {
			Book book=it.next();
			System.out.println(book.getBookName()+"-----");
		}
	}
	
	/**
	 * 
	   * @Description 查询所有未删除的书籍
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testFindAllNamedQuery() {
		List<Book> list=bookService.findAllNamedQuery();
		Iterator<Book> it=list.iterator();
		while(it.hasNext()) {
			Book book=it.next();
			System.out.println(book.getBookName()+"-----");
		}
	}
	/**
	 * 
	   * @Description 按条件分页查询
	   * @Author hdj
	   * @CreatDate 2019年7月31日
	   * @throws  返回值void
	 */
	@Ignore
	@Test
	public void testBookPage() {
		String map = "{\"page\" : 1,\"pageSize\" : 5, \"filter\":{ \"filters\":[{ \"field\" : \"bookName\", \"value\":\"书籍5\"},"
				+ "{\"field\" : \"bookIndex\", \"value\":\"索引5\"}]},\"sort\":[{ \"field\" : \"bookName\", \"dir\":\"DESC\"}] }";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			log.error("IOException{}:", e.getMessage());
		}

		Map mapBook = bookService.getPage(searchParameters);

		 
		System.out.println(mapBook.get("total"));

		System.out.println(mapBook.get("users"));
	}
		
	
}
