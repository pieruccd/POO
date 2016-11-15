package Evenement;

import robot.*;

abstract public class Evenement implements Comparable<Evenement> {
    
    public int date;
    protected Robot robot;
    protected int indexRobot;

    public Evenement(int date, Robot robot, int indexRobot) {
        this.date = date;
        this.robot = robot;
        this.indexRobot = indexRobot;
    }

    public int getDate() {
        return date;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public int getIndexRobot() {
        return indexRobot;
    }
    
    public abstract void execute();
       
    @Override
    public int compareTo(Evenement e) {
        int diff = this.date - e.date;
        if (diff < 0) {
            return -1;
        } else if (diff > 0) {
            return 1;
        } else {
            return 0;
        }   
    }
}
