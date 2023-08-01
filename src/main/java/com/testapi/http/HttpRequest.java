package com.testapi.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class HttpRequest {

	private CloseableHttpClient httpClient;
	private CookieStore cookieStore;
	private HttpMethod method;
	private String contentType;
	private String destination;
	private String protocol = "https";
	private String host = "localhost";
	private Integer port;
	private String path = "/";
	private Multimap<String, String> query = ArrayListMultimap.create();
	private Multimap<String, String> form = ArrayListMultimap.create();
	private String data = null;
	private JSONObject json=null;
	private List<Header> headers = new ArrayList<>();

	public HttpRequest()  {
		SSLContextBuilder builder = new SSLContextBuilder();
		//  忽略https证书的验证
		try {
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SSLConnectionSocketFactory sslsf = null;
		try {
			sslsf = new SSLConnectionSocketFactory(builder.build(),
//					new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
					new String[] { "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(200);// max connection
		httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm)
				.setConnectionManagerShared(true).build();

	}
	/**
	 * 切割//
	 * @param destination
	 * @return
	 * 目标网址解析
	 */
	private static HttpRequest wrapRequest(String destination) {
		HttpRequest req = new HttpRequest();
		int p1 = destination.indexOf("://");
		if (p1 > 0) {
			req.protocol(destination.substring(0, p1));
		} else {
			destination = req.protocol + "://" + destination;
			req.destination = destination;
		}

		return req;
	}

	
	/**
	 * 请求的方式get
	 * @param destination
	 * @return
	 */
	public static HttpRequest get(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.GET).dest(destination);
		return req;
	}
	/**
	 * 请求的方式post
	 * @param destination
	 * @return
	 */
	public static HttpRequest post(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.POST).dest(destination);
		return req;
	}
	
	/**
	 * 请求的方式put
	 * @param destination
	 * @return
	 */
	public static HttpRequest put(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.PUT).dest(destination);
		return req;
	}
	/**
	 * 请求的方式delete
	 * @param destination
	 * @return
	 */
	public static HttpRequest delete(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.DELETE).dest(destination);
		return req;
	}
	/**
	 * 请求的方式patch
	 * @param destination
	 * @return
	 */
	public static HttpRequest patch(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.PATCH).dest(destination);
		return req;
	}
	/**
	 * 请求的方式dest
	 * @param destination
	 * @return
	 */
	private HttpRequest dest(String destination) {
		this.destination = destination;
		return this;
	}


	/**
	 * 请求方式
	 * @return
	 */
	public HttpMethod method() {
		return method;
	}

	/**
	 * 请求方法带参数
	 * @param method
	 * @return
	 */
	public HttpRequest method(HttpMethod method) {
		this.method = method;
		return this;
	}

	/**
	 *protocol
	 * @return
	 */
	public String protocol() {
		return protocol;
	}

	/**
	 * 带参数的协议，http、https
	 * @param protocol
	 * @return
	 */
	public HttpRequest protocol(String protocol) {
		this.protocol = protocol;
		return this;
	}

	public String host() {
		return host;
	}

	public HttpRequest host(String host) {
		this.host = host;
		return this;
	}

	public int port() {
		return port;
	}

	public HttpRequest port(int port) {
		this.port = port;
		return this;
	}

	public String path() {
		return path;
	}

	public HttpRequest path(String path) {
		this.path = path;
		return this;
	}

	public Multimap<String, String> query() {
		return query;
	}

	public HttpRequest query(String name, String value) {
		query.put(name, value);
		return this;
	}

	public HttpResponse send() {
		StringBuilder accumation = null;
		if (this.destination == null) {
			accumation = new StringBuilder();
			accumation.append(this.protocol()).append("://")
					.append(this.host());
			if (this.port != null) {
				accumation.append(':').append(this.port());
			}
			accumation.append(this.path());
			this.destination = accumation.toString();
		} else {
			accumation = new StringBuilder(destination);
		}
		if (!query.isEmpty()) {
			if (destination.indexOf('?') > 0) {
				accumation.append('&');
			} else {
				accumation.append('?');
			}
			int i = 0;
			for (Entry<String, String> entry : query.entries()) {
				if (i > 0) {
					accumation.append('&');
				}
				i++;
				accumation.append(entry.getKey()).append('=')
						.append(entry.getValue());
			}
			this.destination = accumation.toString();
		}
		System.out.println(destination);
		HttpResponse response = null;
		HttpClientContext context = HttpClientContext.create();
		if (cookieStore != null)
			context.setCookieStore(cookieStore.cookieStore());
		switch (method) {
		case GET: {
			HttpGet req = new HttpGet(destination);
			wrapHeader(req);
			try {
				org.apache.http.client.methods.CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case POST: {
			HttpPost req = new HttpPost(destination);
			wrapHeader(req);
			req.setEntity(getReqEntity());
			try {
				org.apache.http.client.methods.CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case PUT: {
			HttpPut req = new HttpPut(destination);
			wrapHeader(req);
			req.setEntity(getReqEntity());
			try {
				org.apache.http.client.methods.CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case PATCH: {
			HttpPatch req = new HttpPatch(destination);
			wrapHeader(req);
			req.setEntity(getReqEntity());
			try {
				org.apache.http.client.methods.CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case DELETE: {
			HttpDelete req = new HttpDelete(destination);
			wrapHeader(req);
			try {
				org.apache.http.client.methods.CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
		return response;
	}
	//添加包头
	private void wrapHeader(HttpRequestBase req) {
		for (Header h : headers) {
			req.addHeader(h.header());
		}
	}
	//请求参数里成对的属性
	private HttpEntity getReqEntity() {
		HttpEntity entity = null;
		if ("application/x-www-form-urlencoded".equals(contentType)) {
			List<BasicNameValuePair> params = new ArrayList<>();
			for (Entry<String, String> entry : form.entries()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(params,
					Charset.forName("UTF-8"));
			entity1.setContentType(contentType);
			entity = entity1;
		} else {
			if (data != null) {
				StringEntity entity1 = new StringEntity(data,
						Charset.forName("UTF-8"));
				if (contentType != null) {
					entity1.setContentType(contentType);
				} else {
					entity1.setContentType("text/plain");
				}
				entity = entity1;
			}
		}
		return entity;
	}
	
	//请求头参数
	public HttpRequest form(String name, String value) {
		if (contentType == null) {
			contentType = "application/x-www-form-urlencoded";
		}
		form.put(name, value);	
		return this;
	}

	public HttpRequest data(String data) {
		if (contentType == null) {
			contentType = "text/plain";
		}
		this.data = data;
		return this;
	}
	//格式为json
	public HttpRequest data(JSONObject json) {
		if (contentType == null) {
			contentType = "application/json";
		}
		this.json = json;
		return this;
	}

	public HttpRequest cookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
		return this;
	}

	public CookieStore cookieStore() {
		return this.cookieStore;
	}
	//获取包头
	public HttpRequest header(String name, String value) {
		headers.add(new Header(name, value));
		return this;
	}

	public HttpRequest header(Header header) {
		headers.add(header);
		return this;
	}

	public HttpRequest contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
}
