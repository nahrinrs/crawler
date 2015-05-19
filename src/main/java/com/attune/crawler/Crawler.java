package com.attune.crawler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;

public class Crawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");

     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return !FILTERS.matcher(href).matches()
                && href.startsWith("https://bonobos.com/");
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         //System.out.println("URL: " + url);

         if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
             if (url.contains("https://bonobos.com/products")){
            	 int qIdx = url.indexOf("?");
            	 int sIdx = url.lastIndexOf("/");
            	 int eIdx = url.indexOf("color=");
            	 if (qIdx >sIdx){
            		 String productName = url.substring(sIdx + 1, qIdx - 1);
            		 String color = URLDecoder.decode(url.substring(eIdx).replace("color=", ""));
                     Map<String,String> pr_data = getProductData(html);
                     try{
                    	 JSONObject obj = new JSONObject(pr_data.get("variant-prices"));
                    	 JSONArray prices = obj.getJSONArray("prices");
                    	 App.visitlog.println("product Name: " + productName + ", Color: " + color + ", Price: " + prices.getJSONObject(0).getString("display_price"));
                     } catch (Exception ex){
                    	 System.out.println(ex);
                     }
            	 }
            	 
             }
             Set<WebURL> links = htmlParseData.getOutgoingUrls();
         }
    }

	private Map<String,String> getProductData(String html) {
		Map<String, String> result = new HashMap<String, String>();
		
		String regex = "productEl.data(.*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);

		while (matcher.find()) {
			String match = html.substring( matcher.start(), matcher.end());
			if (match.contains("variant-prices")){
				result.put("variant-prices", match.substring(match.indexOf("{"), match.lastIndexOf("}") + 1));
			} else if (match.contains("option-types")){
				result.put("option-types", match.substring(match.indexOf("{"), match.lastIndexOf("}") + 1));
			} 
		}
		return result;
	}
}