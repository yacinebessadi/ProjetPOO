package entities;

import enums.StatusUser;
import utils.CourseUtils;

public class Course {
    private static int courseIdCounter = 0; // Static counter for course IDs
    private int courseId; // Unique ID for each accepted course
    private boolean accepter; // Whether the course is accepted
    private User driver; // Driver of the course
    private User passenger; // Passenger of the course

    public Course(User passenger, User driver) {
        this.passenger = passenger;
        this.driver = driver;
        this.accepter = false; // Default to not accepted
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void demandeDeCourse() {
        // Vérifie si les rôles sont valides
        if (CourseUtils.isValidPassengerAndDriver(passenger, driver)) {

            // Vérifie si les itinéraires correspondent
            if (isMatchingByItineraire()) {

                // Vérifie si les préférences sont compatibles
                if (passenger.getProfile().getPreferences().isCompatibleWith(driver.getProfile().getPreferences())) {
                    accepter = true; // Marque la course comme acceptée
                    this.courseId = ++courseIdCounter; // Incrémente et assigne l'ID de la course
                    System.out.println("Demande de Course Numero " + getCourseId() + " acceptée");
                    System.out.println("Les détails: " + passenger.toString() + driver.toString());
                    passenger.getProfile().icrementRide();
                    driver.getProfile().icrementRide();
                } else {
                    System.out.println("Préférences incompatibles.");
                }
            } else {
                System.out.println("Itinéraires incompatibles.");
            }
        } else {
            System.out.println("Rôles invalides: le passager doit être PASSAGER et le chauffeur doit être CHAUFFEUR.");
        }
    }

    // Méthode pour vérifier si les itinéraires correspondent
    private boolean isMatchingByItineraire() {
        Itineraire passengerItineraire = passenger.getProfile().getDisponibilite().getItineraire();
        Itineraire driverItineraire = driver.getProfile().getDisponibilite().getItineraire();

        // Vérifie si le point de départ du passager est le même que celui du chauffeur
        if (passengerItineraire.getPoints().get(0).equals(driverItineraire.getPoints().get(0)) &&
            driverItineraire.contientPoint(passengerItineraire.getPoints().get(1))) {
            System.out.println("Matched Course! Le point de départ est le même et le point d'arrivée est dans l'itinéraire du chauffeur.");
            return true;
        }

        // Vérifie si le point d'arrivée du passager est le même que celui du chauffeur
        if (passengerItineraire.getPoints().get(1).equals(driverItineraire.getPoints().get(driverItineraire.getPoints().size() - 1)) &&
            driverItineraire.contientPoint(passengerItineraire.getPoints().get(0))) {
            System.out.println("Matched Course! Le point d'arrivée est le même et le point de départ est dans l'itinéraire du chauffeur.");
            return true;
        }

        // Aucun matching trouvé
        return false;
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