/**
 * Simple class containing constants used throughout project
 */
package frc.robot;
import java.util.TreeMap;

public class BallShooterConstants {
	//hood  Constants 
	public final static double kHoodUpEncoderMax = -10920; // 3900;
	public final static double kHoodPositionTolerance = 50;
	
	// Shoot motor Constants
	public final static double kShootMotorRPMTolerance = 2000;//sensor units
	public static final boolean kEnableCurrentLimiting_BS = true;

	// Current (amp) limit
	public static final double currentLimit = 40;

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
//	public static final Gains kGains_hoodMotor = new  Gains(0.7, 0.00001, 0.0, .14, 0, 0);
	public static final Gains kGains_hoodMotor = new  Gains(0.9, 0.0002, 0.0, .99, 0, 0);
	// 10/9/21 public static final Gains kGains_hoodMotor = new  Gains(0.9, 0.00005, 0.0, .99, 0, 0);
	//public static final Gains kGains_shootMotor = new Gains(0.149600029, 0.00001, 1.4956, 0.04760003, 500, 1.0);
	// 9/16/21 public static final Gains kGains_shootMotor =   new Gains(0.149600029, 0.000015, 0, 0.049, 0, 0);
	// 10/9/21 public static final Gains kGains_shootMotor =   new Gains(0.1, 0.0005, 0, 0.062, 0, 0);
	public static final Gains kGains_shootMotor =   new Gains(0.065, 0.00001, 0, 0.048, 0, 0);


	/**
	 * This is our best shooting position, edge of control panel trough
	 */
	//
	//public static final double magicRPMS = -4725;
	
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
			// put( 0, new double[] { -3800, -9880 }); //green
			// put( 1500, new double[] { -3800, -7000 });
			// put( 400, new double[] { -3750, -10920 }); //green
			// put( 320, new double[] { -3800, -10400 }); //yellow
			// //put( 265, new double[] { -4200, -8750 }); 
			// put( 245, new double[] { -4200, -7000 }); 
			// put( 210, new double[] { -4000, -9412});
			// put( 140, new double[] { -4900, -9360});  //blue
			// put( 120, new double[] { -5000, -9620}); 
			// put( 80, new double[] { -5100, -9230}); //red
			// put( 70, new double[] { -5600, -9360}); 

		put(0, new double[] { -3800, -7000});
		put(450, new double[] { -4000, -5500});
		put(250, new double[] { -4250, -5000});
		put(150, new double[] { -4600, -4250});
		put(130, new double[] { -5250, -3650});
		put(100, new double[] { -5500, -4300});
		put(60, new double[] { -5980, -4200});

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
	public static final double kLoopsToSettle = 10;
	//public static final int kErrThreshold = 300;

	/**
	 * Position the hood is moved to when idleing.  This should be 0 or stowed so that we can drive under
	 * the control panel when we are not shooting.  
	 */
	public static final double hoodIdlePosition = 0;
	public static final double hoodShootPosition = -4000 ; //-3450;

	/**
	 * Velocity in RPMs that the robot should idle at when not using the shooter.  This is for 
	 * conservation of energy and time so that we don't need to spin up each time we try to shoot.
	 */
	public static final double shootIdleVelocity = 0;
	public static final double shootDefaultVelocity = -3500;
	public static final double shootClimbVelocity = 0;

	}

