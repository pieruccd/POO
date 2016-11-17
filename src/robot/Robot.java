package robot;

import simulation.Case;
import simulation.Carte;
import simulation.EnumDirection;

abstract public class Robot {

    protected int ligne;
    protected int colonne;
    protected double vitesse;
    protected Carte carte;
    protected int quantitéEau;
    protected boolean busy = false;

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
    
    public boolean IsBusy() {
        return this.busy;
    }
    
    public Case getPostion() {
        return carte.getCase(ligne, colonne);
    }

    public int getLigne() {
        return ligne;
    }

    public Carte getCarte() {
        return carte;
    }

    abstract public void remplirReservoir();

    abstract public int getEauReservoir();

    public int getColonne() {
        return colonne;
    }

    public abstract void setPostion(Case pos);

    public abstract void deverserEau(int vol);

    public void deplacer(EnumDirection dir) {
        this.setPosition(this.carte.getVoisin(this.getPostion(), dir).getLigne(),
                this.carte.getVoisin(this.getPostion(), dir).getColonne());
    }
    
    abstract public boolean deplacementPossible(EnumDirection dir);
    
    abstract public boolean positionPossible(int lig, int col);

    public abstract void setPosition(int lig, int col);

    public double getVitesse() {
        return this.vitesse;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }
    
    abstract public double tempsnecessaire(Case voisin, Carte map);
    
    abstract public Robot copy();

    @Override
    public String toString() {
        return "Robot{" + "ligne=" + ligne + ", colonne=" + colonne + ", vitesse=" + vitesse + ", carte=" + carte + '}';
    }

}
