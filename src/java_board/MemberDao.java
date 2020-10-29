package java_board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	private ArrayList<Member> members;
	private int num = 4;

	public MemberDao() {
		members = new ArrayList<>();
//		Article a1 = new Article(1, "안녕하세요1", "내용1", "익명1", getCurrentDate());
//		Article a2 = new Article(2, "반갑습니다2", "내용2", "익명2", getCurrentDate());
//		Article a3 = new Article(3, "안녕3", "내용3", "익명3", getCurrentDate());
//
//		members.add(a1);
//		articles.add(a2);
//		articles.add(a3);
	}

	public void insertMember(Member m) {
		m.setId(num);
		num++;
		m.setRegDate(getCurrentDate());

		members.add(m);
	}
	public static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}
}
