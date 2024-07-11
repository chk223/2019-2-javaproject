package DoYouWantChristmasPresent;

import javax.swing.JPanel;

public class PanelThread extends JPanel implements Runnable {

	Thread myThread;
	int nowHP;
	int nSleepTime;

	public PanelThread(int HP) {
		super();
		initInstanceDatas(HP);
	}

	public void initInstanceDatas(int HP) {
		myThread = null;
		nowHP = HP;
		nSleepTime = 1000;
	}

	public void setSleepTime(int arg) {
		nSleepTime = arg;
	}

	public int getSleepTime() {
		return nSleepTime;
	}

	public void start() {
		if (myThread == null)
			myThread = new Thread(this); // thread»≠ Ω√ƒ—¡‹
		myThread.start(); // ¿⁄µø¿∏∑Œ run method play
	}

	public void stop() {
		if (myThread != null)
			myThread.stop();
	}

	@Override
	public void run() {
//      GaugePanel.Gauges[nowHP].setBackground(Color.red);
		while (MainPanel.HP == nowHP) {

			try {
				GaugePanel.Gauges[nowHP - 1].setVisible(false);
			} catch (Exception e) {
			}

			try {// 1sµøæ» ∏ÿ√Ë¥Ÿ∞° ¥ŸΩ√ µº
				myThread.sleep(nSleepTime);
			} catch (Exception e) {
			}

			try {
				GaugePanel.Gauges[nowHP - 1].setVisible(true);
			} catch (Exception e) {
			}

			try {// 1sµøæ» ∏ÿ√Ë¥Ÿ∞° ¥ŸΩ√ µº
				myThread.sleep(nSleepTime);
			} catch (Exception e) {
			}

		} // while

		if (nowHP == 1)
			try {
				GaugePanel.Gauges[0].setVisible(false);
			} catch (Exception e) {
			}
	}
}