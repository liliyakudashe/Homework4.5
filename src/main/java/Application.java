import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new Employee();

        Employee employee1 = new Employee(1, "Test");
        employeeDAO.create(employee1);
        System.out.println(employeeDAO.readById(1));
        List<Employee> list = employeeDAO.readAll();
        for (Employee employee : list){
            System.out.println(employee);
        }

        Employee employee2 = new Employee(2, "Test2");
        employeeDAO.updateAmountById(2);
        employee2.deleteById(2);

    }
}
