package org.firstinspires.ftc.teamcode.components;

import org.firstinspires.ftc.teamcode.VoyagerBot;

public abstract class Component {

    protected VoyagerBot voyagerBot;

    public void init(VoyagerBot voyagerBot) {
        this.voyagerBot = voyagerBot;
    }
    abstract public void start();

    abstract public void stop();

    abstract public void loop();
}
