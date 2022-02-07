package other;

import java.util.Random;

import javax.swing.JOptionPane;

import Component.FightDialoguePanel;
import Dragon.Hatchling;
import Dragon.Reptile;
import Main.MainFrame;

//����
public class Monster {
	// ü��
	public byte hp;
	// ���ݷ�
	public byte attack;
	// ���ݼӵ�
	public byte attack_speed;

	public Monster() {
		Random random = new Random();
		// ü�°� ���ݷ� �����ϰ� ����
		hp = (byte) (100 + (byte) random.nextInt(30));
		attack = (byte) (20 + (byte) random.nextInt(10));
		attack_speed = (byte) random.nextInt(10);
	}

	// ���� �޼���

	public void MonsterAttack(Reptile reptile) {
		// ���� ���� ���� ������
		Thread monster_attack = new Thread(new MonsterAttackThread(reptile));
		monster_attack.start();
	}

	// ������ ���Ͱ� �����ϴ� ������
	class MonsterAttackThread implements Runnable {
		Reptile target_reptile;

		public MonsterAttackThread(Reptile reptile) {
			target_reptile = reptile;
		}

		@Override
		public void run() {
			boolean is_fight = true;
			while (is_fight) {
				try {
					// ���ݼӵ� �ݿ��ؼ� ����
					Thread.sleep(5000 / attack_speed);
					// ���� ü�� 0�̸� ����
					if (hp <= 0) {
						// �ο� ����
						is_fight = false;
						// �˸� �г� �Ⱥ��̰��ϱ�
						MainFrame.fight_dialogue_panel.setVisible(false);
					}
					// ������ ���ݷ¸�ŭ �巡�� ü�� �϶�
					target_reptile.hp -= attack;
					// �巡�� ü�� üũ
					// �巡���� ü�� 0 �̻�
					if (target_reptile.hp > 0) {
						
						FightDialoguePanel.insert_dialogue("���Ͱ� �����ؿԴ�. (���� �巡�� ü��: " + target_reptile.hp + ")");
					}
					// �巡�� ü�� 0�� ��
					else if (target_reptile.hp <= 0) {
						FightDialoguePanel.insert_dialogue("���Ͱ� �����ؿԴ�. (���� �巡�� ü��: 0)");
						// �ο� ����
						is_fight = false;
						// �˸� �г� �Ⱥ��̰��ϱ�
						MainFrame.fight_dialogue_panel.setVisible(false);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}
}