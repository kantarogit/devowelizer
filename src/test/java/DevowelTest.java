import static model.DevowelInputModel.trying;
import static org.hamcrest.Matchers.is;
import org.apache.http.HttpStatus;
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

    @Test(dataProvider = "inputAndExpectationForDevowelization")
    public void canDevowelInput(String inputString, String expectedDevowelizedString) {
        devowelApiClient = DevowelApiClient.getInstance();
        Response response = devowelApiClient
                .getDevowelized(trying().withInput(inputString)); // method trying() statically imported for better
                                                                  // readability
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // check that the reponse html contains the correct text inside the <body> tag
        response
                .then()
                .assertThat()
                .body("html.body", is(expectedDevowelizedString));
    };
}
