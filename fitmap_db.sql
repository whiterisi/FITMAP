DROP DATABASE IF EXISTS ssafit;

CREATE DATABASE ssafit CHARACTER SET=utf8mb4;

USE ssafit;

CREATE TABLE IF NOT EXISTS `User` ( 
	userId varchar(20) NOT NULL,
	pw varchar(100) DEFAULT NULL,
	nickName varchar(20) DEFAULT NULL,
	name varchar(20) DEFAULT NULL,
    email varchar(20) DEFAULT NULL,
PRIMARY KEY (userId));

CREATE TABLE IF NOT EXISTS `Video` ( 
	videoId varchar(20) NOT NULL,
	title varchar(100) DEFAULT NULL,
    part int DEFAULT NULL,
    channel varchar(50) DEFAULT NULL,
	url varchar(100) DEFAULT NULL,
PRIMARY KEY (videoId));

CREATE TABLE IF NOT EXISTS `DailyRecord` ( 
	recordId int auto_increment,
	userId varchar(20) DEFAULT NULL,
	date date DEFAULT NULL,
	part int DEFAULT NULL,
    minutes int DEFAULT NULL,
    comment varchar(200) DEFAULT NULL,
PRIMARY KEY (recordId),
FOREIGN KEY (userId) REFERENCES User (userId));

CREATE TABLE IF NOT EXISTS `Comment` ( 
	commentId int auto_increment,
	userId varchar(20) DEFAULT NULL,
    videoId varchar(20) DEFAULT NULL,
    parentId int DEFAULT NULL,
	date varchar(20) DEFAULT NULL,
    content varchar(50) DEFAULT NULL,
PRIMARY KEY (commentId),
FOREIGN KEY (userId) REFERENCES User (userId));
#FOREIGN KEY (videoId) REFERENCES Video (videoId)


CREATE TABLE IF NOT EXISTS `LikeList` ( 
	likeId int auto_increment,
	userId varchar(20) DEFAULT NULL,
    videoId varchar(20) DEFAULT NULL,
PRIMARY KEY (likeId),
FOREIGN KEY (userId) REFERENCES User (userId),
FOREIGN KEY (videoId) REFERENCES Video (videoId));

CREATE TABLE IF NOT EXISTS `Follow` ( 
	followId int auto_increment,
	userId varchar(20) DEFAULT NULL,
    follow varchar(20) DEFAULT NULL,
    nickName varchar(20) DEFAULT NULL,
PRIMARY KEY (followId),
FOREIGN KEY (userId) REFERENCES User (userId),
FOREIGN KEY (follow) REFERENCES User (userId));

show tables;

INSERT INTO Video
VALUES 
(
"gMaB-fG4u4g", 
"전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]",
 4,
"ThankyouBUBU",
"https://www.youtube.com/embed/gMaB-fG4u4g"
 ),
  (
    "swRNeYw1JkY",
    "하루 15분! 전신 칼로리 불태우는 다이어트 운동",
     4,
    "ThankyouBUBU",
    "https://www.youtube.com/embed/swRNeYw1JkY"
  ),
(
    "A5MzlPgNcJM",
    "(근육통주의!!!!) 🔥올인원🔥 전신 근력운동 50분 홈트레이닝",
     4,
    "힙으뜸",
    "https://youtu.be/A5MzlPgNcJM"
  ),
(
    "4kZHHPH6heY",
    "NO 층간소음 - 고민없이 하나로 끝내는 전신운동 근력 유산소 - 운동 못한 날 죄책감 씻어줄 30분 홈트",
     4,
    "빅씨스",
    "https://youtu.be/4kZHHPH6heY"
  ),
(
    "yka0VGRh1oc",
    "Lv.4 이 12분짜리 영상 딱! 하나로 전신 한방에 끝내버리세요! [12mins Full body workout]",
     4,
    "권혁 Hulk TV",
    "https://youtu.be/yka0VGRh1oc"
  ),
