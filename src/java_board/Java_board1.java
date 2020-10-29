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
			System.out.println("명령어 입력 : ");
			String cmd = sc.next();

			if (cmd.equals("exit")) {
				System.out.println("명령어 종료");
				break;
			}
			if (cmd.equals("add")) {

				Article a = new Article();

				System.out.println("게시물 제목을 입력해주세요 : ");
				String title = sc.next();
				a.setTitle(title);

				System.out.println("게시물 내용을 입력해주세요 : ");
				String body = sc.next();
				a.setBody(body);
				a.setNickname("익명");

				articledao.insertArticle(a);
				System.out.println("게시물이 저장되었습니다.");
			}
			if (cmd.equals("list")) {
				ArrayList<Article> articles = articledao.getArticles();

				printArticles(articles);
			}
			if (cmd.equals("update")) {
				System.out.println("수정할 게시물 선택  : ");
				int targetId = sc.nextInt();
				Article target = articledao.getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.println("게시물 제목을 입력해주세요 : ");
					String newTitle = sc.next();
					System.out.println("게시물 내용을 입력해주세요 : ");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);

				}
				System.out.println(targetId + "번 게시물이 수정되었습니다");
			}
			if (cmd.equals("delete")) {
				ArrayList<Article> articles = articledao.getArticles();
				System.out.println("삭제할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articledao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					articledao.removeArticle(target);
				}
			}
			if (cmd.equals("read")) {
				System.out.println("상세보기할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articledao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);

					while (true) {
						System.out.println("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로)");
						int readCmd = sc.nextInt();
						if (readCmd == 1) {
							Reply r = new Reply();

							System.out.println("댓글 내용을 입력해주세요 : ");
							String body = sc.next();
							r.setParentId(target.getId());
							r.setBody(body);
							r.setNickname("익명");

							replyDao.insertReply(r);
							System.out.println("댓글이 등록되었습니다");
							printArticle(target);

						} else if (readCmd == 2) {
							System.out.println("좋아요 기능");
						} else if (readCmd == 3) {
							System.out.println("수정 기능");
						} else if (readCmd == 4) {
							System.out.println("삭제 기능");
						} else if (readCmd == 5) {
							break;
						}
					}
				}
			}
			if (cmd.equals("search")) {
				System.out.println(" 검색 항목을 (1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자)");
				int flag = sc.nextInt();
				System.out.println("검색 키워드를 입력해주세요 : ");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles;

				searchedArticles = articledao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
			if (cmd.equals("signup")) {
				System.out.println("=======회원가입을 진행합니다.=======");
				Member m = new Member();

				System.out.println("아이디를 입력해주세요 : ");
				String id = sc.next();
				m.setLoginId(id);

				System.out.println("비밀번호를 입력해주세요 : ");
				String pw = sc.next();
				m.setLoginPw(pw);
				m.setNickname("익명");
				
				System.out.println("닉네임을 입력해주세요 : ");
				String nick = sc.next();
				m.setNickname(nick);
				

				memberDao.insertMember(m);
				System.out.println("=====회원가입이 완료되었습니다=====");
			}
		}
	}

	private static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("============================");

		}
	}

	private static void printReply(ArrayList<Reply> replyList) {
		for (int i = 0; i < replyList.size(); i++) {
			Reply reply = replyList.get(i);
			System.out.println("댓글내용 : " + reply.getBody());
			System.out.println("작성자 : " + reply.getNickname());
			System.out.println("등록날짜 : " + reply.getRegDate());
			System.out.println("=======================");

		}
	}

	private static void printArticle(Article target) {
		System.out.println("====" + target.getId() + "====");
		System.out.println("번호 : " + target.getId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getRegDate());
		System.out.println("조회수 : " + target.getHit());
		System.out.println("========================");
		System.out.println("==========댓글=========");

		ArrayList<Reply> replies = replyDao.getRepliesByParentId(target.getId());
		printReply(replies);
	}
}
