package oracletest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class oracleCustomerSub {

	static Connection connect;// 연결 역할을 하는 클래스
	static PreparedStatement pstmt = null;// 명령 실행
	static ResultSet rs = null;
	static Connection conn;
	private static String ino;
	private int cust_no;
	private String cust_name;
	private String cust_email;
	private String cust_address;
	private String cust_telphone;

	public Connection DBconnect() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "miya", "miya");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connect;
	}

	public void insert(Connection conn) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("----고객정보 입력/조회------");

		System.out.println("고객 번호를 입력하세요.");
		try {
			ino = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("고객번호 입력하는데 문제가 발생하였습니다.");
		}
		cust_no = Integer.parseInt(ino); // 학번 입력

		System.out.println("고객이름을 입력하세요.");
		try {
			cust_name = br.readLine(); // 이름 입력
		} catch (IOException e1) {
			System.out.println("고객이름을 입력하는데 문제가 발생하였습니다.");
		}

		System.out.println("고객 전자우편을 입력하세요.");
		try {
			cust_email = br.readLine();
		} catch (IOException e1) {
			System.out.println("고객 전자우편을 입력하는데 문제가 발생하였습니다.");
		}

		System.out.println("고객 주소를 입력하세요.");
		try {
			cust_address = br.readLine(); // 반 입력
		} catch (IOException e1) {
			System.out.println("고객 주소를 입력하는데 문제가 발생하였습니다.");
		}
		System.out.println("고객 연락처를 입력하세요");
		try {
			cust_telphone = br.readLine(); // 반 입력
		} catch (IOException e1) {
			System.out.println("고객 연락처를 입력하는데 문제가 발생하였습니다.");
		}
		String sql = "insert into customer(cust_no, cust_name, cust_email, cust_address,cust_telphone)"
				+ "values(?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cust_no);
			pstmt.setString(2, cust_name);
			pstmt.setString(3, cust_email);
			pstmt.setString(4, cust_address);
			pstmt.setString(5, cust_telphone);
			pstmt.executeUpdate();// insert, delete, update 공용으로 사용합니다.
			System.out.println("데이터를 성공적으로 입력 하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터를 입력하는데 실패 하였습니다.");
		}
	}

	public void update(Connection conn) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sql = "update customer set cust_name = ?,cust_email =?,cust_address=?,cust_telphone=? where cust_no=?";
		System.out.println("-----고객 정보 수정 -----");

		try {
			System.out.println("수정할 고객번호를 입력하세요");
			String ino = br.readLine();
			cust_no = Integer.parseInt(ino);
		} catch (IOException ioe) {
			System.out.println("고객번호를 입력하는데 문제가 발생하였습니다.");
		}
		try {
			System.out.println("수정할 고객이름을 입력하세요");

			cust_name = br.readLine();

		} catch (IOException e1) {
			System.out.println("고객이름을 수정하는데 문제가 발생하였습니다.");
		}
		try {
			System.out.println("수정할 고객 전자우편을 입력하세요");
			cust_email = br.readLine();

		} catch (IOException e1) {
			System.out.println("전자우편 수정하는데 문제가 발생하였습니다.");
		}
		try {
			System.out.println("수정할 주소를 입력하세요");
			cust_address = br.readLine();
		} catch (IOException e1) {
			System.out.println("주소를 수정하는데 문제가 발생하였습니다.");
		}
		try {
			System.out.println("수정할 연락처를 입력하세요");
			cust_telphone = br.readLine();
		} catch (IOException e1) {
			System.out.println("연락처를 수정 하는데 문제가 발생하였습니다.");
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cust_name);
			pstmt.setString(2, cust_email);
			pstmt.setString(3, cust_address);
			pstmt.setString(4, cust_telphone);
			pstmt.setInt(5, cust_no);
			pstmt.executeUpdate();
			System.out.println("데이터 수정 성공");
		} catch (SQLException e) {
			System.out.println("수정에 실패하였습니다.");
		}
	}

	public void select(Connection conn) {
		try {
			String sql = "select * from customer order by cust_no";

			pstmt = connect.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out
					.println("--------------------------------------고객정보 조회-----------------------------------------");
			System.out.println("고객번호" + "\t" + "고객이름" + "\t" + "전자우편" + "\t\t\t" + "주소" + "\t\t\t" + "연락처");
			System.out.println(
					"----------------------------------------------------------------------------------------");

			while (rs.next()) {
				int cno = rs.getInt("cust_no");
				String cname = rs.getString("cust_name");
				String cemail = rs.getString("cust_email");
				String caddress = rs.getString("cust_address");
				String ctelphone = rs.getString("cust_telphone");

				System.out.println(cno + "\t" + cname + "\t" + cemail + "\t\t" + caddress + "\t\t" + ctelphone);

			}
			System.out.println(
					"------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			System.out.println("돌발상황이 발생하였습니더. 확인하시고 조치바랍니다.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delete(Connection conn) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("------고객정보 삭제-------");
		System.out.println("삭제할 고객의 번호를 입력해주세요");
		try {
			ino = br.readLine();
			cust_no = Integer.parseInt(ino);
			String sql = "delete from customer where cust_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cust_no);
			pstmt.executeUpdate();
			System.out.println("삭제 완료");
		} catch (IOException e1) {
			System.out.println("삭제 실패");
		} catch (SQLException e2) {
			System.out.println("삭제 실패");
		}
	}

	public void selectOne(Connection conn) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String sql = "select * from customer where cust_no = ?";

			System.out.println("검색하실 고객번호를 입력하세요");
			ino = br.readLine();
			cust_no = Integer.parseInt(ino);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cust_no);
			rs = pstmt.executeQuery();

			System.out
					.println("--------------------------------------고객정보 조회-----------------------------------------");
			System.out.println("고객번호" + "\t" + "고객이름" + "\t" + "전자우편" + "\t\t\t" + "주소" + "\t\t\t" + "연락처");
			System.out.println(
					"----------------------------------------------------------------------------------------");

			while (rs.next()) {
				int cno = rs.getInt("cust_no");
				String cname = rs.getString("cust_name");
				String cemail = rs.getString("cust_email");
				String caddress = rs.getString("cust_address");
				String ctelphone = rs.getString("cust_telphone");

				System.out.println(cno + "\t" + cname + "\t" + cemail + "\t\t" + caddress + "\t\t" + ctelphone);

			}
			System.out.println(
					"------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			System.out.println("돌발상황이 발생하였습니더. 확인하시고 조치바랍니다.");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateOne(Connection conn) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("-----고객정보 선택수정 -----");

			System.out.println("1.이름 수정");
			System.out.println("2.전자우편 수정");
			System.out.println("3.주소 수정");
			System.out.println("4.연락처 수정");
			System.out.println("5.고객정보 수정종료");

			int call = sc.nextInt();
			if (call == 5) {
				System.out.println("수정을 종료합니다.");
				break;
			}
			try {
				System.out.println("수정할 고객번호를 입력하세요");
				ino = br.readLine();
				cust_no = Integer.parseInt(ino);
			} catch (IOException ioe) {
				System.out.println("입력 문제가 발생하였습니다.");
			}
			if (call == 1) {
				String sql = "update customer set cust_name=? where cust_no =?";
				try {
					System.out.println("새로운 이름을 입력하세요");
					cust_name = br.readLine();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cust_name);
					pstmt.setInt(2, cust_no);
					pstmt.executeUpdate();
					System.out.println("이름 수정이 완료되었습니다.");
				} catch (IOException e1) {
					System.out.println("이름을 수정하는데 문제가 발생하였습니다.");
				} catch (SQLException e2) {
					System.out.println("sql 이름 수정 오류");
				}
			} else if (call == 2) {
				String sql = "update customer set cust_email=? where cust_no =?";

				try {
					System.out.println("수정할 전자우편을 입력하세요");
					cust_email = br.readLine();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cust_email);
					pstmt.setInt(2, cust_no);
					pstmt.executeUpdate();
					System.out.println("전자우편 수정이 완료되었습니다.");
				} catch (IOException e1) {
					System.out.println("전자우편을 수정하는데 문제가 발생하였습니다.");
				} catch (SQLException e2) {
					System.out.println("sql 전자우편 수정하는데 문제가 발생하였습니다.");
				}
			} else if (call == 3) {
				String sql = "update customer set cust_address = ? where cust_no =?";
				try {
					System.out.println("수정할 주소를 입력하세요");
					cust_address = br.readLine();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cust_address);
					pstmt.setInt(2, cust_no);
					pstmt.executeUpdate();
					System.out.println("주소 수정이 완료되었습니다.");
				} catch (IOException e1) {
					System.out.println("주소를 수정하는데 문제가 발생하였습니다.");
				} catch (SQLException e2) {
					System.out.println("sql 주소 수정하는데 문제가 발생하였습니다.");
				}
			} else if (call == 4) {
				String sql = "update customer set cust_telphone=? where cust_no =?";
				try {
					System.out.println("수정할 연락처를 입력하세요");
					cust_telphone = br.readLine();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, cust_telphone);
					pstmt.setInt(2, cust_no);
					pstmt.executeUpdate();
					System.out.println("연락처 수정이 완료되었습니다.");
				} catch (IOException e1) {
					System.out.println("연락처를 수정하는데 문제가 발생하였습니다.");
				} catch (SQLException e2) {
					System.out.println("sql 연락처를 수정하는데 문제가 발생하였습니다.");
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 시도해주세요");
			}
		}
	}
}
