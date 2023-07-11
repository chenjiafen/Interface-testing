package com.testapi.http;

import org.apache.http.message.BasicHeader;

public class Header {

	private BasicHeader header = null;

	public Header(String name, String value) {
		this.header = new BasicHeader(name, value);
	}

	public BasicHeader header() {
		return header;
	}

	public void header(BasicHeader header) {
		this.header = header;
	}

}
