// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallAcquisition;
import frc.robot.subsystems.BallIndexer;
import frc.robot.subsystems.BallShooter;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shifter;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class AutoLeft extends SequentialCommandGroup {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutoLeft(BallAcquisition m_ballAcquisition, BallIndexer m_ballIndexer, BallShooter m_ballShooter, DriveTrain  m_driveTrain, Shifter m_shifter){

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    addCommands(
        // Add Commands here:
        // Also add parallel commands using the
        //
        // addCommands(
        //      new command1(argsN, subsystem),
        //      parallel(
        //          new command2(argsN, subsystem),
        //          new command3(argsN, subsystem)
        //      )    
        //  );
        new zeroHood(m_ballShooter),
        new ParallelCommandGroup(
     //       new setShootModeOn(m_ballShooter),
            new driveFeet(6.5, 0, m_driveTrain).withTimeout(3.5)
        ),
        
        new autoTurn(25, m_driveTrain).withTimeout(1),
        new turn2LimeLight(m_driveTrain).withTimeout(1),
       // new autonimousShoot(m_ballShooter),
        new teleopAutoShootCMD(m_ballShooter).withTimeout(4),
        new autoTurn(-25, m_driveTrain).withTimeout(1),
        new extendSolenoid(m_ballAcquisition).withTimeout(2),
        new ParallelRaceGroup(
            new startAcquireMotor(m_ballAcquisition),
            new driveFeet(9, 0, m_driveTrain).withTimeout(5)
        ),
        new driveFeet(-9, 0, m_driveTrain).withTimeout(5)   
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
