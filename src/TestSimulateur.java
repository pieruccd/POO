
import Evenement.*;
import simulation.*;
import gui.*;
import java.awt.Color;
import java.util.ArrayList;

public class TestSimulateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);

        /*On charge les données de la simulation*/
        DonneesSimulation testDonneesSimulation = new DonneesSimulation(" .." + "\\\\" + ".." + "\\\\" + "cartes" + "\\\\" + "carteSujet.map");

        /* Scenario détaillé dans la partie 2 de l'énoncé (scénario 1) */
        EvenementDeplacementRobot testEvenement1 = new EvenementDeplacementRobot(0, testDonneesSimulation.getRobots().get(1), EnumDirection.NORD, 1);
        EvenementDeverserEau testEvenement2 = new EvenementDeverserEau(1, testDonneesSimulation.getIncendies().get(4), testDonneesSimulation.getRobots().get(1), 1, 4);
        EvenementDeplacementRobot testEvenement3 = new EvenementDeplacementRobot(2, testDonneesSimulation.getRobots().get(1), EnumDirection.OUEST, 1);
        EvenementDeplacementRobot testEvenement4 = new EvenementDeplacementRobot(3, testDonneesSimulation.getRobots().get(1), EnumDirection.OUEST, 1);
        EvenementRemplir testEvenement5 = new EvenementRemplir(4, testDonneesSimulation.getRobots().get(1), 1);
        EvenementDeplacementRobot testEvenement6 = new EvenementDeplacementRobot(5, testDonneesSimulation.getRobots().get(1), EnumDirection.EST, 1);
        EvenementDeplacementRobot testEvenement7 = new EvenementDeplacementRobot(6, testDonneesSimulation.getRobots().get(1), EnumDirection.EST, 1);
        EvenementDeverserEau testEvenement8 = new EvenementDeverserEau(7, testDonneesSimulation.getIncendies().get(4), testDonneesSimulation.getRobots().get(1), 1, 4);

        ArrayList<Evenement> listeEvenements = new ArrayList<>();
        listeEvenements.add(testEvenement1);
        listeEvenements.add(testEvenement2);
        listeEvenements.add(testEvenement3);
        listeEvenements.add(testEvenement4);
        listeEvenements.add(testEvenement5);
        listeEvenements.add(testEvenement6);
        listeEvenements.add(testEvenement7);
        listeEvenements.add(testEvenement8);

        /* Ajoute le chemin pour le robot 0 vers la case (7,7) */
        Chemin cheminTo77 = new Chemin(9);
        cheminTo77.calculerChemin(testDonneesSimulation.getRobots().get(0), testDonneesSimulation.getRobots().get(0).getPostion(), testDonneesSimulation.getCarte().getCase(7, 7), 0);
        listeEvenements.addAll(cheminTo77.getChemin());

        /* Ajoute le chemin pour le robot 0 vers la case (1,1) */
        Chemin cheminto11 = new Chemin(listeEvenements.get(listeEvenements.size() - 1).date + 1);
        cheminto11.calculerChemin(testDonneesSimulation.getRobots().get(0), testDonneesSimulation.getCarte().getCase(7, 7), testDonneesSimulation.getCarte().getCase(1, 1), 0);
        listeEvenements.addAll(cheminto11.getChemin());

        /* Ajoute le chemin pour le robot 0 vers la case (7,0) */
        Chemin cheminto70 = new Chemin(listeEvenements.get(listeEvenements.size() - 1).date + 1);
        cheminto70.calculerChemin(testDonneesSimulation.getRobots().get(0), testDonneesSimulation.getCarte().getCase(1, 1), testDonneesSimulation.getCarte().getCase(7, 0), 0);
        listeEvenements.addAll(cheminto70.getChemin());

        /*Creation du simulateur*/
        Simulateur simulateur = new Simulateur(gui, testDonneesSimulation, listeEvenements);
    }

}
