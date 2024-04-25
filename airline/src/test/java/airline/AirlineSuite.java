package airline;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

@Suite
@SelectClasses({AdminTest.class, AirlineTest.class, LoginSignupTest.class, PassengerTicketTest.class})
public class AirlineSuite {
    // This class doesn't have any code because it's just a container for the test suite.
    // You can add methods or other configurations if needed.
}
