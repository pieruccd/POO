package simulation;

import simulation.*;
import robot.*;
import io.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.util.ArrayList;

public class DonneesSimulation implements Cloneable {

    private Carte carte;
    private ArrayList<Incendie> incendies;
    private ArrayList<Robot> robots;

    public DonneesSimulation() {
    }
    
    public DonneesSimulation(DonneesSimulation sim) {
        this.carte = new Carte(sim.carte);
        ArrayList<Incendie> incendiesBis = new ArrayList<>();
        for (int i = 0; i < sim.incendies.size(); i++) {
            incendiesBis.add(sim.incendies.get(i).clone());
        }
        ArrayList<Robot> robotsBis = new ArrayList<>(sim.robots.size());
        for (int i=0; i < sim.robots.size(); i++) {
            robotsBis.add(sim.robots.get(i).copy());
        }
        this.incendies = incendiesBis;
        this.robots = robotsBis;
    }

    public DonneesSimulation(String fichierDonnees) {
        try {
            DonneesSimulation res = LecteurDonnees.lire(fichierDonnees);
            this.carte = res.carte;
            this.incendies = res.incendies;
            this.robots = res.robots;
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + fichierDonnees + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + fichierDonnees + " invalide: " + e.getMessage());
        }
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public void setIncendies(ArrayList<Incendie> incendies) {
        this.incendies = incendies;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    @Override
    public String toString() {
        return "DonneesSimulation{" + "carte=" + carte + ", incendies=" + incendies + ", robots=" + robots + '}';
    }

}
