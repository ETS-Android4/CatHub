# CatHub

> ### Contributor: 박승민, 윤정인   
*for 2021 Winter Madcamp Week01*

![ic_cathub_round](https://user-images.githubusercontent.com/54874529/148015089-e548cd17-fd06-4370-a861-7dec711c189b.png)

<p>
<img alt="Java" src="https://img.shields.io/badge/Java-007396.svg?&style=for-the badge&logo=Java&logoColor=white"/> 
<img alt="Android" src="https://img.shields.io/badge/Android-3DDC84.svg?&style=for-the badge&logo=Android&logoColor=white"/> 
</p>

<hr/>

## Splash
- Splash screen using **Lottie** library(https://github.com/LottieFiles/lottie-android)    
<img src="https://user-images.githubusercontent.com/54874529/148012712-454297a2-67f5-4759-a51f-4539ffc17446.gif" height="450px"/>

<hr/>

## Gallery
<img src="https://user-images.githubusercontent.com/54874529/148012618-3de28531-7a6e-4993-b940-7bdcc0375deb.gif" height="450px"/>    
<ul>
    <li> <h3> 카메라 Camera </h3> </li>
    <p>
        기기의 카메라를 이용하여 사진을 촬영하고 갤러리 탭에 저장할 수 있습니다.
    </p>
    <li> <h3> 내부저장소 Internal Storage </h3> </li>
    <p>
        내부저장소에 있는 사진을 불러올 수 있습니다. 다중 선택이 가능합니다.
    </p>
    <li> <h3> 이미지 슬라이더 Image Slider </h3> </li>
    <p>
        RecyclerView를 통해 이미지 슬라이더를 구현했습니다. 무한 스크롤이 가능합니다.
    </p>

</ul>

<hr/>

## Contact
<img src="https://user-images.githubusercontent.com/54874529/148012607-e6d75fc8-52b7-4d8d-ab88-e61f2c04e2dd.gif" height="450px"/>
<ul>
    <li> <h3> 연락처 추가/삭제 Add/Remove Contact </h3> </li>
    <p>
        우측 하단의 버튼을 통해 연락처를 추가할 수 있습니다. 연락처를 우측에서 좌측으로 슬라이드하면 삭제할 수 있습니다.
    </p>
    <li> <h3> 내부저장소 Internal Storage </h3> </li>
    <p>
        내부저장소에 있는 사진을 불러올 수 있습니다. 다중 선택이 가능합니다.
    </p>
    <li> <h3> 이미지 슬라이더 Image Slider </h3> </li>
    <p>
        RecyclerView를 통해 이미지 슬라이더를 구현했습니다. 무한 스크롤이 가능합니다.
    </p>

</ul>

<hr/>

## Mini Games

### Tic Tac Toe
<img src="https://user-images.githubusercontent.com/54874529/148012627-70a2d7f4-ca9a-4a85-adbc-c4585b85f983.gif" height="450px"/>
<ul>
    <li> 2인용 게임인 Tic Tac Toe입니다.</li>
    <li> 두 명이 번갈아가며 O와 X를 3x3 판에 써서 같은 글자를 가로, 세로, 대각선 상에 놓이도록 하는 게임입니다. </li>
</ul>

### Seotda Cat(고양이 섯다)
<img src="https://user-images.githubusercontent.com/54874529/148012624-2151dbff-2543-4784-aca3-a11fc0d69afc.gif" height="450px"/>

당신(Username : Goni)은 CatHub에 있는 고양이들의 사료값을 충당하기 위해 Madam jung 고양이를 찾아갔습니다.
정정당당한 승부를 통해 귀여운 고양이들의 사료값을 쟁취하세요.

### 1. UI 구성 

- 위 쪽의 뒤집어진 두 패는 정마담의 패를 나타냅니다. 아래의 두 패는 당신의 패입니다.   

1) 우측 상단 박스   
현재 정마담의 이름과 잔고, 족보를 나타내는 상태입니다. 정마담이 모든 돈을 잃게 된다면, 당신은 승리합니다!   
2) 판돈 박스   
현재 판에 깔려진 총 금액을 나타냅니다. 승리 혹은 패배 시 모든 금액은 승자에게 돌아갑니다.   
3) Goni 박스     
당신의 이름은 Goni 입니다. 현재 금액과 패를 돌렸을 때 족보가 나타나게 됩니다.   
4) 족보 보기 버튼   
섯다의 룰을 잘 모르는 사람들을 위해 족보 참고 버튼을 넣어두었습니다.   
5) 패 돌리기 버튼   
정마담과 고니에게 패를 각각 두 장씩 돌립니다. 판돈이 0원일 경우에만 패돌리기가 가능합니다.   
6) 베팅 버튼   
패가 돌려진 후 베팅을 할 수 있습니다.    
    - 하프 : 판돈의 절반만큼의 금액을 베팅합니다.    
    - 콜 : 상대의 이전 베팅 금액만큼을 베팅하고 진검승부를 요청합니다. 상대는 하프를 할 수 없으며, 콜을 하여 패 까기에 동의하거나 다이로 죽을 수 있습니다.   
    - 다이 : 죽습니다. 깔린 판돈은 모두 정마담에게..   
7) 판 엎기   
게임이 잘 풀리지 않으면 언제든 판을 엎어 리셋할 수 있습니다.   


### 2. 게임 진행 방식   
1) 패 돌리기로 게임을 시작합니다. 처음 판돈은 각각 50만원씩 지불하여 100만원입니다.   
최초 잔액은 모두 1억 원으로 설정했습니다.   
2) 당신은 항상 선공입니다. 먼저 베팅을 하실 수 있습니다. 베팅을 하게 되면 정마담은 자동으로 베팅을 진행합니다.   
    - 설정 : 정마담은 모든 패에 대하여 Die 20%, Call 40%,Half 40% 로 베팅합니다.   

3) 둘 중 하나가 Die를 하게 되면, 상대의 패가 오픈되며 다시 패를 돌려 재게임을 할 수 있습니다.   

4) 둘 중 하나가 Call을 하여 상대도 Call을 했다면, 진검승부가 진행됩니다. 서로의 패를 오픈하여 족보에 따라 승패를 가린 후 최종 정산을 진행합니다. 이 때, 족보에 따라 재경기가 진행될 수 있습니다.   
    - 암행어사, 땡잡이, 멍구사, 구사 족보 구현   

5) All-in 기능을 구현했습니다. 베팅할 금액보다 적은 금액을 가지고 있다면 All-in 이 되며, 다음 번에 베팅할 수 없게 되므로 Call로 간주하여 진행합니다.   

6) 패를 돌릴 때 잔액이 다음 게임에 참여할 50만원 미만이라면, 최종적으로 패배하게 됩니다. 이 경우, 게임은 자동으로 리셋됩니다.   


