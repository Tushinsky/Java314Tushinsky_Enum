/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import enumeration.Direction;

/**
 *
 * @author Sergii.Tushinskyi
 */
public class Robot {
    private int x;// координата по оси X
    private int y;// координата по оси Y
    private Direction direction;// направление движения
    private int step;// шаг
    private int coordinate;
    private interface Function {
        void operation();
    }
    Function funcLeft = () -> turnLeft();
    Function funcRight = () -> turnRight();

    public Robot() {
        // начальные значения
        x = 0;
        y = 0;
        direction = Direction.UP;// робот смотрит вверх по оси Y
    }

    /**
     * Возвращает текущее положение по оси X
     * @return текущая координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Возвращает текущее положение по оси Y
     * @return текущая координата Y
     */
    public int getY() {
        return y;
    }

    /**
     * Возвращает текущее направление движения
     * @return направление движения
     */
    public Direction getDirection() {
        return direction;
    }
    
    /**
     * Перемещает робота в заданные координаты
     * @param toX коордитана по оси X
     * @param toY координата по оси Y
     */
    public void doMove(int toX, int toY) {
        /*
        чтобы переместиться в точку с заданными координатами, нужно сделать:
        1 - определить направление движения;
        2 - повернуться по часовой или против часовой стрелки если нужно;
        3 - пройти вперёд до нужной координаты;
        4 - повторить действия 1-3 если необходимо;
        */
        // 1 - определяем направление поворота; 2 - выполняем поворот
        setTurnDirection(toX, toY);

        // 3 - идём вперёд до нужной координаты
        moveBegin(toX, toY);
        // 1 - определяем направление поворота; 2 - выполняем поворот
        setTurnDirection(toX, toY);

        // 3 - идём вперёд до нужной координаты
        moveBegin(toX, toY);

    }
    
    /**
     * Задаёт направление поворота робота
     * @param toX координата X точки назначения
     * @param toY координата Y точки назначения
     */
    private void setTurnDirection(int toX, int toY) {
        
        switch(direction) {
            case UP:
                // смотрим вверх: оцениваем по координате +Y
                if(toY > y) {
                    break;
                } else {
                    // точка находится на оси или ниже по оси
                    turnDirectionUpDown(toY, toX, funcLeft, funcRight, direction);
                }
                break;
            case DOWN:
                // смотрим вниз: оцениваем по координате -Y
                if(toY < y) {
                    // точка находится ниже по оси
                    break;
                } else {
                    turnDirectionUpDown(toY, toX, funcRight, funcLeft, 
                            direction);
                }
                break;
            case RIGHT:
//                 смотрим направо: оцениваем по координате X
                if(toX > x) {
                    break;
                } else {
                    turnDirectionLeftRight(toY, toX, funcRight, funcLeft, 
                            direction);
                }
                break;
            case LEFT:
                // смотрим налево: оцениваем по координате -X
                if(toX < x) {
                    // точка находится ниже по оси
                    break;
                } else {
                    turnDirectionLeftRight(toY, toX, funcLeft, funcRight, 
                            direction);
                }
        }
    }
    
    private void turnDirectionUpDown(int toY, int toX, Function func1, 
            Function func2, Direction dir) {
        if(toY == y) {
            if(toX > x) {
                // находится справа
                func2.operation();
            }
            else if(toX < x) {
                // находится слева
                func1.operation();
            } else {
                direction = dir;
            }
        } else if( toY < y) {
            if(toX > x) {
                // находится слева от нас
                func2.operation();
            } else if(toX < x) {
                // находится справа от нас
                func1.operation();
            }
        }
        
    }
    
    private void turnDirectionLeftRight(int toY, int toX, Function func1, 
            Function func2, Direction dir) {
        if(toX == x) {
            // точка находится на оси
            if(toY > y) {
                // находится слева от нас
                func2.operation();
            } else if(toY < y) {
                // находится справа от нас
                func1.operation();
            } else {
                direction = dir;
            }
        } else if(toX > x) {
            // точка находится ниже
            if(toY > y) {
                // находится слева от нас
                func2.operation();
            } else  if(toY < y) {
                // находится справа от нас
                func1.operation();
            }
        }
    }
    
    /**
     * Начинает движение робота
     * @param toX координата точки назначения по оси X
     * @param toY координата точки назначения по оси Y
     */
    private void moveBegin(int toX, int toY) {
        /*
        в зависимости от того, куда направлен взгляд робота, шаг может быть
        либо положительным либо отрицательным
        */
        switch(direction) {
            case UP:
                // смотрим по Y в сторону увеличиния
                moving(1, y, toY, y);
                y = coordinate;
                break;
            case RIGHT:
                // смотрим по X в сторону увеличиния
                moving(1, x, toX, x);
                x = coordinate;
                break;
            case DOWN:
                // смотрим по Y в сторону уменьшения
                moving(-1, y, toY, y);
                y = coordinate;
                break;
            case LEFT:
                // смотрим по X в сторону уменьшения
                moving(-1, x, toX, x);
                x = coordinate;
                
        }
    }
    
    /**
     * Изменяет направление движения робота (направление взгляда) против часовой стрелки
     */
    private void turnLeft() {
        
        // изменяем направление движения робота против часовой стрелки
        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            default:
                direction = Direction.UP;
                break;
        }
    }
    
    /**
     * Изменяет направление движения робота (направление взгляда) по часовой стрелке
     */
    private void turnRight() {
        // изменяем направление движения робота по часовой стрелке
        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case LEFT:
                direction = Direction.UP;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            default:
                direction = Direction.DOWN;
                break;
        }
        
    }
    
    /**
     * Перемещает робота по направлению движения (взгляда) на один шаг
     */
    private void stepForward() {
        coordinate = coordinate + step;
    }
    
    /**
     * Выполняет перемещение по заданной координате
     * @param step величина шага перемещения
     * @param coord координата, по которой происходит перемещение
     * @param start начальная координата
     * @param finish конечная координата
     */
    private void moving(int step, int coord, int start, int finish) {
        this.step = step;
        coordinate = coord;
        // цикл
        for(int i = 0; i < Math.abs(start - finish); i++) {
            stepForward();// двигаемся вперёд
        }
    }

    @Override
    public String toString() {
        return "Robot{" + "x=" + x + ", y=" + y + ", direction=" + direction + '}';
    }
    
    
}
