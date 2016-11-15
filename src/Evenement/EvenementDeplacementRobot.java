package Evenement;

import Evenement.Evenement;
import simulation.*;
import robot.*;

public class EvenementDeplacementRobot extends Evenement {
    
    EnumDirection direction;

    public EvenementDeplacementRobot(int date, Robot robot, EnumDirection direction, int indexRobot) {
        super(date,robot,indexRobot);
        this.direction = direction;
    }

    @Override
    public void execute() {
        robot.deplacer(direction);
    } 
}
