package Dragon;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import Component.DialoguePanel;
import Component.PaintManager;
import Main.MainFrame;
import other.BackGround;

//2차 상속
public class Juvenile extends Reptile {
	// 비행 숙련도
	public byte flight_proficiency;

	public Juvenile(Hatchling hatchling_dragon) {
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)가 성장기가 됐어요.");

		Random random = new Random();

		// 이름
		name = hatchling_dragon.name;
		// 호감도
		likeable = hatchling_dragon.likeable;
		// 진화 게이지
		evolution = 0;
		// 체력
		hp = hatchling_dragon.hp + random.nextInt(5);
		// 공격력
		attack = hatchling_dragon.attack + random.nextInt(20);
		// 공격속도
		attack_speed = hatchling_dragon.attack_speed + random.nextInt(10);
		// 포만감
		full = hatchling_dragon.full + 10;
		// 성별
		gender = hatchling_dragon.gender;
		// 비행 숙련도
		flight_proficiency = 0;

	}

	// 비행 연습
	public void flying_practic() {
		// 알림창
		JOptionPane.showMessageDialog(null, MainFrame.dragon.name + "(이)와 비행연습을 갔어요!");
		// 타이머(2초간 정지 후 알림 메세지)
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				// 랜덤하게 비행숙련도 오름
				Random random = new Random();
				byte random_byte = (byte) (1 + random.nextInt(5));

				// 행동에 따른 드래곤 상태값 변경
				Juvenile downcast_juvenile = (Juvenile) MainFrame.dragon;
				downcast_juvenile.flight_proficiency += random_byte;
				downcast_juvenile.likeable += 3;
				downcast_juvenile.evolution++;
				MainFrame.dragon = downcast_juvenile;

				// 알림창
				JOptionPane.showMessageDialog(null,
						"뭔가 어설프지만... 언젠간 날 수 있을지도?(호감도+3, 진화게이지+1, 비행숙련도+" + random_byte + ")");

				// 비행 배경 대신 집 배경 보여주도록 상태 바꿔주기
				PaintManager.background = BackGround.HOME;
				MainFrame.main_background.repaint();

				// 할일목록 보이게 하기
				MainFrame.todolist_juvenile.setVisible(true);
			}
		};
		Timer timer = new Timer("Timer");
		timer.schedule(task, 2000L);

	}

	// 밥주기 (오버라이딩)
	@Override
	public void feed(String what_eat) {
		Juvenile downcast_juvenile = (Juvenile) MainFrame.dragon;
		// { "먹이기", "과일", "슈퍼웜", "작은동물" };
		if (what_eat.equals("과일")) {
			// 행동에 따른 드래곤 상태값 변경
			downcast_juvenile.likeable += 2;
			downcast_juvenile.evolution++;
			downcast_juvenile.full += 1;
			downcast_juvenile.hp += 1;

			// 알림문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 과일을 줬어요! (호감도+2, 진화게이지+1, 포만감+1, 체력+1)");

		} else if (what_eat.equals("슈퍼웜")) {
			// 행동에 따른 드래곤 상태값 변경
			downcast_juvenile.likeable -= 3;
			downcast_juvenile.evolution++;
			downcast_juvenile.full += 2;
			downcast_juvenile.hp += 2;

			// 알림문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 슈퍼웜을 줬어요! (호감도-3, 진화게이지+1, 포만감+4, 체력+2)");

		} else if (what_eat.equals("작은동물")) {
			// 행동에 따른 드래곤 상태값 변경
			downcast_juvenile.likeable += 5;
			downcast_juvenile.evolution += 3;
			downcast_juvenile.full += 5;
			downcast_juvenile.hp += 7;
			// 알림문구
			DialoguePanel.insert_dialogue(MainFrame.dragon.name + "(이)에게 작은동물을 줬어요! (호감도+5, 진화게이지+2, 포만감+5, 체력+7)");

		}
		MainFrame.dragon = downcast_juvenile;
	}

	// 진화체크
	@Override
	public boolean is_evolution() {
		Juvenile downcast_juvenile = (Juvenile) MainFrame.dragon;
		byte likeable_23 = downcast_juvenile.likeable;
		byte evolution_15 = downcast_juvenile.evolution;
		// 게임종료 조건
		// 체력이 0 이하면 게임 종료
		if (downcast_juvenile.hp <= 0) {
			JOptionPane.showMessageDialog(null, "체력이 0 이하라 " + downcast_juvenile.name + "(이)가 죽었습니다. 게임을 종료합니다");
			System.exit(0);
		}
		// 포만감 0이면 게임 종료
		if (downcast_juvenile.full <= 0) {
			JOptionPane.showMessageDialog(null, "포만감이 0 이하라 " + downcast_juvenile.name + "(이)가 죽었습니다. 게임을 종료합니다");
			System.exit(0);
		}
		// 진화조건 만족
		// 호감도 23 이상이면 진화
		if (likeable_23 > 23) {
			// 알림문구
			DialoguePanel.insert_dialogue("호감도 " + likeable_23 + "(으)로 성장!");
			// 진화조건 만족 시 true 반환
			return true;

			// 진화게이지 15 이상이면 진화
		} else if (evolution_15 > 15) {
			// 알림문구

			DialoguePanel.insert_dialogue("진화게이지 " + evolution_15 + "(으)로 성장!");

			return true;
		}
		return false;
	}

}
