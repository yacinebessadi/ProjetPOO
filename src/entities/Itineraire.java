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

    // Pour voir si le point de démarrage du passager et chauffeur sont les mêmes (usthb ==usthb ou villeA==villeA)
    public static boolean isDemarageSame(List<Communes> passengerPoints, List<Communes> driverPoints) {
        // Check if any required objects are null
        if (passengerPoints == null || driverPoints == null || 
            passengerPoints.isEmpty() || driverPoints.isEmpty()) {
            return false;
        }
        
        // Compare starting points
        return passengerPoints.get(0).equals(driverPoints.get(0));
    }
    



    // Voir si le point d'arrivée est le même car si dans isDemarageSame le point de départ n'est pas le même alors pour faire le matching
    // Vérifier si le point d'arrivée est le même et ensuite vérifier si le point de démarrage du passager est dans celui du chauffeur
    public static boolean isArriverSame(List<Communes> passengerPoints, List<Communes> driverPoints) {
        // Check if any required lists are null or too small
        if (passengerPoints == null || driverPoints == null || 
            passengerPoints.size() < 2 || driverPoints.isEmpty()) {
            return false;
        }
        
        // Compare passenger's second point with driver's last point
        return passengerPoints.get(1).equals(driverPoints.get(driverPoints.size() - 1));
    }
    


    // Méthode  pour vérifier si un point est contenu dans l'itinéraire
    public boolean contientPoint(Communes point) {
        return this.points.contains(point);
    }

    public void updateItineraire(List<Communes> points) {
        setPoints(points);
    }

    
    
    public static boolean matcingItiniraire(Itineraire passengerItineraire, Itineraire driverItineraire) {
        
        // Le point de départ est le même et le point d'arrivée est dans l'itinéraire du chauffeur.

        if(isDemarageSame(passengerItineraire.getPoints(), driverItineraire.getPoints())&& driverItineraire.contientPoint(passengerItineraire.getPoints().get(1))){
            System.out.println("Le Point de Depart de Passeger et de Chauffeur est le meme et le Point d'arrive du passager et dans le itiniraire du chauffeur ");

            return true;
        }
       // Vérifie si le point d'arrivée du passager est le même que celui du chauffeur
        if(isArriverSame(passengerItineraire.getPoints(), driverItineraire.getPoints()) && driverItineraire.contientPoint(passengerItineraire.getPoints().get(0))){
            System.out.println("Le point d'arrivée est le même et le point de départ est dans l'itinéraire du chauffeur.");
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