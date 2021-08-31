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
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.commands.AutoSelector;
import frc.robot.commands.DoNothing;
import frc.robot.commands.clambering;
import frc.robot.commands.driveWithJoyStick;
import frc.robot.commands.extendSolenoid;
import frc.robot.commands.idleBallShooter;
import frc.robot.commands.retractSolenoid;
import frc.robot.commands.reverseAcquireMotor;
import frc.robot.commands.runIndexBelt;
import frc.robot.commands.setShootModeOn;
import frc.robot.commands.shiftDown;
import frc.robot.commands.shiftUp;
import frc.robot.commands.startAcquireMotor;
import frc.robot.commands.teleopAutoShootCMD;
import frc.robot.commands.turn2LimeLight;
import frc.robot.commands.zeroHood;
import frc.robot.subsystems.BallAcquisition;
import frc.robot.subsystems.BallIndexer;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shifter;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Climb m_climb = new Climb();
    public final Shifter m_shifter = new Shifter();
    public final DriveTrain m_driveTrain = new DriveTrain();
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

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_climb.setDefaultCommand(new clambering( m_climb ) );
    m_driveTrain.setDefaultCommand(new driveWithJoyStick( m_driveTrain ) );
    m_ballShooter.setDefaultCommand(new idleBallShooter( m_ballShooter ) );


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    m_chooser.addOption("AutoSelector", new AutoSelector( m_ballAcquisition, m_ballIndexer, m_ballShooter,  m_driveTrain, m_shifter ));
    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.addOption("DoNothing", new DoNothing());
    m_chooser.setDefaultOption("DoNothing", new DoNothing());

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
final JoystickButton btnShiftUp = new JoystickButton(testJoystick, 12);        
btnShiftUp.whenPressed(new shiftUp( m_driveTrain ) ,true);
    SmartDashboard.putData("btnShiftUp",new shiftUp( m_driveTrain ) );

final JoystickButton btnShiftDown = new JoystickButton(testJoystick, 11);        
btnShiftDown.whenPressed(new shiftDown( m_driveTrain ) ,true);
    SmartDashboard.putData("btnShiftDown",new shiftDown( m_driveTrain ) );

final JoystickButton btnResetHood = new JoystickButton(testJoystick, 10);        
btnResetHood.whenPressed(new zeroHood( m_ballShooter ).withTimeout(3.0) ,false);
    SmartDashboard.putData("btnResetHood",new zeroHood( m_ballShooter ).withTimeout(3.0) );

final JoystickButton btnShootModeOn = new JoystickButton(testJoystick, 9);        
btnShootModeOn.whenPressed(new setShootModeOn( m_ballShooter ) ,true);
    SmartDashboard.putData("btnShootModeOn",new setShootModeOn( m_ballShooter ) );

final JoystickButton btnMagazineUp = new JoystickButton(testJoystick, 8);        
btnMagazineUp.whileHeld(new runIndexBelt(.4, m_ballIndexer) ,true);
    SmartDashboard.putData("btnMagazineUp",new runIndexBelt(.4, m_ballIndexer) );

final JoystickButton btnMagazineDown = new JoystickButton(testJoystick, 7);        
btnMagazineDown.whileHeld(new runIndexBelt(-.4, m_ballIndexer) ,true);
    SmartDashboard.putData("btnMagazineDown",new runIndexBelt(-.4, m_ballIndexer) );

final JoystickButton btnRunAcquireMotor = new JoystickButton(testJoystick, 6);        
btnRunAcquireMotor.whileHeld(new startAcquireMotor( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnRunAcquireMotor",new startAcquireMotor( m_ballAcquisition ) );

final JoystickButton btnExtendSolenoid = new JoystickButton(testJoystick, 5);        
btnExtendSolenoid.whenPressed(new extendSolenoid( m_ballAcquisition ).withTimeout(0.5) ,true);
    SmartDashboard.putData("btnExtendSolenoid",new extendSolenoid( m_ballAcquisition ).withTimeout(0.5) );

final JoystickButton btnReverseAquireMotor = new JoystickButton(testJoystick, 4);        
btnReverseAquireMotor.whileHeld(new reverseAcquireMotor( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnReverseAquireMotor",new reverseAcquireMotor( m_ballAcquisition ) );

final JoystickButton btnRetractSolenoid = new JoystickButton(testJoystick, 3);        
btnRetractSolenoid.whenPressed(new retractSolenoid( m_ballAcquisition ) ,true);
    SmartDashboard.putData("btnRetractSolenoid",new retractSolenoid( m_ballAcquisition ) );

final JoystickButton btnTurn2LimeLight = new JoystickButton(testJoystick, 2);        
btnTurn2LimeLight.whenPressed(new turn2LimeLight( m_driveTrain ).withTimeout(5.0) ,true);
    SmartDashboard.putData("btnTurn2LimeLight",new turn2LimeLight( m_driveTrain ).withTimeout(5.0) );

final JoystickButton btnShoot = new JoystickButton(testJoystick, 1);        
btnShoot.whileHeld(new teleopAutoShootCMD( m_ballShooter ).withTimeout(10.0) ,true);
    SmartDashboard.putData("btnShoot",new teleopAutoShootCMD( m_ballShooter ).withTimeout(10.0) );



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

public BallIndexer getm_balIndexer(){
        return m_ballIndexer;
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

