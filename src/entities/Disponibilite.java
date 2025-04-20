package entities;

import enums.DisponibiliteType;

import java.time.LocalDateTime;
import java.util.List;

public class Disponibilite {
    private DisponibiliteType type; // Type of availability (daily, weekly, always)
    private List<LocalDateTime> horaires; // List of specific availability times
    private boolean disponibleOuiOuNon; // Indicates if the user is currently available

    // Constructor
    public Disponibilite(DisponibiliteType type, List<LocalDateTime> horaires, boolean disponibleOuiOuNon) {
        this.type = type;
        this.horaires = horaires;
        this.disponibleOuiOuNon = disponibleOuiOuNon;
    }
      // Implicit constructor
      public Disponibilite() {
        this.type = null;
        this.horaires = null;
        this.disponibleOuiOuNon = false;
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

    public boolean isDisponibleOuiOuNon() {
        return disponibleOuiOuNon;
    }

    public void setDisponibleOuiOuNon(boolean disponibleOuiOuNon) {
        this.disponibleOuiOuNon = disponibleOuiOuNon;
    }

    @Override
    public String toString() {
        return "Disponibilite{" +
                "type=" + type +
                ", horaires=" + horaires +
                ", disponibleOuiOuNon=" + disponibleOuiOuNon +
                '}';
    }
}