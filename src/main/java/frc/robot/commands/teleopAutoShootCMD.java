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
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.BallShooterConstants;
import frc.robot.LimelightUtility;
import frc.robot.RobotContainer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.BallShooter;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class teleopAutoShootCMD extends CommandBase {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private final BallShooter m_ballShooter;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    double hoodEncoderUnits = 0;
    double area = 0;
    double rpms = 0;
    Integer numberOfBalls = 0;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public teleopAutoShootCMD(BallShooter subsystem) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_ballShooter = subsystem;
        addRequirements(m_ballShooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    runIndexBelt indexBeltRunner;
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        LimelightUtility.RefreshTrackingData();
        // Lookup optimal RPMS &  Hood encoder units based on area (if target seen)
        if (LimelightUtility.ValidTargetFound()) {
            area = LimelightUtility.TargetAreaPercentage * 100; 
        } else {
            System.out.println("No target");
            area = 70;
        }  
        rpms = BallShooterConstants.targetPercent2ShooterParms.floorEntry((int)area).getValue()[0];
        hoodEncoderUnits = BallShooterConstants.targetPercent2ShooterParms.floorEntry((int)area).getValue()[1];

        //numberOfBalls = RobotContainer.getInstance().m_ballIndexer.ballCount(); 
        //Robot.ballShooter.prepareToShoot(rpms,hoodEncoderUnits);
        //setTimeout(BallShooterConstants.teleopAutoShootCmdTimeout);
        indexBeltRunner = new runIndexBelt(RobotContainer.getInstance().m_ballIndexer);
        RobotContainer.getInstance().m_ballIndexer.shooterActive = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_ballShooter.ready2Shoot(rpms, hoodEncoderUnits)) {
            if (indexBeltRunner.isFinished()) {
                System.out.println("teleopAutoShootCMD is Running belt motor");
                indexBeltRunner.schedule();
                
            } /*else if(!RobotContainer.getInstance().m_ballIndexer.ballPresent(1)) {
                RobotContainer.getInstance().m_ballAcquisition.startAquireMotor();
            }*/
        } else {
            if (!indexBeltRunner.isFinished()) {
                System.out.println("teleopAutoShootCMD is Cancelling belt motor");
                
                indexBeltRunner.cancel();   
            }
       }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
            //Robot.ballShooter.stopShooter();
            if (!indexBeltRunner.isFinished()) {
                indexBeltRunner.cancel();
            }
            RobotContainer.getInstance().m_ballIndexer.shooterActive =false;
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
