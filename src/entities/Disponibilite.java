package entities;

import enums.DisponibiliteType;

import java.time.LocalDateTime;
import java.util.List;

public class Disponibilite {
    private DisponibiliteType type; // Type of availability (daily, weekly, always)
    private List<LocalDateTime> horaires; // List of specific availability times

    // Constructor
    public Disponibilite(DisponibiliteType type, List<LocalDateTime> horaires) {
        this.type = type;
        this.horaires = horaires;
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

    @Override
    public String toString() {
        return "Disponibilite{" +
                "type=" + type +
                ", horaires=" + horaires +
                '}';
    }
}