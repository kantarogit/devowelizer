import static org.hamcrest.Matchers.is;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import client.DevowelApiClient;
import io.restassured.response.Response;
import model.DevowelInputModel;

public class DevowelTest {

    private DevowelApiClient devowelApiClient;

    @Test
    public void canDevowelInput() {
        devowelApiClient = DevowelApiClient.getInstance();
        Response response = devowelApiClient
                .getDevowelized(DevowelInputModel.trying().withInput("kumsa lidududud apvorski ajt da dremnime"));
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // check that the reponse html contains the correct text inside the <body> tag
        response
                .then()
                .assertThat()
                .body("html.body", is("kms ldddd pvrsk jt d drmnm MRDNAT"));
    };
}
