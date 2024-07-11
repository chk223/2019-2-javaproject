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
//							if (_s1.mapArr[i - 1][j] == 0 || _s1.mapArr[i - 1][j] == 4) // 캐릭터 위가 벽/양말이면
//							{
//								_s1.m_count++;
//								// 이동회수 증가시켜야 함
//								// 이동은 x
//							} else if (_s1.mapArr[i - 1][j] == 1) // 땅이면
//							{
//								_s1.mapArr[i - 1][j] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i - 1][j] = _s1.InsertImage(_s1.mapArr[i - 1][j]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i - 1][j] == 3) // 선물이면
//							{
//								if (_s1.mapArr[i - 2][j] == 1) // 두칸 위가 땅이면
//								{
//									_s1.mapArr[i - 2][j] = 3; // 선물과 산타를 한칸씩 위로 이동
//									_s1.mapArr[i - 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i - 2][j] == 4) // 두칸 위가 양말이면
//								{
//									_s1.mapArr[i - 2][j] = 5; // 선물을 양말에 넣고 산타를 한 칸 위로 이동
//									_s1.mapArr[i - 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// 스테이지 클리어!
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
//							if (_s1.mapArr[i + 1][j] == 0 || _s1.mapArr[i + 1][j] == 4) // 산타 아래가 벽/양말이면
//							{
//								_s1.m_count++;
//								// 이동회수 증가시켜야 함
//								// 이동은 x
//							} else if (_s1.mapArr[i + 1][j] == 1) // 땅이면
//							{
//								_s1.mapArr[i + 1][j] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i + 1][j] = _s1.InsertImage(_s1.mapArr[i + 1][j]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i + 1][j] == 3) // 선물이면
//							{
//								if (_s1.mapArr[i + 2][j] == 1) // 두칸 아래가 땅이면
//								{
//									_s1.mapArr[i + 2][j] = 3; // 선물과 산타를 한칸씩 위로 이동
//									_s1.mapArr[i + 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i - 2][j] == 4) // 두칸 위가 양말이면
//								{
//									_s1.mapArr[i + 2][j] = 5; // 선물을 양말에 넣고 산타를 한 칸 위로 이동
//									_s1.mapArr[i + 1][j] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// 스테이지 클리어!
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
//							if (_s1.mapArr[i][j - 1] == 0 || _s1.mapArr[i][j - 1] == 4) // 산타 왼쪽이 벽/양말이면
//							{
//								_s1.m_count++;
//								// 이동회수 증가시켜야 함
//								// 이동은 x
//							} else if (_s1.mapArr[i][j - 1] == 1) // 땅이면
//							{
//								_s1.mapArr[i][j - 1] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i][j - 1] = _s1.InsertImage(_s1.mapArr[i][j - 1]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i][j - 1] == 3) // 선물이면
//							{
//								if (_s1.mapArr[i][j - 2] == 1) // 두칸 왼쪽이 땅이면
//								{
//									_s1.mapArr[i][j - 2] = 3; // 선물과 산타를 한칸씩 위로 이동
//									_s1.mapArr[i][j - 1] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i][j - 2] == 4) // 두칸 왼쪽이 양말이면
//								{
//									_s1.mapArr[i][j - 2] = 5; // 선물을 양말에 넣고 산타를 한 칸 위로 이동
//									_s1.mapArr[i][j - 1] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// 스테이지 클리어!
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
//							if (_s1.mapArr[i][j + 1] == 0 || _s1.mapArr[i][j + 1] == 4) // 산타 오른쪽이 벽/양말이면
//							{
//								_s1.m_count++;
//								// 이동회수 증가시켜야 함
//								// 이동은 x
//							} else if (_s1.mapArr[i][j + 1] == 1) // 땅이면
//							{
//								_s1.mapArr[i][j + 1] = 2;
//								_s1.mapArr[i][j] = 1;
//								_s1.m_count++;
//								_s1.map[i][j + 1] = _s1.InsertImage(_s1.mapArr[i][j + 1]);
//								_s1.map[i][j] = _s1.InsertImage(_s1.mapArr[i][j]);
//							} else if (_s1.mapArr[i][j + 1] == 3) // 선물이면
//							{
//								if (_s1.mapArr[i][j + 2] == 1) // 두칸 오른쪽이 땅이면
//								{
//									_s1.mapArr[i][j + 2] = 3; // 선물과 산타를 한칸씩 위로 이동
//									_s1.mapArr[i][j + 1] = 2;
//									_s1.mapArr[i][j] = 1;
//								} else if (_s1.mapArr[i][j + 2] == 4) // 두칸 오른쪽이 양말이면
//								{
//									_s1.mapArr[i][j + 2] = 5; // 선물을 양말에 넣고 산타를 한 칸 위로 이동
//									_s1.mapArr[i][j + 1] = 2;
//									_s1.mapArr[i][j] = 1;
//									_s1.emptysocks_count--;
//									if (_s1.emptysocks_count == 0) {
//										// 스테이지 클리어!
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
