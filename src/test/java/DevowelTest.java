import static model.DevowelInputModel.trying;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import client.DevowelApiClient;
import io.restassured.response.Response;

public class DevowelTest {

	private DevowelApiClient devowelApiClient;

	@DataProvider(name = "inputAndExpectationForDevowelization", parallel = true)
	public Object[][] createTestData() {
		return new Object[][] {
				{ "hello o casumo", "hll csm" },
				{ "aeiou", "" },
				{ "AE IOU", "" },
				{ "Brk", "Brk" },
				{ " ", " " },
				{ "", "" },
				{ "Hello", "Hll" },
				{ "123", "123" },
				{ "123 plus 345", "123 pls 345" },
				{ "!@#$%^&*()", "!@#$%^&*()" },
				{ "Поздрав од Македонија", "Пздрв д Мкднј" },
				{ "你好", "你好" },
				{ "こんにちは", "こんにちは" },
				{ "😀😁😂", "😀😁😂" }
		};
	}

	@BeforeClass
	public void setUp() {
		devowelApiClient = DevowelApiClient.getInstance();
	}

	@Test(dataProvider = "inputAndExpectationForDevowelization")
	public void canDevowelInput(String inputString, String expectedDevowelizedString) {
		Response response = devowelApiClient
				.getDevowelized(trying().withInput(inputString)); // method trying() statically imported for better readability

		assertThat(response.getStatusCode(), is(HttpStatus.SC_OK));
		assertThat(response.getBody().asString(), is(expectedDevowelizedString));
	};
}
