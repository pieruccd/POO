package robot;


import simulation.*;


abstract public class RobotTerrestre extends RobotReservoir {

    @Override
    public void remplirReservoir() {
        for (EnumDirection dir : EnumDirection.values()) {
            if (this.carte.getVoisin(this.getPostion(), dir).getNature() == EnumNatureTerrain.EAU) {
                this.quantitéEau = this.volumeReservoir;
                return;
            }
        }
        throw new IllegalArgumentException("Pas de case d'eau à proximité : remplissage impossible");
    }

}
