package cn.com.taiji.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "department")
@Entity
public class Department implements Serializable {
    @Id
    @Column(name = "d_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "d_name",length = 64)
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
    
}
