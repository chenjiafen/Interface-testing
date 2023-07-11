package com.testapi.http;

import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpResponse {
	private org.apache.http.client.methods.CloseableHttpResponse res = null;

	public HttpResponse(org.apache.http.client.methods.CloseableHttpResponse res) {
		this.res = res;
	}
	
	public org.apache.http.HttpResponse response() {
		return res;
	}
	//响应结果转成utf-8
	public String body() {
		return body(Charset.forName("utf-8"));
	}
	
	//把响应结果转成String类型
	public String body(Charset charset) {
		try {
			return EntityUtils.toString(res.getEntity(), charset);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void close() {
		try {
			res.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String firstHeader(String name) {
		return res.getFirstHeader(name).getValue();
	}

	public String lastHeader(String name) {
		return res.getLastHeader(name).getValue();
	}

	public String statusLine() {
		return res.getStatusLine().toString();
	}

	public int statusCode() {
		return res.getStatusLine().getStatusCode();
	}

	@Override
	public String toString() {
		return res.toString();
	}

}
