package Evenement;


import Evenement.Evenement;
import simulation.*;
import robot.*;

public class EvenementDeverserEau extends Evenement {

    Incendie incendie;
    int indexIncendie;

    public EvenementDeverserEau(int date, Incendie incendie, Robot robot, int indexRobot, int indexIncendie) {
        super(date,robot,indexRobot);
        this.incendie = incendie;
        this.indexIncendie = indexIncendie;
    }

    public void setIncendie(Incendie incendie) {
        this.incendie = incendie;
    }

    public int getIndexIncendie() {
        return indexIncendie;
    }

    @Override
    public void execute() {
        if (incendie.getCase().equals(robot.getPostion())) {
            System.out.println("Incendie : " + incendie.getNbLitres());
            System.out.println("Robot : " + robot.getEauReservoir());
            System.out.println("On a versé : " + Math.min(robot.getEauReservoir(), incendie.getNbLitres()));
            int eauVersee = Math.min(robot.getEauReservoir(), incendie.getNbLitres());
            robot.deverserEau(eauVersee);
            this.incendie.recevoirEau(eauVersee);
            System.out.println("Intensité de l'incendie = " + incendie.getNbLitres());
        } else {
            throw new IllegalArgumentException("Le robot et l'incendie ne sont pas sur la même case : ARRET");
        }
    }

}
