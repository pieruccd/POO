package simulation;

import simulation.Case;

public class Incendie implements Cloneable {

    private Case pos;
    private int nbLitres;

    public Incendie(Case Case, int nbLitres) {
        this.pos = Case;
        this.nbLitres = nbLitres;
    }
    
    public Incendie(Incendie inc) {
        this.nbLitres = inc.nbLitres;
        this.pos = inc.pos.clone();
    }

    public Case getCase() {
        return pos;
    }

    public void recevoirEau(int vol) {
        if (vol > nbLitres) {
            nbLitres = 0;
            return;
        } else if (vol < 0) {
            throw new IllegalArgumentException("Volume d'eau à recevoir négatif ! ERREUR");
        } else {
            nbLitres = nbLitres - vol;
        }
    }

    public int getNbLitres() {
        return nbLitres;
    }

    public void setCase(Case Case) {
        this.pos = Case;
    }

    public void setNbLitres(int nbLitres) {
        this.nbLitres = nbLitres;
    }

    @Override
    public Incendie clone() {
        Incendie incendie = null;
        try {
            incendie = (Incendie) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        incendie.pos = this.pos.clone();
        return incendie;
    }

    @Override
    public String toString() {
        return "Incendie{" + "Case=" + pos + ", nbLitres=" + nbLitres + '}';
    }

}
