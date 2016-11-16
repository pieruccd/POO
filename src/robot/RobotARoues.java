package robot;

import simulation.*;


public final class RobotARoues extends RobotTerrestre {

    public RobotARoues(Carte carte, Case pos, double vitesse, int eau) {
        this.carte = carte;
        this.quantitéEau = eau;
        this.tempsRemplissage = 10;
        this.volumeReservoir = 5000;
        this.setVitesse(vitesse);
        this.setPostion(pos);   
    }
    
    public RobotARoues(RobotARoues robot) {
        this.carte = new Carte(robot.carte);
        this.quantitéEau = robot.quantitéEau;
        this.tempsRemplissage = robot.tempsRemplissage;
        this.volumeReservoir = 5000;
        this.setVitesse(robot.vitesse);
        this.setPosition(robot.ligne,robot.colonne);
    }
    
    @Override
    public void setPostion(Case pos) {
        this.setPosition(pos.getLigne(), pos.getColonne());
    }

    @Override
    public boolean deplacementPossible(EnumDirection dir) {
        Case voisin = this.carte.getVoisin(this.carte.getCase(ligne, colonne), dir);
        return (this.carte.getCase(voisin.getLigne(), voisin.getColonne()).getNature() == EnumNatureTerrain.TERRAIN_LIBRE 
                || this.carte.getCase(voisin.getLigne(), voisin.getColonne()).getNature() == EnumNatureTerrain.HABITAT)
                && (this.carte.voisinExiste(this.getPostion(), dir));
    }

    @Override
    public void setPosition(int lig, int col) {
        if (this.carte.getCase(lig, col).getNature() == EnumNatureTerrain.TERRAIN_LIBRE || this.carte.getCase(lig, col).getNature() == EnumNatureTerrain.HABITAT) {
            this.ligne = lig;
            this.colonne = col;
        } else {
            throw new IllegalArgumentException("Terrain incompatible avec un robot à roues");
        }
    }   

    void setVitesse(double vitesse) {
        if (this.getPostion().getNature() == EnumNatureTerrain.HABITAT || this.getPostion().getNature() == EnumNatureTerrain.TERRAIN_LIBRE) {
            if (vitesse > 80 || vitesse < 0) {
                throw new IllegalArgumentException("Vitesse invalide pour un ");
            } else {
                this.vitesse = vitesse;
            }
        }
    }

    public double tempsnecessaire(Case voisin, Carte map) {
        if (voisin.getNature() == EnumNatureTerrain.TERRAIN_LIBRE || voisin.getNature() == EnumNatureTerrain.HABITAT) {
            return this.getVitesse()*map.getTailleCases();
        } else {
            return Double.MAX_VALUE;
        }
    }
    @Override
    public Robot copy() {
        return new RobotARoues(this);
    }

}
