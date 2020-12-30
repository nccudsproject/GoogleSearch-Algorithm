
public class webNode {
	private String urlString;
	private String title;
	private double Score;
	private int key; //存在arraylist中的位置位置
	
	public webNode(String urlString,String title) {
		// TODO Auto-generated constructor stub
		this.urlString=urlString;
		this.title=title;
	}
	
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getScore() {
		return Score;
	}
	public void setScore(double score) {
		Score = score;
	}
}
