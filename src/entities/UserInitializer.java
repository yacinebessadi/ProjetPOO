package entities;

import enums.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserInitializer {
    public static void initializeUsers(Admin admin) {
        List<User> predefinedUsers = new ArrayList<>();
        
        // Define a fixed date for all times (January 1, 2023)
        LocalDateTime baseDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        
        // === ÉTUDIANTS (25 users) ===
        
        // Pair 1: Driver and Passenger
        Profile profileDriver1 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(17).withMinute(0)),
                new Itineraire(List.of(Communes.USTHB, Communes.SIDI_M_HAMED, Communes.EL_MADANIA, Communes.BELOUIZDAD))
            ),
            4
        );
        Etudiant driver1 = new Etudiant("Rachid", "Boudjemaa", "E10001", 5, profileDriver1, 2021, FacultesUSTHB.INFORMATIQUE, Specialite.LICENCE_INFORMATIQUE);
        driver1.setReputation(5.0);
        predefinedUsers.add(driver1);

        Profile profilePassenger1 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0)),
                new Itineraire(List.of(Communes.USTHB, Communes.EL_MADANIA))
            )
        );
        Etudiant passenger1 = new Etudiant("Amina", "Kaddouri", "E10002", 5, profilePassenger1, 2022, FacultesUSTHB.INFORMATIQUE, Specialite.LICENCE_INFORMATIQUE);
        passenger1.setReputation(4.5);
        predefinedUsers.add(passenger1);

        // Special test case for Yacine
        Profile profileYacineDriver = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(0), baseDate.withHour(17).withMinute(0)),
                new Itineraire(List.of(Communes.BOLOGHINE, Communes.SIDI_M_HAMED, Communes.USTHB))
            ),
            4
        );
        Etudiant yacineDriver = new Etudiant("Bessadi", "Yacine", "E23231", 5, profileYacineDriver, 2020, FacultesUSTHB.PHYSIQUE, Specialite.PHYSIQUE);
        yacineDriver.setReputation(5.0);
        predefinedUsers.add(yacineDriver);

        // Pair 2: Driver and Passenger
        Profile profileDriver2 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(0), baseDate.withHour(16).withMinute(30)),
                new Itineraire(List.of(Communes.BOLOGHINE, Communes.SIDI_M_HAMED, Communes.USTHB))
            ),
            5
        );
        Etudiant driver2 = new Etudiant("Sofiane", "Benmoussa", "E10003", 5, profileDriver2, 2020, FacultesUSTHB.PHYSIQUE, Specialite.PHYSIQUE);
        driver2.setReputation(5.0);
        predefinedUsers.add(driver2);
