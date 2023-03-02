import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee implements EmployeeDAO {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private int idCity;
    private Connection connection;

    public Employee(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.idCity = idCity;
    }

    public Employee(int id, String firstName, City city, int amount) {

    }

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
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (first_name | last_name | gender | age  | id_city) VALUES ((?), (?), (?), (?), (?))")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee(id, firstName);
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.id_city=city.id_city AND id=(?)")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("имя"));
                employee.setIdCity(new City(resultSet.getInt("id"),
                        resultSet.getString("city_name")).getIdCity());
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeLis = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.id_city=city.id_city")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                City city = new City(resultSet.getInt("id_city"),
                        resultSet.getString("name_author"));
                int age = Integer.parseInt(resultSet.getString("age"));
                employeeLis.add(new Employee(id, firstName, city, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeLis;
    }

    @Override
    public void updateAmountById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee WHERE id=(?)")) {
            statement.setInt(2, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
