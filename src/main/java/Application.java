import java.sql.*;
public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "07042016";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            statement.setInt(1, 6);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = "Имя: " + resultSet.getString("first_name");
                String  lastName = "Фамилия: " + resultSet.getString("last_name");
                String  gender = "Пол: " + resultSet.getString("gender");
                int age = resultSet.getInt(4);
                int idCity = resultSet.getInt(4);
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println(age);
                System.out.println(idCity);

            }
        }

    }
}
