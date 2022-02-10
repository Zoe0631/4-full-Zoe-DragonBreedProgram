4-full-Zoe-DragonBreedProgram
===================================
드래곤 키우는 프로그램 만들기 
--------------------------------
## 프로젝트 시나리오 설명

  드래곤 알을 부화시켜서 어른이 될 때까지 키우는 '드래곤 키우기 게임 프로그램'입니다.
  
  드래곤의 호감도를 얻기 위한 다양한 행동을 취할 수 있으며 드래곤의 성장단계에 따라 취할 수 있는 행동이 다릅니다.
  
  드래곤이 어른이 됐을 때 드래곤의 호감도에 따라 엔딩이 나뉩니다.
  
 --------------------------------------------------------------------
 ## 프로젝트 클래스 설명
  
  ### A. Map
  #### Home.class
      드래곤과 플레이어가 사는 '집' 클래스입니다.
      '집 온도' 필드가 있으며 집의 온도를 조절하기 위한 메서드가 있습니다.
      온도는 스레드에 의해 일정간격마다 변합니다
      온도는 드래곤이 알에서 부화했을 때 성별에 영향을 끼치며 온도가 25이하면 드래곤이 부화에 실패하고 사망합니다.
      
 ### B. 드래곤 클래스 ( 성장단계별로 클래스 구현)
 ####  한눈에 보기
 ![Untitled Workspace (3)](https://user-images.githubusercontent.com/98700133/153141040-e48456cb-5f51-4f0e-899f-4eb0e020128f.jpg)

  
  
* 드래곤 성장단계: Egg -> Hatchling -> Juvenile -> Adult
*   참고사항

  1. Adult 상태때는 엔딩 화면만 보여주기 때문에 클래스는 따로 구현하지 않음

  2. Hatchling과 Juvenile은 동일한 드래곤이지만 성장단계가 다른 것 뿐입니다. 한 클래스에 구현하면 따로 분기처리를 해줘야하고
     소속 멤버들도 다르기 때문에 다른 클래스에 각각 구현해줬습니다.
 
#### 0. Dragon(추상클래스)

 * 드래곤 성장단계의 공통 사항이 들어갑니다.
 * 멤버변수
   + name (이름)
      - String
    + likeable (호감도)
      - byte
    + evolution (진화게이지)
      - byte
 * 멤버메서드
    + is_evolution (진화조건 만족체크 메서드)
      - 추상메서드, boolean 값 반환
  
#### 1-1. Egg extends Dragon

* 성장단계가 알 일때의 클래스

* 멤버메서드
  + talk_to (알에게 말걸기)
  + increase_degree_control (Home 온도 상승)
  + decrease_degree_control (Home 온도 하락)
  + is_evolution (진화조건 만족 체크)
    - 오버라이딩

#### 1-2. Reptile extends Dragon(추상클래스)

 * 알에서 부화한 이후의 성장단계
 
 * 멤버변수
   + full (포만감)
      - int
   + attack (공격력)
      - int
   + attack_speed (공격속도)
      - int
   + hp (체력)
     - int
   + gender (성별)
     - enum : Gender
 * 멤버메서드
   + stroke (드래곤 쓰다듬기)
   + wash (드래곤 씻기기) 
   + fight (드래곤과 싸우기)
      - 드래곤이 몬스터를 공격하는 스레드
      - 몬스터가 드래곤을 공격하는 스레드
   + feed (드래곤에게 밥주기)
     - 추상메서드
 
#### 2-1. Hatchling extends Reptile

 * 유아기 드래곤 클래스
 
 * 멤버메서드
   + feed (드래곤에게 밥주기)
      - 오버라이딩
   + is_evolution (진화조건 만족 체크)
      - 오버라이딩

#### 2-2. Juvenile extends Reptile

 * 성장기 드래곤 클래스
 
 * 멤버변수
    + flight_proficiency (비행숙련도)
      - int
 * 멤버메서드
    + flying_practice (비행연습하기)
    + feed (드래곤에게 밥주기)
      - 오버라이딩
    + is_evolution (진화조건 만족 체크)
      - 오버라이딩

### C. 몬스터 클래스
 * 드래곤과 싸울 대상 클래스
    + hp (체력)
      - int
    + attack (공격력)
      - int
    + attack_speed (공격속도)
      - int


*그 밖에 GUI 관련 클래스들이 있습니다.

----------------------------------------------
## 폴더구조

![image](https://user-images.githubusercontent.com/98700133/153142235-3a220b07-a6a5-4c49-a6bc-6e56ab61ef47.png)

* Dragon 폴더: Dragon 클래스들 상속받은 클래스들

* enums 폴더: enum 정의 클래스들

* GuiRelatedClass, GuiTodoList: Gui 관련 클래스들

* Image: 배경, 드래곤 이미지

* Main: 메인 메서드와 메인 프레임

* other: Monster 클래스와 Home 클래스

 ----------------------------------------------
 ## 사용 언어 및 패키지
 
 * 사용언어: Java를 사용했습니다.
 
 * 사용패키지: 주로 JAVA GUI 관련 패키지 SWING/AWT들을 사용했습니다.
 
    SWING: JFrame, JPanel, JButton, JComboBox, JLayeredPane  를 상속해서 필요한 클래스들을 만들어 사용했습니다.
    
    AWT: 이벤트 관련 클래스/Font/Color 등의 클래스 사용
 
 ------------------------------------------------
 ## 문제 발생과 해결 방안
 
 ### 문제1
 
  * 문제 발생 클래스: Reptile
    + 문제 상황과 관련된 선언 멤버: fight 메서드(싸우기 메서드), fight 메서드에 필요한 hp 필드
    + hp 필드는 사람마다 건강 상태가 다르듯 100~130 사이입니다.
    
  * 문제
  
      드래곤 객체 생성 시 hp(체력) 필드를 100~130 사이로 초기화했습니다. 
      그런데 객체가 생성되자마자 hp가 0 이하면 게임이 종료되는 종료 조건에 걸려 게임이 종료되는 버그가 발생했습니다.

  * 문제를 해결하기 위해 한 노력
      
      1~10 사이였던 hp 필드 범위를 100 이상 130이하로 바꾼 뒤 생긴 문제라 문제 발생 원인을 특정하기 쉬웠습니다. hp 필드를 콘솔창에 출력해보니 -128이 나왔습니다.

  * 해결
    
      이는 hp 필드의 자료형이 byte였는데  hp가 byte 표현 범위를 벗어나서 생기는 일이었습니다. (byte 표현범위: -128~127)
      자료형의 범위를 벗어났을 경우 쓰레기 값이 나오는 것을 기존에 알고 있었기 때문에 -128이라는 출력 값을 보자마자 자료형을 int로 바꿔줘서 문제를 해결할 수 있었습니다.
      
      
      
 ### 문제2
 
  * 문제 발생 클래스(GUI)
    + 드래곤을 그려주는 그리기 전용 패널(A)과 드래곤의 행동 메서드를 호출해주는 버튼들을 관리해주는 패널(B)
  * 문제
          
      A패널에 B 패널을 추가했을 때 B패널이 시각적으로 보이지 않는 문제가 발생했습니다.
  
  * 원인
    
      이는 A패널의 이미지 아래에 B패널이 추가되기 때문인 것 같았습니다.
      → B패널이 보이지 않다가 프로그램 진행 상황에 따라 A패널에 아예 새로운 이미지를 그려줄 때, 
      B패널이 갑자기 보일 때가 있어서 컴포넌트들 간의 순서상의 이슈가 있다고 판단했습니다.
      
   * 해결

     + 필요 사전 지식 : JLayerPane은 Java Swing(GUI)의 컨테이너에 깊이를 적용해서 컨테이너들을 겹쳐서 사용할 수 있도록 하는 컨테이너입니다.
                       이 컨테이너 객체에 컴포넌트들을 추가할 때 보여줄 순서를 정해줄 수 있습니다.
                       
      ![image](https://user-images.githubusercontent.com/98700133/153046111-a2e1041c-ba93-4c0f-b64a-60b4cf4c5100.png)

                    
     + 위의 지식을 사용해서 JLayerPane 클래스의 C패널을 생성해서 A패널과 B패널을 추가할 때 순서를 정해줘서 B패널이 A패널 위에 오게 할 수 있었습니다.
     
----------------------------------------------------------------
## 프로젝트 진행 후 느낀점과 개선하고 싶은 점

### 느낀점

  1. 기존에 있던 클래스와 코드가 중복되는 클래스를 상속받아 써서 코드 재사용을 막고 보다 간결하게 표현할 수 있었습니다. 
  
  2. 스레드를 프로그램에 추가한 뒤 보다 현실성을 부여해서 프로그램의 완성도가 올라갔다는 생각이 들었습니다.
  
  3. '어떻게 하면 유저가 더 편리하게 사용할 수 있을까' 에 대한 내용을 중점적으로 생각하며 프로그램을 만드는 마음가짐을 가질 수 있었습니다.
  

### 개선하고 싶은 점 

  1. Dragon을 상속받은 각 클래스에 현재 성장단계를 표시해주는 enum 필드를 추가했다면 성장단계 별 분기처리를 할 때 더 편리했을 것 같습니다. 
  
  2. 드래곤의 성장단계별로 클래스를 따로 구현하는 것보다 하나의 클래스에 성장단계 별 접근 가능 멤버를 분기처리 해놓는게 
     드래곤 객체가 성장하는 시나리오 상으로는 더 합리적인 설계가 됐을 것 같습니다.
     
 -----------------------------------------------------------------
## 게임 플레이 실행 캡쳐

* 로딩창
 ![image](https://user-images.githubusercontent.com/98700133/153118622-3b9f918a-aaf3-41ab-93dc-36427135dec9.png)
  
   enter를 누르면 드래곤 이름 입력창이 뜹니다.
  
* 이름입력 창
  ![image](https://user-images.githubusercontent.com/98700133/153118742-88384669-0447-4379-9da0-3ca58f90cd10.png)

  이름을 타이핑 한 뒤 '입력' 버튼창을 누르면 게임이 시작됩니다.
  
 * 게임 화면
  ![image](https://user-images.githubusercontent.com/98700133/153118811-d63a9632-0ec4-4458-882c-2fed5f628ef4.png)

  오른쪽 상단의 TodoList의 버튼들을 눌러서 Dragon을 상속받은 클래스들의 행동 메서드들을 호출합니다.
 
  ![image](https://user-images.githubusercontent.com/98700133/153118982-089e6cc0-90b3-4e63-8f57-f05ab5e2d9bb.png)

  행동에 따라 드래곤 객체의 필드값들이 변하며 '상태창' 버튼을 눌러서 변화값을 확인할 수 있습니다.
  
  ![image](https://user-images.githubusercontent.com/98700133/153194222-cfe25cb4-980c-45f2-93c1-b7417987d269.png)

  '싸우기' 버튼을 누르면 드래곤과 몬스터가 공격속도에 따라 독립적으로 공격합니다.
  
 * 엔딩 화면
 
  ![image](https://user-images.githubusercontent.com/98700133/153135055-8626f968-f4b7-4837-be84-c94ab7c38ed2.png)

  호감도에 따라 엔딩이 갈립니다.

  
 
