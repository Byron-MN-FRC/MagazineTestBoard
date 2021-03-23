// RobotBuilder Version: 3.1
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AdvanceBall;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.extendSolenoid;
import frc.robot.commands.retractSolenoid;
import frc.robot.commands.reverseAcquireMotor;
import frc.robot.commands.startAcquireMotor;
import frc.robot.commands.startGateMotor;
import frc.robot.commands.startLeftBeltMotor;
import frc.robot.commands.startRightBeltMotor;
import frc.robot.commands.startShootMotor;
import frc.robot.commands.stopGateMotor;
import frc.robot.commands.stopLeftBeltMotor;
import frc.robot.commands.stopRightBeltMotor;
import frc.robot.commands.stopShootMotor;
import frc.robot.subsystems.BallAcquisition;
import frc.robot.subsystems.BallIndexer;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shifter;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

  private final Shifter m_shifter = new Shifter();
  private final DriveTrain m_driveTrain = new DriveTrain();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final BallAcquisition m_ballAcquisition = new BallAcquisition();
    public final BallShooter m_ballShooter = new BallShooter();
    public final BallIndexer m_ballIndexer = new BallIndexer();

// Joysticks
private final Joystick testJoystick = new Joystick(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton btnRetractSolenoid = new JoystickButton(testJoystick, 3);        
btnRetractSolenoid.whenPressed(new retractSolenoid( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnRetractSolenoid",new retractSolenoid( m_ballAcquisition ) );

final JoystickButton btnExtendSolenoid = new JoystickButton(testJoystick, 5);        
btnExtendSolenoid.whenPressed(new extendSolenoid( m_ballAcquisition ).withTimeout(0.5) ,true);
    SmartDashboard.putData("btnExtendSolenoid",new extendSolenoid( m_ballAcquisition ).withTimeout(0.5) );

final JoystickButton btnStopShootMotor = new JoystickButton(testJoystick, 2);        
btnStopShootMotor.whenReleased(new stopShootMotor( m_ballShooter ) ,true);
    SmartDashboard.putData("btnStopShootMotor",new stopShootMotor( m_ballShooter ) );

final JoystickButton btnStartShootMotor = new JoystickButton(testJoystick, 2);        
btnStartShootMotor.whileHeld(new startShootMotor( m_ballShooter ) ,true);
    SmartDashboard.putData("btnStartShootMotor",new startShootMotor( m_ballShooter ) );

final JoystickButton btnStopGateMotor = new JoystickButton(testJoystick, 11);        
btnStopGateMotor.whenReleased(new stopGateMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnStopGateMotor",new stopGateMotor( m_ballIndexer ) );

final JoystickButton btnStartGateMotor = new JoystickButton(testJoystick, 13);        
btnStartGateMotor.whileHeld(new startGateMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnStartGateMotor",new startGateMotor( m_ballIndexer ) );

final JoystickButton btnReverseAquireMotor = new JoystickButton(testJoystick, 4);        
btnReverseAquireMotor.whileHeld(new reverseAcquireMotor( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnReverseAquireMotor",new reverseAcquireMotor( m_ballAcquisition ) );

final JoystickButton btnRunAcquireMotor = new JoystickButton(testJoystick, 6);        
btnRunAcquireMotor.whileHeld(new startAcquireMotor( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnRunAcquireMotor",new startAcquireMotor( m_ballAcquisition ) );

final JoystickButton btnAdvanceBall = new JoystickButton(testJoystick, 2);        
btnAdvanceBall.whenPressed(new AdvanceBall( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnAdvanceBall",new AdvanceBall( m_ballIndexer ) );

final JoystickButton btnRightBeltStop = new JoystickButton(testJoystick, 11);        
btnRightBeltStop.whenReleased(new stopRightBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnRightBeltStop",new stopRightBeltMotor( m_ballIndexer ) );

final JoystickButton btnRightBeltStart = new JoystickButton(testJoystick, 11);        
btnRightBeltStart.whileHeld(new startRightBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnRightBeltStart",new startRightBeltMotor( m_ballIndexer ) );

final JoystickButton btnLeftBeltStop = new JoystickButton(testJoystick, 12);        
btnLeftBeltStop.whenReleased(new stopLeftBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnLeftBeltStop",new stopLeftBeltMotor( m_ballIndexer ) );

final JoystickButton btnLeftBeltStart = new JoystickButton(testJoystick, 12);        
btnLeftBeltStart.whileHeld(new startLeftBeltMotor( m_ballIndexer ) ,true);
    SmartDashboard.putData("btnLeftBeltStart",new startLeftBeltMotor( m_ballIndexer ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getTestJoystick() {
        return testJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public DriveTrain getm_DriveTrain(){
        return m_driveTrain;
}

public Shifter getm_shifter(){
    return m_shifter;
}
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}

