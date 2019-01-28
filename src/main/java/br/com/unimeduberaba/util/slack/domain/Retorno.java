package br.com.unimeduberaba.util.slack.domain;

public class Retorno {
	private int statusCode;
	private String content;

	public Retorno() {
	}

	public Retorno(int statusCode, String content) {
		this.statusCode = statusCode;
		this.content = content;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}