package Strategie;


import Evenement.*;
import java.util.ArrayList;
import robot.*;
import simulation.*;
import Dijkstra.*;
import java.util.LinkedList;

public class Chemin {

    private int dateDebut;
    private ArrayList<Evenement> chemin;
    private DonneesSimulation donnees;

    public Chemin(int dateDebut, DonneesSimulation donnees) {
        this.donnees = donnees;
        this.dateDebut = dateDebut;
        this.chemin = new ArrayList<>(0);
    }

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
    
    public void viderChemin() {
        chemin.clear();
    }

    public boolean calculerChemin(Robot robot, Case depart, Case destination, int indexRobot) {
        int dateCour = dateDebut;
        Robot robotCour = robot.copy();
        ArrayList<Vertex> cases = new ArrayList<Vertex>(0);
        ArrayList<Edge> arcs = new ArrayList<>(0);
        LinkedList<Vertex> cheminGraphe = new LinkedList<>();
        Vertex cour;
        //int VoisinPos;
        int n = donnees.getCarte().getNbColonnes();
        for (int j = 0; j < donnees.getCarte().getNbLignes(); j++) {
            for (int i = 0; i < donnees.getCarte().getNbColonnes(); i++) {
                cases.add(new Vertex(Integer.toString(i + j * n), Integer.toString(i + j * n)));
            }
        }
        for (int j = 0; j < donnees.getCarte().getNbLignes(); j++) {
            for (int i = 0; i < donnees.getCarte().getNbColonnes(); i++) {
                for (EnumDirection dir : EnumDirection.values()) {
                    if (robotCour.positionPossible(j,i)) {
                        robotCour.setPosition(j,i);
                        if (robotCour.deplacementPossible(dir)) {
                            cour = cases.get(i + j * n);
                            int VoisinPos = donnees.getCarte().getVoisin(donnees.getCarte().getCase(j, i), dir).getColonne()
                                    + n * donnees.getCarte().getVoisin(donnees.getCarte().getCase(j, i), dir).getLigne();
                            arcs.add(new Edge("ID", cour, cases.get(VoisinPos), 1));
                        }
                    }
                }
            }
        }
        Graph graph = new Graph(cases, arcs);
        DijkstraAlgorithm algo = new DijkstraAlgorithm(graph);
        algo.execute(cases.get(depart.getColonne() + depart.getLigne()*n));
        cheminGraphe = algo.getPath(cases.get(destination.getColonne() + destination.getLigne()*n));
        if (cheminGraphe == null) {
            /* Pas de chemin trouvé */
            return false;
        }
        EnumDirection dirCour = EnumDirection.NORD;
        for (int i=1; i<cheminGraphe.size(); i++) {
            int test = Integer.parseInt(cheminGraphe.get(i).getId()) - Integer.parseInt(cheminGraphe.get(i-1).getId());
            dateCour++;
            if (test == -1) {
                dirCour = EnumDirection.OUEST;
            } else if (test == 1) {
                dirCour = EnumDirection.EST;
            } else if (test == -n) {
                dirCour = EnumDirection.NORD;
            } else if (test == n) {
                dirCour = EnumDirection.SUD;
            }   
            chemin.add(new EvenementDeplacementRobot(dateCour, robot, dirCour, indexRobot));
        }
        return true;   
    }
}
