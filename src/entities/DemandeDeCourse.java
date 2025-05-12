package entities;
import utils.CourseUtils;
import java.time.LocalDateTime;
import java.util.List;

public class DemandeDeCourse {
    public static boolean canMatch(User passenger, User driver) {
        if(driver.getProfile().getnbPlaces()==0){
            System.out.println("Pas de places chez "+driver.getNom()+" "+driver.getPrenom());
            return false;
        }
        if (!Itineraire.matcingItiniraire(
                passenger.getProfile().getDisponibilite().getItineraire(),
                driver.getProfile().getDisponibilite().getItineraire())) {
            System.out.println("Itinéraires incompatibles. "+driver.getNom()+" "+driver.getPrenom()+" Passager:"+" "+passenger.getNom());
            return false;
        }
        if (!passenger.getProfile().getPreferences().isCompatibleWith(driver.getProfile().getPreferences())) {
            System.out.println("Préférences incompatibles.");
            return false;
        }
        
        // Check if the passenger's time is contained in the driver's time list
        List<LocalDateTime> horairesPassager = passenger.getProfile().getDisponibilite().getHoraires();
        List<LocalDateTime> horairesChauffeur = driver.getProfile().getDisponibilite().getHoraires();

        if (horairesPassager == null || horairesPassager.isEmpty()) {
        System.out.println("Le passager n'a pas spécifié d'horaire.");
        return false;
        }

        if (horairesChauffeur == null || horairesChauffeur.isEmpty()) {
        System.out.println("Le chauffeur n'a pas spécifié d'horaires.");
        return false;
        }

        // Check if any of the passenger's times are contained in the driver's times
        for (LocalDateTime horairePassager : horairesPassager) {
        if (horairesChauffeur.contains(horairePassager)) {
        return true; // Match found
        }
        }
        System.out.println("Les horaires ne correspondent pas.");

    return false;
    }

}
