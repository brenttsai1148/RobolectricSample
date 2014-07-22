package com.example.robolectricsample.httpapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response;
import com.example.robolectricsample.common.Env;
import com.example.robolectricsample.utility.L;


//http://loopj.com/android-async-http/

public class HTTPApi {
	
	
	private DefaultHttpClient httpClient;

    public Response get(String url, Map<String, String> headers, String username, String password)
            throws IOException, URISyntaxException {
        URI uri = new URI(url);
        return makeRequest(headers, username, password, new HttpGet(uri), uri.getHost());
    }

    public Response post(String url, Map<String, String> headers, String postBody, String username, String password)
            throws IOException, URISyntaxException {
        URI uri = new URI(url);
        HttpPost method = new HttpPost(uri);
        method.setEntity(new StringEntity(postBody, "UTF-8"));
        return makeRequest(headers, username, password, method, uri.getHost());
    }

    private Response makeRequest(Map<String, String> headers, String username, String password, HttpRequestBase method, String host) {
        try {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                method.setHeader(entry.getKey(), entry.getValue());
            }
            DefaultHttpClient client = getHttpClient();
            addBasicAuthCredentials(client, host, username, password);
            return new Response(client.execute(method));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addBasicAuthCredentials(DefaultHttpClient client, String domainName, String username, String password) {
        if (!(username==null ||username.length()==0) || !(password==null ||password.length()==0)) {
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            client.getCredentialsProvider().setCredentials(new AuthScope(domainName, 443), credentials);
        }
    }

    private DefaultHttpClient getHttpClient() {
        if (httpClient == null) {
            HttpParams parameters = new BasicHttpParams();
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
            httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(parameters, schemeRegistry), parameters);
        }
        return httpClient;
    }

    public static class Response {
        private int statusCode;
        private InputStream responseBody;

        public Response(HttpResponse httpResponse) throws IOException {
            statusCode = httpResponse.getStatusLine().getStatusCode();
            responseBody = httpResponse.getEntity().getContent();
        }

        public int getStatusCode() {
            return statusCode;
        }

        public InputStream getResponseBody() {
            return responseBody;
        }
    }
}
