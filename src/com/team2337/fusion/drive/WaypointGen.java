package com.team2337.fusion.drive;

import jaci.pathfinder.*;
import jaci.pathfinder.modifiers.TankModifier;


public class WaypointGen {

	Waypoint[] points = new Waypoint[] {
		    new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
		    new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
		    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
		};
	
	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
	Trajectory trajectory = Pathfinder.generate(points, config);
	
	TankModifier modifier = new TankModifier(trajectory).modify(0.5);
	
}
