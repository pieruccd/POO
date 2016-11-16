/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alaouibs
 */

import java.util.ArrayList;
import robot.*;
import simulation.*;
import Evenement.*;

public class ChefPompier {
//    private ArrayList<Incendie> incendies;
//    private ArrayList<Robot> robots;
//    private Carte map;
    private DonneesSimulation donnees;
    private ArrayList<Evenement> cheminall;
    int dateCour;

    public void setCheminall(ArrayList<Evenement> cheminall) {      
        this.cheminall = cheminall;
    }

    public void setDatecour(int datecour) {
        this.dateCour = datecour;
    }

    public int getDatecour() {
        return dateCour;
    }
    
    public ChefPompier(DonneesSimulation donnees, int dateDebut) {
        this.dateCour = dateDebut;
//        this.map = map;
//        this.robots = new ArrayList<Robot>();
//        this.robots = robots;
//        this.incendies = new ArrayList<Incendie>();
//        this.incendies = incendies;
            this.donnees = donnees;
        this.cheminall = new ArrayList<Evenement>();
    }

    public ArrayList<Evenement> getCheminall() {
        return cheminall;
    }

    public DonneesSimulation getDonneesSimulation() {
        return donnees;
    }
    
    public void setMap(DonneesSimulation donnees) {
        this.donnees = donnees;
    }
    
    public void strategieelementaire() {
        for(Robot rob : this.donnees.getRobots()){
            rob.setBusy(false);
        }
        Chemin chemin = new Chemin(0, donnees);
        for(Incendie inc : this.donnees.getIncendies()) {
            for(Robot rob : this.donnees.getRobots()) {
                if (! rob.IsBusy()){
                    System.out.println("Position Robot : " + this.donnees.getRobots().indexOf(rob));
                   chemin.calculerChemin(rob, rob.getPostion(), inc.getCase(), this.donnees.getRobots().indexOf(rob));
                   if (!chemin.getChemin().isEmpty()) {
                       rob.setBusy(true);
                       if (rob.getEauReservoir() > 0) {           
                            this.cheminall.addAll(chemin.getChemin());
                            this.dateCour = chemin.getDateDebut();
                            this.cheminall.add(new EvenementDeverserEau(this.dateCour, inc, rob, this.donnees.getIncendies().indexOf(inc), this.donnees.getRobots().indexOf(rob)));
                            this.dateCour ++;
                            break;
                       }
                    
                   }

                }
                
            }
        }
        System.out.println("BONJOUUR");
    } 
    
    
}
