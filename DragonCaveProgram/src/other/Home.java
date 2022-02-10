package other;

import java.util.Random;

import GuiRelatedClass.PaintManager;
import Main.MainFrame;
import enums.Growth;

// �巡���� ����ܰ谡 Egg �� �� ���� �µ��� �������� Home Ŭ����
public class Home {
	// ���� �µ�
	// ���� �µ��� �巡���� ������ �� ������ �� ��� ���ο� ������ �ݴϴ�.
	public byte home_degree;

	// ���µ����� �޼���
	// �µ� �ø��� �޼���
	public void increase_degree() {
		home_degree += 2;
	}

	// �µ� ������ �޼���
	public void decrease_degree() {
		home_degree -= 2;
	}

	public Home() {
		home_degree = 33;
		// ���µ� ��ȭ ������ ȣ��
		Thread home_thread = new Thread(new HomeWithThread());
		home_thread.start();
	}

	// �� �µ� ��ȭ ������
	// 0.5 �� �������� �����ϰ� �µ��� �������ų� �ö󰩴ϴ�.
	class HomeWithThread implements Runnable {
		@Override
		public void run() {
			Random random = new Random();
			boolean is_egg = true;
			while (is_egg) {

				try {
					// ������°� Hatchling�� �� ������ ����
					if (PaintManager.stage == Growth.HATCHLING) {
						MainFrame.home = null;
						is_egg = false;
						break;
					}
					// 0.5 �ʿ� �ѹ��� �� �µ� ��� Ȥ�� �϶�
					Thread.sleep(500);
					byte random_byte = (byte) random.nextInt(2);
					// �µ� �϶�
					if (random_byte == 0) {
						home_degree -= 2;

						// �µ� ���
					} else if (random_byte == 1) {
						home_degree += 1;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			;
		}
	}
}