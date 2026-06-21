package org.firstinspires.ftc.teamcode;

import androidx.core.view.KeyEventDispatcher;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.components.Component;

import java.util.List;
import java.util.ArrayList;

public class VoyagerBot {

    HardwareMap hwMap = null;

    public IMU imu;

    public Telemetry telemetry = null;

    public Gamepad gamepad1 = null;

    public Gamepad gamepad2 = null;

    public DcMotor leftFrontDrive, leftRearDrive, rightFrontDrive, rightRearDrive;

    public DcMotorEx lowerOuttakeMotor, upperOuttakeMotor;

    public DcMotor intakeMotor, feederMotor;

    public Servo aimServo;

    public Limelight3A limelight;

    private ArrayList<Component> components;

    public void init(HardwareMap _hwMap, Telemetry _telemetry, Gamepad _gamepad1, Gamepad _gamepad2, ArrayList<Component> _components) {

        hwMap = _hwMap;
        telemetry = _telemetry;
        gamepad1 = _gamepad1;
        gamepad2 = _gamepad2;

        components = _components;
        components.forEach(component -> component.init(this));

        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        );
        imu.initialize(parameters);

//         all of the hardware initialization takes place here

        leftFrontDrive = hwMap.get(DcMotor.class, "leftFrontDrive");
        leftRearDrive = hwMap.get(DcMotor.class, "leftRearDrive");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFrontDrive");
        rightRearDrive = hwMap.get(DcMotor.class, "rightRearDrive");

        //TODO: Make sure these are the right directions
        leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRearDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRearDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        lowerOuttakeMotor = hwMap.get(DcMotorEx.class, "lowerOuttake");
        upperOuttakeMotor = hwMap.get(DcMotorEx.class, "upperOuttake");

        lowerOuttakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        upperOuttakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        lowerOuttakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lowerOuttakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        upperOuttakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lowerOuttakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        upperOuttakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        lowerOuttakeMotor.setVelocityPIDFCoefficients(
                8.0,   // P
                2.0,   // I
                0.0,   // D
                16.5   // F
        );

        //feeding system

        intakeMotor = hwMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        feederMotor = hwMap.get(DcMotor.class, "feeder");
        feederMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        feederMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        aimServo = hwMap.get(Servo.class, "aim");

        limelight = hwMap.get(Limelight3A.class, "limelight");
    }

    public void start() {
        components.forEach(Component::start);
    }

    public void stop() {
        components.forEach(Component::stop);
    }

    public void loop() {
        components.forEach(Component::loop);
    }

    public double getHeading() {
        YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
        return angles.getYaw(AngleUnit.RADIANS);
    }
}