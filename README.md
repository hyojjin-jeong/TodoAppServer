# TodoAppServer
이 프로그램은 사용자들이 일정을 관리하고 댓글을 남길 수 있는 서버 프로그램입니다.

## 🛠️ Tech Stack
- 언어 : Java - JDK 17, Groovy
- 프레임워크 : Spring Boot 3.2.5
- 데이터베이스 : Mysql - 8.0.28

## 📕 기획 명세서

### ERD Diagram
<img src="https://github.com/hyojjin-jeong/TodoAppServer/assets/64136923/2a2a48a2-b5fe-4350-b70b-6194a91d4e07" width="800">

### API 명세서
[API 명세서](https://documenter.getpostman.com/view/19357660/2sA3JRZK3T)

## 🔖 Features

- 회원가입 & 로그인
  - 별명, username, 비밀번호를 입력하여 회원가입할 수 있습니다.
  - username과 비밀번호를 입력하여 로그인할 수 있습니다.
  - 인가 & 인증 필터를 사용해 로그인한 사용자만 해당 서버의 기능을 사용할 수 있습니다.
- 일정 관리
  - 일정 등록:
      - 일정, 일정 상세 내용, 작성자, 비밀번호, 작성일을 저장할 수 있습니다.
  - 일정 목록 조회:
      - 현재 등록된 모든 일정 정보를 작성일 기준 내림차순으로 조회할 수 있습니다.
      - 선택한 일정의 정보를 조회할 수 있습니다.
  - 일정 업데이트:
      - 일정 수정과 삭제는 비밀번호와 요청해 비밀번호가 일치할 경우에만 가능합니다.
      - 선택한 일정의 일정, 상세 내용을 수정할 수 있습니다.
      - 선택한 일정을 삭제할 수 있습니다.
- 댓글 관리
  - 댓글 등록:
    - 선택한 일정에 댓글을 달 수 있습니다.
  - 댓글 수정:
    - 해당 댓글이 사용자가 작성한 댓글일 경우에만 수정할 수 있습니다.
    - 댓글의 내용을 수정할 수 있습니다.
  - 댓글 삭제:
    - 해당 댓글이 사용자가 작성한 댓글일 경우에만 삭제할 수 있습니다.
    - 댓글을 삭제할 수 있습니다.

## 🐱 Github Rules
|작업 타입| 작업 내용  |
|-------|--------|
|setting|환경 설정|
| add|새로운 기능 추가|
|fix|코드 수정|
|refactor|코드 리팩토링|
|del|기능/파일 삭제|
|test|test code 작성|
|gitfix|gitignore 수정|
