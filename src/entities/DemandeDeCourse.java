
package entities;
import utils.CourseUtils;

public class DemandeDeCourse {
    public static boolean canMatch(User passenger, User driver) {
        if (!CourseUtils.isValidPassengerAndDriver(passenger, driver)) {
            System.out.println("Rôles invalides: le passager doit être PASSAGER et le chauffeur doit être CHAUFFEUR.");
            return false;
        }
        if (!Itineraire.matcingItiniraire(
                passenger.getProfile().getDisponibilite().getItineraire(),
                driver.getProfile().getDisponibilite().getItineraire())) {
            System.out.println("Itinéraires incompatibles.");
            return false;
        }
        if (!passenger.getProfile().getPreferences().isCompatibleWith(driver.getProfile().getPreferences())) {
            System.out.println("Préférences incompatibles.");
            return false;
        }
        return true;
    }
}