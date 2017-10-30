package com.wangbei.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.wangbei.service.wxpay.api.HttpService;

public class HtmlUtil {

	public static String getElementInnterText(String html, String selector) {
		html = html.replaceAll("</p>", "</p>@@");
		html = html.replaceAll("</h2>", "</h2>@@");
		html = html.replaceAll("</div>", "</div>@@");
		html = html.replaceAll("<br>", "@@");
		html = html.replaceAll("<br />", "@@");
		html = html.replaceAll("<br/>", "@@");
		Document doc = Jsoup.parse(html);
		Elements titleEle = doc.select(selector);
		if (titleEle.size() > 0) {
			return titleEle.get(0).text().replaceAll("@@", "\n\n");
		}
		return null;
	}

	public static List<String> getLinkHref(String html, String selector) {
		List<String> result = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Elements eles = doc.select(selector);
		for (int i = 0; i < eles.size(); i++) {
			result.add(eles.get(i).attr("href"));
		}
		return result;
	}

	public static String getElementInnerHtml(String html, String selector) {
		Document doc = Jsoup.parse(html);
		Elements titleEle = doc.select(selector);
		if (titleEle.size() > 0) {
			return titleEle.get(0).html();
		}
		return null;
	}

	public static String getElementInnerImageLink(String html, String selector) {
		Document doc = Jsoup.parse(html);
		Elements imgEle = doc.select(selector + " img");
		if (imgEle.size() > 0) {
			return imgEle.get(0).attr("src");
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		String html = HttpService.sendGetForHtml("http://www.liaotuo.org/fojing/wuliangshoujing/40729.html");
		String title = getElementInnterText(html, "h1[class].B1_tit");
		String content = getElementInnterText(html, "div[class].B1_text");

		System.out.println(title);
		System.out.println(content);
	}

}
