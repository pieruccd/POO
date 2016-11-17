package simulation;

import Evenement.*;
import gui.*;
import java.awt.Color;
import java.util.ArrayList;

public final class Simulateur implements Simulable {

    private final GUISimulator gui;
    private DonneesSimulation donnees;
    private final DonneesSimulation donnesInitiales;
    private final int tailleCase = 100; // Uniquement Graphique, n'a rien avoir avec la taille des cases dans Carte
    private int dateSimulation = 0; // Par défaut on se met à la date 0
    private final ArrayList<Evenement> listeEvenements;

    public Simulateur(GUISimulator gui, DonneesSimulation donnees, ArrayList<Evenement> listeEvenements) {
        this.gui = gui;
        this.donnees = donnees;
        this.donnesInitiales = new DonneesSimulation(donnees);
        gui.setSimulable(this);
        this.listeEvenements = listeEvenements;
        drawMap();
    }

    public void addEvenement(Evenement e) {
        listeEvenements.add(e);
    }

    public void incrementeDate() {
        for (int i = 0; i < listeEvenements.size(); i++) {
            if (listeEvenements.get(i).date == dateSimulation) {
                listeEvenements.get(i).execute();
                this.drawMap();
            }
        }

        this.dateSimulation++;
    }

    public boolean simulationTerminee() {
        for (int i = 0; i < listeEvenements.size(); i++) {
            if (listeEvenements.get(i).date >= dateSimulation) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void next() {
        if (!simulationTerminee()) {
            System.out.println("Next !");
            incrementeDate();
        }
    }

    @Override
    public void restart() {
        System.out.println("Restart");
        dateSimulation = 0;
        /* Mise à jour des donées */
        this.donnees = new DonneesSimulation(donnesInitiales);
        /* Mise a jour de la carte */
        drawMap();
        /* Mise a jour des evenements */
        for (int i = 0; i < listeEvenements.size(); i++) {
            listeEvenements.get(i).setRobot(donnees.getRobots().get(listeEvenements.get(i).getIndexRobot()));
            if (listeEvenements.get(i) instanceof EvenementDeverserEau) {
                ((EvenementDeverserEau)listeEvenements.get(i)).setIncendie(donnees.getIncendies().get(((EvenementDeverserEau)listeEvenements.get(i)).getIndexIncendie()));
            }
        }
    }

    public void drawMap() {

        int xOffset = tailleCase / 2;
        int yOffset = tailleCase / 2;
        int xCour = xOffset;
        int yCour = yOffset;

        /* Dessin des rectangles représentant une case */
        for (int i = 0; i < donnees.getCarte().getNbColonnes(); i++) {
            xCour = xOffset;
            for (int j = 0; j < donnees.getCarte().getNbLignes(); j++) {
                gui.addGraphicalElement(new Rectangle(xCour, yCour, Color.BLACK, Color.WHITE, tailleCase, tailleCase));
                switch (donnees.getCarte().getCase(i, j).getNature()) {
                    case EAU:
                        gui.addGraphicalElement(new Text(xCour, yCour, Color.BLACK, "EAU"));
                        break;
                    case FORET:
                        gui.addGraphicalElement(new Text(xCour, yCour, Color.BLACK, "FORET"));
                        break;
                    case HABITAT:
                        gui.addGraphicalElement(new Text(xCour, yCour, Color.BLACK, "HABITAT"));
                        break;
                    case ROCHE:
                        gui.addGraphicalElement(new Text(xCour, yCour, Color.BLACK, "ROCHE"));
                        break;
                    case TERRAIN_LIBRE:
                        gui.addGraphicalElement(new Text(xCour, yCour, Color.BLACK, "TERRAIN_LIBRE"));
                        break;
                }
                gui.addGraphicalElement(new Text(xCour, yCour + 10, Color.BLACK, i + "," + j));
                //gui.addGraphicalElement(new ImageElement(xCour, yCour, "..\\..\\images\\foret.gif", tailleCase, tailleCase, gui));
                xCour = xCour + tailleCase;
            }
            yCour = yCour + tailleCase;
        }

        for (int i = 0; i < donnees.getIncendies().size(); i++) {
            if (donnees.getIncendies().get(i).getNbLitres() != 0) {
                gui.addGraphicalElement(new Text(donnees.getIncendies().get(i).getPos().getColonne() * tailleCase + xOffset,
                        donnees.getIncendies().get(i).getPos().getLigne() * tailleCase + 20 + yOffset, Color.RED, "I ="
                        + donnees.getIncendies().get(i).getNbLitres()));
            }
        }

        for (int i = 0; i < donnees.getRobots().size(); i++) {
            gui.addGraphicalElement(new Text(donnees.getRobots().get(i).getColonne() * tailleCase + xOffset,
                    donnees.getRobots().get(i).getLigne() * tailleCase - 20 + yOffset, Color.BLUE, "Robot"));
        }

    }

}
