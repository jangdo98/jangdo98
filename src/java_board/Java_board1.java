package java_board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Java_board1 {

	static MemberDao memberDao = new MemberDao();
	static ReplyDao replyDao = new ReplyDao();
	static ArticleDao articledao = new ArticleDao();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("��ɾ� �Է� : ");
			String cmd = sc.next();

			if (cmd.equals("exit")) {
				System.out.println("��ɾ� ����");
				break;
			}
			if (cmd.equals("add")) {

				Article a = new Article();

				System.out.println("�Խù� ������ �Է����ּ��� : ");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("�Խù� ������ �Է����ּ��� : ");
				String body = sc.next();
				a.setBody(body);
				a.setNickname("�͸�");

				articledao.insertArticle(a);
				System.out.println("�Խù��� ����Ǿ����ϴ�.");
			}
			if (cmd.equals("list")) {
				ArrayList<Article> articles = articledao.getArticles();

				printArticles(articles);
			}
			if (cmd.equals("update")) {
				System.out.println("������ �Խù� ����  : ");
				int targetId = sc.nextInt();
				Article target = articledao.getArticleById(targetId);
				if (target == null) {
					System.out.println("���� �Խù��Դϴ�.");
				} else {
					System.out.println("�Խù� ������ �Է����ּ��� : ");
					String newTitle = sc.next();
					System.out.println("�Խù� ������ �Է����ּ��� : ");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);

				}
				System.out.println(targetId + "�� �Խù��� �����Ǿ����ϴ�");
			}
			if (cmd.equals("delete")) {
				ArrayList<Article> articles = articledao.getArticles();
				System.out.println("������ �Խù� ���� : ");
				int targetId = sc.nextInt();
				Article target = articledao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					articledao.removeArticle(target);
				}
			}
			if (cmd.equals("read")) {
				System.out.println("�󼼺����� �Խù� ���� : ");
				int targetId = sc.nextInt();
				Article target = articledao.getArticleById(targetId);
				if (target == null) {
					System.out.println("�Խù��� �������� �ʽ��ϴ�.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

					while (true) {
						System.out.println("�󼼺��� ����� �������ּ���(1. ��� ���, 2. ���ƿ�, 3. ����, 4. ����, 5. �������)");
						int readCmd = sc.nextInt();
						if (readCmd == 1) {
							Reply r = new Reply();

							System.out.println("��� ������ �Է����ּ��� : ");
							String body = sc.next();
							r.setParentId(target.getId());
							r.setBody(body);
							r.setNickname("�͸�");

							replyDao.insertReply(r);
							System.out.println("����� ��ϵǾ����ϴ�");
							printArticle(target);

						} else if (readCmd == 2) {
							System.out.println("���ƿ� ���");
						} else if (readCmd == 3) {
							System.out.println("���� ���");
						} else if (readCmd == 4) {
							System.out.println("���� ���");
						} else if (readCmd == 5) {
							break;
						}
					}
				}
			}
			if (cmd.equals("search")) {
				System.out.println(" �˻� �׸��� (1. ����, 2. ����, 3. ���� + ����, 4. �ۼ���)");
				int flag = sc.nextInt();
				System.out.println("�˻� Ű���带 �Է����ּ��� : ");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles;

				searchedArticles = articledao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
			if (cmd.equals("signup")) {
				System.out.println("=======ȸ�������� �����մϴ�.=======");
				Member m = new Member();

				System.out.println("���̵� �Է����ּ��� : ");
				String id = sc.next();
				m.setLoginId(id);

				System.out.println("��й�ȣ�� �Է����ּ��� : ");
				String pw = sc.next();
				m.setLoginPw(pw);
				m.setNickname("�͸�");
				
				System.out.println("�г����� �Է����ּ��� : ");
				String nick = sc.next();
				m.setNickname(nick);
				

				memberDao.insertMember(m);
				System.out.println("=====ȸ�������� �Ϸ�Ǿ����ϴ�=====");
			}
		}
	}

	private static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("��ȣ : " + article.getId());
			System.out.println("���� : " + article.getTitle());
			System.out.println("��ϳ�¥ : " + article.getRegDate());
			System.out.println("�ۼ��� : " + article.getNickname());
			System.out.println("��ȸ�� : " + article.getHit());
			System.out.println("============================");

		}
	}

	private static void printReply(ArrayList<Reply> replyList) {
		for (int i = 0; i < replyList.size(); i++) {
			Reply reply = replyList.get(i);
			System.out.println("��۳��� : " + reply.getBody());
			System.out.println("�ۼ��� : " + reply.getNickname());
			System.out.println("��ϳ�¥ : " + reply.getRegDate());
			System.out.println("=======================");

		}
	}

	private static void printArticle(Article target) {
		System.out.println("====" + target.getId() + "====");
		System.out.println("��ȣ : " + target.getId());
		System.out.println("���� : " + target.getTitle());
		System.out.println("���� : " + target.getBody());
		System.out.println("��ϳ�¥ : " + target.getRegDate());
		System.out.println("��ȸ�� : " + target.getHit());
		System.out.println("========================");
		System.out.println("==========���=========");

		ArrayList<Reply> replies = replyDao.getRepliesByParentId(target.getId());
		printReply(replies);
	}
}
