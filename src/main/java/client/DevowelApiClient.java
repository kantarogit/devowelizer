package client;
import io.restassured.response.Response;
import model.DevowelInputModel;

/**
 * Singleton class for the Devowel API client
 * It extends the BaseApiClient which contains the common methods for all API clients
 * It contains one method per each API endpoint that just returns the RestAssured Response object
 */
public class DevowelApiClient extends BaseApiClient {

    //this is set to empty string but in microservice architecture it would be the path to the API
    private static final String API_PATH = "";
    private static GlobalApiProperties globalProperties = GlobalApiProperties.getInstance();
    private static DevowelApiClient instance;

    public static DevowelApiClient getInstance() {
        if (instance == null) {
            instance = new DevowelApiClient();
        }
        return instance;
    }

    private DevowelApiClient() {
        super(globalProperties.get("app.casumo.api"));
        setInitialPath(API_PATH);
    }

    /**
     * This method calls the GET /devowelize endpoint
     * @param input - the input model for the request
     * @return - the RestAssured Response object
     */
    public Response getDevowelized(DevowelInputModel input) {
        return requestSpecification()
                .get(input.getInput());
    }
}
