/**
 * Simple class containing constants used throughout project
 */
package frc.robot;
import java.util.TreeMap;

public class BallShooterConstants {
	//hood  Constants 
	public final static double kHoodUpEncoderMax = 3900;
	public final static double kHoodPositionTolerance = 230;
	
	// Shoot motor Constants
	public final static double kShootMotorRPMTolerance = 50;//rpms
	public static final boolean kEnableCurrentLimiting_BS = true;

	// Current (amp) limit
	public static final double currentLimit = 20;

	// Threshold that must be exceeded for current limiting to occur
	public static final double thresholdLimit = 30;

	// How long the current has to be above the threshold to trigger limiting
	public static final double thresholdTime = 0;

	public static final int kPIDLoopIdx = 0; 
	public static final int kSlotIdx = 0;

    /**
	 * Set to zero to skip waiting for confirmation. Set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public final static int kTimeoutMs = 30;

    /*
	 * Gains(kp, ki, kd, kf, izone, peak output);
	 */
	public static final Gains kGains_hoodMotor = new  Gains(0.7, 0.00001, 0.0, .14, 0, 1.0);
	public static final Gains kGains_shootMotor = new Gains(0.4, 0.00001, 0.0, 0.049, 500, 1.0);


	/**
	 * This is our best shooting position, edge of control panel trough
	 */
	//
	public static final double magicRPMS = -4725;
	
	public static final TreeMap<Integer, double[]> targetPercent2ShooterParms = new TreeMap<Integer, double[]>() {
		private static final long serialVersionUID = 1L;
		// {	   //target						Hood
		// 	   //percentage			RPMs	Encoder
		// 	put( 350, new double[] { -3360, 4500 });
		// 	put( 200, new double[] { -3675, 4400 });
		// 	put( 134, new double[] { -3832, 4250});
		// 	put( 100, new double[] { -3990, 4100 });
		// 	put( 70, new double[] { magicRPMS, 3400 }); // magic spot
		// 	put( 25, new double[] { -5250, 2700 });
		// 	put( 0, new double[] { -4725, 3400 });

		// }
		{	   //target						Hood
			//percentage			RPMs	Encoder
		 put( 219, new double[] { -4000, 4600});
		 put( 110, new double[] { -4300, 4100 });
		 put( 0, new double[] { -4800, 3900 });

	 }
	};
	
	
	
	/**
	 * How many sensor units per rotation. Using Talon FX Integrated Encoder.
	 * 
	 * @link https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
	 */
	public final static int kSensorUnitsPerRotation = 2048;
		//example usage:
	/*public static void main(String[] args) throws Exception {
		double ta = 150;
		Integer key = (int)ta;
		if (key < 0 || key >= 100) {
			//percentage out of range
		}
		else {
			System.out.println("RPMS: " + targetPercent2ShooterParms.floorEntry(key).getValue()[0]);
			System.out.println("HE: " + targetPercent2ShooterParms.floorEntry(key).getValue()[1]);
		}
		}*/
	public static final boolean debug = true;
	public static final boolean test = false;
	public static final double teleopAutoShootCmdTimeout = 10;
	public static final double kLoopsToSettle = 15;
	public static final int kErrThreshold = 300;

	/**
	 * Position the hood is moved to when idleing.  This should be 0 or stowed so that we can drive under
	 * the control panel when we are not shooting.  
	 */
	public static final double hoodIdlePosition = 0;
	public static final double hoodShootPosition = 4200;
	
	/**
	 * Velocity in RPMs that the robot should idle at when not using the shooter.  This is for 
	 * conservation of energy and time so that we don't need to spin up each time we try to shoot.
	 */
	public static final double shootIdleVelocity = 0;
	public static final double shootDefultVelocity = -4000;
	public static final double shootClimbVelocity = 0;

	}

