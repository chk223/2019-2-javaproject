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

	static Stage1 S1;// ��������1 �г� / ����ƽ���� �����Ͽ� �ٸ� Ŭ���������� �ش� ��ü�� ����Ҽ� �ְ� ����
	static Stage2 S2;// ��������2 �г�
	static Stage3 S3;// ��������3 �г�
	static int movecount = 0, HP = S1.max_movement1 - movecount; // �̵� Ƚ���� ���ִ� movement�� ���� HP�� ��Ÿ���� HP
	static int beforeMaxMovement = S1.max_movement1; // Stage�� �̵��� �� ���� Stage�� ���� �̵� Ƚ���� �����ϴ� beforeMaxMovement
	private ImageIcon BackImage, imgOpen, imgHowto;// ���, ����ȭ��, ����ȭ���� �̹����� �ҷ����� ���� ����
	private JPanel OpenPanel, HowtoPanel;// Open Howto �̹����� �ø� �г�
	private JLabel lblimgOpen, lblimgHowto;// Open Howto�� �̹����� �ø������� �ʿ��� ���̺�
	static ButtonListener buttonL;// ��ư������
	private Clip cBGM;// BGM
	private boolean bBGM;// ���� BGM�� ��������� �ƴ��� �˱�����
	static JButton btnBGM, btnStart, btnHowto, btnStage1, btnStage2, btnStage3, btnExit;// ���������� ���� �� ��ư��
	private Font ButtonFont = new Font("Verdana", Font.BOLD, 20);// ��� ��ư�鿡 ���� ��Ʈ
	static GaugePanel GaugeViewPanel;// ��Ÿ�� ���� ü��(������)�� ��Ÿ�� �г�

	public MainPanel() {
		setPreferredSize(new Dimension(700, 650));// ȭ�� ��ü ũ��
		setLayout(null);
		BackImage = new ImageIcon("Image/BackWall.PNG");// ����̹��� ����
		buttonL = new ButtonListener();// ��ư������ ����

		// BGM
		try {
			cBGM = AudioSystem.getClip();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {// �ش� ��ο� �ִ� �������� ��� / �ش� ������ ������� Exception�� �߻��ϱ� ������ try-catchó��
			cBGM.open(AudioSystem.getAudioInputStream(new URL("file:Sound/BGM.wav")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cBGM.start();// BGM ����
		cBGM.loop(Clip.LOOP_CONTINUOUSLY);// BGM �ݺ����
		bBGM = true;// true�϶� ���

		// BGM�� ũ�� �� �� �ִ� ��ư
		btnBGM = new JButton(new ImageIcon("Image/BGM.png"));// ��ư�� �̹��� ����
		btnBGM.setBounds(575, 115, 100, 100);// ��ġ
		btnBGM.setBorderPainted(false);// �׵θ� �� ����
		btnBGM.setContentAreaFilled(false);// ��ư �� �̹����� �ƴ� ������ ������
		btnBGM.addActionListener(new BGMListener());// bgm���� ���Ǵ� �������̱� ������ ����ü�� ����
		add(btnBGM);

		// ����ȭ��, ����ȭ��, �������������� ũ��,��ġ�� ���� -> �Ŀ� setVisible�� ȭ����ȯ ȿ���� ���� ����
		// ����ȭ��
		// ���� �г� ���� �̹����� �÷��� ���̺��� �ø��� ����
		OpenPanel = new JPanel();
		OpenPanel.setBounds(50, 50, 500, 500);// MainPanel������ OpenPanel ��ġ�� ũ��
		OpenPanel.setLayout(null);
		imgOpen = new ImageIcon("image/Open.PNG");
		lblimgOpen = new JLabel(imgOpen);// ���̺� �̹��� ����
		lblimgOpen.setBounds(0, 0, 500, 500);// OpenPanel������ lblimpOpen ��ġ�� ũ��
		OpenPanel.add(lblimgOpen);// �̹����� ���Ե� ���̺��� �гο� ���
		add(OpenPanel);// �� �г��� �����гο� ����

		// ����ȭ��
		// �̹��� ��ü�� ������ �͵��� ����ȭ��� ����
		HowtoPanel = new JPanel();
		HowtoPanel.setBounds(50, 50, 500, 500);
		HowtoPanel.setLayout(null);
		imgHowto = new ImageIcon("image/Howto.PNG");
		lblimgHowto = new JLabel(imgHowto);
		lblimgHowto.setBounds(0, 0, 500, 500);
		HowtoPanel.add(lblimgHowto);
		add(HowtoPanel);

		// �� ���������� �����ϰ� �����гο� �ø�
		S1 = new Stage1();
		S2 = new Stage2();
		S3 = new Stage3();
		add(S1);
		add(S2);
		add(S3);

		// Gauge
		GaugeViewPanel = new GaugePanel(HP); // GaugeViewPanel�� HP�� parameter�� ���� ����
		GaugeViewPanel.setBackground(new Color(0, 0, 0, 0)); // ���� ����(����)
		GaugeViewPanel.setVisible(false); // Start��ư�� ������ ���� �Ⱥ���
		add(GaugeViewPanel); // MainPanel�� �߰�

		// button
		// Start ��ư
		btnStart = new JButton(new ImageIcon("Image/RedButton.png"));// ��ư�� �̹��� ����
		btnStart.setBounds(575, 280, 100, 50);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setText("START");// �̹��� ���� �ø� ��
		btnStart.setForeground(Color.white);// ���� ��
		btnStart.setHorizontalTextPosition(JButton.CENTER);// ���� �¿� ��ġ
		btnStart.setVerticalTextPosition(JButton.CENTER);// ���� ���� ��ġ
		btnStart.addMouseListener(buttonL); // ��ư������ ����
		add(btnStart);// �����гο� �ø�

		// Howto ��ư (������ Start ��ư�� ��)
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

		// Stage1 ��ư (������ Start ��ư�� ��)
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

		// Stage2 ��ư (������ Start ��ư�� ��)
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

		// Stage3 ��ư (������ Start ��ư�� ��)
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

		// Exit ��ư (������ Start ��ư�� ��)
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

		// ��ư�� �г� Display �ʱ�ȭ
		// ���۽� Start Howto Exit ��ư�� ���̰� / Start��ư ������ Start Howto �Ⱥ��̰� �ϰ� Stage1,2,3 ���̰�
		// �� ��
		// Exit ��ư�� �׻� ����
		btnStart.setVisible(true);
		btnHowto.setVisible(true);
		btnExit.setVisible(true);
		btnStage1.setVisible(false);
		btnStage2.setVisible(false);
		btnStage3.setVisible(false);

		// �� 5�� �г��� ��ġ, ũ�Ⱑ ����, ���࿡ ���� �ϳ��� true�� �ؼ� ȭ����ȯ
		OpenPanel.setVisible(true);
		HowtoPanel.setVisible(false);
		S1.setVisible(false);
		S2.setVisible(false);
		S3.setVisible(false);
	}// constructor

	public void paintComponent(Graphics page) {// �����г� ����̹��� ����
		super.paintComponent(page);
		page.drawImage(BackImage.getImage(), 0, 0, this);// �����гο� (0, 0) ��ġ�� �̹��� ���� -> ����̹���
	}// paintComponent()

	private class ButtonListener implements MouseListener {// ��ư������

		@Override
		public void mouseClicked(MouseEvent e) {// ��ư Ŭ����
			JButton btn = (JButton) e.getSource();// JButton���� ĳ�����ؼ� ����
			if (btn == btnStart) {
				// ���� OpenPanel, HowtoPanel, btnStart, btnHowto�� ������
				OpenPanel.setVisible(false);
				HowtoPanel.setVisible(false);
				btnStart.setVisible(false);
				btnHowto.setVisible(false);
				// btnStage1, btnStage2, btnStage3 ��ư ��Ÿ��
				btnStage1.setVisible(true);
				btnStage2.setVisible(true);
				btnStage3.setVisible(true);
				// ���� �����ܰ踦 Ŭ�������� �������Ƿ� ���� �ܰ�� �Ѿ�� ���ϰ� ��ư ��Ȱ��ȭ
				btnStage2.setEnabled(false);
				btnStage2.removeMouseListener(this);
				btnStage3.setEnabled(false);
				btnStage3.removeMouseListener(this);
				// ���۽� ��������1�� �������� ��
				S1.setVisible(true);
				S1.requestFocus(true);
				// Gauge�г�
				GaugeViewPanel.setVisible(true); // Stage1���� ���Ա� ������ ���̵��� ����
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				// �� Stage�� ���� �̵� Ƚ����ŭ�� Gauges[]�� remove
				GaugeViewPanel.paintComponent(HP);
				// HP��ŭ Gauges[] �����ϴ� paintComponent method ȣ��
				Stage1.nowGauge.initInstanceDatas(HP);
				Stage1.nowGauge.start(); // Thread ����
			} else if (btn == btnHowto) {// ���� ���� ȭ������ ��ȯ / ������ START��ư ������ ������ �� �� �����Ƿ� OpenPanel�� HowtoPanel�� �ٷ���
				OpenPanel.setVisible(false);
				HowtoPanel.setVisible(true);
			} else if (btn == btnStage1) {
				// ��������1�� ���̵��� ȭ����ȯ�ϰ� Focus
				S1.setVisible(true);
				S2.setVisible(false);
				S3.setVisible(false);
				S1.requestFocus(true);
				S1.InitStage1();// Stage1 �ʱ�ȭ�Լ�
				movecount = 0; // �̵� Ƚ�� �ʱ�ȭ
				HP = S1.max_movement1 - movecount; // HP �ʱ�ȭ
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				// �� Stage�� ���� �̵� Ƚ����ŭ�� Gauges[]�� remove
				GaugeViewPanel.paintComponent(HP); // Gauges[]�� S1�� ���� �̵� Ƚ����ŭ ����
				Stage1.nowGauge.initInstanceDatas(HP);
				Stage1.nowGauge.start(); // Thread ����
				GaugePanel.remainingCount.setText("" + MainPanel.HP); // remainingCount�� HP�� ����
				beforeMaxMovement = S1.max_movement1; // ������ ���� Ƚ���� beforeMaxMovement�� ����
			} else if (btn == btnStage2) {
				// ��������2�� ���̵��� ȭ����ȯ�ϰ� Focus
				S1.setVisible(false);
				S2.setVisible(true);
				S3.setVisible(false);
				S2.requestFocus(true);
				S2.InitStage2();// Stage2 �ʱ�ȭ�Լ�
				// (btn == btnStage1)�� ����
				movecount = 0;
				HP = S2.max_movement2 - movecount;
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				GaugeViewPanel.paintComponent(HP);
				Stage2.nowGauge.initInstanceDatas(HP);
				Stage2.nowGauge.start();
				GaugePanel.remainingCount.setText("" + MainPanel.HP);
				beforeMaxMovement = S2.max_movement2;
			} else if (btn == btnStage3) {
				// ��������3�� ���̵��� ȭ����ȯ�ϰ� Focus
				S1.setVisible(false);
				S2.setVisible(false);
				S3.setVisible(true);
				S3.requestFocus(true);
				S3.InitStage3();// Stage3 �ʱ�ȭ�Լ�
				// (btn == btnStage1)�� ����
				movecount = 0;
				HP = S3.max_movement3 - movecount;
				GaugeViewPanel.removePaintGauge(beforeMaxMovement);
				GaugeViewPanel.paintComponent(HP);
				Stage3.nowGauge.initInstanceDatas(HP);
				Stage3.nowGauge.start();
				GaugePanel.remainingCount.setText("" + MainPanel.HP);
				beforeMaxMovement = S3.max_movement3;
			} else if (btn == btnExit) {// Exit ��ư�� ����
				System.exit(0);
			}
		}// mouseClicked()

		// ��ư Hovering�ÿ� ����ȭ
		@Override
		public void mouseEntered(MouseEvent e) {// ���콺 ȭ��ǥ�� ��ư���� �ö� ��
			JButton btn = (JButton) e.getSource();// JButton���� ĳ�����ؼ� ����
			if (btn == btnStage1) {
				btnStage1.setIcon(new ImageIcon("Image/GreenButton.png"));// �̹��� ����
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

		// ��ư Hovering�ÿ� ����ȭ
		@Override
		public void mouseExited(MouseEvent e) {// ���콺 ȭ��ǥ�� ��ư ������ ���� ��
			JButton btn = (JButton) e.getSource();// JButton���� ĳ�����ؼ� ����
			if (btn == btnStage1) {
				btnStage1.setIcon(new ImageIcon("Image/RedButton.png"));// �̹��� ����
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

	private class BGMListener implements ActionListener {// BGM�� ������

		@Override
		public void actionPerformed(ActionEvent e) {

			if (bBGM) {// BGM ������̸� ����
				cBGM.stop();
				bBGM = false;
				// �߰��� ��ư�� ���� BGM�� ���ų� �Ѱ� ���� �ٽ� ��Ÿ�� �����̵��� ���������� Focus�� ����
				S1.requestFocus();
				S2.requestFocus();
				S3.requestFocus();
			} else if (bBGM == false) {// ���������� ���
				cBGM.start();
				bBGM = true;
				S1.requestFocus();
				S2.requestFocus();
				S3.requestFocus();
			}

		}// actionPerformed()

	}// BGMListener class

}// MainPanel class