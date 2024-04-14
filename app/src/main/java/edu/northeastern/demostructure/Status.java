package edu.northeastern.demostructure;

import java.util.Objects;
import java.util.Stack;

public class Status {
    private int strength;
    private int knowledge;
    private int charm;
    private int magic;
    public static final String study = "studying";
    public static final String exercise = "exercising";
    public static final String date = "dating";
    public static final String learnMagic = "learningMagic";

    private final int fullPoint = 100;
    private final int increment = 20;
    private final int decrement = 5;
    private final Stack<Action> actionStack = new Stack<>();

    private String doing = "nothing";


    public Status(){
        this.strength = 20;
        this.knowledge = 20;
        this.charm = 20;
        this.magic = 0;
        calculate();
    }

    // use addMovement() with even name
    public void addMovement(String event){
        Action action = new Action(event);
        actionStack.push(action);
    }

    private void calculate(){
        while (true) {
            synchronized (actionStack) {
                // Wait until there is an action in the stack
                while (actionStack.isEmpty()) {
                    try {
                        actionStack.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Process the action
                Action act = actionStack.pop();
                this.doing = act.getName();
                act.startTimer();
                if (Objects.equals(act.getName(), study)){
                    this.knowledge += increment;
                    this.strength -= decrement;
                } else if (Objects.equals(act.getName(), exercise)){
                    this.strength += increment;
                    this.knowledge -= decrement;
                } else if (Objects.equals(act.getName(), date)){
                    this.charm += increment;
                    this.strength -= decrement;
                } else if (Objects.equals(act.getName(), learnMagic)){
                    this.magic += increment;
                    this.charm -= decrement;
                }
                this.doing = "nothing";
            }
        }
    }


    // use Doing() to check what is the acting event, one event will keep 20 seconds
    public String doing(){
        return doing;
    }

    // use checkStatus to return the ending number, 1: strength, 2: knowledge 3:charm 4: magic-> become god
    public int checkStatus(){
        if (this.strength < 0 ||
                this.knowledge < 0 ||
                this.charm < 0 ||
                this.magic < 0){
            return -1;
        }
        if (this.strength >= fullPoint &&
                this.knowledge >= fullPoint &&
                this.charm >= fullPoint &&
                this.magic >= fullPoint){
            return 4;
        }else if (this.strength >= fullPoint){
            return 1;
        }else if (this.knowledge >= fullPoint){
            return 2;
        }else if (this.charm >= fullPoint){
            return 3;
        }
        return 0;
    }

    public int[] getStatus(){
        int[] res = new int[4];
        res[0] = strength;
        res[1] = knowledge;
        res[2] = charm;
        res[3] = magic;
        return res;
    }



}
