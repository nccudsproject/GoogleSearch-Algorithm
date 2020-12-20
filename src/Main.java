import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {

	public static void main(String[] args) {
		try {
			Scanner searchWord = new Scanner(System.in);
			//test
			String content = new GoogleQuery(searchWord.next()).fetchContent();
			HashMap<String, String> retVal = new HashMap<String, String>();
			Document doc = Jsoup.parse(content); //轉成網頁結構
//			System.out.println(doc.text());
			Elements lis = doc.select("div");
			lis = lis.select(".kCrYT");
//			System.out.println(lis);
			int count=0;
			for(Element li : lis) {
				try {
					System.out.println(li.select("a").get(0));
					count++;
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
