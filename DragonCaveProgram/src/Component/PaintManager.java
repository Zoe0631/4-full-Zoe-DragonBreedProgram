package Component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import other.BackGround;
import other.Growth;

public class PaintManager extends JPanel {
	// ��溰�׸�
	private Image loadingScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/loading.png").getImage();
	private Image mainScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/home.jpg").getImage();
	private Image fightScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/yard_1.png").getImage();
	private Image flightScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/flight.png").getImage();
	private Image happy_endScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/happyending.png").getImage();
	private Image endScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/normalending.png").getImage();;
	private Image sad_endScreen = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/sadending.png").getImage();;

	// �巡���� ����ܰ躰 �׸�
	private Image egg_image = new ImageIcon("C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/egg.png")
			.getImage();
	private Image hatchling_image = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/hatchling.png").getImage();
	private Image juvenile_image = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/juvenile.png").getImage();
	private Image adult_image = new ImageIcon(
			"C:/Users/yeji1/git/repository/dragon_breeding/DragonCaveProgram/src/Image/adult.png").getImage();

	// �׷��� ���� ����(enum)
	public static Growth stage; // ������ �巡���� ����ܰ� ����
	public static BackGround background; // ������ ����� ����

	public PaintManager() {
		setSize(1280, 720);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		setVisible(true);
		init();
	}

	// �ʱ�ȭ
	private void init() {
		// ������ �׸� ���� �ʱ�ȭ
		stage = Growth.EGG;
		background = BackGround.LOADING;

	}

	public void paintComponent(Graphics g) {
// ������ �׸� �б�ó��
		// �ε�â
		if (background == BackGround.LOADING) {
			g.drawImage(loadingScreen, 0, 0, null);
			// �ο����
		} else if (background == BackGround.FIGHT) {
			g.drawImage(fightScreen, 0, 0, null);

			switch (stage) {
			case HATCHLING:

				g.drawImage(hatchling_image, 200, 500, null);
				break;
			case JUVENILE:

				g.drawImage(juvenile_image, 200, 500, null);
				break;

			}
			// �������
		} else if (background == BackGround.FLIGHT) {
			g.drawImage(flightScreen, 0, 0, null);
			g.drawImage(juvenile_image, 1000, 550, null);
			// ��
		} else if (background == BackGround.HOME) {
			g.drawImage(mainScreen, 0, 0, null);
			// ����â�� ���� �巡�� �׸�
			switch (stage) {
			case EGG:

				g.drawImage(egg_image, 550, 350, null);

				break;

			case HATCHLING:

				g.drawImage(hatchling_image, 580, 350, null);
				break;
			case JUVENILE:

				g.drawImage(juvenile_image, 580, 350, null);
				break;
			case ADULT:
				g.drawImage(adult_image, 580, 350, null);
				break;

			}
			// ���ǿ���
		} else if (background == BackGround.HAPPY_END) {
			g.drawImage(happy_endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);
			// �븻����
		} else if (background == BackGround.NORMAL_END) {
			g.drawImage(endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);
			// ���忣��
		} else if (background == BackGround.SAD_END) {
			g.drawImage(sad_endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);

		}

	}

}
