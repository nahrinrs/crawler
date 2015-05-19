package com.attune.crawler.bono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

import com.attune.crawler.base.Product;

import java.net.URLDecoder;

public class Crawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");
    private static DateFormat df = new SimpleDateFormat("[yyyyMMdd HHmmss] ");

     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         boolean ret = !FILTERS.matcher(href).matches()
                 && href.startsWith("https://bonobos.com/");
         //System.out.println("shouldVisit:" + ret);
         return ret;
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
    	 //System.out.println("visit...");
         String url = page.getWebURL().getURL();
         System.out.println("URL: " + url);

         if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
             if (url.contains("https://bonobos.com/products")){
                 System.out.println("html: " + html);
            	 Product product = parseData(url, html);
            	 if (product != null){
          			RunCrawler.visitlog.println(product.toString());
            	 } 
             }
             Set<WebURL> links = htmlParseData.getOutgoingUrls();
             System.out.println("getOutgoingUrls: " + links.size());
         }
     }
     
	@Override
	protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
		//System.out.println("handlePageStatusCode...");
		StringBuilder sb = new StringBuilder(df.format(Calendar.getInstance().getTime()));
		sb.append(webUrl.getURL()).append(",").append(statusCode).append(",").append(statusDescription);
		RunCrawler.fetchlog.println(sb.toString());
	}

     private Product parseData(String url, String html) {
    	 if (url.length() == 0 || html.length() == 0 ) return null;
    	 Product product = null;
    	 int qIdx = url.indexOf("?");
    	 int sIdx = url.lastIndexOf("/");
    	 int eIdx = url.indexOf("color=");
    	 if (qIdx >sIdx){
    		 product = new Product();
    		 product.setProductName(url.substring(sIdx + 1, qIdx));
    		 String color = URLDecoder.decode(url.substring(eIdx).replace("color=", ""));
    		 product.setColor(color);
             Map<String,String> pr_data = getExtraProductData(html);
             // price
             try{
            	 JSONObject obj = new JSONObject(pr_data.get("variant-prices"));
            	 JSONArray prices = obj.getJSONArray("prices");
            	 String price = prices.getJSONObject(0).getString("display_price");
            	 product.setPrice(price);
             } catch (Exception ex){
            	 System.out.println(ex);
             }
             // product id
             try{
            	 JSONArray variants = new JSONArray(pr_data.get("variants"));
            	 String id = variants.getJSONObject(0).get("id").toString();
            	 product.setId(id);
             } catch (Exception ex){
            	 System.out.println(ex);
             }
    	 }
		return product;
		
	}
     
	private Map<String,String> getExtraProductData(String html) {
		Map<String, String> result = new HashMap<String, String>();
		
		String regex = "productEl.data(.*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);

		while (matcher.find()) {
			String match = html.substring( matcher.start(), matcher.end());
			if (match.contains("variant-prices")){
				result.put("variant-prices", match.substring(match.indexOf("{"), match.lastIndexOf("}") + 1));
			} else if (match.contains("option-types")){
				result.put("option-types", match.substring(match.indexOf("["), match.lastIndexOf("]") + 1));
			} else if (match.contains("variants")){
				result.put("variants", match.substring(match.indexOf("["), match.lastIndexOf("]") + 1));
			} 
			
		}
		return result;
	}
}