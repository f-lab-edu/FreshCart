# FreshCart
소개: 신선 식품을 판매하는 e-commerce 서비스 입니다 

# ERD 설계 
https://www.erdcloud.com/d/ezfSvS66CSxhzFp8o

# 주요 기능 
- 회원 가입/로그인 
- 판매자의 제품 등록
다양한 옵션 구성이 가능한 상품을 등록하고 주문할 수 있도록 구현
- 소비자의 제품 주문   

# 기타
Elastic APM Java Agent 설정

APM 설정 - 인텔리제이 Edit Configurations -> VM Options 에 추가 (Run 시 실행됨)

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
