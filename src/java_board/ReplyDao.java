package java_board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReplyDao {
	private ArrayList<Reply> replies;
	private int num = 1;

	public ReplyDao() {
		replies = new ArrayList<>();
//		Reply a1 = new Reply(1, "안녕하세요1", "내용1", "익명1", getCurrentDate());
//		Reply a2 = new Reply(2, "반갑습니다2", "내용2", "익명2", getCurrentDate());
//		Reply a3 = new Reply(3, "안녕3", "내용3", "익명3", getCurrentDate());
//
//		replies.add(a1);
//		replies.add(a2);
//		replies.add(a3);
	}

	public void insertReply(Reply a) {
		a.setId(num);
		num++;
		a.setRegDate(getCurrentDate());
		replies.add(a);
	}
//
//	public void removeArticle(Article a) {
//		articles.remove(a);
//	}
//
	public static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

//	public ArrayList<Article> getSearchedArticlesByFlag(int flag, String keyword) {
//
//		ArrayList<Article> searchedArticles = new ArrayList<>();
//
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//			String str = article.getPropertiesByFlag(flag);
//
//			if (str.contains(keyword)) {
//				searchedArticles.add(article);
//			}
//		}
//
//		return searchedArticles;
//
//	}
//
//	public static Article getArticleById(int targetId) {
//		for (int i = 0; i < articles.size(); i++) {
//			int id = articles.get(i).getId();
//			if (targetId == id) {
//				return articles.get(i);
//			}
//		}
//		return null;
//	}
//
	public ArrayList<Reply> getReplies() {
		return replies;
	}
	public ArrayList<Reply> getRepliesByParentId(int parentId){
		ArrayList<Reply> searchedReplies = new ArrayList<>();
		for (int i = 0; i <replies.size(); i++) {
			Reply reply = replies.get(i);
			if (reply.getParentId() == parentId) {
				searchedReplies.add(reply);
			}
		}
		return searchedReplies;
	}
}
