import java.sql.SQLException;
import java.util.List;

public interface daoInterface {
    void open() throws SQLException;
    void saveCourses(Course... szkolenia) throws SQLException;
    List<Course> getCourses() throws SQLException;
    void close() throws SQLException;


}
