
import Evenement.*;
import java.util.ArrayList;
import robot.*;
import simulation.*;

public class Chemin {


    private int dateDebut;
    private ArrayList<Evenement> chemin;
    private DonneesSimulation donnees; 
    
    public Chemin(int dateDebut, DonneesSimulation donnees) {
        this.donnees = donnees;
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
        System.out.println("Incendie ligne : " + destination.getLigne() + " Incendie colonne : " + destination.getColonne());
        Case prec = new Case(0,0);
                System.out.println("Index Robot fdp :" + indexRobot);        
        while ((robotCour.getPostion().getLigne() != destination.getLigne() || robotCour.getColonne() != destination.getColonne())) {
            dist = Double.MAX_VALUE;
            for (EnumDirection dir : EnumDirection.values()) {
                System.out.println(dir);
                if (robotCour.deplacementPossible(dir)) {
                    if ((distance(robotCour.getCarte().getVoisin(robotCour.getPostion(), dir), destination) < dist) && !robotCour.getCarte().getVoisin(robotCour.getPostion(), dir).issamecase(prec)) {
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
            prec = robotCour.getPostion();
            robotCour.deplacer(dirCour);
            EvenementDeplacementRobot cheminElementaire = new EvenementDeplacementRobot(dateCour, robot, dirCour, indexRobot);
            this.chemin.add(cheminElementaire);
            this.dateDebut = dateCour;
        }
        System.out.println("Fin While");
    }
    
    public void Dijkstra(Robot robot, Case depart, Case destination, int indexRobot) {
        ArrayList<Case> Q = new ArrayList<Case>();
        double distance[][] = new double[donnees.getCarte().getNbLignes()][donnees.getCarte().getNbColonnes()];
        Case prec[][] = new Case[donnees.getCarte().getNbLignes()][donnees.getCarte().getNbColonnes()];
        //CreerRobotCourt
        if (robot.getClass() == RobotAChenille) {
            
        }
        double alt = 0; // Variable sauvegarde
        
        for (int i = 0; i < donnees.getCarte().getNbLignes(); i ++) {
            for (int j = 0; j < donnees.getCarte().getNbColonnes(); j++) {
                distance[i][j] = Double.MAX_VALUE;
                prec[i][j] = new Case(-1,-1);
                Q.add(donnees.getCarte().getCase(i, j));
            }
        }

        distance[depart.getLigne()][depart.getColonne()] = 0;        
        Case voisin;
        //Tant que Q n'est pas vide
        while(!Q.isEmpty()) {
            Case u = Chemin.mindist(Q, distance);
            // Je dois placer robotCour là
            //Pour chaque voisin de u (donc 4 voisins)
            for (EnumDirection dir : EnumDirection.values()) {
                voisin = donnees.getCarte().getVoisin(u, dir);
                if (voisin != null) {
                    alt = distance[u.getLigne()][u.getLigne()] + robotCour.tempsnecessaire(voisin, donnees.getCarte());
                    if (alt < distance[voisin.getLigne()][voisin.getColonne()]) {
                        distance[voisin.getLigne()][voisin.getColonne()] = alt;
                        prec[voisin.getLigne()][voisin.getColonne()] = u;
                    }
                }
            }
        }
    }
    
    public static Case mindist(ArrayList<Case> Q, double distance[][]) {
        double minimum = Double.MAX_VALUE;
        Case caseMin = new Case(0,0);
        for (Case q : Q) {
           if (distance[q.getLigne()][q.getColonne()] < minimum) {
               minimum = distance[q.getLigne()][q.getColonne()];
               caseMin = q;
           }
        }
        return caseMin;
    }
    

    
    
    
    
    
    
    
    
    
}
