package java_board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleDao {

	private ArrayList<Article> articles;
	private int num = 4;

	public ArticleDao() {
		articles = new ArrayList<>();
		Article a1 = new Article(1, "안녕하세요1", "내용1", 1, getCurrentDate());
		Article a2 = new Article(2, "반갑습니다2", "내용2", 2, getCurrentDate());
		Article a3 = new Article(3, "안녕3", "내용3", 3, getCurrentDate());

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
	}

	public void insertArticle(Article a) {
		a.setId(num);
		num++;
		a.setRegDate(getCurrentDate());

		articles.add(a);
	}

	public void removeArticle(Article a) {
		articles.remove(a);
	}

	public static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

	public ArrayList<Article> getSearchedArticlesByFlag(int flag, String keyword) {

		ArrayList<Article> searchedArticles = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String str = article.getPropertiesByFlag(flag);
		
			if (str.contains(keyword)) {
				searchedArticles.add(article);
			}
		}

		return searchedArticles;

	}

	public Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (targetId == id) {
				return articles.get(i);
			}
		}
		return null;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

}