import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;
	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		//.tw可以指定搜尋台灣的網頁
		
		this.url ="https://www.google.com.tw/search?sxsrf=ALeKk00RuwmrezLvpG4Yb2VrWYKNjhEVzw%3A1609342671041&source=hp&ei=zp7sX4P5POmFr7wPytm74A0&q="+searchKeyword+"&num=100&gs_lcp=CgZwc3ktYWIQDDIECAAQHlD7BVj7BWC7DGgAcAB4AIABWogBWpIBATGYAQCgAQKgAQGqAQdnd3Mtd2l6&sclient=psy-ab&ved=0ahUKEwiDmOjRhPbtAhXpwosBHcrsDtwQ4dUDCAK"; 
//				"http://www.google.com.tw/search?q="+searchKeyword+"&ie=UTF-8&oe=UTF-8&num=100"; //模仿輸入的搜尋結果，num=要的結果數量  //&oe=utf8&num=30
//		this.url = "https://www.youtube.com/results?search_query="+searchKeyword; //模仿youtube， 待測試
		System.out.println(url);
	}
	
	public String fetchContent() throws IOException
	{
		String retVal = "";
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Mozilla/5.0");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		InputStream in = conn.getInputStream();
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		String line = null;
		
		while((line=bufReader.readLine())!=null)
		{
			retVal += line;
		}
		return retVal;
	}
	public HashMap<String, String> query() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content); //轉成網頁結構
		System.out.println(doc.text());
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT"); //.+class name, #+id name, or 直接tag name
		// System.out.println(lis.size());
			
		for(Element li : lis)
		{
			try 
			{
//				System.out.println(li);
//				System.out.println("\n\n\n");
				String citeUrl = li.select("a").get(0).attr("href"); 
				String title = li.select("a").get(0).select(".vvjwJb").text(); //.Text()會選到文字，tag裡面的內容不會被選到
				System.out.println(title + ","+citeUrl);
				retVal.put(title, citeUrl); //hash放東西用put
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}		
		}
		return retVal;
	}
}
