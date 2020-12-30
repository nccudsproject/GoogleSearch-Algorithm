import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;




public class Main {

	public static void main(String[] args) {
		try {
			Scanner searchWord = new Scanner(System.in);
			//test
			String content = new GoogleQuery(searchWord.nextLine().replace(" ", "+")).fetchContent();
//			HashMap<String, String> retVal = new HashMap<String, String>();
			Document doc = Jsoup.parse(content); //轉成網頁結構
//			doc.charset(StandardCharsets.UTF_8);
//			System.out.println(content);
			Elements lis = doc.select("div");
			lis = lis.select(".kCrYT");
//			System.out.println(lis);
			//------------------new--------------
			ArrayList<webNode> weblist = new ArrayList<webNode>();
			//-----------------------------------
			int count=0;
			for(Element li : lis) {
				try {
					Elements testString = li.select("a");
					String citeUrl = li.select("a").get(0).attr("href"); 
					String title = li.select("a").get(0).select(".vvjwJb").text();
					if(li.select("a").get(0).select(".vvjwJb").text().isEmpty()) {
						title = "no Title";
					} //.Text()會選到文字，tag裡面的內容不會被選到
					//------------------new--------------
					weblist.add(new webNode(citeUrl, title));
					System.out.println(weblist.size());
					//-----------------------------------
//					System.out.println(title);
					System.out.println(title + ","+citeUrl);
//					System.out.println(testString.get(0).attr("href").substring(7));
//					retVal.put(title, citeUrl); //hash放東西用put
//					System.out.println(testString.get(0).select(".vvjwJb"));
//					System.out.println(testString.get(0).select(".vvjwJb").text().isEmpty());
					System.out.println("--------------------------------------------------");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.println(count);
			//
			
//			System.out.println(new GoogleQuery(searchWord.next()).query());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
