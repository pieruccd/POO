package Strategie;


import java.util.ArrayList;
import robot.*;
import simulation.*;
import Evenement.*;

public class ChefPompier {

    private DonneesSimulation donnees;
    private ArrayList<Evenement> evenementsTotaux;
    int dateCour;

    public ChefPompier(DonneesSimulation donnees, int dateDebut) {
        this.dateCour = dateDebut;
        this.donnees = donnees;
        this.evenementsTotaux = new ArrayList<Evenement>();
    }

    public void setEvenementsTotaux(ArrayList<Evenement> evenementsTotaux) {
        this.evenementsTotaux = evenementsTotaux;
    }

    public void setDatecour(int datecour) {
        this.dateCour = datecour;
    }

    public int getDatecour() {
        return dateCour;
    }

    public ArrayList<Evenement> getEvenementsTotaux() {
        return evenementsTotaux;
    }

    public DonneesSimulation getDonneesSimulation() {
        return donnees;
    }

    public void setMap(DonneesSimulation donnees) {
        this.donnees = donnees;
    }

    public void strategieelementaire() {
        Chemin cheminCour = new Chemin(0, donnees);
        int dateCur = 0;
        for (int i = 0; i < donnees.getIncendies().size(); i++) {
            if ((!donnees.getIncendies().get(i).isAffecte()) && (!donnees.getIncendies().get(i).estEteint())) {
                for (int j = 0; j < donnees.getRobots().size(); j++) {
                    if ((!donnees.getRobots().get(j).IsBusy())) {
                        if (cheminCour.calculerChemin(donnees.getRobots().get(j), donnees.getRobots().get(j).getPostion(), donnees.getIncendies().get(i).getPos(), j)) {
                            evenementsTotaux.addAll(cheminCour.getChemin());
                            evenementsTotaux.add(new EvenementDeverserEau(dateCur + cheminCour.getChemin().size() + 1,
                                    donnees.getIncendies().get(i), donnees.getRobots().get(j), j, i));
                            dateCur = dateCur + cheminCour.getChemin().size() + 2;
                            cheminCour.viderChemin();
                            donnees.getRobots().get(j).setBusy(true);
                            donnees.getIncendies().get(i).setAffecte(true);
                            cheminCour.setDateDebut(dateCur);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("NOMBRE D'EVENEMENTS : " + evenementsTotaux.size());
    }

}