(
    "rSBOuArsz1k",
    "힙으뜸 기초체력 홈트 15분루틴 (ft.땀폭발 전신순환운동, 코어운동)",
     4,
    "힙으뜸",
    "https://youtu.be/rSBOuArsz1k"
  ),
(
    "W4SpM2gxGdE",
    "왕초보 탈출 - 전신운동 초보 홈트 여자",
     4,
    "빅씨스",
    "https://youtu.be/W4SpM2gxGdE"
  ),
(
    "N-15wUPnqpc",
    "하루 10분! 전신 칼로리 불태우는 다이어트 홈트레이닝 ",
     4,
    "Allblanc TV ",
    "https://youtu.be/N-15wUPnqpc"
  ),
(
    "LG6CNzlj_6o",
    "역대급 땀폭발 HIIT - 고강도 인터벌 트레이닝 35분 전신 올인원",
     4,
    "빅씨스",
    "https://youtu.be/LG6CNzlj_6o"
  ),
(
    "s14NQ6Cz4QE",
    "살 빠지는 전신운동 루틴 with 피지컬갤러리",
     4,
    "힙으뜸",
    "https://youtu.be/s14NQ6Cz4QE"
  ),
(
    "PHdzU1S50AY",
    "🔥보기싫은 전신 지방🔥 양심적으로 오늘 많이 먹었으면 들어오세요. (전신운동 다이어트/칼로리폭발운동)",
     4,
    "비타민신지니 VitaminJINY",
    "https://youtu.be/PHdzU1S50AY"
  ),
  (
   "54tTYO-vU2E",
   "상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]",
    1,
   "ThankyouBUBU",
    "https://www.youtube.com/embed/54tTYO-vU2E"
),
(
   "QqqZH3j_vH0",
    "상체비만 다이어트 최고의 운동 [상체 핵매운맛]",
    1,
    "ThankyouBUBU",
   "https://www.youtube.com/embed/QqqZH3j_vH0"
),
  (
    "XjEfUcZLbG4",
    "🔥건장한 상체🔥가 여리+슬림+탄탄해지는 '상체올킬루틴' (7일효과: 팔뚝살,등살,뱃살 완전빠짐&탄력up!!!)",
     1,
    "이지은 다이어트 Jiny diet",
    "https://youtu.be/XjEfUcZLbG4"
  ),
  (
    "cUjNYSSlwts",
    "기구없이❌ 따라하는 ALL 상체근육 홈트레이닝 루틴 (등, 가슴, 어깨, 팔, 복근)",
     1,
    "바벨라토르 홈트레이닝 BARBELLATOR",
    "https://youtu.be/cUjNYSSlwts"
  ),
  (
    "xoWKLNwNva0",
    "등, 가슴, 팔, 어깨 탄탄하게 만들기 - 덤벨 상체 운동 홈트 루틴",
     1,
    "빅씨스",
    "https://youtu.be/xoWKLNwNva0"
  ),
  (
    "GZtB7W9Uafk",
    "집에서 할 수 있는 상체운동 루틴 | 다이어트+근육증가 버전!! 이대로만 하세요",
     1,
    "핏블리 FITVELY",
    "https://youtu.be/GZtB7W9Uafk"
  ),
  (
    "uTmsvZOAh5U",
    "Lv.3 8분만에 상체전체를 탈탈 털어버리는 홈트루틴 (누구나 쉽게 가능)",
     1,
    "권혁 Hulk TV",
    "https://youtu.be/uTmsvZOAh5U"
  ),
  (
    "Vx2yxXQ0Pkk",
    "🏅요일별운동🏅 목요일 상체집중 근력운동 15분 루틴!",
     1,
    "힙으뜸",
    "https://youtu.be/Vx2yxXQ0Pkk"
  ),
  (
    "e1DHt9wfQOA",
    "NO 층간소음 - 20분 상체운동 루틴 + 스트레칭",
     1,
    "빅씨스",
    "https://youtu.be/e1DHt9wfQOA"
  ),
  (
    "voxhcy_oDGA",
    "상체 덤벨 홈트레이닝 운동루틴🔥 사이드밴드/덤벨로우/백익스텐션/체스트스퀴즈 [웨이홈트]",
     1,
    "핏블리 FITVELY",
    "https://youtu.be/voxhcy_oDGA"
  ),
  (
    "87uwSVdvPY8",
    "상하체불균형 : 상체비만 운동법 I upper body strength and fat burning workout",
     1,
    "힙으뜸",
    "https://youtu.be/87uwSVdvPY8"
  ),
  (
    "XmouqUNwFdY",
    "자꾸 민소매만 입고싶어지는 운동! 섹시하고 탄탄한 가슴,어깨,팔만들기! [덤벨 6분]",
     1,
    "소미핏 SomiFit",
    "https://youtu.be/XmouqUNwFdY"
  ),
