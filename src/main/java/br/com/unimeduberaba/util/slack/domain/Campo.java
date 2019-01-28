package br.com.unimeduberaba.util.slack.domain;

import com.google.gson.annotations.SerializedName;

public class Campo {
	private String title;
	private String value;

	@SerializedName("short")
	private Boolean curto;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getCurto() {
		return curto;
	}

	public void setCurto(Boolean curto) {
		this.curto = curto;
	}
}