package simulation;

public class Case implements Cloneable {

    private int ligne = 0;
    private int colonne = 0;

    private EnumNatureTerrain nature;

    public Case(int ligne, int colonne, EnumNatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    public Case(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public EnumNatureTerrain getNature() {
        return nature;
    }

    /**
     * @param ligne the ligne to set
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * @param colonne the colonne to set
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(EnumNatureTerrain nature) {
        this.nature = nature;
    }

    @Override
    public String toString() {
        return "Case{" + "ligne=" + ligne + ", colonne=" + colonne + ", nature=" + nature + '}';
    }

    public boolean equals(Case pos) {
        return (pos.ligne == this.ligne) && (pos.colonne == this.colonne) && (pos.nature == pos.nature);
    }

    @Override
    public Case clone() {
        Case o = null;
        try {
            o = (Case) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
    }

}
