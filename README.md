# 🏠 서비스 소개

마켓컬리와 같이 신선 식품을 판매하는 온라인 식재료 판매 서비스  

# 🔗  사용 기술 스택
- Java 11, Spring Boot, Gradle 
- JPA, MyBatis
- MySQL, Flyway
- Redis Clustering
- Docker
- ElasticSearch
- Github Actions 

# 🔗 서비스 구조 

![img.png](img/architecture.png)

# 📌 프로젝트 의도 

### 객체 지향 설계
- 레이어드 아키텍처를 도입하여, 관심사를 분리하고 단방향으로 의존성을 제한했습니다.  
- 변경 가능성이 있는 클래스는 인터페이스를 활용하여 변경에 유연하도록 했습니다.

### 문제 상황에 대한 대처 
- 재고에 유저가 동시에 접근해, 경쟁상태가 발생할 경우 -> 비관적 락 적용
- 서버 확장(scale-out)에 대비 -> 세션 로그인에 Redis 적용하여 저장소 중앙화 
- Redis Fail 에 대한 대비 -> Redis Clustering 적용 

### 기술에 대한 깊이 있는 학습
- 여러 기술의 장단점을 비교하고 사용해본 뒤, 상황에 맞는 기술을 쓰고자 했습니다.
- 학습 목적의 프로젝트이기 때문에, 기술의 개발 의도를 이해하고 깊이 있게 써보고 싶었습니다. 
  이를 위해 인증 기능에 사용되는 HttpSession, 스프링 시큐리티 등을 사용하지 않고 직접 구현 했습니다.

### 코드 개선 
- 인증/인가 시 AOP를 활용하여, 반복적인 코드를 줄였습니다.
- Google CheckStyle로 코딩 컨벤션을 적용하고 준수하였습니다. 

# 📌  트러블 슈팅 / 기술 선택 과정 

제목을 클릭하시면 블로그 포스팅에서 자세한 내용을 확인하실 수 있습니다. 

- [[JPA/MYSQL] 재고에 동시 접근할 때 일어나는 갱신 분실 문제 해결하기](https://writerroom.tistory.com/338)


- [객체 지향적 설계/유지 보수하기 좋은 코드를 위한 고민](https://writerroom.tistory.com/332)


- [Redis로 다중 서버 환경에서 로그인 정보 불일치 문제 해결](https://writerroom.tistory.com/333)


- [Redis Clustering 를 통해 FailOver 구현하기 (도커 컴포즈 환경)](https://writerroom.tistory.com/341)


- [로그인 구현 시 세션과 토큰 방식 비교](https://writerroom.tistory.com/320)


- [인터셉터 / 커스텀 애노테이션 활용하여 로그인 강제](https://writerroom.tistory.com/326)


- [협업에 용이한 작업 환경 구성](https://writerroom.tistory.com/336)


- [솔트를 이용해 비밀번호 암호화의 보안 강화](https://writerroom.tistory.com/327)

# 📌  ERD 설계
[ERD 설계](https://www.erdcloud.com/d/ezfSvS66CSxhzFp8o)

![img.png](img/img.png)


#  🔗 기능 명세서

## Common 

- 유저가 고유한 이메일 주소로 `회원가입`을 합니다.  
- 유저는 `회원가입`을 통해 Role을 획득합니다.
- 유저가 `로그인`을 통해 서비스를 이용합니다 (주문 등)
- 유저는 서비스를 나가기 위해 `로그아웃`을 해야 합니다.

## Seller
- 판매자는 `상품을 등록`할 수 있습니다. 
- 판매자는 등록한 상품의 `재고를 등록`할 수 있습니다
  (단일 상품은 상품 별로, 부가 옵션이 있는 제품은 옵션 별로 등록합니다.)
- 판매자는 아몬드 우유 2종과 같이 단일 상품이 아닌 경우, 아래와 같이 `옵션을 지정`할 수 있습니다.

(1) 필수 / 선택 여부

(2) 옵션 별로 가격이 달라질 수 있음.

(3) 특정 옵션은 주문 가능 개수 제한을 가질 수 있음.

(4) 옵션은 한 개만/여러 개 선택 가능하도록 지정할 수 있음.

## Buyer
- 유저는 화면에서 `전체 상품을 조회`할 수 있습니다.
- 유저가 주문을 원할 경우, 카트에 제품을 담습니다. 카트를 주문서로 변환하여 주문합니다.
- 유저가 주문을 완료할 경우, 주문 수량만큼 상품의 `재고가 감소`합니다.

# 🔗 기타

Elastic APM Java Agent 설정

APM 설정 - 인텔리제이 Edit Configurations -> VM Options 에 추가 (Run 시 실행됨)
```
-javaagent:/{jar 파일의 위치}/elastic-apm-agent-1.34.1.jar
-Delastic.apm.disable_send=false
-Delastic.apm.environment=local
-Delastic.apm.service_name=freshcart
-Delastic.apm.enable_log_correlation=true
-Delastic.apm.application_packages=com.example.freshcart
-Delastic.apm.trace_methods_duration_threshold=1ms
-Delastic.apm.transaction_sample_rate=1
-Delastic.apm.server_urls=http://localhost:8200
-Delastic.apm.secret_token=
-Delastic.apm.span_frames_min_duration=1ms
-Delastic.apm.span_min_duration=0ms
-Delastic.apm.trace_methods=com.example.freshcart.*
-Delastic.apm.max_queue_size=2048

```
