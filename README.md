# 👉 일정관리 앱(develop) 과제 소개

* 프로젝트 명 : Java Spring Boot로 일정관리 앱을 구현해보자.(develop)
    * JPA를 바탕으로 일정 관리 앱를 구현하는 과제입니다.
    * Postman을 이용한 요청 및 응답으로 일정을 CRUD 및 DB에 저장할 수 있습니다.

* 개발 기간 : 2025.02.07 ~ 2025.02.13 (약 6일)

* github : <https://github.com/queenriwon/ScheduleDevelopProject>
* blog : <https://queenriwon3.tistory.com/112>

* 개발 환경
  * development : IntelliJ IDEA, git, github
  * environment : JAVA JDK 17, Spring Boot 3.4.2, JPA, MySQL, swagger 2.3.0

<br><br>


# 👉 Lv.0 - API 명세서, ERD 다이어그램


<details>
  <summary>상세 API 명세서</summary>
	
* 상세 API 명세서(설계단계)
  https://flaxen-swan-41e.notion.site/Lv-0-192b649ebbbd80eda2b9ee81449163ec

* 구현 후 API 명세서(Postman 사용)
  https://documenter.getpostman.com/view/41347390/2sAYXBFKXD
  
</details>

<details>
  <summary>ERD 다이어그램</summary>

  <img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FQpCRb%2FbtsMf5A9qq5%2FtRK2gl2FVKPPcoZoWArLq0%2Fimg.png">
  
</details>


<br><br>



# 👉 트러블슈팅

250210 - Java Spring 일정관리 앱 구현과 트러블 슈팅: 일정과 유저 CRUD, Validation, PasswordEncoder, 공통 예외 및 공통 응답 처리

<https://queenriwon3.tistory.com/109>

250211 - Java Spring 일정관리 앱 구현과 트러블 슈팅: 로그인 filter, service와 repository 설계, 댓글 CRUD, Pagenation (HttpServletRequest, Page, Pageable)

<https://queenriwon3.tistory.com/110>

250212 - Java Spring 일정관리 앱 구현과 트러블 슈팅: 양방향 관계, 로그인 filter의 예외처리와 한계, interceptor 사용, 커스텀 Annotation, 상속을 이용한 공통예외처리(@OneToMany, filter, Argument Resolver, HandlerMethod)

<https://queenriwon3.tistory.com/111>



<br><br>


# 👉 구현 내용

<details>
	<summary>필수 구현사항</summary>

* Lv.1
  * CRUD 기능이 정상적으로 구동되는가? 
  * 일정 엔티티에 '작성 유저명, 할일 제목, 내용, 작성일' 필드가 포함되었는가?
* Lv.2
  * CRUD 기능이 정상적으로 구동되는가?
  * 유저 엔티티에 '유저명, 이메일, 작성일, 수정일' 필드가 포함되었는가?
  * 일정 엔티티가 작성 유저명 대신 유저 고유 식별자 필드를 포함하고 있는가?
* Lv.3
  * 유저 엔티티에 비밀번호 필드가 추가되었는가?
* Lv.4
  * 이메일과 비밀번호를 사용하여 로그인 기능이 정상적으로 작동하는가?
  * 필터를 통해 요청 전후에 인증 처리가 잘 이루어지고 있는가?
  * 회원가입과 로그인 요청은 인증 처리에서 제외되고 있는가?
  * 로그인 실패 시 401 정확하게 반환되는가? 

</details>

<details>
	<summary>선택 구현사항</summary>
  
* Lv.3 (구현)
  * @Valid 어노테이션을 사용하여 객체의 제약 조건을 검증하는 기능이 구현되었는가?
  * 다양한 제약조건 검증 어노테이션(@NotNull, @Size 등)을 활용하였는가?
* Lv.4 (구현)
  * 비밀번호가 성공적으로 암호화되어 저장되는가?
  * PasswordEncoder 클래스를 구현하고 활용했는가?
* Lv.5 (구현)
  * CRUD 기능이 정상적으로 구동되는가?
  * 댓글 엔티티에 '댓글 내용, 작성일, 수정일, 작성 유저명' 필드가 포함되었는가?
  * 댓글과 일정 간의 연관관계가 설정되었는가?
* LV.6  (구현)
  * Spring Data JPA의 Pageable과 Page 인터페이스를 활용하여 페이징이 구현하였는가?
  * 페이지 번호와 페이지 크기를 쿼리 파라미터로 받는 기능이 정상적으로 동작하는가?
  * 지정된 필드(할일 제목, 할일 내용, 댓글 개수, 일정 작성일, 수정일, 작성 유저명)가 조회되는가?
  * 디폴트 페이지 크기가 10으로 설정되어 있는가?
  * 일정의 수정일을 기준으로 내림차순 정렬이 잘 이루어지고 있는가?
 
</details>


<br><br>




# 👉 구현 핵심
* 공통응답과 공통예외처리, 페이징
* 세션을 불러오기 위한 파라미터에 커스텀 Annotation 사용(인가)
* 필터(인터셉트) 화이트리스트 url를 커스텀 Annotation으로 구분
* Optional로 null처리하기
* swagger로 api 명세서 생성하기
* Service 당 하나의 담당repository를 연결하여 유지보수와 코드 재사용성 늘리기


<br><br>

# 👉 부족하거나 아쉬운 점, 공부하고 싶은 내용
* 유틸 클래스나 레코드에 대해 공부
* 간단하게 page객체 사용하기
* 연관관계 관련 공부
* Soft delete 또는 보안에 신경쓰기
* 여러 개의 에러코드를 응답할 수 있도록 하기(에러 메세지를 리스트로 관리)
* builder()을 사용하여 DTO와 entity를 매핑하기




<br><br>



# 👉구현결과

PostMan api명세서 참고
  https://documenter.getpostman.com/view/41347390/2sAYXBFKXD



