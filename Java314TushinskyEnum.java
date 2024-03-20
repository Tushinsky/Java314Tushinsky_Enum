/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java314tushinskyenum;

import java.awt.event.ActionEvent;
import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import robot.Robot;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class Java314TushinskyEnum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        robot.Robot robot = new Robot();// создаём робота
        // двигаем его
        robot.doMove(2, 2);
        System.out.println(robot);
        robot.doMove(2, 5);
        System.out.println(robot);
        robot.doMove(6, 5);
        System.out.println(robot);
        robot.doMove(10, 5);
        System.out.println(robot);
        robot.doMove(10, 8);
        System.out.println(robot);
        robot.doMove(2, 8);
        System.out.println(robot);
        robot.doMove(2, 10);
        System.out.println(robot);
        robot.doMove(6, 10);
        System.out.println(robot);
        robot.doMove(6, 8);
        System.out.println(robot);
        robot.doMove(6, 2);
        System.out.println(robot);
        robot.doMove(2, 2);
        System.out.println(robot);
        robot.doMove(-2, -2);
        System.out.println(robot);
        robot.doMove(2, -2);
        System.out.println(robot);
        robot.doMove(2,0);
        System.out.println(robot);
        
        // теперь тоже самое через встроенныый поток в классе Robot
//        Timer timer = new Timer(1000, (ActionEvent ae) -> {
//            robot.doStop();
//            System.exit(0);
//        });
//        robot.doMoveThread();
//        timer.start();
        
        // теперь тоже самое через поток, созданный в классе Main
        robotThread thread = new robotThread(robot);
        Timer timer = new Timer(1000, (ActionEvent ae) -> {
            robot.doStop();
            System.exit(0);
        });
        thread.start();
        timer.start();
    }
    
    private static class robotThread extends Thread {

        private final Robot robot;
        public robotThread(Robot robot) {
            this.robot = robot;
        }

        @Override
        public void run() {
            while(!interrupted()) {
                try {
                    int dx = (int)(Math.random() * 10);
                    int dy = (int)(Math.random() * 10);
                    System.out.println("dx=" + dx + ", dy=" + dy);
                    robot.doMove(dx, dy);
                    System.out.println(robot.toString());// печатаем координаты робота
                    
                    sleep(200);
                } catch (InterruptedException ex) {
                    System.out.println("Поток остановлен");// поток остановлен
                    Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
