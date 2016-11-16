package robot;

import simulation.*;

public final class RobotAChenilles extends RobotTerrestre {

    public RobotAChenilles(Carte carte, Case pos, double vitesse, int eau) {
        this.carte = carte;
        this.quantitéEau = eau;
        this.tempsRemplissage = 5;
        this.volumeReservoir = 2000;
        this.setVitesse(vitesse);
        this.setPostion(pos);
    }
    
    public RobotAChenilles(RobotAChenilles robot) {
        this.carte = new Carte(robot.carte);
        this.quantitéEau = robot.quantitéEau;
        this.tempsRemplissage = 5;
        this.volumeReservoir = 2000;
        this.setVitesse(robot.vitesse);
        this.setPosition(robot.ligne, robot.colonne);
    }

    @Override
    public RobotAChenilles copy() {
        return new RobotAChenilles(this);
    }

    @Override
    public void setPostion(Case pos) {
        this.setPosition(pos.getLigne(), pos.getColonne());
    }

    @Override
    public boolean deplacementPossible(EnumDirection dir) {
        Case voisin = this.carte.getVoisin(this.carte.getCase(ligne, colonne), dir);
        return (voisin.getNature() != EnumNatureTerrain.EAU) 
                && (voisin.getNature() != EnumNatureTerrain.ROCHE)
                && this.carte.voisinExiste(voisin, dir);
    }

    @Override
    public void setPosition(int lig, int col) {
        if (this.carte.getCase(lig, col).getNature() == EnumNatureTerrain.EAU || this.carte.getCase(lig, col).getNature() == EnumNatureTerrain.ROCHE) {
            throw new IllegalArgumentException("Un robot à chenille ne peux aller sur ce type de chemin ! ");
        } else if (this.carte.getCase(lig, col).getNature() == EnumNatureTerrain.FORET && this.getPostion().getNature() != EnumNatureTerrain.FORET) {
            this.vitesse = this.vitesse / 2;
        } else if (this.carte.getCase(lig, col).getNature() != EnumNatureTerrain.FORET && this.getPostion().getNature() == EnumNatureTerrain.FORET) {
            this.vitesse = this.vitesse * 2;
        }
    }

    public void setVitesse(double vitesse) {
        if (this.getPostion().getNature() == EnumNatureTerrain.HABITAT || this.getPostion().getNature() == EnumNatureTerrain.TERRAIN_LIBRE) {
            if (vitesse < 0 || vitesse > 80) {
                throw new IllegalArgumentException("Vitesse invalide pour un robot à chenilles ! (Demandé : " + vitesse + " )");
            } else {
                this.vitesse = vitesse;
            }
        } else if (this.getPostion().getNature() == EnumNatureTerrain.FORET) {
            if (vitesse < 0 || vitesse > 40) {
                throw new IllegalArgumentException("Vitesse invalide pour un robot à chenilles sur de la forêt ! (Demandé : " + vitesse + " )");
            } else {
                this.vitesse = vitesse;
            }
        }
    }
    
    public double tempsnecessaire(Case voisin, Carte map) {
        if (voisin.getNature() == EnumNatureTerrain.FORET) {
            return ((this.getVitesse()*(this.getVitesse()/2))/2)*map.getTailleCases()*2;
        } else if ((voisin.getNature() == EnumNatureTerrain.EAU)) {
            return Double.MAX_VALUE;
        } else if (voisin.getNature() == EnumNatureTerrain.ROCHE) {
            return Double.MAX_VALUE;
        } else {
            return ((this.getVitesse()*this.getVitesse())/2)*map.getTailleCases()*2;
        }
    }

}
