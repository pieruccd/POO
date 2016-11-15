
import Evenement.*;
import java.util.ArrayList;
import robot.*;
import simulation.*;

public class Chemin {

    private int dateDebut;
    private ArrayList<Evenement> chemin;

    public Chemin(int dateDebut) {
        this.dateDebut = dateDebut;
        this.chemin = new ArrayList<>(0);
    }

    public int getDateDebut() {
        return dateDebut;
    }

    public ArrayList<Evenement> getChemin() {
        return chemin;
    }

    public void setDateDebut(int dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setChemin(ArrayList<Evenement> chemin) {
        this.chemin = chemin;
    }

    static double distance(Case pos1, Case pos2) {
        int x1 = pos1.getLigne();
        int x2 = pos2.getLigne();
        int y1 = pos1.getColonne();
        int y2 = pos2.getColonne();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void calculerChemin(Robot robot, Case depart, Case destination, int indexRobot) {
        /* Algorithme glouton */
        int dateCour = dateDebut;
        double dist;
        EnumDirection dirCour = EnumDirection.NORD;
        Robot robotCour = robot.copy();
        robotCour.setPosition(depart.getLigne(),depart.getColonne());
        System.out.println("CALCUL ...");
        while ((robotCour.getPostion().getLigne() != destination.getLigne() || robotCour.getColonne() != destination.getColonne())) {
            dist = Double.MAX_VALUE;
            for (EnumDirection dir : EnumDirection.values()) {
                if (robotCour.deplacementPossible(dir)) {
                    if (distance(robotCour.getCarte().getVoisin(robotCour.getPostion(), dir), destination) < dist) {
                        dist = distance(robotCour.getCarte().getVoisin(robotCour.getPostion(), dir), destination);
                        dirCour = dir;
                    }
                }
                if (dist == Double.MAX_VALUE) {
                    System.out.println("Le robot est bloqué !"); 
                    return;
                }
            }
            /* On choisit le meilleur chemin local */
            dateCour++;
            robotCour.deplacer(dirCour);
            EvenementDeplacementRobot cheminElementaire = new EvenementDeplacementRobot(dateCour, robot, dirCour, indexRobot);
            this.chemin.add(cheminElementaire);
        }
    }

}
