package br.com.unimeduberaba.util.slack.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Anexo {
	private String fallback;

	private String color;

	private String pretext;

	@SerializedName("author_name")
	private String authorName;

	@SerializedName("author_link")
	private String authorLink;

	@SerializedName("author_icon")
	private String authorIcon;

	private String title;

	@SerializedName("title_link")
	private String titleLink;

	private String text;

	private List<Campo> fields;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("thumb_url")
	private String thumbUrl;

	private String footer;

	@SerializedName("footer_icon")
	private String footerIcon;

	private Long ts;

	public Anexo() {
		this.color = "#009c64";
		this.authorLink = "http://www.unimeduberaba.com.br/";
		this.titleLink = "http://www.unimeduberaba.com.br/";
		this.thumbUrl = "https://s3-media1.fl.yelpcdn.com/bphoto/2DiVHPmt77Kj0hWdiS_gSg/l.jpg";
		this.footer = "Unimed Uberaba";
		this.ts = Instant.now().getEpochSecond();
	}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPretext() {
		return pretext;
	}

	public void setPretext(String pretext) {
		this.pretext = pretext;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorLink() {
		return authorLink;
	}

	public void setAuthorLink(String authorLink) {
		this.authorLink = authorLink;
	}

	public String getAuthorIcon() {
		return authorIcon;
	}

	public void setAuthorIcon(String authorIcon) {
		this.authorIcon = authorIcon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleLink() {
		return titleLink;
	}

	public void setTitleLink(String titleLink) {
		this.titleLink = titleLink;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Campo> getFields() {
		return fields;
	}

	public void setFields(List<Campo> fields) {
		this.fields = fields;
	}

	public void addField(Campo field) {
		if (this.fields == null) {
			this.fields = new ArrayList<>();
		}

		this.fields.add(field);
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getFooterIcon() {
		return footerIcon;
	}

	public void setFooterIcon(String footerIcon) {
		this.footerIcon = footerIcon;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}
}