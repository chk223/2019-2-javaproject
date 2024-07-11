package DoYouWantChristmasPresent;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Stage3 extends JPanel {

	static int max_movement3 = 75;// 총 이동횟수 제한(상한)
	private int emptysocks_count = 5;// 빈 양말의 개수, 이 개수가 0개가 되면 스테이지가 클리어되고, 게임이 종료메세지 출력
	// 초기 맵 변수 설정
	private static int mapArr[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 3, 0, 0 }, { 0, 1, 3, 1, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 1, 1, 0, 1, 0, 0 }, { 0, 4, 1, 1, 3, 1, 3, 1, 1, 0 }, { 0, 4, 4, 1, 1, 3, 1, 1, 1, 0 },
			{ 0, 4, 4, 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	// mapArr은 값이 바뀌기 때문에 초기화를 위해 맵 초기 변수를 저장해 둠
	private static int init_mapArr[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 3, 0, 0 }, { 0, 1, 3, 1, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 1, 1, 0, 1, 0, 0 }, { 0, 4, 1, 1, 3, 1, 3, 1, 1, 0 }, { 0, 4, 4, 1, 1, 3, 1, 1, 1, 0 },
			{ 0, 4, 4, 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	// 구조 4 = 양말 / 3 = 선물 / 1 = 빈공간 / 0 = 벽

	private static ImageIcon image; // 이미지를 라벨에 삽입하기 위한 이미지 아이콘
	private static JLabel lblpixel[][], lblSanta, lblPresent[];// lblpixel은 선물, 산타를 제외한 모든 맵 구성, lblSanta는 산타만을 표시,
	// lblPresent는 선물을 저장
	private static int x, y, s_x, s_y, present_count;// x,y는 맵 구성시 좌표를 설정하기 위한 변수, s_x, s_y는 산타의 좌표를 설정, present_count는
	// 선물의 개수를 설정하거나 재구성할때 사용하는 변수(몇번째 선물인지 카운팅)
	static int p_x[], p_y[];// 선물의 x,y좌표 & 배열의 인덱스는 present_count가 주로 오며 몇번째 선물인지를 뜻함
	static PanelThread nowGauge; // 현재 체력을 보여주기 위해 깜빡이는 기능을 넣는 PanelThread의 변수 nowGauge

	public Stage3() {
		setBounds(50, 50, 500, 500); // 맵 크기를 500,500으로 설정, (50,50)부터 오른쪽 아래로 맵 구성
		setBackground(Color.white); // 맵 배경색을 흰색으로 설정 -> 후에 '땅'인 경우의 색이 됨
		setFocusable(true);// 키 리스너를 받을 수 있게끔 설정
		addKeyListener(new KeyboardListener()); // 키 리스너 추가
		setLayout(null);// 레이아웃 매니저동작을 null 시킴
		MainPanel.movecount = 0;// 메인에 있는 movecount(이동횟수)를 0으로 초기화
		nowGauge = new PanelThread(MainPanel.HP);
		nowGauge.setVisible(true);
		lblpixel = new JLabel[10][10];// 맵구성(선물, 산타 제외)을 담당할 라벨배열 10x10(맵크기)설정
		p_x = new int[5];// [n]번째 선물의 x좌표
		p_y = new int[5];// [n]번째 선물의 y좌표
		lblPresent = new JLabel[5]; // [n]번째 선물의 라벨
		present_count = 0; // 선물 개수 카운팅 변수 초기화
		PaintGame(); // 게임 화면 구성
		Santa_paint(); // 산타(캐릭터) 배치
	}// constructor

	public void PaintGame() {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				switch (mapArr[i][j]) {
				case 0: // 벽
					x = j * 50;
					y = i * 50;
					image = new ImageIcon("image/wall.png");
					lblpixel[i][j] = new JLabel(image);
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
					break;
				case 1: // 땅
					x = j * 50;
					y = i * 50;
					lblpixel[i][j] = new JLabel();
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
					break;
				case 2: // 산타
					break;
				case 3: // 선물
					p_y[present_count] = i;
					p_x[present_count] = j;
					Present_Paint(p_x[present_count], p_y[present_count], present_count);
					present_count++;
					break;
				case 4: // 양말
					x = j * 50;
					y = i * 50;
					image = new ImageIcon("image/sock.png");
					lblpixel[i][j] = new JLabel(image);
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
					break;
				case 5: // 양말에 선물 들어감
					x = j * 50;
					y = i * 50;
					image = new ImageIcon("image/sockinpre.png");
					lblpixel[i][j] = new JLabel(image);
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
				}
			} // for(j)
		} // for(i)

	}

	public void InitStage3() { // 스테이지 초기화 함수

		// 산타 초기화
		lblSanta.setIcon(null); // 기존에 얹어져 있던 이미지 아이콘을 전부 null 처리
		// 선물 초기화
		for (int i = 0; i < 4; i++) {
			lblPresent[i].setIcon(null);// 기존에 얹어져 있던 이미지 아이콘을 전부 null 처리
		}
		// 맵 전체에서 양말 & 선물이 들어가있는 양말 초기화
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (init_mapArr[i][j] == 4 || init_mapArr[i][j] == 5) {
					// 기존에 얹어져 있던 이미지 아이콘을 전부 null 처리
					lblpixel[i][j].setIcon(null);
				}
			} // for(j)
		} // for(i)
		Santa_Repaint(); // 산타를 다시 그려줌(초기화)
		present_count = 0; // 선물 카운팅 변수도 초기화
		emptysocks_count = 5; // 빈 양말 개수도 초기값으로 설정
		for (int i = 0; i < 10; i++) { // 초기 맵 변수(init_mapArr)을 이용해 mapArr(맵 구성 변수) 초기화시킴
			for (int j = 0; j < 10; j++) {
				mapArr[i][j] = init_mapArr[i][j];
			} // for(j)
		} // for(i)
			// 전체 맵에서 선물과 양말을 다시 그려줌 (벽은 정보가 바뀌지 않기 때문에 그대로 둠)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mapArr[i][j] == 3) { // 맵 정보가 3이면 (선물이면)
					// 선물 좌표 설정
					p_x[present_count] = j;
					p_y[present_count] = i;
					// 선물 라벨의 이미지아이콘을 null->image로 대체
					MovePresent(j, i, present_count);
					present_count++; // 선물 카운팅 변수 증가
				} else if (mapArr[i][j] == 4) { // 양말 배치
					image = new ImageIcon("image/Sock.png");
					lblpixel[i][j].setIcon(image);
				}
			} // for(j)
		} // for(i)

		MainPanel.GaugeViewPanel.removePaintGauge(MainPanel.beforeMaxMovement); // beforeMaxMovement만큼 Gauges[]를 remove
		MainPanel.movecount = 0; // 이동 횟수를 0으로 초기화
		MainPanel.HP = max_movement3 - MainPanel.movecount; // 현재 HP를 초기화
		GaugePanel.remainingCount.setText("" + MainPanel.HP); // 남은 이동 횟수를 타나내 주는 remainingCount(JLabel)를 초기화
		MainPanel.GaugeViewPanel.paintComponent(MainPanel.HP); // HP만큼 Gauge[]를 생성해주는 함수 호출
		MainPanel.GaugeViewPanel.hpPanel.repaint(); // hpPanel을 갱신해주기 위해
		nowGauge.initInstanceDatas(MainPanel.HP); // Thread작동을 초기화해주기 위해
		nowGauge.start(); // 스레드 작동
		MainPanel.beforeMaxMovement = max_movement3; // 다음 스테이지로 넘어갈 때, removePaintGauge의 parameter로 넘겨주기 위해 저장
	}

	public void Santa_paint() {
		// 산타를 그리는(배치하는) 함수
		// 산타의 좌표 설정
		s_x = 7;
		s_y = 7;
		// 이미지 아이콘을 산타 전용 lblSanta에 얹고 배치시킴
		image = new ImageIcon("image/Santa.png");
		lblSanta = new JLabel(image);
		lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50);
		add(lblSanta);
	}

	public void Santa_Repaint() {
		// 산타 위치를 초기화시킬때 쓰는 함수
		// 산타가 나타날 좌표 설정
		s_x = 7;
		s_y = 7;
		// lblSanta의 이미지를 산타 이미지로 교체
		image = new ImageIcon("image/Santa.png");
		lblSanta.setIcon(image);
		lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50);
		add(lblSanta);
	}

	public void insert_Present_in_Sock(int x, int y) {
		// 양말 안에 선물을 넣는 함수(이미지 교체)
		image = new ImageIcon("image/PresentinSock.png");
		lblpixel[y][x].setIcon(image); // setIcon을 통해 이미지를 바꿔줌
		lblpixel[y][x].setBounds(x * 50, y * 50, 50, 50); // 배치
		emptysocks_count--; // 빈 양말카운팅하는 변수의 수를 하나 줄여줌 ( 이 숫자가 0이 되는 것이 스테이지 클리어 조건)
		add(lblpixel[y][x]);
	}

	public void remove_Present_from_Sock(int x, int y) { // 양말에 들어있는 선물을 빼는 함수(선물든 양말 -> 빈 양말)
		image = new ImageIcon("image/Sock.png");
		// 다른 함수들과 같은 방법으로 이미지를 교체하고 배치
		lblpixel[y][x].setIcon(image);
		lblpixel[y][x].setBounds(x * 50, y * 50, 50, 50);
		emptysocks_count++; // 양말에서 선물을 빼내었기 때문에 빈 양말의 수는 다시 증가
		add(lblpixel[y][x]);
	}

	public void Take_out_Present_from_Sock(int x, int y) { // 양말에 들어있는 선물을 빼서 바깥으로 내보내주는 함수
		// 선물 든 양말에서 꺼낼 곳(땅) 의 좌표를 x,y에 받아 와서 없어진 선물 중 아무거나 가져와서 그 i번째 선물에 좌표를 설정 후 선물
		// 이미지를 설정해서 선물을 배치하는 함수
		for (int i = 0; i < 5; i++) {
			if (mapArr[p_y[i]][p_x[i]] != 3) { // 없어진 선물 탐색
				p_y[i] = y;
				p_x[i] = x;
				MovePresent(x, y, i); // 선물 배치
				break;
			}
		}
	}

	public void Present_Paint(int x, int y, int present_cnt) {
		// 초기에 선물을 그려주는 함수 이미지 아이콘을 present_cnt번째 선물 전용 라벨에 얹고 배치시킴
		image = new ImageIcon("image/present.png");
		lblPresent[present_cnt] = new JLabel(image);
		lblPresent[present_cnt].setBounds(x * 50, y * 50, 50, 50);
		add(lblPresent[present_cnt]);
	}

	public void RemovePresent(int x, int y, int present_cnt) {
		// 선물을 양말안에 넣을때 해당 선물을 지워주는 역할
		lblPresent[present_cnt].setIcon(null);
		lblPresent[present_cnt].setBounds(x * 50, y * 50, 50, 50);
		add(lblPresent[present_cnt]);
	}

	public void MovePresent(int x, int y, int present_cnt) {
		// 선물 이미지로 라벨을 교체(배치)할때 사용, 선물을 양말 바깥으로 뺄 때 등(초기 설정 이외에는 이 함수를 사용해서 선물을 그린다)
		image = new ImageIcon("image/present.png");
		lblPresent[present_cnt].setIcon(image);
		lblPresent[present_cnt].setBounds(x * 50, y * 50, 50, 50);
		add(lblPresent[present_cnt]);
	}

	public class KeyboardListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (mapArr[s_y - 1][s_x] == 0) { // 산타 위가 벽이면 이동 불가}
				} else if (mapArr[s_y - 1][s_x] == 3) { // 위에 선물이 있다면
					for (present_count = 0; present_count < 4; present_count++) { // 위에 있는 선물이 어떤 선물인지(몇 번째 선물인지) 탐색
						if (p_y[present_count] == s_y - 1 && p_x[present_count] == s_x) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count] - 1][p_x[present_count]] == 0) { // 선물 위가 벽이면
					} // if
					else if (mapArr[p_y[present_count] - 1][p_x[present_count]] == 1) { // 선물 위가 땅이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						p_y[present_count] -= 1; // 선물 좌표 한칸 위로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // 선물 옮길곳의 인덱스 3(선물)
						MovePresent(p_x[present_count], p_y[present_count], present_count); // 선물 옮기는 함수
						s_y -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동회수 증가
					} else if (mapArr[p_y[present_count] - 1][p_x[present_count]] == 4) { // 선물 위가 양말이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						RemovePresent(p_x[present_count], p_y[present_count], present_count); // 선물을 양말안에 넣을때 해당 선물을
						// 지워주는 함수
						p_y[present_count] -= 1; // 선물 좌표 한칸 위로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // 선물 옮길곳의 인덱스 5(선물든 양말)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]); // 양말에 선물 넣는 함수
						s_y -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동회수 증가
					}
				} else if (mapArr[s_y - 1][s_x] == 5) { // 산타 위에 선물 든 양말이 있으면
					if (mapArr[s_y - 2][s_x] == 4) { // 그 위가 양말이라면
						mapArr[s_y - 2][s_x] = 5; // 맵 정보 갱신
						mapArr[s_y - 1][s_x] = 4; // 맵 정보 갱신
						// 다른 양말로 선물을 옮김
						remove_Present_from_Sock(s_x, s_y - 1);// 선물 빼기
						insert_Present_in_Sock(s_x, s_y - 2); // 선물 넣기
						s_y -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동회수 증가
					} else if (mapArr[s_y - 2][s_x] == 1) { // 그 위가 땅이라면
						mapArr[s_y - 2][s_x] = 5; // 맵 정보 갱신
						mapArr[s_y - 1][s_x] = 4; // 맵 정보 갱신
						remove_Present_from_Sock(s_x, s_y - 1);// 선물 빼는 함수
						Take_out_Present_from_Sock(s_x, s_y - 2); // 선물 빼는 함수
						s_y -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동회수 증가
					}
				} else {// 나머지 경우(땅일 경우)
					MainPanel.movecount++; // 이동회수 증가
					s_y -= 1; // 산타 위치 변경
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(남은 이동 횟수) 갱신
				if (MainPanel.HP >= 0) { // HP가 남았을 때
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread 갱신
					nowGauge.start(); // Thread 작동
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // 소모된 체력을 없애주기 위해 removeRed method 호출
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // 남은 이동 횟수를 보여주는 remainingCount 갱신
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // 이동한 산타의 위치를 바꿔줌
				} else { // 남은 이동 횟수가 없을 때
					GaugePanel.remainingCount.setText("" + 0); // 남은 이동 횟수를 보여주는 remainingCount 0으로 설정
					GaugePanel.removeRed(max_movement3, max_movement3); // 체력을 모두 지워주기 위해 removeRed method 호출
				}
				break;
			case KeyEvent.VK_DOWN: // 키보드 아래 방향키 눌리면
				if (mapArr[s_y + 1][s_x] == 0) { // 산타 아래가 벽이면 이동 불가
				} else if (mapArr[s_y + 1][s_x] == 3) { // 아래에 선물이 있다면
					for (present_count = 0; present_count < 4; present_count++) { // 아래에 있는 선물이 어떤 선물인지 탐색
						if (p_y[present_count] == s_y + 1 && p_x[present_count] == s_x) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count] + 1][p_x[present_count]] == 0) { // 선물 아래가 벽이면
					} // if
					else if (mapArr[p_y[present_count] + 1][p_x[present_count]] == 1) { // 선물 아래가 땅이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						p_y[present_count] += 1; // 선물 좌표 한칸 아래로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // 선물 옮길곳의 인덱스 3(선물)
						MovePresent(p_x[present_count], p_y[present_count], present_count); // 선물 옮기는 함수
						s_y += 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					} else if (mapArr[p_y[present_count] + 1][p_x[present_count]] == 4) { // 선물 아래가 양말이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						RemovePresent(p_x[present_count], p_y[present_count], present_count);// 선물을 양말안에 넣을때 해당 선물을 지워주는
						// 함수
						p_y[present_count] += 1; // 선물 좌표 한칸 아래로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // 선물 옮길곳의 인덱스 5(선물든 양말)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]); // 양말에 선물을 넣는 함수
						s_y += 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					}
				} else if (mapArr[s_y + 1][s_x] == 5) { // 산타 아래에 선물 든 양말이 있으면
					if (mapArr[s_y + 2][s_x] == 4) { // 그 아래가 양말이라면
						mapArr[s_y + 2][s_x] = 5; // 맵 정보 갱신
						mapArr[s_y + 1][s_x] = 4; // 맵 정보 갱신
						// 다른 양말로 선물을 옮김
						remove_Present_from_Sock(s_x, s_y + 1);// 선물 빼기
						insert_Present_in_Sock(s_x, s_y + 2); // 선물 넣기
						s_y += 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					} else if (mapArr[s_y + 2][s_x] == 1) { // 그 아래가 땅이라면
						mapArr[s_y + 2][s_x] = 3; // 맵 정보 갱신
						mapArr[s_y + 1][s_x] = 4; // 맵 정보 갱신
						remove_Present_from_Sock(s_x, s_y + 1);// 선물 빼기
						Take_out_Present_from_Sock(s_x, s_y + 2); // 선물이 들어있는 양말에서 선물을 빼는 함수
						s_y += 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					}
				} else {// 나머지 경우(땅일 경우)
					MainPanel.movecount++; // 이동횟수 증가
					s_y += 1; // 산타 위치 변경
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(남은 이동 횟수) 갱신
				if (MainPanel.HP >= 0) { // HP가 남았을 때
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread 갱신
					nowGauge.start(); // Thread 작동
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // 소모된 체력을 없애주기 위해 removeRed method 호출
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // 남은 이동 횟수를 보여주는 remainingCount 갱신
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // 이동한 산타의 위치를 바꿔줌
				} else { // 남은 이동 횟수가 없을 때
					GaugePanel.remainingCount.setText("" + 0); // 남은 이동 횟수를 보여주는 remainingCount 0으로 설정
					GaugePanel.removeRed(max_movement3, max_movement3); // 체력을 모두 지워주기 위해 removeRed method 호출
				}
				break;
			case KeyEvent.VK_LEFT:
				if (mapArr[s_y][s_x - 1] == 0) {// 산타 왼쪽이 벽이면 이동 불가
				} else if (mapArr[s_y][s_x - 1] == 3) { // 왼쪽에 선물이 있다면
					for (present_count = 0; present_count < 4; present_count++) { // 왼쪽에 있는 선물이 어떤 선물인지 탐색
						if (p_y[present_count] == s_y && p_x[present_count] == s_x - 1) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count]][p_x[present_count] - 1] == 0) { // 선물 왼쪽이 벽이면
					} // if
					else if (mapArr[p_y[present_count]][p_x[present_count] - 1] == 1) { // 선물 왼쪽이 땅이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						p_x[present_count] -= 1; // 선물 좌표 한칸 위로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // 선물 옮길곳의 인덱스 3(선물)
						MovePresent(p_x[present_count], p_y[present_count], present_count); // 선물 옮기는 함수
						s_x -= 1; // 산타 위치 변경
						MainPanel.movecount++;// 이동횟수 증가
					} else if (mapArr[p_y[present_count]][p_x[present_count] - 1] == 4) { // 선물 왼쪽이 양말이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						RemovePresent(p_x[present_count], p_y[present_count], present_count); // 선물을 양말안에 넣을때 해당 선물을
						// 지워주는 함수
						p_x[present_count] -= 1; // 선물 좌표 한칸 왼쪽으로로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // 선물 옮길곳의 인덱스 5(선물든 양말)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]); // 양말에 선물을 넣는 함수
						s_x -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					}
				} else if (mapArr[s_y][s_x - 1] == 5) { // 산타 왼쪽에 선물 든 양말이 있으면
					if (mapArr[s_y][s_x - 2] == 4) { // 그 왼쪽이 양말이라면
						mapArr[s_y][s_x - 2] = 5; // 맵 정보 갱신
						mapArr[s_y][s_x - 1] = 4; // 맵 정보 갱신
						// 다른 양말로 선물을 옮김
						remove_Present_from_Sock(s_x - 1, s_y);// 선물 빼기
						insert_Present_in_Sock(s_x - 2, s_y); // 선물 넣기
						s_x -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					} else if (mapArr[s_y][s_x - 2] == 1) { // 그 왼쪽이 땅이라면
						mapArr[s_y][s_x - 2] = 3; // 맵 정보 갱신
						mapArr[s_y][s_x - 1] = 4; // 맵 정보 갱신
						remove_Present_from_Sock(s_x - 1, s_y);// 선물 빼기
						Take_out_Present_from_Sock(s_x - 2, s_y); // 선물든 양말에서 선물을 빼는 함수
						s_x -= 1; // 산타 위치 변경
						MainPanel.movecount++; // 이동횟수 증가
					}
				} else {// 나머지 경우(땅일 경우)
					MainPanel.movecount++; // 이동횟수 증가
					s_x -= 1; // 산타 위치 변경
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(남은 이동 횟수) 갱신
				if (MainPanel.HP >= 0) { // HP가 남았을 때
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread 갱신
					nowGauge.start(); // Thread 작동
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // 소모된 체력을 없애주기 위해 removeRed method 호출
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // 남은 이동 횟수를 보여주는 remainingCount 갱신
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // 이동한 산타의 위치를 바꿔줌
				} else { // 남은 이동 횟수가 없을 때
					GaugePanel.remainingCount.setText("" + 0); // 남은 이동 횟수를 보여주는 remainingCount 0으로 설정
					GaugePanel.removeRed(max_movement3, max_movement3); // 체력을 모두 지워주기 위해 removeRed method 호출
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (mapArr[s_y][s_x + 1] == 0) {// 산타 오른쪽이 벽이면 이동 불가
				} else if (mapArr[s_y][s_x + 1] == 3) { // 오른쪽에 선물이 있다면
					for (present_count = 0; present_count < 4; present_count++) { // 오른쪽에 있는 선물이 어떤 선물인지 탐색
						if (p_y[present_count] == s_y && p_x[present_count] == s_x + 1) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count]][p_x[present_count] + 1] == 0) { // 선물 오른쪽이 벽이면
					} // if
					else if (mapArr[p_y[present_count]][p_x[present_count] + 1] == 1) { // 선물 오른쪽이 땅이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						p_x[present_count] += 1; // 선물 좌표 한칸 위로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // 선물 옮길곳의 인덱스 3(선물)
						MovePresent(p_x[present_count], p_y[present_count], present_count);// 선물 옮기는 함수
						s_x += 1; // 산타 위치 변경
						MainPanel.movecount++;// 이동 횟수 증가
					} else if (mapArr[p_y[present_count]][p_x[present_count] + 1] == 4) { // 선물 오른쪽이 양말이면
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // 원래 선물위치 인덱스 1 (땅)
						RemovePresent(p_x[present_count], p_y[present_count], present_count);// 선물을 양말안에 넣을때 해당 선물을 지워주는
						// 함수
						p_x[present_count] += 1; // 선물 좌표 한칸 왼쪽으로로 이동
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // 선물 옮길곳의 인덱스 5(선물든 양말)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]);// 양말에 선물을 넣는 함수
						s_x += 1;// 산타 위치 변경
						MainPanel.movecount++;// 이동 횟수 증가
					}
				} else if (mapArr[s_y][s_x + 1] == 5) { // 산타 오른쪽에 선물 든 양말이 있으면
					if (mapArr[s_y][s_x + 2] == 4) { // 그 오른쪽이 양말이라면
						mapArr[s_y][s_x + 2] = 5; // 맵 정보 갱신
						mapArr[s_y][s_x + 1] = 4; // 맵 정보 갱신
						// 다른 양말로 선물을 옮김
						remove_Present_from_Sock(s_x + 1, s_y);// 선물 빼기
						insert_Present_in_Sock(s_x + 2, s_y); // 선물 넣기
						s_x += 1; // 산타 위치 변경
						MainPanel.movecount++;// 이동 횟수 증가
					} else if (mapArr[s_y][s_x + 2] == 1) { // 그 오른쪽이 땅이라면
						mapArr[s_y][s_x + 2] = 3; // 맵 정보 갱신
						mapArr[s_y][s_x + 1] = 4; // 맵 정보 갱신
						remove_Present_from_Sock(s_x + 1, s_y);// 선물 빼기
						Take_out_Present_from_Sock(s_x + 2, s_y);// 선물든 양말에서 선물을 빼는 함수
						s_x += 1; // 산타 위치 변경
						MainPanel.movecount++;// 이동 횟수 증가
					}
				} else { // 나머지 경우(땅일 경우)
					MainPanel.movecount++;// 이동 횟수 증가
					s_x += 1;// 산타 위치 변경
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(남은 이동 횟수) 갱신
				if (MainPanel.HP >= 0) { // HP가 남았을 때
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread 갱신
					nowGauge.start(); // Thread 작동
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // 소모된 체력을 없애주기 위해 removeRed method 호출
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // 남은 이동 횟수를 보여주는 remainingCount 갱신
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // 이동한 산타의 위치를 바꿔줌
				} else { // 남은 이동 횟수가 없을 때
					GaugePanel.remainingCount.setText("" + 0); // 남은 이동 횟수를 보여주는 remainingCount 0으로 설정
					GaugePanel.removeRed(max_movement3, max_movement3); // 체력을 모두 지워주기 위해 removeRed method 호출
				}
				break;
			}// switch

			if (MainPanel.HP < 0) {// 체력이 모두 소모되었을경우 재시작하는지 묻는 대화상자
				// 대화상자에서 Yes면 0, No나 상자를 닫으면 1 값을 받음
				int ans = JOptionPane.showConfirmDialog(MainPanel.S3, "실패.   다시하시겠습니까?", "실패",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (ans == 0) {// Yes
					InitStage3();// 초기화
					MainPanel.S3.requestFocus(true);
				} else {// No
					System.exit(0);// 게임종료
				}
			}

			if (emptysocks_count == 0) {// 게임 클리어시
				// 확인버튼만 있는 대화상자
				JOptionPane.showMessageDialog(MainPanel.S3, "축하합니다. 모든 스테이지를 클리어하셨습니다.", "성공!",
						JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}

		}// keyPressed

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	} // KeyboardListener class

}// Stage3 class