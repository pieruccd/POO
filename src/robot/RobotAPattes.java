package robot;

import simulation.*;

public final class RobotAPattes extends Robot {

    public RobotAPattes(Carte carte, Case pos) {
        this.carte = carte;
        this.setPostion(pos);
    }

    public RobotAPattes(RobotAPattes robot) {
        this.carte = new Carte(robot.carte);
        this.setPosition(robot.ligne, robot.colonne);
    }

    @Override
    public void deverserEau(int vol) {
        // Comme le reservoir est infini, rien de spécial à faire ici ...
    }

    @Override
    public void remplirReservoir() {
        // Comme le reservoir est infini, rien de spécial à faire ici ...
    }

    @Override
    public RobotAPattes copy() {
        return new RobotAPattes(this);
    }

    @Override
    public int getEauReservoir() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setPostion(Case pos) {
        this.setPosition(pos.getLigne(), pos.getColonne());
    }

    @Override
    public boolean deplacementPossible(EnumDirection dir) {
        if (!this.carte.voisinExiste(this.carte.getCase(ligne, colonne), dir)) {
            return false;
        }
        Case voisin = this.carte.getVoisin(this.carte.getCase(ligne, colonne), dir);
        return this.carte.getCase(voisin.getLigne(), voisin.getColonne()).getNature() != EnumNatureTerrain.EAU;
    }

    @Override
    public boolean positionPossible(int lig, int col) {
        return ((lig < carte.getNbLignes()) && (lig >= 0) && (col < carte.getNbColonnes()) && (col >= 0)
                && (this.getCarte().getCase(lig, col).getNature() != EnumNatureTerrain.EAU));
    }

    @Override
    public void setPosition(int lig, int col) {
        if (this.carte.getCase(lig, col).getNature() == EnumNatureTerrain.EAU) {
            throw new IllegalArgumentException("Un robot à pattes ne peux pas se rendre sur l'eau !");
        } else if (this.getPostion().getNature() == EnumNatureTerrain.ROCHE) {
            this.ligne = lig;
            this.colonne = col;
            this.vitesse = 10;
        } else {
            this.colonne = col;
            this.ligne = lig;
            this.vitesse = 30;
        }
    }

    public double tempsnecessaire(Case voisin, Carte map) {
        if (voisin.getNature() == EnumNatureTerrain.ROCHE) {
            return ((this.getVitesse() * 10) / 2) * map.getTailleCases() * 2;
        } else if (voisin.getNature() == EnumNatureTerrain.EAU) {
            return Double.MAX_VALUE;
        } else {
            return ((this.getVitesse() * 30) / 2) * map.getTailleCases() * 2;
        }
    }
}