//
        Profile profilePassenger2 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(0)),
                new Itineraire(List.of(Communes.BOLOGHINE, Communes.USTHB))
            )
        );
        Etudiant passenger2 = new Etudiant("Lydia", "Mebarki", "E10004", 5, profilePassenger2, 2021, FacultesUSTHB.PHYSIQUE, Specialite.PHYSIQUE);
        passenger2.setReputation(4.8);
        predefinedUsers.add(passenger2);

        // Pair 3: Driver and Passenger
        Profile profileDriver3 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(15), baseDate.withHour(16).withMinute(15)),
                new Itineraire(List.of(Communes.USTHB, Communes.BIR_MOURAD_RAIS, Communes.EL_BIAR, Communes.BOUZAREAH))
            ),
            4
        );
        Etudiant driver3 = new Etudiant("Tarek", "Benali", "E10005", 5, profileDriver3, 2019, FacultesUSTHB.BIOLOGIE, Specialite.BIOLOGIE);
        driver3.setReputation(5.0);
        predefinedUsers.add(driver3);

        Profile profilePassenger3 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(30)),
                new Itineraire(List.of(Communes.USTHB, Communes.EL_BIAR))
            )
        );
        Etudiant passenger3 = new Etudiant("Yasmine", "Slimani", "E10006", 5, profilePassenger3, 2020, FacultesUSTHB.BIOLOGIE, Specialite.BIOLOGIE);
        passenger3.setReputation(4.7);
        predefinedUsers.add(passenger3);

        // Pair 4: Driver and Passenger
        Profile profileDriver4 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(45), baseDate.withHour(17).withMinute(15)),
                new Itineraire(List.of(Communes.HYDRA, Communes.BEN_AKNOUN, Communes.BOUZAREAH, Communes.USTHB))
            ),
            5
        );
        Etudiant driver4 = new Etudiant("Yacine", "Benmoussa", "E10007", 5, profileDriver4, 2020, FacultesUSTHB.GENIE_ELECTRIQUE, Specialite.ELECTRONIQUE);
        driver4.setReputation(5.0);
        predefinedUsers.add(driver4);

        Profile profilePassenger4 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(15)),
                new Itineraire(List.of(Communes.BEN_AKNOUN, Communes.USTHB))
            )
        );
        Etudiant passenger4 = new Etudiant("Nassima", "Chaoui", "E10008", 5, profilePassenger4, 2021, FacultesUSTHB.GENIE_ELECTRIQUE, Specialite.ELECTRONIQUE);
        passenger4.setReputation(4.9);
        predefinedUsers.add(passenger4);

        // Pair 5: Driver and Passenger
        Profile profileDriver5 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(16).withMinute(30)),
                new Itineraire(List.of(Communes.USTHB, Communes.BIRKHADEM, Communes.EL_HARRACH, Communes.BARAKI))
            ),
            4
        );
        Etudiant driver5 = new Etudiant("Karim", "Benmoussa", "E10009", 5, profileDriver5, 2019, FacultesUSTHB.MATHEMATIQUES, Specialite.MATHEMATIQUES);
        driver5.setReputation(5.0);
        predefinedUsers.add(driver5);

        Profile profilePassenger5 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0)),
                new Itineraire(List.of(Communes.USTHB, Communes.EL_HARRACH))
            )
        );
        Etudiant passenger5 = new Etudiant("Lila", "Hamidi", "E10010", 5, profilePassenger5, 2020, FacultesUSTHB.MATHEMATIQUES, Specialite.MATHEMATIQUES);
        passenger5.setReputation(4.8);
        predefinedUsers.add(passenger5);

        // Pair 6: Driver and Passenger
        Profile driverProfile6 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(0), baseDate.withHour(16).withMinute(0)),
                new Itineraire(List.of(Communes.DAR_EL_BEIDA, Communes.BAB_EZZOUAR, Communes.OUED_SMAR, Communes.USTHB))
            ),
            5
        );
        Etudiant etu6 = new Etudiant("Amine", "Boudjemaa", "E10011", 5, driverProfile6, 2021, FacultesUSTHB.INFORMATIQUE, Specialite.LICENCE_INFORMATIQUE);
        etu6.setReputation(5.0);
        predefinedUsers.add(etu6);

        Profile passengerProfile6 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(15)),
                new Itineraire(List.of(Communes.BAB_EZZOUAR, Communes.USTHB))
            )
        );
        etu6.setProfile(passengerProfile6);
        etu6.setReputation(5.0);
        predefinedUsers.add(etu6);

        // Pair 7: Driver and Passenger
        Profile driverProfile7 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(17).withMinute(30)),
                new Itineraire(List.of(Communes.USTHB, Communes.KOUBA, Communes.HUSSEIN_DEY, Communes.BACHDJERRAH))
            ),
            4
        );
        Etudiant etu7 = new Etudiant("Farid", "Benmoussa", "E10013", 5, driverProfile7, 2020, FacultesUSTHB.PHYSIQUE, Specialite.PHYSIQUE);
        etu7.setReputation(5.0);
        predefinedUsers.add(etu7);

        Profile passengerProfile7 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0)),
                new Itineraire(List.of(Communes.USTHB, Communes.HUSSEIN_DEY))
            )
        );
        etu7.setProfile(passengerProfile7);
        etu7.setReputation(5.0);
        predefinedUsers.add(etu7);

        // Pair 8: Driver and Passenger
        Profile driverProfile8 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(15), baseDate.withHour(16).withMinute(15)),
                new Itineraire(List.of(Communes.BORDJ_EL_KIFFAN, Communes.EL_MAGHARIA, Communes.BENI_MESSOUS, Communes.USTHB))
            ),
            5
        );
        Etudiant etu8 = new Etudiant("Kamel", "Benali", "E10015", 5, driverProfile8, 2019, FacultesUSTHB.BIOLOGIE, Specialite.BIOLOGIE);
        etu8.setReputation(5.0);
        predefinedUsers.add(etu8);

        Profile passengerProfile8 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(30)),
                new Itineraire(List.of(Communes.BENI_MESSOUS, Communes.USTHB))
            )
        );
        etu8.setProfile(passengerProfile8);
        etu8.setReputation(5.0);
        predefinedUsers.add(etu8);

        // Pair 9: Driver and Passenger
        Profile driverProfile9 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(45), baseDate.withHour(17).withMinute(15)),
                new Itineraire(List.of(Communes.USTHB, Communes.LES_EUCALYPTUS, Communes.BIRTOUTA, Communes.TESSALA_EL_MERDJA))
            ),
            4
        );
        Etudiant etu9 = new Etudiant("Nabil", "Benmoussa", "E10017", 5, driverProfile9, 2020, FacultesUSTHB.GENIE_ELECTRIQUE, Specialite.ELECTRONIQUE);
        etu9.setReputation(5.0);
        predefinedUsers.add(etu9);

        Profile passengerProfile9 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(15)),
                new Itineraire(List.of(Communes.USTHB, Communes.BIRTOUTA))
            )
        );
        etu9.setProfile(passengerProfile9);
        etu9.setReputation(5.0);
        predefinedUsers.add(etu9);

        // Pair 10: Driver and Passenger
        Profile driverProfile10 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(16).withMinute(30)),
                new Itineraire(List.of(Communes.SIDI_MOUSSA, Communes.AIN_TAYA, Communes.BORDJ_EL_BAHRI, Communes.USTHB))
            ),
            5
        );
        Etudiant etu10 = new Etudiant("Reda", "Benmoussa", "E10019", 5, driverProfile10, 2019, FacultesUSTHB.MATHEMATIQUES, Specialite.MATHEMATIQUES);
        etu10.setReputation(5.0);
        predefinedUsers.add(etu10);

        Profile passengerProfile10 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0)),
                new Itineraire(List.of(Communes.AIN_TAYA, Communes.USTHB))
            )
        );
        etu10.setProfile(passengerProfile10);
        etu10.setReputation(5.0);
        predefinedUsers.add(etu10);

        // Rest of students adding correct specialties
        Etudiant etu11 = new Etudiant("Farid", "Chaoui", "E10020", 5, createStudentProfile(true, baseDate), 2020, FacultesUSTHB.GENIE_CIVIL, Specialite.GENIE_CIVIL);
        etu11.setReputation(5.0);
        predefinedUsers.add(etu11);
        Etudiant etu12 = new Etudiant("Karima", "Slimani", "E10021", 5, createStudentProfile(false, baseDate), 2021, FacultesUSTHB.INFORMATIQUE, Specialite.LICENCE_ACAD);
        etu12.setReputation(5.0);
        predefinedUsers.add(etu12);
        Etudiant etu13 = new Etudiant("Samir", "Benziane", "E10022", 5, createStudentProfile(true, baseDate), 2019, FacultesUSTHB.GENIE_ELECTRIQUE, Specialite.TELECOMMUNICATION);
        etu13.setReputation(5.0);
        predefinedUsers.add(etu13);
        Etudiant etu14 = new Etudiant("Sabrina", "Ouali", "E10023", 5, createStudentProfile(false, baseDate), 2022, FacultesUSTHB.MATHEMATIQUES, Specialite.MATHEMATIQUES);
        etu14.setReputation(5.0);
        predefinedUsers.add(etu14);
        Etudiant etu15 = new Etudiant("Mounir", "Taleb", "E10024", 5, createStudentProfile(true, baseDate), 2020, FacultesUSTHB.CHIMIE, Specialite.CHIMIE);
        etu15.setReputation(5.0);
        predefinedUsers.add(etu15);
        Etudiant etu16 = new Etudiant("Warda", "Bouaziz", "E10025", 5, createStudentProfile(false, baseDate), 2021, FacultesUSTHB.PHYSIQUE, Specialite.PHYSIQUE);
        etu16.setReputation(5.0);
        predefinedUsers.add(etu16);
        
        // === ENSEIGNANTS (15 users) ===
        
        // Teacher 1 - Correcting preferences
        Profile teacherProfile1 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0), baseDate.withHour(16).withMinute(0)),
                new Itineraire(List.of(Communes.USTHB, Communes.BAB_EZZOUAR, Communes.DAR_EL_BEIDA, Communes.ROUIBA))
            ),
            4
        );
        Enseignant enseignant1 = new Enseignant("Mostefa", "Belkacemi", "T20001", 5, teacherProfile1, 2010, FacultesUSTHB.INFORMATIQUE);
        enseignant1.setReputation(5.0);
        predefinedUsers.add(enseignant1);
        
        // Teacher 2
        Profile teacherProfile2 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(9).withMinute(0)),
                new Itineraire(List.of(Communes.SIDI_M_HAMED, Communes.USTHB))
            )
        );
        Enseignant enseignant2 = new Enseignant("Fatiha", "Benmokhtar", "T20002", 5, teacherProfile2, 2012, FacultesUSTHB.MATHEMATIQUES);
        enseignant2.setReputation(5.0);
        predefinedUsers.add(enseignant2);
        
        // Teacher 3
        Profile teacherProfile3 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(17).withMinute(30)),
                new Itineraire(List.of(Communes.BIR_MOURAD_RAIS, Communes.HYDRA, Communes.BEN_AKNOUN, Communes.USTHB))
            ),
            5
        );
        Enseignant enseignant3 = new Enseignant("Hamid", "Rahmani", "T20003", 5, teacherProfile3, 2008, FacultesUSTHB.PHYSIQUE);
        enseignant3.setReputation(5.0);
        predefinedUsers.add(enseignant3);
        
        // Teacher 4 - Correcting preferences
        Profile teacherProfile4 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(15)),
                new Itineraire(List.of(Communes.USTHB, Communes.KOUBA))
            )
        );
        Enseignant enseignant4 = new Enseignant("Djamila", "Bouaziz", "T20004", 5, teacherProfile4, 2013, FacultesUSTHB.CHIMIE);
        enseignant4.setReputation(5.0);
        predefinedUsers.add(enseignant4);
        
        // Teacher 5
        Profile teacherProfile5 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0), baseDate.withHour(16).withMinute(30)),
                new Itineraire(List.of(Communes.USTHB, Communes.BIRKHADEM, Communes.SAOULA, Communes.BABA_HASSEN))
            ),
            3
        );
        Enseignant enseignant5 = new Enseignant("Kamel", "Bensalem", "T20005", 5, teacherProfile5, 2011, FacultesUSTHB.BIOLOGIE);
        enseignant5.setReputation(5.0);
        predefinedUsers.add(enseignant5);
        
        // Adding 10 more teachers
        Enseignant enseignant6 = new Enseignant("Soraya", "Matari", "T20006", 4.3, createTeacherProfile(true, baseDate), 2009, FacultesUSTHB.GENIE_CIVIL);
        enseignant6.setReputation(5.0);
        predefinedUsers.add(enseignant6);
        Enseignant enseignant7 = new Enseignant("Yassine", "Khelifi", "T20007", 4.8, createTeacherProfile(false, baseDate), 2014, FacultesUSTHB.GENIE_ELECTRIQUE);
        enseignant7.setReputation(5.0);
        predefinedUsers.add(enseignant7);
        Enseignant enseignant8 = new Enseignant("Nassima", "Ameur", "T20008", 4.4, createTeacherProfile(true, baseDate), 2010, FacultesUSTHB.INFORMATIQUE);
        enseignant8.setReputation(5.0);
        predefinedUsers.add(enseignant8);
        Enseignant enseignant9 = new Enseignant("Tarik", "Benabbas", "T20009", 4.7, createTeacherProfile(false, baseDate), 2012, FacultesUSTHB.MATHEMATIQUES);
        enseignant9.setReputation(5.0);
        predefinedUsers.add(enseignant9);
        Enseignant enseignant10 = new Enseignant("Assia", "Ferhat", "T20010", 4.5, createTeacherProfile(true, baseDate), 2008, FacultesUSTHB.PHYSIQUE);
        enseignant10.setReputation(5.0);
        predefinedUsers.add(enseignant10);
        Enseignant enseignant11 = new Enseignant("Malik", "Bouroubi", "T20011", 4.6, createTeacherProfile(false, baseDate), 2013, FacultesUSTHB.CHIMIE);
        enseignant11.setReputation(5.0);
        predefinedUsers.add(enseignant11);
        Enseignant enseignant12 = new Enseignant("Lamia", "Dahmani", "T20012", 4.2, createTeacherProfile(true, baseDate), 2011, FacultesUSTHB.BIOLOGIE);
        enseignant12.setReputation(5.0);
        predefinedUsers.add(enseignant12);
        Enseignant enseignant13 = new Enseignant("Salim", "Admi", "T20013", 4.9, createTeacherProfile(false, baseDate), 2009, FacultesUSTHB.GENIE_CIVIL);
        enseignant13.setReputation(5.0);
        predefinedUsers.add(enseignant13);
        Enseignant enseignant14 = new Enseignant("Nadjat", "Merouane", "T20014", 4.3, createTeacherProfile(true, baseDate), 2014, FacultesUSTHB.GENIE_ELECTRIQUE);
        enseignant14.setReputation(5.0);
        predefinedUsers.add(enseignant14);
        Enseignant enseignant15 = new Enseignant("Yazid", "Hamdani", "T20015", 4.5, createTeacherProfile(false, baseDate), 2010, FacultesUSTHB.INFORMATIQUE);
        enseignant15.setReputation(5.0);
        predefinedUsers.add(enseignant15);
        
        // === ATS (10 users) ===
        
        // ATS 1 - Correcting preferences
        Profile atsProfile1 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(0), baseDate.withHour(15).withMinute(30)),
                new Itineraire(List.of(Communes.BIRTOUTA, Communes.LES_EUCALYPTUS, Communes.BAB_EZZOUAR, Communes.USTHB))
            ),
            4
        );
        ATS ats1 = new ATS("Abdelkader", "Benali", "A30001", 4.4, atsProfile1, 2015, "Service Informatique");
        ats1.setReputation(5.0);
        predefinedUsers.add(ats1);
        
        // ATS 2
        Profile atsProfile2 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(30)),
                new Itineraire(List.of(Communes.USTHB, Communes.MOHAMMADIA))
            )
        );
        ATS ats2 = new ATS("Zohra", "Hamadi", "A30002", 4.1, atsProfile2, 2016, "Service Administratif");
        ats2.setReputation(5.0);
        predefinedUsers.add(ats2);
        
        // ATS 3
        Profile atsProfile3 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(45), baseDate.withHour(16).withMinute(45)),
                new Itineraire(List.of(Communes.USTHB, Communes.BARAKI, Communes.LES_EUCALYPTUS, Communes.SAOULA))
            ),
            5
        );
        ATS ats3 = new ATS("Mustapha", "Taibi", "A30003", 4.6, atsProfile3, 2014, "Service Maintenance");
        ats3.setReputation(5.0);
        predefinedUsers.add(ats3);
        
        // ATS 4 - Correcting preferences
        Profile atsProfile4 = new Profile(
            StatusUser.PASSAGER,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_MUSIQUE, BagagesPreferences.AVEC_BAGAGES),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(8).withMinute(0)),
                new Itineraire(List.of(Communes.BOUZAREAH, Communes.USTHB))
            )
        );
        ATS ats4 = new ATS("Nadia", "Djebar", "A30004", 3.9, atsProfile4, 2017, "Service Technique");
        ats4.setReputation(5.0);
        predefinedUsers.add(ats4);
        
        // ATS 5
        Profile atsProfile5 = new Profile(
            StatusUser.CHAUFFEUR,
            TypeCourse.ALLER_SIMPLE,
            new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
            new Disponibilite(
                DisponibiliteType.QUOTIDIEN,
                List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(16).withMinute(0)),
                new Itineraire(List.of(Communes.AIN_TAYA, Communes.BORDJ_EL_BAHRI, Communes.BORDJ_EL_KIFFAN, Communes.USTHB))
            ),
            3
        );
        ATS ats5 = new ATS("Bachir", "Boulahia", "A30005", 4.2, atsProfile5, 2015, "Service Logistique");
        ats5.setReputation(5.0);
        predefinedUsers.add(ats5);
        
        // Adding 5 more ATS
        ATS ats6 = new ATS("Meriem", "Sebbane", "A30006", 4.3, createATSProfile(true, baseDate), 2016, "Service Sécurité");
        ats6.setReputation(5.0);
        predefinedUsers.add(ats6);
        ATS ats7 = new ATS("Lamine", "Daoudi", "A30007", 4.0, createATSProfile(false, baseDate), 2014, "Service Imprimerie");
        ats7.setReputation(5.0);
        predefinedUsers.add(ats7);
        ATS ats8 = new ATS("Farida", "Kaci", "A30008", 4.5, createATSProfile(true, baseDate), 2017, "Service Bibliothèque");
        ats8.setReputation(5.0);
        predefinedUsers.add(ats8);
        ATS ats9 = new ATS("Toufik", "Laouar", "A30009", 3.8, createATSProfile(false, baseDate), 2015, "Service Restauration");
        ats9.setReputation(5.0);
        predefinedUsers.add(ats9);
        ATS ats10 = new ATS("Dalila", "Ghoulam", "A30010", 4.1, createATSProfile(true, baseDate), 2016, "Service Entretien");
        ats10.setReputation(5.0);
        predefinedUsers.add(ats10);

        // Add all predefined users to the admin
        for (User user : predefinedUsers) {
            admin.ajouterUtilisateur(user);
        }
    }
    
    // Helper methods to create profiles - corrected to use proper enums
    private static Profile createStudentProfile(boolean isChauffeur, LocalDateTime baseDate) {
        if (isChauffeur) {
            return new Profile(
                StatusUser.CHAUFFEUR,
                TypeCourse.ALLER_SIMPLE,
                new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
                new Disponibilite(
                    DisponibiliteType.QUOTIDIEN,
                    List.of(baseDate.withHour(8).withMinute(0), baseDate.withHour(17).withMinute(0)),
                    new Itineraire(List.of(Communes.USTHB, Communes.SIDI_M_HAMED, Communes.EL_MADANIA))
                ),
                4
            );
        } else {
            return new Profile(
                StatusUser.PASSAGER,
                TypeCourse.ALLER_SIMPLE,
                new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
                new Disponibilite(
                    DisponibiliteType.QUOTIDIEN,
                    List.of(baseDate.withHour(8).withMinute(0)),
                    new Itineraire(List.of(Communes.EL_MADANIA, Communes.USTHB))
                )
            );
        }
    }
    
    private static Profile createTeacherProfile(boolean isChauffeur, LocalDateTime baseDate) {
        if (isChauffeur) {
            return new Profile(
                StatusUser.CHAUFFEUR,
                TypeCourse.ALLER_SIMPLE,
                new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.AVEC_MUSIQUE, BagagesPreferences.SANS_PREFERENCE),
                new Disponibilite(
                    DisponibiliteType.QUOTIDIEN,
                    List.of(baseDate.withHour(7).withMinute(30), baseDate.withHour(16).withMinute(30)),
                    new Itineraire(List.of(Communes.HYDRA, Communes.BEN_AKNOUN, Communes.BOUZAREAH, Communes.USTHB))
                ),
                5
            );
        } else {
            return new Profile(
                StatusUser.PASSAGER,
                TypeCourse.ALLER_SIMPLE,
                new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
                new Disponibilite(
                    DisponibiliteType.QUOTIDIEN,
                    List.of(baseDate.withHour(8).withMinute(15)),
                    new Itineraire(List.of(Communes.USTHB, Communes.KOUBA))
                )
            );
        }
    }
    
    private static Profile createATSProfile(boolean isChauffeur, LocalDateTime baseDate) {
        if (isChauffeur) {
            return new Profile(
                StatusUser.CHAUFFEUR,
                TypeCourse.ALLER_SIMPLE,
                new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
                new Disponibilite(
                    DisponibiliteType.QUOTIDIEN,
                    List.of(baseDate.withHour(7).withMinute(0), baseDate.withHour(15).withMinute(30)),
                    new Itineraire(List.of(Communes.USTHB, Communes.EL_HARRACH, Communes.OUED_SMAR, Communes.BARAKI))
                ),
                4
            );
        } else {
            return new Profile(
                StatusUser.PASSAGER,
                TypeCourse.ALLER_SIMPLE,
                new Preferences(SexePreferences.SANS_PREFERENCE, MusiquePreferences.SANS_PREFERENCE, BagagesPreferences.SANS_PREFERENCE),
                new Disponibilite(
                    DisponibiliteType.QUOTIDIEN,
                    List.of(baseDate.withHour(8).withMinute(30)),
                    new Itineraire(List.of(Communes.BAB_EZZOUAR, Communes.USTHB))
                )
            );
        }
    }
}