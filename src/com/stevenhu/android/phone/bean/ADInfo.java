package com.stevenhu.android.phone.bean;

/**
 * 描述：广告信息</br>
 */
public class ADInfo {
	
	String id = "";
	int url = 0;
	String content = "";
	String type = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUrl() {
		return url;
	}

	public void setUrl(int url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
