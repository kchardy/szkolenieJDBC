
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * łączenie i komendy do bazy danych
 *  * @author kchardy
 */
public class Dao implements daoInterface{

    private Connection conn;
    private Statement st;

    private void initConnection() throws SQLException {
        final String HOST = "localhost";
        final int PORT = 0000;
        final String DB_NAME = "Baza";
        final String USER_NAME = "root";
        final String PASSWORD = "admin";
        String dburl = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

        conn = DriverManager.getConnection(dburl, USER_NAME, PASSWORD);
        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.printf("Połączenie nawiązane poprzez: %s%n", dbmd.getDriverName());
        st = conn.createStatement();
    }

    private void createTable() throws SQLException {
        System.out.println("\n---> TWORZENIE TABELI...");
        final String SQL_CREATE = "CREATE TABLE szkolenie ("
                + "id INT NOT NULL,"
                + "code VARCHAR(255),"
                + "name VARCHAR(255),"
                + "days INT,"
                + "data VARCHAR(255),"
                + "PRIMARY KEY (id))";
        st.executeUpdate(SQL_CREATE);
    }

    @Override
    public void open() throws SQLException {
        initConnection();
        createTable();
    }

    @Override
    public void saveCourses(Course... szkolenia) throws SQLException {
        System.out.println("\n---> WSTAWIANIE DANYCH...");
java.util.Date datee = new java.util.Date();
java.sql.Date dt = new java.sql.Date(datee.getTime());
        for (Course c : szkolenia) {
            String insert = String.format("INSERT INTO szkolenie VALUES (%d, '%s','%s', %d, '%s')",
                    c.getId(),
                    c.getCode(),
                    c.getName(),
                    c.getDays(),
                    c.getData().toString());
            System.out.println(insert);
            st.executeUpdate(insert);
        }
    }

    @Override
    public List<Course> getCourses() throws SQLException {
        System.out.println("\n\n---> ODCZYT DANYCH Z TABELI...");
        final String SQL_SELECT = "SELECT * FROM szkolenie";
        List<Course> courses = new ArrayList<>();

        try (ResultSet rs = st.executeQuery(SQL_SELECT)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            System.out.printf("Tabela 'szkolenie' zawiera %d kolumn o nazwach: ", columns);
            for (int i = 1; i <= columns; i++) {
                System.out.print(rsmd.getColumnName(i) + ", ");
            }
            System.out.println();

            while (rs.next()) {
                Course c = new Course();

                c.setId(rs.getInt(1));
                c.setCode(rs.getString(2));
                c.setName(rs.getString(3));
                c.setDays(rs.getInt(4));
                c.setData(rs.getString(5));
                courses.add(c);
            }
        }
        return courses;
    }

    private void dropTable() throws SQLException {
        System.out.println("\n---> USUNIĘCIE TABELI...");
        final String SQL_DROP = "DROP TABLE szkolenie";
        st.executeUpdate(SQL_DROP);
    }

    private void closeConnection() {
        try {
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        dropTable();
        closeConnection();
    }
}
