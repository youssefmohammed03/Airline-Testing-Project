package airline;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

@Suite
@SelectClasses({FlightTest.class, SearchFlightTest.class})
public class FlightSuite {
	
}
