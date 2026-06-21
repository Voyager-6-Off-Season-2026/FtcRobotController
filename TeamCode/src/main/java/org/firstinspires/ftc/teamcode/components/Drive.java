package org.firstinspires.ftc.teamcode.components;

abstract public class Drive extends Component {

    protected double drive, strafe, rotate;

    double driverHeading = Math.PI / 2; // facing robot from right side

    double driveSpeed = 0.8;

    double rotateSpeed = 0.9;


    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void loop() {
        drive(voyagerBot.gamepad1.left_stick_y, voyagerBot.gamepad1.left_stick_x, voyagerBot.gamepad1.right_stick_x);
    }

    abstract public void drive(double leftJoystickY, double leftJoystickX, double rightJoystickX);
}
