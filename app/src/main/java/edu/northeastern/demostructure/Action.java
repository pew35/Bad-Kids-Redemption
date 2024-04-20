package edu.northeastern.demostructure;

import java.util.Timer;
import java.util.TimerTask;

public class Action {
    private String name;
    private boolean done;
    private Timer timer;

    public Action(String name) {
        this.name = name;
        this.done = false;
        this.timer = new Timer();
    }

    public void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                complete();
            }
        }, 20000); // 20 seconds
    }
    private void complete() {
        System.out.println("Action completed: " + name);
        done = true;
    }

    public boolean isDone() {
        return done;
    }
    public String getName() {
        return name;
    }

}
