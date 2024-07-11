package DoYouWantChristmasPresent;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Stage3 extends JPanel {

	static int max_movement3 = 75;// �� �̵�Ƚ�� ����(����)
	private int emptysocks_count = 5;// �� �縻�� ����, �� ������ 0���� �Ǹ� ���������� Ŭ����ǰ�, ������ ����޼��� ���
	// �ʱ� �� ���� ����
	private static int mapArr[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 3, 0, 0 }, { 0, 1, 3, 1, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 1, 1, 0, 1, 0, 0 }, { 0, 4, 1, 1, 3, 1, 3, 1, 1, 0 }, { 0, 4, 4, 1, 1, 3, 1, 1, 1, 0 },
			{ 0, 4, 4, 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	// mapArr�� ���� �ٲ�� ������ �ʱ�ȭ�� ���� �� �ʱ� ������ ������ ��
	private static int init_mapArr[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 3, 0, 0 }, { 0, 1, 3, 1, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 1, 1, 0, 1, 0, 0 }, { 0, 4, 1, 1, 3, 1, 3, 1, 1, 0 }, { 0, 4, 4, 1, 1, 3, 1, 1, 1, 0 },
			{ 0, 4, 4, 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	// ���� 4 = �縻 / 3 = ���� / 1 = ����� / 0 = ��

	private static ImageIcon image; // �̹����� �󺧿� �����ϱ� ���� �̹��� ������
	private static JLabel lblpixel[][], lblSanta, lblPresent[];// lblpixel�� ����, ��Ÿ�� ������ ��� �� ����, lblSanta�� ��Ÿ���� ǥ��,
	// lblPresent�� ������ ����
	private static int x, y, s_x, s_y, present_count;// x,y�� �� ������ ��ǥ�� �����ϱ� ���� ����, s_x, s_y�� ��Ÿ�� ��ǥ�� ����, present_count��
	// ������ ������ �����ϰų� �籸���Ҷ� ����ϴ� ����(���° �������� ī����)
	static int p_x[], p_y[];// ������ x,y��ǥ & �迭�� �ε����� present_count�� �ַ� ���� ���° ���������� ����
	static PanelThread nowGauge; // ���� ü���� �����ֱ� ���� �����̴� ����� �ִ� PanelThread�� ���� nowGauge

	public Stage3() {
		setBounds(50, 50, 500, 500); // �� ũ�⸦ 500,500���� ����, (50,50)���� ������ �Ʒ��� �� ����
		setBackground(Color.white); // �� ������ ������� ���� -> �Ŀ� '��'�� ����� ���� ��
		setFocusable(true);// Ű �����ʸ� ���� �� �ְԲ� ����
		addKeyListener(new KeyboardListener()); // Ű ������ �߰�
		setLayout(null);// ���̾ƿ� �Ŵ��������� null ��Ŵ
		MainPanel.movecount = 0;// ���ο� �ִ� movecount(�̵�Ƚ��)�� 0���� �ʱ�ȭ
		nowGauge = new PanelThread(MainPanel.HP);
		nowGauge.setVisible(true);
		lblpixel = new JLabel[10][10];// �ʱ���(����, ��Ÿ ����)�� ����� �󺧹迭 10x10(��ũ��)����
		p_x = new int[5];// [n]��° ������ x��ǥ
		p_y = new int[5];// [n]��° ������ y��ǥ
		lblPresent = new JLabel[5]; // [n]��° ������ ��
		present_count = 0; // ���� ���� ī���� ���� �ʱ�ȭ
		PaintGame(); // ���� ȭ�� ����
		Santa_paint(); // ��Ÿ(ĳ����) ��ġ
	}// constructor

	public void PaintGame() {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				switch (mapArr[i][j]) {
				case 0: // ��
					x = j * 50;
					y = i * 50;
					image = new ImageIcon("image/wall.png");
					lblpixel[i][j] = new JLabel(image);
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
					break;
				case 1: // ��
					x = j * 50;
					y = i * 50;
					lblpixel[i][j] = new JLabel();
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
					break;
				case 2: // ��Ÿ
					break;
				case 3: // ����
					p_y[present_count] = i;
					p_x[present_count] = j;
					Present_Paint(p_x[present_count], p_y[present_count], present_count);
					present_count++;
					break;
				case 4: // �縻
					x = j * 50;
					y = i * 50;
					image = new ImageIcon("image/sock.png");
					lblpixel[i][j] = new JLabel(image);
					lblpixel[i][j].setBounds(x, y, 50, 50);
					add(lblpixel[i][j]);
					break;
				case 5: // �縻�� ���� ��
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

	public void InitStage3() { // �������� �ʱ�ȭ �Լ�

		// ��Ÿ �ʱ�ȭ
		lblSanta.setIcon(null); // ������ ����� �ִ� �̹��� �������� ���� null ó��
		// ���� �ʱ�ȭ
		for (int i = 0; i < 4; i++) {
			lblPresent[i].setIcon(null);// ������ ����� �ִ� �̹��� �������� ���� null ó��
		}
		// �� ��ü���� �縻 & ������ ���ִ� �縻 �ʱ�ȭ
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (init_mapArr[i][j] == 4 || init_mapArr[i][j] == 5) {
					// ������ ����� �ִ� �̹��� �������� ���� null ó��
					lblpixel[i][j].setIcon(null);
				}
			} // for(j)
		} // for(i)
		Santa_Repaint(); // ��Ÿ�� �ٽ� �׷���(�ʱ�ȭ)
		present_count = 0; // ���� ī���� ������ �ʱ�ȭ
		emptysocks_count = 5; // �� �縻 ������ �ʱⰪ���� ����
		for (int i = 0; i < 10; i++) { // �ʱ� �� ����(init_mapArr)�� �̿��� mapArr(�� ���� ����) �ʱ�ȭ��Ŵ
			for (int j = 0; j < 10; j++) {
				mapArr[i][j] = init_mapArr[i][j];
			} // for(j)
		} // for(i)
			// ��ü �ʿ��� ������ �縻�� �ٽ� �׷��� (���� ������ �ٲ��� �ʱ� ������ �״�� ��)
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mapArr[i][j] == 3) { // �� ������ 3�̸� (�����̸�)
					// ���� ��ǥ ����
					p_x[present_count] = j;
					p_y[present_count] = i;
					// ���� ���� �̹����������� null->image�� ��ü
					MovePresent(j, i, present_count);
					present_count++; // ���� ī���� ���� ����
				} else if (mapArr[i][j] == 4) { // �縻 ��ġ
					image = new ImageIcon("image/Sock.png");
					lblpixel[i][j].setIcon(image);
				}
			} // for(j)
		} // for(i)

		MainPanel.GaugeViewPanel.removePaintGauge(MainPanel.beforeMaxMovement); // beforeMaxMovement��ŭ Gauges[]�� remove
		MainPanel.movecount = 0; // �̵� Ƚ���� 0���� �ʱ�ȭ
		MainPanel.HP = max_movement3 - MainPanel.movecount; // ���� HP�� �ʱ�ȭ
		GaugePanel.remainingCount.setText("" + MainPanel.HP); // ���� �̵� Ƚ���� Ÿ���� �ִ� remainingCount(JLabel)�� �ʱ�ȭ
		MainPanel.GaugeViewPanel.paintComponent(MainPanel.HP); // HP��ŭ Gauge[]�� �������ִ� �Լ� ȣ��
		MainPanel.GaugeViewPanel.hpPanel.repaint(); // hpPanel�� �������ֱ� ����
		nowGauge.initInstanceDatas(MainPanel.HP); // Thread�۵��� �ʱ�ȭ���ֱ� ����
		nowGauge.start(); // ������ �۵�
		MainPanel.beforeMaxMovement = max_movement3; // ���� ���������� �Ѿ ��, removePaintGauge�� parameter�� �Ѱ��ֱ� ���� ����
	}

	public void Santa_paint() {
		// ��Ÿ�� �׸���(��ġ�ϴ�) �Լ�
		// ��Ÿ�� ��ǥ ����
		s_x = 7;
		s_y = 7;
		// �̹��� �������� ��Ÿ ���� lblSanta�� ��� ��ġ��Ŵ
		image = new ImageIcon("image/Santa.png");
		lblSanta = new JLabel(image);
		lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50);
		add(lblSanta);
	}

	public void Santa_Repaint() {
		// ��Ÿ ��ġ�� �ʱ�ȭ��ų�� ���� �Լ�
		// ��Ÿ�� ��Ÿ�� ��ǥ ����
		s_x = 7;
		s_y = 7;
		// lblSanta�� �̹����� ��Ÿ �̹����� ��ü
		image = new ImageIcon("image/Santa.png");
		lblSanta.setIcon(image);
		lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50);
		add(lblSanta);
	}

	public void insert_Present_in_Sock(int x, int y) {
		// �縻 �ȿ� ������ �ִ� �Լ�(�̹��� ��ü)
		image = new ImageIcon("image/PresentinSock.png");
		lblpixel[y][x].setIcon(image); // setIcon�� ���� �̹����� �ٲ���
		lblpixel[y][x].setBounds(x * 50, y * 50, 50, 50); // ��ġ
		emptysocks_count--; // �� �縻ī�����ϴ� ������ ���� �ϳ� �ٿ��� ( �� ���ڰ� 0�� �Ǵ� ���� �������� Ŭ���� ����)
		add(lblpixel[y][x]);
	}

	public void remove_Present_from_Sock(int x, int y) { // �縻�� ����ִ� ������ ���� �Լ�(������ �縻 -> �� �縻)
		image = new ImageIcon("image/Sock.png");
		// �ٸ� �Լ���� ���� ������� �̹����� ��ü�ϰ� ��ġ
		lblpixel[y][x].setIcon(image);
		lblpixel[y][x].setBounds(x * 50, y * 50, 50, 50);
		emptysocks_count++; // �縻���� ������ �������� ������ �� �縻�� ���� �ٽ� ����
		add(lblpixel[y][x]);
	}

	public void Take_out_Present_from_Sock(int x, int y) { // �縻�� ����ִ� ������ ���� �ٱ����� �������ִ� �Լ�
		// ���� �� �縻���� ���� ��(��) �� ��ǥ�� x,y�� �޾� �ͼ� ������ ���� �� �ƹ��ų� �����ͼ� �� i��° ������ ��ǥ�� ���� �� ����
		// �̹����� �����ؼ� ������ ��ġ�ϴ� �Լ�
		for (int i = 0; i < 5; i++) {
			if (mapArr[p_y[i]][p_x[i]] != 3) { // ������ ���� Ž��
				p_y[i] = y;
				p_x[i] = x;
				MovePresent(x, y, i); // ���� ��ġ
				break;
			}
		}
	}

	public void Present_Paint(int x, int y, int present_cnt) {
		// �ʱ⿡ ������ �׷��ִ� �Լ� �̹��� �������� present_cnt��° ���� ���� �󺧿� ��� ��ġ��Ŵ
		image = new ImageIcon("image/present.png");
		lblPresent[present_cnt] = new JLabel(image);
		lblPresent[present_cnt].setBounds(x * 50, y * 50, 50, 50);
		add(lblPresent[present_cnt]);
	}

	public void RemovePresent(int x, int y, int present_cnt) {
		// ������ �縻�ȿ� ������ �ش� ������ �����ִ� ����
		lblPresent[present_cnt].setIcon(null);
		lblPresent[present_cnt].setBounds(x * 50, y * 50, 50, 50);
		add(lblPresent[present_cnt]);
	}

	public void MovePresent(int x, int y, int present_cnt) {
		// ���� �̹����� ���� ��ü(��ġ)�Ҷ� ���, ������ �縻 �ٱ����� �� �� ��(�ʱ� ���� �̿ܿ��� �� �Լ��� ����ؼ� ������ �׸���)
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
				if (mapArr[s_y - 1][s_x] == 0) { // ��Ÿ ���� ���̸� �̵� �Ұ�}
				} else if (mapArr[s_y - 1][s_x] == 3) { // ���� ������ �ִٸ�
					for (present_count = 0; present_count < 4; present_count++) { // ���� �ִ� ������ � ��������(�� ��° ��������) Ž��
						if (p_y[present_count] == s_y - 1 && p_x[present_count] == s_x) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count] - 1][p_x[present_count]] == 0) { // ���� ���� ���̸�
					} // if
					else if (mapArr[p_y[present_count] - 1][p_x[present_count]] == 1) { // ���� ���� ���̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						p_y[present_count] -= 1; // ���� ��ǥ ��ĭ ���� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // ���� �ű���� �ε��� 3(����)
						MovePresent(p_x[present_count], p_y[present_count], present_count); // ���� �ű�� �Լ�
						s_y -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�ȸ�� ����
					} else if (mapArr[p_y[present_count] - 1][p_x[present_count]] == 4) { // ���� ���� �縻�̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						RemovePresent(p_x[present_count], p_y[present_count], present_count); // ������ �縻�ȿ� ������ �ش� ������
						// �����ִ� �Լ�
						p_y[present_count] -= 1; // ���� ��ǥ ��ĭ ���� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // ���� �ű���� �ε��� 5(������ �縻)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]); // �縻�� ���� �ִ� �Լ�
						s_y -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�ȸ�� ����
					}
				} else if (mapArr[s_y - 1][s_x] == 5) { // ��Ÿ ���� ���� �� �縻�� ������
					if (mapArr[s_y - 2][s_x] == 4) { // �� ���� �縻�̶��
						mapArr[s_y - 2][s_x] = 5; // �� ���� ����
						mapArr[s_y - 1][s_x] = 4; // �� ���� ����
						// �ٸ� �縻�� ������ �ű�
						remove_Present_from_Sock(s_x, s_y - 1);// ���� ����
						insert_Present_in_Sock(s_x, s_y - 2); // ���� �ֱ�
						s_y -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�ȸ�� ����
					} else if (mapArr[s_y - 2][s_x] == 1) { // �� ���� ���̶��
						mapArr[s_y - 2][s_x] = 5; // �� ���� ����
						mapArr[s_y - 1][s_x] = 4; // �� ���� ����
						remove_Present_from_Sock(s_x, s_y - 1);// ���� ���� �Լ�
						Take_out_Present_from_Sock(s_x, s_y - 2); // ���� ���� �Լ�
						s_y -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�ȸ�� ����
					}
				} else {// ������ ���(���� ���)
					MainPanel.movecount++; // �̵�ȸ�� ����
					s_y -= 1; // ��Ÿ ��ġ ����
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(���� �̵� Ƚ��) ����
				if (MainPanel.HP >= 0) { // HP�� ������ ��
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread ����
					nowGauge.start(); // Thread �۵�
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // �Ҹ�� ü���� �����ֱ� ���� removeRed method ȣ��
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // ���� �̵� Ƚ���� �����ִ� remainingCount ����
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // �̵��� ��Ÿ�� ��ġ�� �ٲ���
				} else { // ���� �̵� Ƚ���� ���� ��
					GaugePanel.remainingCount.setText("" + 0); // ���� �̵� Ƚ���� �����ִ� remainingCount 0���� ����
					GaugePanel.removeRed(max_movement3, max_movement3); // ü���� ��� �����ֱ� ���� removeRed method ȣ��
				}
				break;
			case KeyEvent.VK_DOWN: // Ű���� �Ʒ� ����Ű ������
				if (mapArr[s_y + 1][s_x] == 0) { // ��Ÿ �Ʒ��� ���̸� �̵� �Ұ�
				} else if (mapArr[s_y + 1][s_x] == 3) { // �Ʒ��� ������ �ִٸ�
					for (present_count = 0; present_count < 4; present_count++) { // �Ʒ��� �ִ� ������ � �������� Ž��
						if (p_y[present_count] == s_y + 1 && p_x[present_count] == s_x) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count] + 1][p_x[present_count]] == 0) { // ���� �Ʒ��� ���̸�
					} // if
					else if (mapArr[p_y[present_count] + 1][p_x[present_count]] == 1) { // ���� �Ʒ��� ���̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						p_y[present_count] += 1; // ���� ��ǥ ��ĭ �Ʒ��� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // ���� �ű���� �ε��� 3(����)
						MovePresent(p_x[present_count], p_y[present_count], present_count); // ���� �ű�� �Լ�
						s_y += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					} else if (mapArr[p_y[present_count] + 1][p_x[present_count]] == 4) { // ���� �Ʒ��� �縻�̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						RemovePresent(p_x[present_count], p_y[present_count], present_count);// ������ �縻�ȿ� ������ �ش� ������ �����ִ�
						// �Լ�
						p_y[present_count] += 1; // ���� ��ǥ ��ĭ �Ʒ��� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // ���� �ű���� �ε��� 5(������ �縻)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]); // �縻�� ������ �ִ� �Լ�
						s_y += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					}
				} else if (mapArr[s_y + 1][s_x] == 5) { // ��Ÿ �Ʒ��� ���� �� �縻�� ������
					if (mapArr[s_y + 2][s_x] == 4) { // �� �Ʒ��� �縻�̶��
						mapArr[s_y + 2][s_x] = 5; // �� ���� ����
						mapArr[s_y + 1][s_x] = 4; // �� ���� ����
						// �ٸ� �縻�� ������ �ű�
						remove_Present_from_Sock(s_x, s_y + 1);// ���� ����
						insert_Present_in_Sock(s_x, s_y + 2); // ���� �ֱ�
						s_y += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					} else if (mapArr[s_y + 2][s_x] == 1) { // �� �Ʒ��� ���̶��
						mapArr[s_y + 2][s_x] = 3; // �� ���� ����
						mapArr[s_y + 1][s_x] = 4; // �� ���� ����
						remove_Present_from_Sock(s_x, s_y + 1);// ���� ����
						Take_out_Present_from_Sock(s_x, s_y + 2); // ������ ����ִ� �縻���� ������ ���� �Լ�
						s_y += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					}
				} else {// ������ ���(���� ���)
					MainPanel.movecount++; // �̵�Ƚ�� ����
					s_y += 1; // ��Ÿ ��ġ ����
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(���� �̵� Ƚ��) ����
				if (MainPanel.HP >= 0) { // HP�� ������ ��
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread ����
					nowGauge.start(); // Thread �۵�
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // �Ҹ�� ü���� �����ֱ� ���� removeRed method ȣ��
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // ���� �̵� Ƚ���� �����ִ� remainingCount ����
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // �̵��� ��Ÿ�� ��ġ�� �ٲ���
				} else { // ���� �̵� Ƚ���� ���� ��
					GaugePanel.remainingCount.setText("" + 0); // ���� �̵� Ƚ���� �����ִ� remainingCount 0���� ����
					GaugePanel.removeRed(max_movement3, max_movement3); // ü���� ��� �����ֱ� ���� removeRed method ȣ��
				}
				break;
			case KeyEvent.VK_LEFT:
				if (mapArr[s_y][s_x - 1] == 0) {// ��Ÿ ������ ���̸� �̵� �Ұ�
				} else if (mapArr[s_y][s_x - 1] == 3) { // ���ʿ� ������ �ִٸ�
					for (present_count = 0; present_count < 4; present_count++) { // ���ʿ� �ִ� ������ � �������� Ž��
						if (p_y[present_count] == s_y && p_x[present_count] == s_x - 1) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count]][p_x[present_count] - 1] == 0) { // ���� ������ ���̸�
					} // if
					else if (mapArr[p_y[present_count]][p_x[present_count] - 1] == 1) { // ���� ������ ���̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						p_x[present_count] -= 1; // ���� ��ǥ ��ĭ ���� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // ���� �ű���� �ε��� 3(����)
						MovePresent(p_x[present_count], p_y[present_count], present_count); // ���� �ű�� �Լ�
						s_x -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++;// �̵�Ƚ�� ����
					} else if (mapArr[p_y[present_count]][p_x[present_count] - 1] == 4) { // ���� ������ �縻�̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						RemovePresent(p_x[present_count], p_y[present_count], present_count); // ������ �縻�ȿ� ������ �ش� ������
						// �����ִ� �Լ�
						p_x[present_count] -= 1; // ���� ��ǥ ��ĭ �������η� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // ���� �ű���� �ε��� 5(������ �縻)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]); // �縻�� ������ �ִ� �Լ�
						s_x -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					}
				} else if (mapArr[s_y][s_x - 1] == 5) { // ��Ÿ ���ʿ� ���� �� �縻�� ������
					if (mapArr[s_y][s_x - 2] == 4) { // �� ������ �縻�̶��
						mapArr[s_y][s_x - 2] = 5; // �� ���� ����
						mapArr[s_y][s_x - 1] = 4; // �� ���� ����
						// �ٸ� �縻�� ������ �ű�
						remove_Present_from_Sock(s_x - 1, s_y);// ���� ����
						insert_Present_in_Sock(s_x - 2, s_y); // ���� �ֱ�
						s_x -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					} else if (mapArr[s_y][s_x - 2] == 1) { // �� ������ ���̶��
						mapArr[s_y][s_x - 2] = 3; // �� ���� ����
						mapArr[s_y][s_x - 1] = 4; // �� ���� ����
						remove_Present_from_Sock(s_x - 1, s_y);// ���� ����
						Take_out_Present_from_Sock(s_x - 2, s_y); // ������ �縻���� ������ ���� �Լ�
						s_x -= 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++; // �̵�Ƚ�� ����
					}
				} else {// ������ ���(���� ���)
					MainPanel.movecount++; // �̵�Ƚ�� ����
					s_x -= 1; // ��Ÿ ��ġ ����
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(���� �̵� Ƚ��) ����
				if (MainPanel.HP >= 0) { // HP�� ������ ��
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread ����
					nowGauge.start(); // Thread �۵�
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // �Ҹ�� ü���� �����ֱ� ���� removeRed method ȣ��
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // ���� �̵� Ƚ���� �����ִ� remainingCount ����
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // �̵��� ��Ÿ�� ��ġ�� �ٲ���
				} else { // ���� �̵� Ƚ���� ���� ��
					GaugePanel.remainingCount.setText("" + 0); // ���� �̵� Ƚ���� �����ִ� remainingCount 0���� ����
					GaugePanel.removeRed(max_movement3, max_movement3); // ü���� ��� �����ֱ� ���� removeRed method ȣ��
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (mapArr[s_y][s_x + 1] == 0) {// ��Ÿ �������� ���̸� �̵� �Ұ�
				} else if (mapArr[s_y][s_x + 1] == 3) { // �����ʿ� ������ �ִٸ�
					for (present_count = 0; present_count < 4; present_count++) { // �����ʿ� �ִ� ������ � �������� Ž��
						if (p_y[present_count] == s_y && p_x[present_count] == s_x + 1) {
							break;
						} // if
					} // for
					if (mapArr[p_y[present_count]][p_x[present_count] + 1] == 0) { // ���� �������� ���̸�
					} // if
					else if (mapArr[p_y[present_count]][p_x[present_count] + 1] == 1) { // ���� �������� ���̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						p_x[present_count] += 1; // ���� ��ǥ ��ĭ ���� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 3; // ���� �ű���� �ε��� 3(����)
						MovePresent(p_x[present_count], p_y[present_count], present_count);// ���� �ű�� �Լ�
						s_x += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++;// �̵� Ƚ�� ����
					} else if (mapArr[p_y[present_count]][p_x[present_count] + 1] == 4) { // ���� �������� �縻�̸�
						mapArr[p_y[present_count]][p_x[present_count]] = 1; // ���� ������ġ �ε��� 1 (��)
						RemovePresent(p_x[present_count], p_y[present_count], present_count);// ������ �縻�ȿ� ������ �ش� ������ �����ִ�
						// �Լ�
						p_x[present_count] += 1; // ���� ��ǥ ��ĭ �������η� �̵�
						mapArr[p_y[present_count]][p_x[present_count]] = 5; // ���� �ű���� �ε��� 5(������ �縻)
						insert_Present_in_Sock(p_x[present_count], p_y[present_count]);// �縻�� ������ �ִ� �Լ�
						s_x += 1;// ��Ÿ ��ġ ����
						MainPanel.movecount++;// �̵� Ƚ�� ����
					}
				} else if (mapArr[s_y][s_x + 1] == 5) { // ��Ÿ �����ʿ� ���� �� �縻�� ������
					if (mapArr[s_y][s_x + 2] == 4) { // �� �������� �縻�̶��
						mapArr[s_y][s_x + 2] = 5; // �� ���� ����
						mapArr[s_y][s_x + 1] = 4; // �� ���� ����
						// �ٸ� �縻�� ������ �ű�
						remove_Present_from_Sock(s_x + 1, s_y);// ���� ����
						insert_Present_in_Sock(s_x + 2, s_y); // ���� �ֱ�
						s_x += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++;// �̵� Ƚ�� ����
					} else if (mapArr[s_y][s_x + 2] == 1) { // �� �������� ���̶��
						mapArr[s_y][s_x + 2] = 3; // �� ���� ����
						mapArr[s_y][s_x + 1] = 4; // �� ���� ����
						remove_Present_from_Sock(s_x + 1, s_y);// ���� ����
						Take_out_Present_from_Sock(s_x + 2, s_y);// ������ �縻���� ������ ���� �Լ�
						s_x += 1; // ��Ÿ ��ġ ����
						MainPanel.movecount++;// �̵� Ƚ�� ����
					}
				} else { // ������ ���(���� ���)
					MainPanel.movecount++;// �̵� Ƚ�� ����
					s_x += 1;// ��Ÿ ��ġ ����
				}
				MainPanel.HP = max_movement3 - MainPanel.movecount; // HP(���� �̵� Ƚ��) ����
				if (MainPanel.HP >= 0) { // HP�� ������ ��
					nowGauge.initInstanceDatas(MainPanel.HP); // Thread ����
					nowGauge.start(); // Thread �۵�
					GaugePanel.removeRed(max_movement3, MainPanel.movecount); // �Ҹ�� ü���� �����ֱ� ���� removeRed method ȣ��
					GaugePanel.remainingCount.setText("" + MainPanel.HP); // ���� �̵� Ƚ���� �����ִ� remainingCount ����
					lblSanta.setBounds(s_x * 50, s_y * 50, 50, 50); // �̵��� ��Ÿ�� ��ġ�� �ٲ���
				} else { // ���� �̵� Ƚ���� ���� ��
					GaugePanel.remainingCount.setText("" + 0); // ���� �̵� Ƚ���� �����ִ� remainingCount 0���� ����
					GaugePanel.removeRed(max_movement3, max_movement3); // ü���� ��� �����ֱ� ���� removeRed method ȣ��
				}
				break;
			}// switch

			if (MainPanel.HP < 0) {// ü���� ��� �Ҹ�Ǿ������ ������ϴ��� ���� ��ȭ����
				// ��ȭ���ڿ��� Yes�� 0, No�� ���ڸ� ������ 1 ���� ����
				int ans = JOptionPane.showConfirmDialog(MainPanel.S3, "����.   �ٽ��Ͻðڽ��ϱ�?", "����",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (ans == 0) {// Yes
					InitStage3();// �ʱ�ȭ
					MainPanel.S3.requestFocus(true);
				} else {// No
					System.exit(0);// ��������
				}
			}

			if (emptysocks_count == 0) {// ���� Ŭ�����
				// Ȯ�ι�ư�� �ִ� ��ȭ����
				JOptionPane.showMessageDialog(MainPanel.S3, "�����մϴ�. ��� ���������� Ŭ�����ϼ̽��ϴ�.", "����!",
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