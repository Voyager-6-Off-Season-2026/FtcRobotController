package org.firstinspires.ftc.teamcode.components;

public class FieldCentricDrive extends Drive {
    @Override
    public void drive(double leftJoystickY, double leftJoystickX, double rightJoystickX) {
        drive = -leftJoystickY * driveSpeed;
        strafe = leftJoystickX * driveSpeed;
        rotate = rightJoystickX * rotateSpeed;

        double robotHeading = voyagerBot.getHeading();

        double rotX = strafe * Math.cos(-robotHeading) - drive * Math.sin(-robotHeading);
        double rotY = strafe * Math.sin(-robotHeading) + drive * Math.cos(-robotHeading);

        rotX *=1.1;

        // Normalize motor powers
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotate), 1);

        double frontLeftPower = (rotY + rotX + rotate) / denominator;
        double backLeftPower = (rotY - rotX + rotate) / denominator;
        double frontRightPower = (rotY - rotX - rotate) / denominator;
        double backRightPower = (rotY + rotX - rotate) / denominator;

        voyagerBot.leftFrontDrive.setPower(frontLeftPower);
        voyagerBot.leftRearDrive.setPower(backLeftPower);
        voyagerBot.rightFrontDrive.setPower(frontRightPower);
        voyagerBot.rightRearDrive.setPower(backRightPower);
    }
}
