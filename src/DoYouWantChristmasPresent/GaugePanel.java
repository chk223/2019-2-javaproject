package DoYouWantChristmasPresent;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GaugePanel extends JPanel {

	ImageIcon imgFace;
	static JLabel imgLabel;
	static JPanel hpPanel;
	static JPanel[] Gauges;
	static JPanel limitCount;
	static JLabel remainingLabel, remainingCount;

	public GaugePanel() {
		setBounds(50, 550, 650, 100);
		setLayout(null);

		imgFace = new ImageIcon("image/SantaFace.png");
		imgLabel = new JLabel(imgFace);
		imgLabel.setBounds(20, 25, 50, 50);
		add(imgLabel);

		hpPanel = new JPanel();
		hpPanel.setBounds(85, 40, 400, 20);
		hpPanel.setLayout(null);
		hpPanel.setBorder(new LineBorder(Color.black));
		add(hpPanel);

		limitCount = new JPanel();
		limitCount.setBounds(525, 20, 100, 65);

		remainingLabel = new JLabel();
		remainingLabel.setText("남은 이동 횟수");
		limitCount.add(remainingLabel);

		remainingCount = new JLabel();
		remainingCount.setText("" + 0);
		remainingCount.setBounds(525, 50, 100, 65);
		limitCount.add(remainingCount);

		add(limitCount);

	}

	public GaugePanel(int limit) {
		setBounds(50, 550, 650, 100);
		setLayout(null);

		imgFace = new ImageIcon("image/SantaFace.png");
		imgLabel = new JLabel(imgFace);
		imgLabel.setBounds(20, 25, 50, 50);
		add(imgLabel);

		hpPanel = new JPanel();
		hpPanel.setBounds(85, 40, 400, 20);
		hpPanel.setLayout(null);
		hpPanel.setBorder(new LineBorder(Color.black));
		add(hpPanel);

		limitCount = new JPanel();
		limitCount.setBounds(525, 20, 100, 65);

		remainingLabel = new JLabel();
		remainingLabel.setText("남은 이동 횟수");
		limitCount.add(remainingLabel);

		remainingCount = new JLabel();
		remainingCount.setText("" + limit);
		remainingCount.setBounds(525, 50, 100, 65);
		limitCount.add(remainingCount);

		add(limitCount);

		paintComponent(limit);
	}

	public void paintComponent(int limit) {
		Gauges = new JPanel[limit];

		for (int i = 0; i < limit; i++) {
			Gauges[i] = new JPanel();
			Gauges[i].setVisible(true);
			Gauges[i].setBackground(Color.red);
			Gauges[i].setBounds((400 / limit) * i, 0, 400 / limit, 20);
			MainPanel.GaugeViewPanel.hpPanel.add(Gauges[i]);
		} // for
	}

	public void removePaintGauge(int limit) {
		for (int i = 0; i < limit; i++)
			hpPanel.remove(Gauges[i]);
	}

	public static void removeRed(int max, int movecount) {

		for (int i = 0; i < movecount; i++) {
			Gauges[max - 1 - i].setVisible(false);
		}
	}
}