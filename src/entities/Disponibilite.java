package entities;

import enums.DisponibiliteType;

import java.time.LocalDateTime;
import java.util.List;

public class Disponibilite {
    private DisponibiliteType type; // Type of availability (daily, weekly, always)
    private List<LocalDateTime> horaires; // List of specific availability times 
    private Itineraire itineraire; // User's itinerary (single point for passengers, multiple points for drivers)



    // Constructor
    public Disponibilite(DisponibiliteType type, List<LocalDateTime> horaires, Itineraire itineraire) {
        this.type = type;
        this.horaires = horaires;
        this.itineraire = itineraire;
    }


    // Default constructor
    public Disponibilite() {
        this.type = null;
        this.horaires = null;
        this.itineraire = null;
    }

    // Getters and Setters
    public DisponibiliteType getType() {
        return type;
    }

    public void setType(DisponibiliteType type) {
        this.type = type;
    }

    public List<LocalDateTime> getHoraires() {
        return horaires;
    }

    public void setHoraires(List<LocalDateTime> horaires) {
        this.horaires = horaires;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

  
    @Override
    public String toString() {
        return "Disponibilite{" +
                "type=" + type +
                ", horaires=" + horaires +
                ", itineraire=" + itineraire +
                '}';
    }
}