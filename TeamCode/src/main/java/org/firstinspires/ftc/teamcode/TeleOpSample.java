package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.List;

import org.firstinspires.ftc.teamcode.components.Component;
import org.firstinspires.ftc.teamcode.components.FieldCentricDrive;
import org.firstinspires.ftc.teamcode.components.RobotCentricDrive;

import java.util.ArrayList;

@TeleOp(name="TeleOpSample", group="TeleOp")
public class TeleOpSample extends OpMode {

    private VoyagerBot voyagerBot;

    @Override
    public void init() {
        ArrayList<Component> components = new ArrayList<>();
        components.add(new FieldCentricDrive());
        voyagerBot = new VoyagerBot();
        voyagerBot.init(hardwareMap, telemetry, gamepad1, gamepad2, components);
    }

    @Override
    public void start() {
        voyagerBot.start();
    }

    @Override
    public void loop() {
        voyagerBot.loop();
    }

    @Override
    public void stop() {
        voyagerBot.stop();
    }
}
