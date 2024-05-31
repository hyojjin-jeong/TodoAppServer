# TodoAppServer
이 프로그램은 사용자들의 일정을 관리하는 프로그램입니다.

## 🛠️ Tech Stack
- 언어 : Java - JDK 17, Groovy
- 프레임워크 : Spring Boot 3.2.5
- 데이터베이스 : Mysql - 8.0.28

## 📕 기획 명세서

### ERD Diagram
<img src="https://github.com/hyojjin-jeong/TodoAppServer/assets/64136923/2a2a48a2-b5fe-4350-b70b-6194a91d4e07" width="300">

### API 명세서
[API 명세서](https://documenter.getpostman.com/view/19357660/2sA3JRZK3T)

## 🔖 Features

- 일정 등록:
    - 일정, 일정 상세 내용, 작성자, 비밀번호, 작성일을 저장할 수 있습니다.
- 일정 목록 조회:
    - 현재 등록된 모든 일정 정보를 작성일 기준 내림차순으로 조회할 수 있습니다.
    - 선택한 일정의 정보를 조회할 수 있습니다.
- 일정 업데이트:
    - 일정 수정과 삭제는 비밀번호와 요청해 비밀번호가 일치할 경우에만 가능합니다.
    - 선택한 일정의 일정, 상세 내용을 수정할 수 있습니다.
    - 선택한 일정을 삭제할 수 있습니다.

## 🐱 Github Rules
<img src="https://github.com/GIGAZO/TeamProject/assets/102974424/5195ca23-e684-43b8-a14f-8b79cd94302f" width="450">
