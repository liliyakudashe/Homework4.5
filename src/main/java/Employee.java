import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements EmployeeDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    private int age;
    private int idCity;

    public Employee(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.idCity = idCity;
    }

    //public Employee(int id, String firstName, City city, int amount) {

    //}

    public Employee() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && idCity == employee.idCity && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, age, idCity);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", idCity=" + idCity +
                '}';
    }

    @Override
    public void create(Employee employee) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();){
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee readById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class,  id);
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeLis = (List<Employee>) HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Employee").list();
        return employeeLis;
    }

    @Override
    public void updateAmountById(int id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(id);
            transaction.commit();
        }
    }

    @Override
    public void deleteById(int id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        }
    }
}
