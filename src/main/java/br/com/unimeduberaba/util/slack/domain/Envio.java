package br.com.unimeduberaba.util.slack.domain;

import java.util.ArrayList;
import java.util.List;

public class Envio {
	private String text;
	private List<Anexo> attachments;

	public Envio() {
		this.attachments = new ArrayList<>();
	}

	public Envio(String text) {
		this.text = text;
		this.attachments = new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public void setText(String test) {
		this.text = test;
	}

	public List<Anexo> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Anexo> attachments) {
		this.attachments = attachments;
	}

	public void addAttachments(Anexo attachment) {
		this.attachments.add(attachment);
	}
}