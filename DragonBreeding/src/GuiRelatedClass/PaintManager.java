package GuiRelatedClass;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import enums.BackGround;
import enums.Growth;

// 배경과 드래곤을 그려줄 그리기 전용 패널 클래스
public class PaintManager extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 배경 별 그림
	private Image loadingScreen = new ImageIcon(getClass().getClassLoader().getResource("loading.png")).getImage();
	private Image guideScreen = new ImageIcon(getClass().getClassLoader().getResource("guide.png")).getImage();
	private Image mainScreen = new ImageIcon(getClass().getClassLoader().getResource("home.jpg")).getImage();
	private Image fightScreen = new ImageIcon(getClass().getClassLoader().getResource("yard_1.png")).getImage();
	private Image flightScreen = new ImageIcon(getClass().getClassLoader().getResource("flight.png")).getImage();
	private Image happy_endScreen = new ImageIcon(getClass().getClassLoader().getResource("happyending.png")).getImage();
	private Image endScreen = new ImageIcon(getClass().getClassLoader().getResource("normalending.png")).getImage();;
	private Image sad_endScreen = new ImageIcon(getClass().getClassLoader().getResource("sadending.png")).getImage();;

	// 드래곤의 성장단계별 그림
	private Image egg_image = new ImageIcon(getClass().getClassLoader().getResource("egg.png")).getImage();
	private Image hatchling_image = new ImageIcon(getClass().getClassLoader().getResource("hatchling.png")).getImage();
	private Image juvenile_image = new ImageIcon(getClass().getClassLoader().getResource( "juvenile.png")).getImage();
	private Image juvenile_flight = new ImageIcon(getClass().getClassLoader().getResource( "juvenile_symmetry.png")).getImage();
	private Image adult_image = new ImageIcon(getClass().getClassLoader().getResource("adult.png")).getImage();

	// 어떤 배경과 드래곤을 그려줄지 정해주는 상태(enum) 변수
	public static Growth stage; // 보여줄 드래곤의 성장단계 상태
	public static BackGround background; // 보여줄 배경의 상태

	// 기본설정
	public PaintManager() {
		setSize(1280, 720);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(null);
		setVisible(true);
		init();
	}

	// 초기화
	private void init() {
		// 보여줄 배경과 드래곤 상태 초기화
		stage = Growth.EGG;
		background = BackGround.LOADING;

	}

	// 그리기 메서드 (JPanel의 메서드 오버라이딩)
	public void paintComponent(Graphics g) {
// 보여줄 그림 분기처리
		// 보여줄 배경: 로딩창
		if (background == BackGround.LOADING) {
			g.drawImage(loadingScreen, 0, 0, null);
			// 보여줄 배경: 가이드 화면
		} else if (background == BackGround.GUIDE) {
			g.drawImage(guideScreen, 0, 0, null);

			// 보여줄 배경: 싸움장면
		} else if (background == BackGround.FIGHT) {
			g.drawImage(fightScreen, 0, 0, null);

			switch (stage) {
			// 보여줄 드래곤: 유아기
			case HATCHLING:

				g.drawImage(hatchling_image, 200, 450, null);
				break;
			// 보여줄 드래곤: 성장기
			case JUVENILE:

				g.drawImage(juvenile_image, 200, 400, null);
				break;

			}
			// 보여줄 배경: 비행장면
		} else if (background == BackGround.FLIGHT) {
			g.drawImage(flightScreen, 0, 0, null);
			g.drawImage(juvenile_flight, 1000, 400, null);
			// 보여줄 배경: 집
		} else if (background == BackGround.HOME) {
			g.drawImage(mainScreen, 0, 0, null);
			switch (stage) {
			// 보여줄 드래곤: 알
			case EGG:

				g.drawImage(egg_image, 155, 275, null);

				break;
			// 보여줄 드래곤: 유아기
			case HATCHLING:

				g.drawImage(hatchling_image, 155, 310, null);
				break;
			// 보여줄 드래곤: 성장기
			case JUVENILE:

				g.drawImage(juvenile_image, 145, 280, null);
				break;
			// 보여줄 드래곤: 어른 (엔딩)
			case ADULT:
				g.drawImage(adult_image, 580, 300, null);
				break;

			}
			// 보여줄 배경: 해피엔딩
		} else if (background == BackGround.HAPPY_END) {
			g.drawImage(happy_endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);
			// 보여줄 배경: 노말엔딩
		} else if (background == BackGround.NORMAL_END) {
			g.drawImage(endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);
			// 보여줄 배경: 세드엔딩
		} else if (background == BackGround.SAD_END) {
			g.drawImage(sad_endScreen, 0, 0, null);
			g.drawImage(adult_image, 800, 100, null);

		}

	}

}
