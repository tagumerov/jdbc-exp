import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String pass = "P@ssw0rd";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(*), YEAR(subscription_date), DATE_FORMAT(subscription_date, \"%m\")" +
                    "FROM purchaselist \n" +
                    "GROUP BY course_name, YEAR(subscription_date), MONTH(subscription_date);");
            System.out.printf("%-40s", "Имя курса");
            System.out.println("Количество покупок" + "\t" + "Год" + "\t" + "\t" + "Месяц");
            while (resultSet.next()) {
                String coursename = resultSet.getString("course_name");
                int purchasesCount = resultSet.getInt("COUNT(*)");
                int year = resultSet.getInt("YEAR(subscription_date)");
                int month = resultSet.getInt("DATE_FORMAT(subscription_date, \"%m\")");
                System.out.printf("%-40s", coursename);
                System.out.print("\t" + "\t" + purchasesCount + "\t" + "\t" + "\t" + year + "\t");
                System.out.printf("%02d%n", month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
