package oracletest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class oracleCustomerTotal {

	public static void main(String[] args) {

		oracleCustomerSub ocs = new oracleCustomerSub();
		
		
		while (true) {
			
			Connection conn = null;
			conn = ocs.DBconnect();
			
		System.out.println("-------------------");
		System.out.println("1.고객정보 입력/조회             ");
		System.out.println("2.고객정보 수정/조회             ");
		System.out.println("3.고객정보 삭제/조회             ");
		System.out.println("4.고객정보조회 프로그램             ");
		System.out.println("5.고객번호로 조회              ");
		System.out.println("6.고객정보 선택수정            ");
		System.out.println("7.고객정보 프로그램 종료             ");
		System.out.println("-------------------");

		Scanner sc = new Scanner(System.in);
		int menu = sc.nextInt();		
			switch (menu) {
			case 1:
				ocs.insert(conn);
				ocs.select(conn);
				break;
			case 2:
				ocs.update(conn);
				ocs.select(conn);
				break;
			case 3:
				ocs.delete(conn);
				ocs.select(conn);
				break;
			case 4:
				ocs.select(conn);
				break;
			case 5:
				ocs.selectOne(conn);
				break;
			case 6:
				ocs.updateOne(conn);
				break;
			case 7:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("메뉴를 다시 입력해 주세요.");
			}
		}
	}

}
