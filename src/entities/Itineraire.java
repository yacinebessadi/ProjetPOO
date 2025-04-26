package entities;

import java.util.List;
import java.util.ArrayList; // Import ArrayList

public class Itineraire {
    private List<String> points; // Liste des points de l'itinéraire pour les passagers et les chauffeurs

    public Itineraire(List<String> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("La liste des points de l'itinéraire ne peut pas être nulle ou vide.");
        }
        this.points = new ArrayList<>(points);
    }

    public Itineraire() {
        this.points = new ArrayList<>();
    }

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("La liste des points de l'itinéraire ne peut pas être nulle ou vide.");
        }
        this.points = new ArrayList<>(points);
    }

    public void addPoint(String point) {
        if (point == null || point.isEmpty()) {
            throw new IllegalArgumentException("Le point ne peut pas être nul ou vide.");
        }
        this.points.add(point);
    }

    // Pour voir si le point de démarrage du passager et chauffeur sont les mêmes
    public static boolean isDemarageSame(User passenger, User driver) {
        return passenger.getProfile().getDisponibilite().getItineraire().getPoints().get(0)
                .equals(driver.getProfile().getDisponibilite().getItineraire().getPoints().get(0));
    }

    // Voir si le point d'arrivée est le même car si dans le premier if le point de départ n'est pas le même alors pour faire le matching
    // Vérifier si le point d'arrivée est le même et ensuite vérifier si le point de démarrage du passager est dans celui du chauffeur
    public static boolean isArriverSame(User passenger, User driver) {
        List<String> driverPoints = driver.getProfile().getDisponibilite().getItineraire().getPoints();
        return passenger.getProfile().getDisponibilite().getItineraire().getPoints().get(1)
                .equals(driverPoints.get(driverPoints.size() - 1));
    }
    // Méthode générique pour vérifier si un point est contenu dans l'itinéraire
    public boolean contientPoint(String point) {
        return this.points.contains(point);
    }
    @Override
    public String toString() {
        return "Itineraire{" +
                "points=" + points +
                '}';
    }
}