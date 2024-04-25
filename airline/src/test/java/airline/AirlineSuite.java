package airline;

import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SelectClasses;

@Suite
@SelectClasses({AdminTest.class, AirlineTest.class, LoginSignupTest.class, PassengerTicketTest.class})
public class AirlineSuite {

}
