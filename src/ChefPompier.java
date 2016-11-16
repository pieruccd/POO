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
    private ArrayList<Incendie> incendies;
    private ArrayList<Robot> robots;
    private Carte map;
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
    
    public ChefPompier(ArrayList<Incendie> incendies, ArrayList<Robot> robots, Carte map, int dateDebut) {
        this.dateCour = dateDebut;
        this.map = map;
        this.robots = robots;
        this.incendies = incendies;
    }

    public ArrayList<Evenement> getCheminall() {
        return cheminall;
    }

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public Carte getMap() {
        return map;
    }
    
    public void setIncendies(ArrayList<Incendie> incendies) {
        this.incendies = incendies;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public void setMap(Carte map) {
        this.map = map;
    }
    
    public void strategieelementaire() {
        for(Robot rob : this.robots){
            rob.setBusy(false);
        }
        Chemin chemin = new Chemin(0);
        for(Incendie inc : this.incendies) {
            for(Robot rob : this.robots) {
                if (! rob.IsBusy()){
                   chemin.calculerChemin(rob, rob.getPostion(), inc.getCase(), robots.indexOf(rob));
                   if (!chemin.getChemin().isEmpty()) {
                       rob.setBusy(true);
                       if (rob.getEauReservoir() > 0) {           
                            this.cheminall.addAll(chemin.getChemin());
                            this.dateCour = chemin.getDateDebut();
                            this.cheminall.add(new EvenementDeverserEau(this.dateCour, inc, rob, incendies.indexOf(inc), robots.indexOf(rob)));
                            this.dateCour ++;
                       }
                    
                   }

                }
                
            }
        }
    } 
    
    
}