(
     "tzN6ypk6Sps",
    "하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]",
     2,
    "김강민",
    "https://www.youtube.com/embed/tzN6ypk6Sps"
),
(
    "u5OgcZdNbMo",
    "저는 하체 식주의자 입니다",
     2,
     "GYM종국",
     "https://www.youtube.com/embed/u5OgcZdNbMo"
),
  (
    "0s9W3-CD0cE",
    "다리가 길어보이는 힙딥 없는 힙업 운동 - 하체운동 25분",
     2,
    "빅씨스",
    "https://youtu.be/0s9W3-CD0cE"
  ),
(
    "pDFuLG0xrsU",
    "🏅요일별운동🏅 금요일 하체집중 근력운동 15분 루틴!",
     2,
    "힙으뜸",
    "https://youtu.be/pDFuLG0xrsU"
  ),
(
    "dpBYYEhdofI",
    "앞벅지 볼록, 뒷벅지 셀룰라이트, 허벅지 안쪽살 모조리 불태우고🔥 [여리탄탄 일자 허벅지] 되는 7일 루틴",
     2,
    "이지은 다이어트 Jiny diet",
    "https://youtu.be/dpBYYEhdofI"
  ),
(
    "js8z5wIZ0wg",
    "(층간소음X, 설명O) 진짜 힘듦주의.. 🔥초강력🔥 힙으뜸 하체운동 2주 챌린지",
     2,
    "힙으뜸",
    "https://youtu.be/js8z5wIZ0wg"
  ),
(
    "DWYDL-WxF1U",
    "하체 날, 딱 10분 밖에 없다면 - 스쿼트 10가지 동작 - 하체운동 홈트 루틴",
     2,
    "빅씨스",
    "https://youtu.be/DWYDL-WxF1U"
  ),
(
    "9WhpAVOSyl8",
    "하루 딱6분! 다리라인 살리는 운동 [허벅지&엉덩이]",
     2,
    "Thankyou BUBU",
    "https://youtu.be/9WhpAVOSyl8"
  ),
(
    "48eKwtozuCo",
    "(층간소음X, 설명O) 🔥무조건 빠짐🔥 다이어트 다짐한 사람들 다 들어와!!",
     2,
    "힙으뜸",
    "https://youtu.be/48eKwtozuCo"
  ),
(
    "NDsjmxTROEo",
    "하체비만 11자다리 최고의 운동 [하체 핵매운맛]",
     2,
    "Thankyou BUBU",
    "https://youtu.be/NDsjmxTROEo"
  ),
(
    "4qqBQ0Xs4nc",
    "엉덩이와 하체를 탄탄하게 - 하체운동 18분 - 덤벨운동 홈트",
     2,
    "빅씨스",
    "https://youtu.be/4qqBQ0Xs4nc"
  ),
