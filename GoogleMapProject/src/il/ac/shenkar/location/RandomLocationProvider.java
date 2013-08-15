package il.ac.shenkar.location;

import il.ac.shenkar.common.CampusInLocation;

/**
 * This class will return random location in the campus
 * it was made only for testing 
 * @author cadan
 *
 */
public class RandomLocationProvider implements ILocationProvider
{

	@Override
	public CampusInLocation getLoction() {

		return new CampusInLocation();
	}
	

}
