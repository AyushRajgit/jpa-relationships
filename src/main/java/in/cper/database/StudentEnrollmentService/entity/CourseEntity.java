package in.cper.database.StudentEnrollmentService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String courseCode;
    private long courseFee;
    private long seatsAvailable;

    public long getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(long courseFee) {
        this.courseFee = courseFee;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public long getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(long seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
