package DoYouWantChristmasPresent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	static Stage1 S1;// 스테이지1 패널 / 스태틱으로 선언하여 다른 클래스에서도 해당 객체를 사용할수 있게 해줌
	static Stage2 S2;// 스테이지2 패널
	static Stage3 S3;// 스테이지3 패널
	static int movecount = 0, HP = S1.max_movement1 - movecount; // 이동 횟수를 세주는 movement와 현재 HP를 나타내는 HP
	static int beforeMaxMovement = S1.max_movement1; // Stage를 이동할 때 이전 Stage의 제한 이동 횟수를 저장하는 beforeMaxMovement
	private ImageIcon BackImage, imgOpen, imgHowto;// 배경, 시작화면, 설명화면의 이미지를 불러오기 위한 변수
	private JPanel OpenPanel, HowtoPanel;// Open Howto 이미지를 올릴 패널
	private JLabel lblimgOpen, lblimgHowto;// Open Howto에 이미지를 올리기위해 필요한 레이블
	static ButtonListener buttonL;// 버튼리스너
	private Clip cBGM;// BGM
	private boolean bBGM;// 현재 BGM이 재생중인지 아닌지 알기위해
	static JButton btnBGM, btnStart, btnHowto, btnStage1, btnStage2, btnStage3, btnExit;// 게임진행을 위한 각 버튼들
	private Font ButtonFont = new Font("Verdana", Font.BOLD, 20);// 모든 버튼들에 사용될 폰트
	static GaugePanel GaugeViewPanel;// 산타의 남은 체력(게이지)를 나타낼 패널

	public MainPanel() {
		setPreferredSize(new Dimension(700, 650));// 화면 전체 크기
		setLayout(null);
		BackImage = new ImageIcon("Image/BackWall.PNG");// 배경이미지 삽입
		buttonL = new ButtonListener();// 버튼리스너 생성

		// BGM
		try {
			cBGM = AudioSystem.getClip();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {// 해당 경로에 있는 음악파일 재생 / 해당 파일이 없을경우 Exception이 발생하기 때문에 try-catch처리
			cBGM.open(AudioSystem.getAudioInputStream(new URL("file:Sound/BGM.wav")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cBGM.start();// BGM 시작
		cBGM.loop(Clip.LOOP_CONTINUOUSLY);// BGM 반복재생
		bBGM = true;// true일때 재생

		// BGM을 크고 끌 수 있는 버튼
		btnBGM = new JButton(new ImageIcon("Image/BGM.png"));// 버튼에 이미지 삽입
		btnBGM.setBounds(575, 115, 100, 100);// 위치
		btnBGM.setBorderPainted(false);// 테두리 선 삭제
		btnBGM.setContentAreaFilled(false);// 버튼 위 이미지가 아닌 공간을 없애줌
		btnBGM.addActionListener(new BGMListener());// bgm에만 사용되는 리스너이기 때문에 무명객체로 생성
		add(btnBGM);

		// 시작화면, 설명화면, 각스테이지들이 크기,위치가 같음 -> 후에 setVisible로 화면전환 효과를 내기 위함
		// 시작화면
		// 시작 패널 위에 이미지가 올려진 레이블을 올리는 형식
		OpenPanel = new JPanel();
		OpenPanel.setBounds(50, 50, 500, 500);// MainPanel에서의 OpenPanel 위치와 크기
		OpenPanel.setLayout(null);
		imgOpen = new ImageIcon("image/Open.PNG");
		lblimgOpen = new JLabel(imgOpen);// 레이블에 이미지 삽입
		lblimgOpen.setBounds(0, 0, 500, 500);// OpenPanel에서의 lblimpOpen 위치와 크기
		OpenPanel.add(lblimgOpen);// 이미지가 삽입된 레이블을 패널에 얹고
		add(OpenPanel);// 그 패널을 메인패널에 얹음

		// 설명화면
		// 이미지 자체를 제외한 것들은 시작화면과 같음
		HowtoPanel = new JPanel();
		HowtoPanel.setBounds(50, 50, 500, 500);
		HowtoPanel.setLayout(null);
		imgHowto = new ImageIcon("image/Howto.PNG");
		lblimgHowto = new JLabel(imgHowto);
		lblimgHowto.setBounds(0, 0, 500, 500);
		HowtoPanel.add(lblimgHowto);
		add(HowtoPanel);

		// 각 스테이지를 생성하고 메인패널에 올림
		S1 = new Stage1();
		S2 = new Stage2();
		S3 = new Stage3();
		add(S1);
		add(S2);
		add(S3);

		// Gauge
		GaugeViewPanel = new GaugePanel(HP); // GaugeViewPanel을 HP를 parameter로 보내 생성
		GaugeViewPanel.setBackground(new Color(0, 0, 0, 0)); // 배경색 설정(투명)
		GaugeViewPanel.setVisible(false); // Start버튼을 누르기 전에 안보임
		add(GaugeViewPanel); // MainPanel에 추가

		// button
		// Start 버튼
		btnStart = new JButton(new ImageIcon("Image/RedButton.png"));// 버튼에 이미지 삽입
		btnStart.setBounds(575, 280, 100, 50);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setText("START");// 이미지 위에 올릴 글
		btnStart.setForeground(Color.white);// 글자 색
		btnStart.setHorizontalTextPosition(JButton.CENTER);// 글자 좌우 위치
		btnStart.setVerticalTextPosition(JButton.CENTER);// 글자 상하 위치
		btnStart.addMouseListener(buttonL); // 버튼리스너 삽입
		add(btnStart);// 메인패널에 올림

		// Howto 버튼 (내용은 Start 버튼과 동)
		btnHowto = new JButton(new ImageIcon("Image/RedButton.png"));
		btnHowto.setBounds(575, 385, 100, 50);
		btnHowto.setBorderPainted(false);
		btnHowto.setContentAreaFilled(false);
		btnHowto.setText("HOW TO");
		btnHowto.setForeground(Color.white);
		btnHowto.setHorizontalTextPosition(JButton.CENTER);
		btnHowto.setVerticalTextPosition(JButton.CENTER);
		btnHowto.addMouseListener(buttonL);
		add(btnHowto);

		// Stage1 버튼 (내용은 Start 버튼과 동)
		btnStage1 = new JButton(new ImageIcon("Image/RedButton.png"));
		btnStage1.setBounds(575, 280, 100, 50);
		btnStage1.setBorderPainted(false);
		btnStage1.setContentAreaFilled(false);
		btnStage1.setText("STAGE 1");
		btnStage1.setForeground(Color.white);
		btnStage1.setHorizontalTextPosition(JButton.CENTER);
		btnStage1.setVerticalTextPosition(JButton.CENTER);
		btnStage1.addMouseListener(buttonL);
		add(btnStage1);

		// Stage2 버튼 (내용은 Start 버튼과 동)
		btnStage2 = new JButton(new ImageIcon("Image/RedButton.png"));
		btnStage2.setBounds(575, 350, 100, 50);
		btnStage2.setBorderPainted(false);
		btnStage2.setContentAreaFilled(false);
		btnStage2.setText("STAGE 2");
		btnStage2.setForeground(Color.white);
		btnStage2.setHorizontalTextPosition(JButton.CENTER);
		btnStage2.setVerticalTextPosition(JButton.CENTER);
		btnStage2.addMouseListener(buttonL);
		add(btnStage2);

		// Stage3 버튼 (내용은 Start 버튼과 동)
		btnStage3 = new JButton(new ImageIcon("Image/RedButton.png"));
		btnStage3.setBounds(575, 420, 100, 50);
		btnStage3.setBorderPainted(false);
		btnStage3.setContentAreaFilled(false);
		btnStage3.setText("STAGE 3");
		btnStage3.setForeground(Color.white);
		btnStage3.setHorizontalTextPosition(JButton.CENTER);
		btnStage3.setVerticalTextPosition(JButton.CENTER);
		btnStage3.addMouseListener(buttonL);
		add(btnStage3);

		// Exit 버튼 (내용은 Start 버튼과 동)
		btnExit = new JButton(new ImageIcon("Image/RedButton.png"));
		btnExit.setBounds(575, 490, 100, 50);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setText("EXIT");
		btnExit.setForeground(Color.white);
		btnExit.setHorizontalTextPosition(JButton.CENTER);
		btnExit.setVerticalTextPosition(JButton.CENTER);
		btnExit.addMouseListener(buttonL);
		add(btnExit);

		// 버튼과 패널 Display 초기화
		// 시작시 Start Howto Exit 버튼만 보이게 / Start버튼 누를시 Start Howto 안보이게 하고 Stage1,2,3 보이게
		// 할 것
		// Exit 버튼은 항상 있음
		btnStart.setVisible(true);
		btnHowto.setVisible(true);
		btnExit.setVisible(true);
		btnStage1.setVisible(false);
		btnStage2.setVisible(false);
		btnStage3.setVisible(false);

		// 밑 5개 패널은 위치, 크기가 같음, 진행에 따라 하나만 true로 해서 화면전환
		OpenPanel.setVisible(true);
		HowtoPanel.setVisible(false);
		S1.setVisible(false);
		S2.setVisible(false);
		S3.setVisible(false);
	}// constructor

	public void paintComponent(Graphics page) {// 메인패널 배경이미지 삽입
		super.paintComponent(page);
		page.drawImage(BackImage.getImage(), 0, 0, this);// 메인패널에 (0, 0) 위치에 이미지 삽입 -> 배경이미지
	}// paintComponent()

	private class ButtonListener implements MouseListener {// 버튼리스너

		@Override
		public void mouseClicked(MouseEvent e) {// 버튼 클릭시
			JButton btn = (JButton) e.getSource();// JButton으로 캐스팅해서 받음
			if (btn == btnStart) {
				// 이후 OpenPanel, HowtoPanel, btnStart, btnHowto는 사용안함
				OpenPanel.setVisible(false);
				HowtoPanel.setVisible(false);
				btnStart.setVisible(false);
				btnHowto.setVisible(false);
				// btnStage1, btnStage2, btnStage3 버튼 나타냄
				btnStage1.setVisible(true);
				btnStage2.setVisible(true);
				btnStage3.setVisible(true);
				// 아직 이전단계를 클리어하지 못했으므로 다음 단계로 넘어가지 못하게 버튼 비활성화
				btnStage2.setEnabled(false);
				btnStage2.removeMouseListener(this);
				btnStage3.setEnabled(false);
				btnStage3.removeMouseListener(this);
				// 시작시 스테이지1이 나오도록 함
				S1.setVisible(true);
				S1.requestFocus(true);
				// Gauge패널
				GaugeViewPanel.setVisible(true); // Stage1으로 들어왔기 때문에 보이도록 설정
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				// 전 Stage의 제한 이동 횟수만큼의 Gauges[]를 remove
				GaugeViewPanel.paintComponent(HP);
				// HP만큼 Gauges[] 생성하는 paintComponent method 호출
				Stage1.nowGauge.initInstanceDatas(HP);
				Stage1.nowGauge.start(); // Thread 실행
			} else if (btn == btnHowto) {// 게임 설명 화면으로 전환 / 설명은 START버튼 누르기 전에만 볼 수 있으므로 OpenPanel과 HowtoPanel만 다뤘음
				OpenPanel.setVisible(false);
				HowtoPanel.setVisible(true);
			} else if (btn == btnStage1) {
				// 스테이지1만 보이도록 화면전환하고 Focus
				S1.setVisible(true);
				S2.setVisible(false);
				S3.setVisible(false);
				S1.requestFocus(true);
				S1.InitStage1();// Stage1 초기화함수
				movecount = 0; // 이동 횟수 초기화
				HP = S1.max_movement1 - movecount; // HP 초기화
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				// 전 Stage의 제한 이동 횟수만큼의 Gauges[]를 remove
				GaugeViewPanel.paintComponent(HP); // Gauges[]를 S1의 제한 이동 횟수만큼 생성
				Stage1.nowGauge.initInstanceDatas(HP);
				Stage1.nowGauge.start(); // Thread 실행
				GaugePanel.remainingCount.setText("" + MainPanel.HP); // remainingCount에 HP를 설정
				beforeMaxMovement = S1.max_movement1; // 현재의 제한 횟수를 beforeMaxMovement에 저장
			} else if (btn == btnStage2) {
				// 스테이지2만 보이도록 화면전환하고 Focus
				S1.setVisible(false);
				S2.setVisible(true);
				S3.setVisible(false);
				S2.requestFocus(true);
				S2.InitStage2();// Stage2 초기화함수
				// (btn == btnStage1)과 동일
				movecount = 0;
				HP = S2.max_movement2 - movecount;
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				GaugeViewPanel.paintComponent(HP);
				Stage2.nowGauge.initInstanceDatas(HP);
				Stage2.nowGauge.start();
				GaugePanel.remainingCount.setText("" + MainPanel.HP);
				beforeMaxMovement = S2.max_movement2;
			} else if (btn == btnStage3) {
				// 스테이지3만 보이도록 화면전환하고 Focus
				S1.setVisible(false);
				S2.setVisible(false);
				S3.setVisible(true);
				S3.requestFocus(true);
				S3.InitStage3();// Stage3 초기화함수
				// (btn == btnStage1)과 동일
				movecount = 0;
				HP = S3.max_movement3 - movecount;
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				GaugeViewPanel.paintComponent(HP);
				Stage3.nowGauge.initInstanceDatas(HP);
				Stage3.nowGauge.start();
				GaugePanel.remainingCount.setText("" + MainPanel.HP);
				beforeMaxMovement = S3.max_movement3;
			} else if (btn == btnExit) {// Exit 버튼시 종료
				System.exit(0);
			}
		}// mouseClicked()

		// 버튼 Hovering시에 색변화
		@Override
		public void mouseEntered(MouseEvent e) {// 마우스 화살표가 버튼위로 올라갈 시
			JButton btn = (JButton) e.getSource();// JButton으로 캐스팅해서 받음
			if (btn == btnStage1) {
				btnStage1.setIcon(new ImageIcon("Image/GreenButton.png"));// 이미지 변경
			} else if (btn == btnStage2) {
				btnStage2.setIcon(new ImageIcon("Image/GreenButton.png"));
			} else if (btn == btnStage3) {
				btnStage3.setIcon(new ImageIcon("Image/GreenButton.png"));
			} else if (btn == btnExit) {
				btnExit.setIcon(new ImageIcon("Image/GreenButton.png"));
			} else if (btn == btnStart) {
				btnStart.setIcon(new ImageIcon("Image/GreenButton.png"));
			} else if (btn == btnHowto) {
				btnHowto.setIcon(new ImageIcon("Image/GreenButton.png"));
			}
		}// mouseEntered()

		// 버튼 Hovering시에 색변화
		@Override
		public void mouseExited(MouseEvent e) {// 마우스 화살표가 버튼 밖으로 나올 시
			JButton btn = (JButton) e.getSource();// JButton으로 캐스팅해서 받음
			if (btn == btnStage1) {
				btnStage1.setIcon(new ImageIcon("Image/RedButton.png"));// 이미지 변경
			} else if (btn == btnStage2) {
				btnStage2.setIcon(new ImageIcon("Image/RedButton.png"));
			} else if (btn == btnStage3) {
				btnStage3.setIcon(new ImageIcon("Image/RedButton.png"));
			} else if (btn == btnExit) {
				btnExit.setIcon(new ImageIcon("Image/RedButton.png"));
			} else if (btn == btnStart) {
				btnStart.setIcon(new ImageIcon("Image/RedButton.png"));
			} else if (btn == btnHowto) {
				btnHowto.setIcon(new ImageIcon("Image/RedButton.png"));
			}
		}// mouseExited()

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}// ButtonListener class

	private class BGMListener implements ActionListener {// BGM용 리스너

		@Override
		public void actionPerformed(ActionEvent e) {

			if (bBGM) {// BGM 재생중이면 멈춤
				cBGM.stop();
				bBGM = false;
				// 중간에 버튼을 눌러 BGM을 끄거나 켜고 나서 다시 산타가 움직이도록 스테이지에 Focus를 해줌
				S1.requestFocus();
				S2.requestFocus();
				S3.requestFocus();
			} else if (bBGM == false) {// 멈춰있으면 재생
				cBGM.start();
				bBGM = true;
				S1.requestFocus();
				S2.requestFocus();
				S3.requestFocus();
			}

		}// actionPerformed()

	}// BGMListener class

}// MainPanel class