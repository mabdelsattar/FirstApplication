package com.example.mohammedabdelsattar.firstapplication;


	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


	public class ServiceHandler{
	 
	    static String response = null;
	    public final static int GET = 1;
	    public final static int POST = 2;
	    
	  
	    public ServiceHandler() {
	 
	    }
	 
	    /**
	     * Making service call
	     * @url - url to make request
	     * @method - http request method
	     * */
	    public String makeServiceCall(String url, int method) /*1 or 2 */{ 
	        return this.makeServiceCall(url, method, null); 
	    }
	 
	    /** 
	     * Making service call
	     * @url - url to make request
	     * @method - http request method
	     * @params - http request params
	     * */
	    public String makeServiceCall(String url, int method,
	            List<NameValuePair> params) {
	        try {
	        	HttpParams httpParameters = new BasicHttpParams();
	            // http client
	        	int timeoutConnection = 30000;
	        	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	        	// Set the default socket timeout (SO_TIMEOUT) 
	        	// in milliseconds which is the timeout for waiting for data.
	        	int timeoutSocket = 50000;
	        	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
	            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
	            HttpEntity httpEntity = null;
	            HttpResponse httpResponse = null;	            
	            // Checking http request method type
	            if (method == POST) {	            	
	            	HttpPost httpPost = new HttpPost(url);
	               
	               
	                // adding post params
	                if (params != null) {
	                	JSONObject obj = new JSONObject();
	                	try {
	                		for(int i=0 ;i<params.size(); i++){
							obj.put(params.get(i).getName(),params.get(i).getValue());
	                		}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    httpPost.setEntity(new StringEntity(obj.toString(), "UTF-8"));
	                }
 
	                httpResponse = httpClient.execute(httpPost);
	            } else if (method == GET) {
	            	// appending params to url
					if (params != null) {
						
					}
					HttpGet httpGet = new HttpGet(url);
					httpResponse = httpClient.execute(httpGet);

	            }
	            httpEntity = httpResponse.getEntity();
	            
	            response = EntityUtils.toString(httpEntity);
	         //   Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
	 
	        } catch (UnsupportedEncodingException e) {
	          //  e.printStackTrace();
	        } catch (ClientProtocolException e) {
	           // e.printStackTrace();
	        } catch (IOException e) {
	          //  e.printStackTrace();
	        }
	         
	        return response;
	 
	    
	}
}