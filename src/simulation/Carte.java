package simulation;

public class Carte implements Cloneable {

    private Case[][] tableauCarte;
    private int nbLignes;
    private int nbColonnes;
    private int tailleCases;

    public Carte(int nbLignes, int nbColonnes, int tailleCases) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tailleCases = tailleCases;
        this.tableauCarte = new Case[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                tableauCarte[i][j] = new Case(i, j);
            }
        }
    }
   
    public Carte(Carte map) {
        this.nbColonnes = map.nbColonnes;
        this.nbLignes = map.nbLignes;
        this.tailleCases = map.tailleCases;
        this.tableauCarte = new Case[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                tableauCarte[i][j] = map.getCase(i, j).clone();
            }
        }
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getTailleCases() {
        return tailleCases;
    }

    public Case getCase(int lig, int col) {
        if (lig < 0 || lig >= nbLignes || col < 0 || col >= nbColonnes) {
            throw new IllegalArgumentException("Case (" + lig + "," + col + ") hors de Portée de la Carte !");
        } else {
            return tableauCarte[lig][col];
        }
    }

    public void setCase(Case pos) {
        if ((pos.getLigne() >= 0) && (pos.getLigne() < nbLignes) && (pos.getColonne() >= 0) && (pos.getColonne() < nbColonnes)) {
            tableauCarte[pos.getLigne()][pos.getColonne()] = pos;
        } else {
            throw new IllegalArgumentException("Coordonnées en dehors du tableau de la carte !");
        }
    }

    public boolean voisinExiste(Case src, EnumDirection dir) {
        switch (dir) {
            case EST:
                return (src.getColonne() < (this.nbLignes - 1) && src.getColonne() >= 0);
            case OUEST:
                return (src.getColonne() > 0 && src.getColonne() < this.nbColonnes);
            case NORD:
                return (src.getLigne() < this.nbLignes && src.getLigne() > 0);
            case SUD:
                return (src.getLigne() >= 0 && src.getLigne() < (this.nbColonnes - 1));
        }
        return false;
    }

    public Case getVoisin(Case src, EnumDirection dir) {
        if (this.voisinExiste(src, dir)) {
            switch (dir) {
                case EST:
                    return (this.getCase(src.getLigne(), src.getColonne() + 1));
                case OUEST:
                    return (this.getCase(src.getLigne(), src.getColonne() - 1));
                case NORD:
                    return (this.getCase(src.getLigne() - 1, src.getColonne()));
                case SUD:
                    return (this.getCase(src.getLigne() + 1, src.getColonne()));
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Carte{" + "nbLignes=" + nbLignes + ", nbColonnes=" + nbColonnes + ", tailleCases=" + tailleCases + '}';
    }

}
