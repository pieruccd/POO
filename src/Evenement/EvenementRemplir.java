package Evenement;

import robot.*;

public class EvenementRemplir extends Evenement {

    public EvenementRemplir(int date, Robot robot, int indexRobot) {
        super(date,robot,indexRobot);
    }

    @Override
    public void execute() {
        robot.remplirReservoir();
        System.out.println("Le robot est rempli : Quantité d'eau = " + robot.getEauReservoir());
    }
    
}
