package com.haiyu.manager.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClientUtil 工具类
 * @author Frez
 *
 */
public class HttpClientUtil {
	
	// 设置默认超时时间为60s
	public static final int DEFAULT_TIME_OUT = 60 * 1000;
	// 默认字符集UTF-8
	public static final String DEFAULT_CHARSET = "UTF-8";

	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
	/**
	 * http请求
	 * @param url
	 * @param paramMap
	 * @param charset
	 * @param isPost
	 * @return
	 */
	public static String sendHttpRequest(String url, Map<String, String> paramMap, String charset, boolean isPost) {
		return sendHttpRequest(url, paramMap, charset, isPost, DEFAULT_TIME_OUT);
	}

	/**
	 * http请求
	 * @param url
	 * @param paramMap
	 * @param charset
	 * @param isPost
	 * @param timeout
	 * @return
	 */
	public static String sendHttpRequest(String url, Map<String, String> paramMap, String charset, boolean isPost, int timeout) {
		if (isPost) {
			return httpPost(url, paramMap, charset, timeout);
		}
		return httpGet(url, paramMap, charset, timeout);
	}

	public static String httpPost(String url, Map<String, String> params){
		return httpPost(url, params, DEFAULT_CHARSET, DEFAULT_TIME_OUT);
	}
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static String httpPost(String url, Map<String, String> params, String charset, int timeout) {
		log.debug("http request url: {}  charset: {}  timeout: {} request str: ", url, charset, timeout, params);
		// 参数组装
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			pairs.add(new BasicNameValuePair(key, formatStr(value)));
		}
		HttpEntity httpEntity;
		try {
			httpEntity = new UrlEncodedFormEntity(pairs);
			return httpPost(url, charset, timeout, httpEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	public static String httpPost(String url, String jsonStr){
		return httpPost(url, jsonStr, "UTF-8", DEFAULT_TIME_OUT);
	}
	
	/**
	 * 上面那个默认content-type=ContentType.TEXT_PLAIN, 满足不了要求
	 * 这个content-type=application/json
	 * @param url
	 * @param jsonStr
	 * @return
	 * @throws Exception 
	 */
	public static String httpJsonPost(String url, String jsonStr) throws Exception{
		StringEntity httpEntity = new StringEntity(jsonStr,"UTF-8");  
		httpEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        return httpPost(url, "UTF-8", DEFAULT_TIME_OUT, httpEntity);
	}
	
	/**
	 * post请求
	 * @param url
	 * @param jsonStr
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static String httpPost(String url, String jsonStr, String charset, int timeout){
		log.debug("http request url: {} charset:{} timeout: {} request jsonStr: {}", url, charset, timeout, jsonStr);

		HttpEntity httpEntity = null;
		try {
			httpEntity = new StringEntity(jsonStr, charset);
			return httpPost(url, charset, timeout, httpEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String httpPost(String url, HttpEntity httpEntity) {
		return httpPost(url, "UTF-8", DEFAULT_TIME_OUT, httpEntity);
	}
	
	/**
	 * post请求
	 * @param url
	 * @param charset
	 * @param timeout
	 * @param httpEntity
	 * @return
	 */
	public static String httpPost(String url, String charset, int timeout, HttpEntity httpEntity) {
		log.debug("http url: {}", url);
		if (url == null || url.equals("")) {
			return null;
		}
		String result = null;
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String responseBody = null;
		HttpResponse httpResponse = null;
		try {
			HttpParams httpParams = new BasicHttpParams();
			httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
			httpClient =  new DefaultHttpClient(httpParams);
			httpPost = new HttpPost(url);
			httpPost.setEntity(httpEntity);
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = httpResponse.getEntity();
			responseBody = EntityUtils.toString(entity, charset);
			result = responseBody;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (httpClient != null) {
					httpClient.getConnectionManager().shutdown();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.debug("http return:{}", result);
		return result;
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpGet(String url, Map<String, String> params){
		return httpGet(url, params, "UTF-8", DEFAULT_TIME_OUT);
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static String httpGet(String url, Map<String, String> params, String charset, int timeout) {
		HttpClient httpClient = null;
		try {
			HttpParams httpParams = new BasicHttpParams();
			httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_TIME_OUT);
			httpClient =  new DefaultHttpClient(httpParams);
			HttpEntity httpEntity = httpGetEntity(httpClient, url, params, DEFAULT_CHARSET);
			return EntityUtils.toString(httpEntity, charset);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (httpClient != null) {
					httpClient.getConnectionManager().shutdown();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static byte[] httpGetBytes(String url, Map<String, String> params) {
		HttpClient httpClient = null;
		try {
			HttpParams httpParams = new BasicHttpParams();
			httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_TIME_OUT);
			httpClient =  new DefaultHttpClient(httpParams);
			HttpEntity httpEntity = httpGetEntity(httpClient, url, params, DEFAULT_CHARSET);
			return EntityUtils.toByteArray(httpEntity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (httpClient != null) {
					httpClient.getConnectionManager().shutdown();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static HttpEntity httpGetEntity(HttpClient httpClient, String url, Map<String, String> params, String charset) {
		log.debug("http request url: {} charset:{} request str: {}", url, charset, params);
		if (url == null || url.equals("")) {
			return null;
		}
		HttpGet httpGet;
		HttpResponse httpResponse;
		HttpEntity entity = null;
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<>();
				for (Entry<String, String> entry : params.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					pairs.add(new BasicNameValuePair(key, formatStr(value)));
				}
				url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			httpGet = new HttpGet(url);
			httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			entity = httpResponse.getEntity();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return entity;
	}
	
	private static String formatStr(String text) {
		return (null == text ? "" : text.trim());
	}
}
