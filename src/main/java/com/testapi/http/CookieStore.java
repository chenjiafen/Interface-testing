package com.testapi.http;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie2;

import java.util.List;

public class CookieStore {

	private BasicCookieStore cookieStore = new BasicCookieStore();

	public BasicCookieStore cookieStore() {
		return cookieStore;
	}

	public void cookieStore(BasicCookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public String cookie(String name) {
		List<Cookie> cookies = this.cookieStore.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals(name)) {
				return c.getValue();
			}
		}
		return null;
	}

	public Cookie cookie(String name, String value) {
		BasicClientCookie2 c = new BasicClientCookie2(name, value);
		cookieStore.addCookie(c);
		return c;
	}

	@Override
	public String toString() {
		StringBuilder accum = new StringBuilder();
		List<Cookie> cookies = this.cookieStore.getCookies();
		for (Cookie c : cookies) {
			accum.append(' ').append(c.getName()).append(':')
					.append(c.getValue());
		}
		return accum.toString();
	}

}
