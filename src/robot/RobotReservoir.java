package robot;

abstract public class RobotReservoir extends Robot {

    protected int volumeReservoir;
    protected int tempsRemplissage;
    
    @Override
    public void deverserEau(int vol) {
        if (vol < 0 || vol > quantitéEau) {
            throw new IllegalArgumentException("Volume d'eau à verser incohérent ! Demandé : " + vol + "Dans le reservoir : " + this.quantitéEau);
        } else {
            this.quantitéEau = this.quantitéEau - vol;
        }
    }

    @Override
    public int getEauReservoir() {
        return quantitéEau;
    }

    public int getVolumeReservoir() {
        return volumeReservoir;
    }

    public int getTempsRemplissage() {
        return tempsRemplissage;
    }

    @Override
    public String toString() {
        return super.toString() + ", eauReservoir=" + quantitéEau + ", volumeReservoir=" + volumeReservoir + ", tempsRemplissage=" + tempsRemplissage + '}';
    }
    
    
   
}
