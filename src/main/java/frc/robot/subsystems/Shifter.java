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


import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Shifter extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    public boolean highGear = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private DoubleSolenoid shifter;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public Shifter() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
 shifter = new DoubleSolenoid(10, 2, 4);
 addChild("shifter",shifter);
 


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
        SmartDashboard.putString("drive/Gear", highGear ? "High Gear" : "Low Gear");
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void shiftUp() {
        shifter.set(Value.kForward);
        highGear = true;
    }

    public void shiftDown() {
        shifter.set(Value.kReverse);
        highGear = false;
    }

    public void reinitializeShifter() {
        shifter.set(Value.kReverse);
        highGear = false;
    }
}