(
    "xpzMr3nSOIE",
    "[한글 자막] X-HIT 마일리 사이러스 다리 운동",
     2,
    "코치미스쿨Coach ME School",
    "https://youtu.be/xpzMr3nSOIE"
  ),
(
    "PjGcOP-TQPE",
     "11자복근 복부 최고의 운동 [복근 핵매운맛]",
    3,
    "ThankyouBUBU",
    "https://www.youtube.com/embed/PjGcOP-TQPE"
),
(
    "7TLk7pscICk",
     "(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)",
    3,
    "SomiFit",
    "https://www.youtube.com/embed/7TLk7pscICk"
),
(
    "kETh8T3it4k",
    "(층간소음X, 설명O) 복근운동과 유산소를 한번에❗️서서하는 복근운동 1탄🔥",
     3,
    "힙으뜸",
    "https://youtu.be/kETh8T3it4k"
  ),
(
    "lRJZC342Bs0",
    "서서하는 복근운동 이 영상하나로 끝! 16MIN STANDING ABS FOR BEGINNER",
     3,
    "MIZI",
    "https://youtu.be/lRJZC342Bs0"
  ),
(
    "QSZ0mUGO_CA",
    "(층간소음X, 설명O) 🔥짧고 굵게🔥 복근만들기 2주 챌린지",
     3,
    "힙으뜸",
    "https://youtu.be/QSZ0mUGO_CA"
  ),
(
    "jj6ze_eqmYI",
    "Lv.3 완벽한 복근을 만들기 위한 8분 루틴! 따라만하세요! (누구나 집에서도 가능) [8mins intense Abs Workout]",
     3,
    "권혁 Hulk's TV",
    "https://youtu.be/jj6ze_eqmYI"
  ),
(
    "PoYzxj8Iy5M",
    "2주만에 복근 만드는 운동 2 MIN ABS WORKOUT",
     3,
    "ThankyouBUBU",
    "https://youtu.be/PoYzxj8Iy5M"
  ),
(
    "sqQpL1wKW6M",
    "12분 서서하는 복근운동 홈트레이닝 - 체지방 태우기는 보너스",
     3,
    "빅씨스",
    "https://youtu.be/sqQpL1wKW6M"
  ),
(
    "EojxzBZbo4o",
    "[누워서하는] 복부 파괴🔥 핵마라맛 11분 복부운동 (ABS WORKOUT 10 MIN)",
     3,
    "제이제이살롱드핏",
    "https://youtu.be/EojxzBZbo4o"
  ),
(
    "orpoYsCXlKA",
    "전소미, 모모의 복근을 만들어준 바로 그 운동! (일주일만 함께해요🔥)",
     3,
    "이지은 다이어트 Jiny diet",
    "https://youtu.be/orpoYsCXlKA"
  ),
(
    "EFadnuaH-Jg",
    "[데스런] 집에서 하는 맨몸 10분 복근운동.",
     3,
    "DeSLun데스런",
    "https://youtu.be/EFadnuaH-Jg"
  ),
(
    "-hEV_0jbFgc",
    "하루 단 6분! 누워서 하는 최고의 복근운동 / TOTAL AB WORKOUT 6MIN",
     3,
    "에이핏 afit",
    "https://youtu.be/-hEV_0jbFgc"
  );

  
select * from Video;

insert into User
values('1','123','123','123','123'),
('123','123','123','123','123'),
('222','22','22','22','22');
insert into DailyRecord 
values(0,'1','2022-05-03','2','1423','22'),
(0,'1','2022-05-02','2','1523','221'),
(0,'1','2022-05-02','2','1523','221'),
(0,'1','2022-05-02','2','1523','221'),
(0,'1','2022-05-02','2','1523','221'),
(0,'1','2022-05-02','2','1523','221'),
(0,'123','2022-05-12','2','12','2'),
(0,'123','2022-05-23','2','2','2'),
(0,'222','2022-05-23','2','12','23');
