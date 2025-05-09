package entities;

import enums.CourseStatus;
import enums.StatusUser;
import utils.CourseUtils;

public class Course {
    private static int courseIdCounter = 0; // Static counter for course IDs
    private int courseId; // Unique ID for each accepted course
    private boolean accepter; // Whether the course is accepted
    private User driver; // Driver of the course
    private User passenger; // Passenger of the course
    private CourseStatus statusCourse=CourseStatus.EN_COURS;

    public Course(User passenger, User driver) {
        this.passenger = passenger;
        this.driver = driver;
        this.accepter = false; // Default to not accepted
    }
    

    public int getCourseId() {
        return this.courseId;
    }

    public void acceptCourse() {
        this.accepter = true;
        this.courseId = ++courseIdCounter;
        System.out.println("Demande de Course Numero " + getCourseId() + " acceptée");
        System.out.println("Les détails: " + passenger.toString() + driver.toString());
        passenger.getProfile().icrementRide();
        driver.getProfile().icrementRide();
    }


    public boolean isAccepter() {
        return accepter;
    }

    public User getDriver() {
        return driver;
    }

    public User getPassenger() {
        return passenger;
    }
    
    public CourseStatus getStatusCourse(){
        return statusCourse;
    }
    public void terminerLaCourse(){
        this.statusCourse=CourseStatus.TERMINEE;
        System.out.println("Course "+getCourseId()+"terminée");
    }
    
    
    @Override
    public String toString() {
        return "Course{\n" +
                "  courseId: " + courseId + ",\n" +
                "  accepter: " + accepter + ",\n" +
                "  driver: " + driver.toString() + ",\n" +
                "  passenger: " + passenger.toString() + "\n" +
                '}';
    }
}