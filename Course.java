/**
 * @author kchardy
 */

import java.time.LocalDate;

public class Course {

    private int id;
    private String code;
    private String name;
    private int days;
    private LocalDate data;


    public Course() {}

    public Course(int id, String code, String name, int days, LocalDate data)
     {
        this.id = id;
        this.code = code;
        this.name = name;
        this.days = days;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String data) {
        LocalDate date = LocalDate.parse(data);
        this.data = date;
    }


    @Override
    public String toString() {
        return "\nCourse{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", days=" + days +
               ", data=" + data +
                // '\'' +
                '}';
    }


}
