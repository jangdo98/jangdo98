package java_board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	private ArrayList<Member> members;
	private int num = 4;

	public MemberDao() {
		members = new ArrayList<>();
//		Article a1 = new Article(1, "�ȳ��ϼ���1", "����1", "�͸�1", getCurrentDate());
//		Article a2 = new Article(2, "�ݰ����ϴ�2", "����2", "�͸�2", getCurrentDate());
//		Article a3 = new Article(3, "�ȳ�3", "����3", "�͸�3", getCurrentDate());
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

	public Member getMemberByLoginIdAndLoginPw(String id, String pw) {

		for (int i = 0; i < members.size(); i++) {
			Member m = members.get(i);
			if (m.getLoginId().equals(id) && m.getLoginPw().equals(pw)) {
				return m;
			}
		}

		return null;

	}

	public static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}
}
