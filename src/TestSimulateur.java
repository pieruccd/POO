
import Strategie.ChefPompier;
import simulation.*;
import gui.*;
import java.awt.Color;

public class TestSimulateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);

        /*On charge les données de la simulation*/
        DonneesSimulation testDonneesSimulation = new DonneesSimulation("./cartes" + "/" + "carteSujet.map");
        
        ChefPompier testChefPompier = new ChefPompier(testDonneesSimulation, 0);
        testChefPompier.strategieelementaire();
        
        Simulateur simulateur = new Simulateur(gui, testDonneesSimulation, testChefPompier.getEvenementsTotaux());
    }

}
