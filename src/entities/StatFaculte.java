package entities;
import enums.FacultesUSTHB;
/* classe qui representes les stats d'une faculté
 * le nb de corses , nb users, moyenne de course par user... */
 
public class StatFaculte {
FacultesUSTHB faculte;
 private int totalRides;
private int nbUtilisateurs;

//constructeur
public StatFaculte(FacultesUSTHB faculte){
	this.faculte=faculte;
	this.totalRides=0;
	this.nbUtilisateurs=0;
}

public void ajouterUtilisateur(int rides) {
	this.totalRides+=rides;
	this.nbUtilisateurs++;
}

//calcule la moy de rides pour cette fac
public double getMoyenneRides() {
	return nbUtilisateurs == 0 ? 0 : (double) totalRides / nbUtilisateurs;
}

//getters
public FacultesUSTHB getFaculte() {
	return faculte;	
}
public int getTotalRides() {
return nbUtilisateurs;}

@Override
public String toString()
{return String.format("%-35s : %.2f rides/utilisateur (%d utilisateurs)",
        faculte.name().replace('_', ' '),
        getMoyenneRides(),
        nbUtilisateurs
    );
}
}
