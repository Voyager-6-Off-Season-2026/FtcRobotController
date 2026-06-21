package org.firstinspires.ftc.teamcode.components;

public class RobotCentricDrive extends Drive {
    @Override
    public void drive(double leftJoystickY, double leftJoystickX, double rightJoystickX) {
        drive = -1 * leftJoystickY * driveSpeed;
        strafe = 1 * leftJoystickX * driveSpeed;
        rotate = 1 * rightJoystickX * rotateSpeed;

        voyagerBot.leftFrontDrive.setPower(drive + strafe + rotate);
        voyagerBot.leftRearDrive.setPower(drive - strafe + rotate);
        voyagerBot.rightFrontDrive.setPower(drive - strafe - rotate);
        voyagerBot.rightRearDrive.setPower(drive + strafe - rotate);

        voyagerBot.telemetry.addData("leftFrontDrive", drive + strafe + rotate);
        voyagerBot.telemetry.addData("leftRearDrive", drive - strafe + rotate);
        voyagerBot.telemetry.addData("rightFrontDrive", drive - strafe - rotate);
        voyagerBot.telemetry.addData("rightRearDrive", drive + strafe - rotate);
        voyagerBot.telemetry.addData("leftJoystickY", leftJoystickY);
        voyagerBot.telemetry.addData("leftJoystickX", leftJoystickX);
        voyagerBot.telemetry.addData("rightJoystickX", rightJoystickX);
    }
}
