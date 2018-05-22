import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author kchardy
 */
public class Test {

    static LocalDate myLocalDate = LocalDate.now();
    static LocalDate myLocalDate2 = LocalDate.of(2020,10,10);
    static LocalDate myLocalDate3 = LocalDate.of(2018,8,31);

    static {
        try {
            System.out.println("---> REJESTROWANIE STEROWNIKA...");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Niezarejestrowany sterownik");
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        Course c1 = new Course(1, "JP01", "Java Podstawy czesc 1", 5, myLocalDate);
        Course  c2 = new Course (2, "JP02", "Java Podstawy czesc 2", 4, myLocalDate2);
        Course  c3 = new Course (3, "AJ", "Akademia Javy", 7, myLocalDate3);

        Dao dao = new Dao();
        try {
            dao.open();
            dao.saveCourses(c1, c2, c3);
            List<Course > customers = dao.getCourses();
            System.out.println(customers);
            dao.close();
        } catch (SQLException e) {
            System.out.print("Błąd bazy danych: " + e.getMessage());
        }
    }
}
