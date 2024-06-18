<br>
<h1 align="middle" style="text-weight: bold">DogSeek 애견 맞춤 식품 Curation</h1>


## 프로젝트 소개
입력된 강아지의 정보를 기반으로 보유 중인 사료와 간식 데이터들 중 사용자의 요구사항에 가장 적합한 제품을 추천해주는 서비스입니다.
<br>

🚩 프로젝트 개발기간 : 2024.06.17 ~

## 멤버 구성
<div align="center">

| **Project Manager** | **Configuration Manager** | **DataBase Manager** | **DataBase Manager** | **DataBase Manager** | **DataBase Manager** |
| :------: |  :------: | :------: | :------: | :------: | :------: |
| [<img src="https://avatars.githubusercontent.com/u/134928447?v=4" height=150 width=150> <br/> 윤수빈](https://github.com/nunu1101) | [<img src="https://avatars.githubusercontent.com/u/155221216?v=4" height=150 width=150> <br/> 강민서](https://github.com/KANGMINSEO0) | [<img src="https://avatars.githubusercontent.com/u/157683193?v=4" height=150 width=150> <br/> 권순상](https://github.com/sunskwon) | [<img src="https://avatars.githubusercontent.com/u/157683190?v=4" height=150 width=150> <br/> 구예성](https://github.com/KUYESUNG) | [<img src="https://avatars.githubusercontent.com/u/152046800?v=4" height=150 width=150> <br/> 조은성](https://github.com/eunseongjo) | [<img src="https://avatars.githubusercontent.com/u/159097835?v=4" height=150 width=150> <br/> 박진현](https://github.com/0COK0) |
</div>
<br>

## 프로젝트 기능
### 1. 로그인<br>
    a. 관리자
       i) 관리자는 지정된 접속 정보를 이용하여 접속할 수 있다.
    b. 사용자(회원)
       i) 사용자는 지정된 접속 정보를 이용하여 접속할 수 있다.
       ii) 사용자는 회원 가입시 기입한 정보 외
           성별, 생년월일, 애견 정보(종, 이름, 나이, 성별, 중성화 여부, 질병, 체중, 선호 식재료 등)를 수정할 수 있다.
    c. 비회원
       i) 비회원은 지정된 접속 정보 없이 사용할 수 있다.
       ii) 비회원은 이메일, 비밀번호, 이름, 연락처, 인증번호, 이용약관 동의를 통해 회원 가입 가능하다.

### 2. 사료 검색<br>
    a. 관리자
       i) 관리자는 사료 DB에 접근하여 신규 사료 추가, 기존 사료 검색 및 조회, 기존 사료 정보 수정 및 기존 사료 정보 삭제를 할 수 있다.
       ii) 관리자는 검색 조건 설정을 통해 원하는 조건의 사료를 검색할 수 있다.
       iii) 관리자는 애견 정보 입력을 통해 견종별 추천 사료를 검색할 수 있다.
       ix) 관리자는 회원과 비회원의 검색 내역을 확인할 수 있다.
    b. 회원
       i) 회원은 사료 DB에 접근하여 기존 사료 검색 및 조회를 할 수 있다.
       ii) 회원은 검색 조건 설정을 통해 원하는 조건의 사료를 검색할 수 있다.
       iii) 회원은 애견 정보 입력을 통해 견종별 추천 사료를 검색할 수 있다.
    c. 비회원
       i) 비회원은 사료 DB에 접근하여 기존 사료 검색 및 조회를 할 수 있다.
       ii) 비회원은 검색 조건 설정을 통해 원하는 조건의 사료를 검색할 수 있다.
       iii) 비회원은 애견 정보 입력을 통해 견종별 추천 사료를 검색할 수 있다.

### 3. 견종 검색<br>
    a. 관리자
      i) 관리자는 견종 DB에 접근하여 신규 견종 추가, 기존 견종 검색 및 조회, 기존 견종 정보 수정 및 기존 견종 정보 삭제를 할 수 있다.
      ii) 관리자는 검색을 통해 원하는 견종의 정보를 검색할 수 있다.
      iii) 관리자는 회원과 비회원의 검색 내역을 확인할 수 있다.
    b. 회원
      i) 회원은 견종 DB에 접근하여 기존 견종 검색 및 조회를 진행할 수 있다
      ii) 회원은 검색을 통해 원하는 견종의 정보를 검색할 수 있다.
    c. 비회원
      i) 비회원은 견종 DB에 접근하여 기존 견종 검색 및 조회를 진행할 수 있다
      ii) 비회원은 검색을 통해 원하는 견종의 정보를 검색할 수 있다.

### 4. 게시판<br>
    a. 관리자
       i) 관리자는 게시판 DB에 접근하여 신규 게시물 추가, 기존 게시물 검색 및 조회, 기존 게시물 수정 및 게시물 삭제를 할 수 있다.
    b. 회원
       i) 회원은 게시판 DB에 접근하여 신규 게시물 추가, 기존 게시물 검색 및 조회를 할 수 있다.
       ii) 회원은 본인이 작성한 게시물 수정 및 삭제를 할 수 있다.
    c. 비회원
       i) 비회원은 게시판 DB에 접근하여 기존 게시물 검색 및 조회를 할 수 있다.     

### 5. 서비스 관리<br>
    a. 관리자
       i) 관리자는 서비스 관리를 통해 사료 및 견종 DB에 접속해서
          ㄱ) 신규 정보 회
          ㄷ) 기존 정보 수정
          ㄹ) 기존 정보 삭제
          를 진행할 수 있다.
       ii) 관리자는 서비스 관리를 통해 회원과 비회원의 검색 기록을 확인할 수 있다.
    b. 회원
       i) 회원은 관리자 권한이 없다.
    c. 비회원
       i) 비회원은 관리자 권한이 없다.

## 시작가이드
#### Chrome 환경 권장
 1. 프로젝트 cloning
 2. IDE로 프로젝트 실행
 3. 접속 경로 미정

#### IDE :  IntelliJ
#### Java : JDK 17
#### Depengencies
 1. Spring Web
 2. Spring Boot Dev Tools
 3. MyBatis
 4. MySQLDriver
 5. Spring security
    추가예정

<br>

## 사용 기술
![image](https://github.com/Dogpamines/DogSeek/assets/155221216/2bf64aac-dae4-4457-b2bd-f15f3825e947)<br>

