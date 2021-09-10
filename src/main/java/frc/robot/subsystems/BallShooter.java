// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.BallShooterConstants;
/**
 *
 */
public class BallShooter extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX shootMotor;
private WPI_TalonSRX hoodMotor;
private DigitalInput limitSwitchDown;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

     public final SupplyCurrentLimitConfiguration currentLimiting = new SupplyCurrentLimitConfiguration(
            BallShooterConstants.kEnableCurrentLimiting_BS, BallShooterConstants.currentLimit,
            BallShooterConstants.thresholdLimit, BallShooterConstants.thresholdTime);

    private double masterShootRPM = 0;
    private double masterHoodPos = 0;

    public boolean inAuton = false;

    public BallShooter() {
        // Temporary read values from screen
        SmartDashboard.putNumber("sdShoot RPMS", -4800);
        SmartDashboard.putNumber("sdHood Position", -3000);
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
shootMotor = new WPI_TalonFX(13);
 
 

hoodMotor = new WPI_TalonSRX(8);
 
 

limitSwitchDown = new DigitalInput(0);
 addChild("limitSwitchDown",limitSwitchDown);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    shootMotorConfig();
    hoodMotorConfig();
}

    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        double motorOutput = shootMotor.getMotorOutputPercent();
        if (BallShooterConstants.test) {
            SmartDashboard.putNumber("test/shooter/Motor Output", motorOutput);
            SmartDashboard.putNumber("test/shooter/Motor Velocity", shootMotor.getSelectedSensorVelocity(0));
            SmartDashboard.putNumber("test/shooter/Motor Error", shootMotor.getClosedLoopError(0));
            SmartDashboard.putNumber("test/shooter/Hood Position", hoodMotor.getSelectedSensorPosition(0));
            SmartDashboard.putNumber("test/shooter/Hood Error", hoodMotor.getClosedLoopError(0));
            SmartDashboard.putBoolean("HoodLimitSwitch", isLimitSwitchDown());
        }

        double err = hoodMotor.getClosedLoopError(BallShooterConstants.kPIDLoopIdx);
        SmartDashboard.putNumber("Shooter/HoodError", err);
        SmartDashboard.putBoolean("Shooter/HoodReady", Math.abs(err) < BallShooterConstants.kHoodPositionTolerance);

        double closedLpErr = Math.abs(shootMotor.getClosedLoopError(BallShooterConstants.kPIDLoopIdx));
        SmartDashboard.putNumber("Shooter/ShootMotorError", closedLpErr);
        SmartDashboard.putBoolean("Shooter/ShootMotorReady",_withinThresholdLoops > BallShooterConstants.kLoopsToSettle);


        //SmartDashboard.putNumber("shooter/Flywheel Velocity(RPM)",
        //        velocityPer100msToRpm(shootMotor.getSelectedSensorVelocity(0)));
        //SmartDashboard.putNumber("shooter/Flywheel Temp", shootMotor.getTemperature());
        //SmartDashboard.putNumber("shooter/Shooter Stator Amps", shootMotor.getStatorCurrent());
        SmartDashboard.putNumber("shooter/Motor RPMs", -velocityPer100msToRpm(shootMotor.getSelectedSensorVelocity(0)));
        SmartDashboard.putBoolean("HoodLimitSwitch", isLimitSwitchDown());

        // if (Robot.climb.cMode == false) {
             setMasterHoodPos(masterHoodPos);
             setMasterShootVelocity(masterShootRPM);
        // } else {
        //   shootMotor.set(ControlMode.PercentOutput, 0fd);
        // setMasterHoodPos(masterHoodPos);
        // }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    // public void fireMotor(double rpms) {
    //     toConsoleln("fireMotor() called");
    //     masterHoodPos = BallShooterConstants.kHoodUpEncoderMax;
    //     masterShootRPM = rpms;
    //     setMasterShootVelocity(rpms);
    // }

    public void idleSubsystem() {
        masterHoodPos = BallShooterConstants.hoodIdlePosition;
        //If we're in Autonomous we want to set the Idle velocity which is 0
        if (inAuton) {
            // masterShootRPM = BallShooterConstants.magicRPMS;
            masterShootRPM = BallShooterConstants.shootIdleVelocity;
        } else if (teleopWithIdle) {
            masterShootRPM = BallShooterConstants.shootDefaultVelocity;
            masterHoodPos = BallShooterConstants.hoodShootPosition;

        } else {
            masterShootRPM = BallShooterConstants.shootIdleVelocity;
            masterShootRPM = BallShooterConstants.hoodIdlePosition;
        }
    }

    private int loop = 0;

    /**
     * ready2Shoot tests both the shooter rpm and the hood position to determine if
     * they are ready to attempt to shoot.
     * 
     * @param rpms             - Target RPM that we wish to reach
     * @param hoodEncoderUnits - Target position in encoder units for the hood.
     * @return True if both the shooter and the hood have reached their specified
     *         speed and position within tolerances.
     */
    public boolean ready2Shoot(final double rpms, final double hoodEncoderUnits) {
        // final double velocityPer100ms = rpmToVelocityPer100ms(rpms);

        masterHoodPos = hoodEncoderUnits;
        masterShootRPM = rpms;

        boolean iwthresh = isWithinThreshold();
        boolean hp = hoodAtPosition();
        // report debugging information
        if (++loop >= 15) {
            toConsoleln("ShootTarget=" + rpmToVelocityPer100ms(rpms) + " Cur="
                    + shootMotor.getSelectedSensorVelocity(BallShooterConstants.kPIDLoopIdx) + " Err="
                    + shootMotor.getClosedLoopError(BallShooterConstants.kPIDLoopIdx) + " InThreshold " + iwthresh
                    + shootMotor.getSelectedSensorVelocity(BallShooterConstants.kPIDLoopIdx)*10*60/2048);
            toConsoleln("hoodTarget=" + hoodEncoderUnits + " Cur="
                    + hoodMotor.getSelectedSensorPosition(BallShooterConstants.kPIDLoopIdx) + " Err="
                    + hoodMotor.getClosedLoopError(BallShooterConstants.kPIDLoopIdx) + " hood at position=" + hp);
            toConsoleln("Returning " + (iwthresh && hp));
        }

        // return isWithinThreshold() && hoodAtPosition();
        if (iwthresh && hp) {
            //LimelightUtility.WriteDouble("ledMode", 1); // 1 = Limelight Off
            //LimelightUtility.EnableDriverCamera(true);
        }

        SmartDashboard.putBoolean("Shooter/ReadyToShoot", iwthresh && hp);

        return iwthresh && hp;
    }

    public void stopShooter() {
        // The default command should take over and pull us back up to an idled velocity
        shootMotor.set(ControlMode.PercentOutput, 0);
        toConsoleln("stopShooter() called.");
    }

    public void shootMotorConfig() {
        /* Factory Default all hardware to prevent unexpected behaviour */
        shootMotor.configFactoryDefault();

        /* Config neutral deadband to be the smallest possible */
        shootMotor.configNeutralDeadband(0.001);

        /* Config sensor used for Primary PID [Velocity] */
        shootMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kTimeoutMs);

        /* Config the peak and nominal outputs */
        shootMotor.configNominalOutputForward(0, BallShooterConstants.kTimeoutMs);
        shootMotor.configNominalOutputReverse(0, BallShooterConstants.kTimeoutMs);
        shootMotor.configPeakOutputForward(1, BallShooterConstants.kTimeoutMs);
        shootMotor.configPeakOutputReverse(-1, BallShooterConstants.kTimeoutMs);

        /* Config the Velocity closed loop gains in slot0 */
        shootMotor.config_kF(BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kGains_shootMotor.kF,
                BallShooterConstants.kTimeoutMs);
        shootMotor.config_kP(BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kGains_shootMotor.kP,
                BallShooterConstants.kTimeoutMs);
        shootMotor.config_kI(BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kGains_shootMotor.kI,
                BallShooterConstants.kTimeoutMs);
        shootMotor.config_kD(BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kGains_shootMotor.kD,
                BallShooterConstants.kTimeoutMs);
        /*
         * Talon FX does not need sensor phase set for its integrated sensor This is
         * because it will always be correct if the selected feedback device is
         * integrated sensor (default value) and the user calls getSelectedSensor* to
         * get the sensor's position/velocity.
         * 
         * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#
         * sensor-phase
         */
        // shootMotor.setSensorPhase(true);

        // Ramp motor w/ current limiting on
        shootMotor.configSupplyCurrentLimit(currentLimiting);
        // shootMotor.configOpenloopRamp(1.75, BallShooterConstants.kTimeoutMs);
        // shootMotor.configClosedloopRamp(.05, BallShooterConstants.kTimeoutMs);

        hoodMotorConfig();

    }

    public void coolerTime() {

    }

    // public void coolMotor() {
    // if (shootMotor.getTemperature() > 50) {
    // coolingSolenoidShooter.set(true);
    // coolingOn = true;
    // } else if (coolingOn) {
    // coolingSolenoidShooter.set(false);
    // coolingOn = false;
    // }
    // }

    public void reinitializeShooter() {
        toConsoleln("reinitializeShooter() called.");

        shootMotorConfig(); // temporary
        // coolingSolenoidShooter.set(false);
        // coolingOn = false;
        // timer = 0;
        teleopWithIdle = false;
    }

    public double getShooterRPM() {
        return -shootMotor.getSelectedSensorVelocity() * 600 / BallShooterConstants.kSensorUnitsPerRotation;

    }

    public Boolean targetEncoder() {
        final double currentEncoderUnits = hoodMotor.getSelectedSensorPosition(0);
        if (Math.abs(currentEncoderUnits - BallShooterConstants.kHoodUpEncoderMax) < 190) {
            return true;
        }
        return false;
    }

    public void hoodUp() {
        toConsoleln("hoodup() called.");
        masterHoodPos = BallShooterConstants.kHoodUpEncoderMax;
    }

    public void hoodDown() {
        toConsoleln("hoodDown() called.");
        masterHoodPos = 0;
    }

    public double getHoodEncoderUnits() {
        return hoodMotor.getSelectedSensorPosition(0);
    }

    public void hoodMotorConfig() {
        /* Factory default hardware to prevent unexpected behavior */
        hoodMotor.configFactoryDefault();

        /* Configure Sensor Source for Pirmary PID */
        hoodMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kTimeoutMs);

        /**
         * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
         * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
         * sensor to have positive increment when driving Talon Forward (Green LED)
         */
        hoodMotor.setSensorPhase(true); // required to stop
        hoodMotor.setInverted(true);

        /* Set relevant frame periods to be at least as fast as periodic rate */
        hoodMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, BallShooterConstants.kTimeoutMs);
        hoodMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, BallShooterConstants.kTimeoutMs);

        /* Set the peak and nominal outputs */
        hoodMotor.configNominalOutputForward(0, BallShooterConstants.kTimeoutMs);
        hoodMotor.configNominalOutputReverse(0, BallShooterConstants.kTimeoutMs);
        hoodMotor.configPeakOutputForward(1, BallShooterConstants.kTimeoutMs);
        hoodMotor.configPeakOutputReverse(-1, BallShooterConstants.kTimeoutMs);

        /* Set Motion Magic gains in slot0 - see documentation */
        hoodMotor.selectProfileSlot(BallShooterConstants.kSlotIdx, BallShooterConstants.kPIDLoopIdx);
        hoodMotor.config_kF(BallShooterConstants.kSlotIdx, BallShooterConstants.kGains_hoodMotor.kF,
                BallShooterConstants.kTimeoutMs);
        hoodMotor.config_kP(BallShooterConstants.kSlotIdx, BallShooterConstants.kGains_hoodMotor.kP,
                BallShooterConstants.kTimeoutMs);
        hoodMotor.config_kI(BallShooterConstants.kSlotIdx, BallShooterConstants.kGains_hoodMotor.kI,
                BallShooterConstants.kTimeoutMs);
        hoodMotor.config_kD(BallShooterConstants.kSlotIdx, BallShooterConstants.kGains_hoodMotor.kD,
                BallShooterConstants.kTimeoutMs);
        /* Set acceleration and cruise velocity - see documentation */
        hoodMotor.configMotionCruiseVelocity(300, BallShooterConstants.kTimeoutMs);
        hoodMotor.configMotionAcceleration(150, BallShooterConstants.kTimeoutMs);

        /* Zero the sensor */
        hoodMotor.setSelectedSensorPosition(0, BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kTimeoutMs);

    }

    public boolean isLimitSwitchDown() {
        return limitSwitchDown.get();
    }

    /**
     * Given velocity in rotations per minute (rpm), rpmToVelocityPer100ms
     * calculates and returns the velocity in encoder units per 100ms
     * 
     * @param rpm - Motor Rotations per minute
     * @return - Encoder units per 100ms
     */
    public double rpmToVelocityPer100ms(final double rpm) {
        return rpm * BallShooterConstants.kSensorUnitsPerRotation / 600;
    }

    /**
     * Given velocity in encoder units per second, velocityPer100msToRpm calculates
     * and returns the velocity in rotations per minute (rpm)
     * 
     * @param velPer100ms
     * @return
     */
    public double velocityPer100msToRpm(final double velPer100ms) {
        return velPer100ms / BallShooterConstants.kSensorUnitsPerRotation * 600;
    }

    /**
     * toConsoleln - this routine looks at the BallShooterConstants.debug to
     * determine if it should write something to the console or not. If debug is
     * true, it will to a println of the string passed in.
     * 
     * @param s - the string to write to the console out with a carriage return
     *          after it.
     */
    private void toConsoleln(final String s) {
        if (BallShooterConstants.debug) {
            System.out.println(s);
        }
    }

    /**
     * toConsole - this routine looks at the BallShooterConstants.debug to determine
     * if it should write something to the console or not. If debug is true, it will
     * to a println of the string passed in.
     * 
     * @param s - the string to write out to the console..
     */
    // private void toConsole(final String s) {
    // if (BallShooterConstants.debug) {
    // System.out.print(s);
    // }
    // }

    private double _withinThresholdLoops = 0;

    /**
     * isWithinThreshold tracks the error reported by the shooter motor and
     * determines if it has "settled". It uses an error threshhold (and a counter
     * (kPIDLoopIdx)to determine if
     * 
     * @return - true if it has settled, otherwise false.
     */
    public boolean isWithinThreshold() {
        /* Check if closed loop error is within the threshld */
        double closedLpErr = Math.abs(shootMotor.getClosedLoopError(BallShooterConstants.kPIDLoopIdx));


        if (closedLpErr < BallShooterConstants.kShootMotorRPMTolerance) {
            ++_withinThresholdLoops;
            toConsoleln("incrementing threshold loops: " + _withinThresholdLoops);
        } else {
            _withinThresholdLoops = 0;
            toConsoleln("restart threshold loops: " + _withinThresholdLoops);
        }

        SmartDashboard.putNumber("Shooter/ShootMotorError", closedLpErr);
        SmartDashboard.putBoolean("Shooter/ShootMotorReady",_withinThresholdLoops > BallShooterConstants.kLoopsToSettle);

        return (_withinThresholdLoops > BallShooterConstants.kLoopsToSettle);
    }

    public boolean hoodAtPosition() {
        final double err = hoodMotor.getClosedLoopError(BallShooterConstants.kPIDLoopIdx);
        
        return Math.abs(err) < BallShooterConstants.kHoodPositionTolerance;
    }

    double pp = 0;
    double ii = 0;
    double dd = 0;
    double ff = 0;
    double oldTarget = 0;
    public boolean teleopWithIdle = false;

    public void testShootMotor() {

        double p = SmartDashboard.getNumber("shooter/P", 0);
        double i = SmartDashboard.getNumber("shooter/I", 0);
        double d = SmartDashboard.getNumber("shooter/D", 0);
        double f = SmartDashboard.getNumber("shooter/F", 0);
        double targetRPMs = SmartDashboard.getNumber("shooter/targetVelocity", 200);

        if ((p != pp) || (i != ii) || (d != dd) || (f != ff) || (targetRPMs != oldTarget)) {
            shootMotor.config_kF(0, f, 20);
            shootMotor.config_kP(0, p, 20);
            shootMotor.config_kI(0, i, 20);
            shootMotor.config_kD(0, d, 20);
            shootMotor.config_IntegralZone(0, 500);
        }
        pp = p;
        ii = i;
        dd = d;
        ff = f;
        oldTarget = targetRPMs;
        masterShootRPM = targetRPMs;

    }

    public void testHoodMotor() {

        double p = SmartDashboard.getNumber("shooter/P", 0);
        double i = SmartDashboard.getNumber("shooter/I", 0);
        double d = SmartDashboard.getNumber("shooter/D", 0);
        double f = SmartDashboard.getNumber("shooter/F", 0);
        double targetPos = SmartDashboard.getNumber("shooter/targetPosition", 200);

        if ((p != pp) || (i != ii) || (d != dd) || (f != ff) || (targetPos != oldTarget)) {
            hoodMotor.config_kF(0, f, 20);
            hoodMotor.config_kP(0, p, 20);
            hoodMotor.config_kI(0, i, 20);
            hoodMotor.config_kD(0, d, 20);
            hoodMotor.config_IntegralZone(0, 500);
        }
        pp = p;
        ii = i;
        dd = d;
        ff = f;
        oldTarget = targetPos;
        masterHoodPos = targetPos;

    }

    public void setMasterHoodPos(double pos) {
        // hoodMotor.set(ControlMode.Position,pos);
        hoodMotor.set(ControlMode.MotionMagic, pos);
    }

    public void setMasterShootVelocity(double rpms) {
        if (rpms == 0) {
            shootMotor.set(ControlMode.PercentOutput, rpms);
        } else {
            shootMotor.set(ControlMode.Velocity, rpmToVelocityPer100ms(rpms));
        }
    }

    public boolean zeroOutHood() {
        // hoodMotor.configPeakCurrentLimit(1);
        // hoodMotor.configPeakCurrentDuration(300);
        // hoodMotor.enableCurrentLimit(true);

        hoodMotor.set(ControlMode.PercentOutput, 0.6);
        // check to see if the limit switch is trippped.
        boolean limitReached = isLimitSwitchDown();

        // check to see that we are not stalled
        //boolean hoodStalled = this.hoodMotor.getSupplyCurrent() > 1;

        // if either above, Stop and zero
        if (limitReached) {
            hoodMotor.setSelectedSensorPosition(0, BallShooterConstants.kPIDLoopIdx, BallShooterConstants.kTimeoutMs);
            hoodMotor.set(ControlMode.PercentOutput, 0);
            return true;
        }
        return false;
    }

    public void setShootIdleVelocity(double rpms) {
        setMasterShootVelocity(rpms);
    }

    public void stopHoodMotor() {
        hoodMotor.set(ControlMode.PercentOutput, 0);
    }
    public void stopControlMode() {
        hoodMotor.set(ControlMode.Disabled, 0);
    }

}

