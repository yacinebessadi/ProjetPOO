package entities;

import java.util.List;
import java.util.ArrayList;
import enums.Communes;

public class Itineraire {
    private List<Communes> points; // Liste des points de l'itinéraire pour les passagers et les chauffeurs

    public Itineraire(List<Communes> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("La liste des points de l'itinéraire ne peut pas être nulle ou vide.");
        }
        this.points = new ArrayList<>(points);
    }

    public Itineraire() {
        this.points = new ArrayList<>();
    }

    public List<Communes> getPoints() {
        return points;
    }

    public void setPoints(List<Communes> points) {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("La liste des points de l'itinéraire ne peut pas être nulle ou vide.");
        }
        this.points = new ArrayList<>(points);
    }

    public void addPoint(Communes point) {
        if (point == null) {
            throw new IllegalArgumentException("Le point ne peut pas être nul.");
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
        List<Communes> driverPoints = driver.getProfile().getDisponibilite().getItineraire().getPoints();
        return passenger.getProfile().getDisponibilite().getItineraire().getPoints().get(1)
                .equals(driverPoints.get(driverPoints.size() - 1));
    }
    // Méthode générique pour vérifier si un point est contenu dans l'itinéraire

    public boolean contientPoint(Communes point) {
        return this.points.contains(point);
    }

    public void updateItineraire(List<Communes> points) {
        setPoints(points);
    }

    
    
    public static boolean matcingItiniraire(Itineraire passengerItineraire, Itineraire driverItineraire) {

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

    @Override
    public String toString() {
        return "Itineraire{" +
                "points=" + points +
                '}';
    }
}