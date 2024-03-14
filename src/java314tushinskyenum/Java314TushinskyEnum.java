/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java314tushinskyenum;

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
        
    }
    
}
