package test.com.twm.pt.robolectricsampletests.httpapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.util.Strings.fromStream;

import java.net.URI;
import java.util.HashMap;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.tester.org.apache.http.HttpRequestInfo;

import com.example.robolectricsample.httpapi.HTTPApi;

@RunWith(RobolectricTestRunner.class)
public class HTTPApiTest {
	private HTTPApi httpApi;

    @Before
    public void setup() {
    	Robolectric.setDefaultHttpResponse(200, "OK");
        httpApi = new HTTPApi();
    }

    @Test
    public void testGet_FormsCorrectRequest_noBasicAuth() throws Exception {
        Robolectric.addPendingHttpResponse(200, "OK");

        httpApi.get("www.example.com", new HashMap<String, String>(), null, null);

        assertThat(((HttpUriRequest) Robolectric.getSentHttpRequest(0)).getURI(), equalTo(URI.create("www.example.com")));
    }

    @Test
    public void testGet_shouldApplyCorrectHeaders() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("foo", "bar");
        httpApi.get("www.example.com", headers, null, null);

        HttpRequest sentHttpRequest = Robolectric.getSentHttpRequest(0);
        assertThat(sentHttpRequest.getHeaders("foo")[0].getValue(), equalTo("bar"));
    }

    @Test
    public void testGet_ShouldUseCorrectHttpMethod() throws Exception {
        httpApi.get("www.example.com", new HashMap<String, String>(), null, null);
        HttpUriRequest sentHttpRequest = (HttpUriRequest) Robolectric.getSentHttpRequest(0);
        assertThat(sentHttpRequest.getMethod(), equalTo(HttpGet.METHOD_NAME));
    }

    @Test
    public void testGet_FormsCorrectRequest_withBasicAuth() throws Exception {
        Robolectric.addPendingHttpResponse(200, "OK");
        httpApi.get("www.example.com", new HashMap<String, String>(), "username", "password");
        HttpRequestInfo sentHttpRequestData = Robolectric.getSentHttpRequestInfo(0);

        CredentialsProvider credentialsProvider =
                (CredentialsProvider) sentHttpRequestData.getHttpContext().getAttribute(ClientContext.CREDS_PROVIDER);
        assertThat(credentialsProvider.getCredentials(AuthScope.ANY).getUserPrincipal().getName(), equalTo("username"));
        assertThat(credentialsProvider.getCredentials(AuthScope.ANY).getPassword(), equalTo("password"));
    }

    @Test
    public void shouldReturnCorrectResponse() throws Exception {
    	
        Robolectric.addPendingHttpResponse(666, "it's all cool really");
        HTTPApi.Response response = httpApi.get("www.example.com", new HashMap<String, String>(), null, null);
        assertThat(fromStream(response.getResponseBody()), equalTo("it's all cool really"));
        assertThat(response.getStatusCode(), equalTo(666));
    }

    @Test
    public void testPost_ShouldUseCorrectMethod() throws Exception {
        httpApi.post("www.example.com", new HashMap<String, String>(), "a post body", null, null);

        HttpUriRequest sentHttpRequest = (HttpUriRequest) Robolectric.getSentHttpRequest(0);
        assertThat(sentHttpRequest.getMethod(), equalTo(HttpPost.METHOD_NAME));
    }

    @Test
    public void testPost_ShouldIncludePostBody() throws Exception {
        httpApi.post("www.example.com", new HashMap<String, String>(), "a post body", null, null);

        HttpPost sentHttpRequest = (HttpPost) Robolectric.getSentHttpRequest(0);
        StringEntity entity = (StringEntity) sentHttpRequest.getEntity();
        String sentPostBody = fromStream(entity.getContent());
        assertThat(sentPostBody, equalTo("a post body"));
        assertThat(entity.getContentType().getValue(), equalTo("text/plain; charset=UTF-8"));
    }
}
