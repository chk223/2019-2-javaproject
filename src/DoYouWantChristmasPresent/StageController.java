//package DoYouWantChristmasPresent;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class StageController {
//
//	Stage1 _s1;
//
//	public StageController() {
//
//		this._s1 = Appmanager.getInstance().getStage();
//		this._s1.addKeyListener(new KeyboardListener());
//	} // constructor
//
//	private class KeyboardListener implements KeyListener {
//
//		@Override
//		public void keyTyped(KeyEvent e) {
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//			switch (e.getKeyCode()) {
//			case KeyEvent.VK_UP:
//				for (int i = 0; i < 10; i++) {
//					for (int j = 0; j < 10; j++) {
//						if (_s1.mapArr[i][j] == 2) {
//							if (_s1.mapArr[i - 1][j] == 0 || _s1.mapArr[i - 1][j] == 4) // ĳ���� ���� ��/�縻�̸�
//							{
//								_s1.m_count++;
//								// �̵�ȸ�� �������Ѿ� ��
//								// �̵��� x
//							} else if (_s1.mapArr[i - 1][j] == 1) // ���̸�
//							{
//								_s1.mapArr[i - 1][j] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i - 1][j] = _s1.InsertImage(_s1.mapArr[i - 1][j]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i - 1][j] == 3) // �����̸�
//							{
//								if (_s1.mapArr[i - 2][j] == 1) // ��ĭ ���� ���̸�
//								{
//									_s1.mapArr[i - 2][j] = 3; // ������ ��Ÿ�� ��ĭ�� ���� �̵�
//									_s1.mapArr[i - 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i - 2][j] == 4) // ��ĭ ���� �縻�̸�
//								{
//									_s1.mapArr[i - 2][j] = 5; // ������ �縻�� �ְ� ��Ÿ�� �� ĭ ���� �̵�
//									_s1.mapArr[i - 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// �������� Ŭ����!
//									} // if
//								}
//								_s1.m_count++;
//								_s1.map[i - 2][j] = _s1.InsertImage(_s1.mapArr[i - 2][j]);
//								_s1.map[i - 1][j] = _s1.InsertImage(_s1.mapArr[i - 1][j]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							}
//						} // if
//					} // for(j)
//				} // for(i)
//				break;
//			case KeyEvent.VK_DOWN:
//				for (int i = 0; i < 10; i++) {
//					for (int j = 0; j < 10; j++) {
//						if (_s1.mapArr[i][j] == 2) {
//							if (_s1.mapArr[i + 1][j] == 0 || _s1.mapArr[i + 1][j] == 4) // ��Ÿ �Ʒ��� ��/�縻�̸�
//							{
//								_s1.m_count++;
//								// �̵�ȸ�� �������Ѿ� ��
//								// �̵��� x
//							} else if (_s1.mapArr[i + 1][j] == 1) // ���̸�
//							{
//								_s1.mapArr[i + 1][j] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i + 1][j] = _s1.InsertImage(_s1.mapArr[i + 1][j]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i + 1][j] == 3) // �����̸�
//							{
//								if (_s1.mapArr[i + 2][j] == 1) // ��ĭ �Ʒ��� ���̸�
//								{
//									_s1.mapArr[i + 2][j] = 3; // ������ ��Ÿ�� ��ĭ�� ���� �̵�
//									_s1.mapArr[i + 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i - 2][j] == 4) // ��ĭ ���� �縻�̸�
//								{
//									_s1.mapArr[i + 2][j] = 5; // ������ �縻�� �ְ� ��Ÿ�� �� ĭ ���� �̵�
//									_s1.mapArr[i + 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// �������� Ŭ����!
//									} // if
//								}
//								_s1.m_count++;
//								_s1.map[i + 2][j] = _s1.InsertImage(_s1.mapArr[i + 2][j]);
//								_s1.map[i + 1][j] = _s1.InsertImage(_s1.mapArr[i + 1][j]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							}
//						} // if
//					} // for(j)
//				} // for(i)
//				break;
//			case KeyEvent.VK_LEFT:
//				for (int i = 0; i < 10; i++) {
//					for (int j = 0; j < 10; j++) {
//						if (_s1.mapArr[i][j] == 2) {
//							if (_s1.mapArr[i][j - 1] == 0 || _s1.mapArr[i][j - 1] == 4) // ��Ÿ ������ ��/�縻�̸�
//							{
//								_s1.m_count++;
//								// �̵�ȸ�� �������Ѿ� ��
//								// �̵��� x
//							} else if (_s1.mapArr[i][j - 1] == 1) // ���̸�
//							{
//								_s1.mapArr[i][j - 1] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i][j - 1] = _s1.InsertImage(_s1.mapArr[i][j - 1]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i][j - 1] == 3) // �����̸�
//							{
//								if (_s1.mapArr[i][j - 2] == 1) // ��ĭ ������ ���̸�
//								{
//									_s1.mapArr[i][j - 2] = 3; // ������ ��Ÿ�� ��ĭ�� ���� �̵�
//									_s1.mapArr[i][j - 1] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i][j - 2] == 4) // ��ĭ ������ �縻�̸�
//								{
//									_s1.mapArr[i][j - 2] = 5; // ������ �縻�� �ְ� ��Ÿ�� �� ĭ ���� �̵�
//									_s1.mapArr[i][j - 1] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// �������� Ŭ����!
//									} // if
//								}
//								_s1.m_count++;
//								_s1.map[i][j - 2] = _s1.InsertImage(_s1.mapArr[i][j - 2]);
//								_s1.map[i][j - 1] = _s1.InsertImage(_s1.mapArr[i][j - 1]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							}
//						} // if
//					} // for(j)
//				} // for(i)
//				break;
//			case KeyEvent.VK_RIGHT:
//				for (int i = 0; i < 10; i++) {
//					for (int j = 0; j < 10; j++) {
//						if (_s1.mapArr[i][j] == 2) {
//							if (_s1.mapArr[i][j + 1] == 0 || _s1.mapArr[i][j + 1] == 4) // ��Ÿ �������� ��/�縻�̸�
//							{
//								_s1.m_count++;
//								// �̵�ȸ�� �������Ѿ� ��
//								// �̵��� x
//							} else if (_s1.mapArr[i][j + 1] == 1) // ���̸�
//							{
//								_s1.mapArr[i][j + 1] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i][j + 1] = _s1.InsertImage(_s1.mapArr[i][j + 1]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i][j + 1] == 3) // �����̸�
//							{
//								if (_s1.mapArr[i][j + 2] == 1) // ��ĭ �������� ���̸�
//								{
//									_s1.mapArr[i][j + 2] = 3; // ������ ��Ÿ�� ��ĭ�� ���� �̵�
//									_s1.mapArr[i][j + 1] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i][j + 2] == 4) // ��ĭ �������� �縻�̸�
//								{
//									_s1.mapArr[i][j + 2] = 5; // ������ �縻�� �ְ� ��Ÿ�� �� ĭ ���� �̵�
//									_s1.mapArr[i][j + 1] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// �������� Ŭ����!
//									} // if
//								}
//								_s1.m_count++;
//								_s1.map[i][j + 2] = _s1.InsertImage(_s1.mapArr[i][j + 2]);
//								_s1.map[i][j + 1] = _s1.InsertImage(_s1.mapArr[i][j + 1]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							}
//						} // if
//					} // for(j)
//				} // for(i)
//				break;
//			}// switch
//		}// keyPressed
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//		}
//	} // KeyboardListener class
//
//} // Stage controller
