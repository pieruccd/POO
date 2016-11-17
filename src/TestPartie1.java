
import robot.*;
import simulation.EnumNatureTerrain;
import simulation.EnumDirection;
import simulation.Case;
import simulation.Carte;
import simulation.DonneesSimulation;
import simulation.Incendie;

public class TestPartie1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* Test Case */
        System.out.println("TEST CASE" + "\n");

        Case testCase = new Case(1, 1);
        testCase.setNature(EnumNatureTerrain.HABITAT);
        System.out.println(testCase.toString());
        testCase = new Case(1, 1, EnumNatureTerrain.EAU);
        System.out.println(testCase.toString());
        testCase.setColonne(3);
        testCase.setLigne(2);
        testCase.setNature(EnumNatureTerrain.FORET);
        System.out.println(testCase.toString());

        System.out.println("\n" + "TEST CLONAGE CASE (SANS EXCEPTION)" + "\n");
        testCase = new Case(3, 3, EnumNatureTerrain.EAU);
        Case testCaseClone = (Case) testCase.clone();
        testCase.setColonne(0);
        System.out.println(testCase);
        System.out.println(testCaseClone);


        /* Test Carte */
        System.out.println("\n" + "TEST CARTE" + "\n");

        Carte testCarte = new Carte(5, 5, 1);
        System.out.println(testCarte.toString());
        System.out.println(testCarte.getVoisin(testCarte.getCase(0, 0), EnumDirection.SUD));
        System.out.println(testCarte.getVoisin(testCarte.getCase(0, 0), EnumDirection.SUD));
        System.out.println(testCarte.voisinExiste(testCarte.getCase(0, 4), EnumDirection.SUD));
        System.out.println(testCarte.getVoisin(testCarte.getCase(0, 4), EnumDirection.OUEST));
        System.out.println(testCarte.getVoisin(testCarte.getCase(1, 4), EnumDirection.OUEST));
        System.out.println(testCarte.getVoisin(testCarte.getCase(1, 4), EnumDirection.NORD));
        System.out.println(testCarte.getCase(2, 3).toString());
        testCarte.setCase(testCase);
        System.out.println(testCase);
        System.out.println(testCarte.getCase(2, 2).toString());

        System.out.println("\n" + "TEST CONSTRUCTEUR CARTE (SANS EXCEPTION)" + "\n");
        Carte CarteClone = new Carte(testCarte);
        System.out.println(CarteClone);
        Case testCaseCloneCarte = new Case(2, 2, EnumNatureTerrain.FORET);
        CarteClone.setCase(testCaseCloneCarte);
        System.out.println(testCarte.getCase(2, 2));
        System.out.println(CarteClone.getCase(2, 2));

        /* Test Drone */
        System.out.println("\n" + "TEST DRONE" + "\n");

        testCarte.getCase(3, 3).setNature(EnumNatureTerrain.EAU);
        Drone testDrone = new Drone(testCarte, testCarte.getCase(3, 3), 120, 5000);
        System.out.println(testDrone.toString());
        testDrone.remplirReservoir();
        testDrone.deverserEau(1000);
        testDrone.setVitesse(50);
        testDrone.setPostion(testCarte.getCase(4, 2));
        System.out.println(testDrone.toString());

        /* Test Robot a pattes */
        System.out.println("\n" + "TEST ROBOT A PATTES" + "\n");

        /* Test Robot a chenilles */
        System.out.println("\n" + "TEST ROBOT A CHENILLES " + "\n");

        /* Test Robot a roues */
        System.out.println("\n" + "TEST ROBOT A ROUES" + "\n");

        /* Test Incendie */
        System.out.println("\n" + "TEST INCENDIE" + "\n");
        Incendie testIncendie = new Incendie(testCase, 100);
        System.out.println(testIncendie.getPos());

        Incendie testIncendieClone = testIncendie.clone();
        System.out.println("Incendie : " + testIncendie);
        System.out.println("Incendie Clone :" + testIncendieClone);
        testIncendieClone.setNbLitres(1589);
        System.out.println("Incendie : " + testIncendie);
        System.out.println("Incendie Clone :" + testIncendieClone);


        /* Test DonneesSimulation */
        DonneesSimulation testDonneesSimulation = new DonneesSimulation(" .." + "\\\\" + ".." + "\\\\" + "cartes" + "\\\\" + "carteSujet.map");
        System.out.println(testDonneesSimulation.toString());

    }

}
