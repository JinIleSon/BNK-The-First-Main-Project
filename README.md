🏦 BNK 부산은행 퇴직연금 통합 플랫폼 (Team 2)

K-Digital Training · BNK 금융DT 아카데미
부산은행 퇴직연금 사이트 & 실시간 증권/마이데이터/지점조회 통합 서비스
부기은행(퇴직연금) · Team 2조

1. 프로젝트 개요
항목	내용
프로젝트 주제	부산은행 퇴직연금 사이트
프로젝트 기간	2025.11.05 ~ 2025.12.08
배경 및 목적	퇴직연금 상품 판매/관리와 증권·마이데이터·지점 조회까지 아우르는 BNK 퇴직연금 통합 플랫폼 구현
주요 기능	퇴직연금 상품 구매/변경, 실시간 증권 정보(국내/해외) 조회, 마이데이터 기반 상품 비교, BNK 지점/ATM 통합 조회, 회원/로그인 관리
배포 주소	http://3.39.247.70:8080/BNK

GitHub	https://github.com/JinIleSon/BNK-The-First-Main-Project.git
2. 팀 구성
팀 조직도 & 역할
이름	역할	주요 업무 내용
손진일	PM, 팀장	증권(실시간 랭킹/주문), 마이페이지(ETF), 관리자 파트, 멀티 스레드 + WebSocket 설계
전세현	팀원	외부 API 수집, 환율/마이데이터(타행 IRP/연금 비교) 파트
강민철	팀원	상품/보유상품 변경/마이페이지 상품, 배포/운영 파트
이준우	팀원	로그인/회원가입/아이디·비밀번호 찾기, 인증 흐름, QR 기반 IRP 계좌 개설
조지영	팀원	기업뱅킹·영업점/ATM 조회, 공통 프론트(UI/UX) 파트
3. 요구사항 정의서 · WBS · 테스트 시나리오

실제 문서는 /docs/requirements, /docs/wbs, /docs/test-scenarios 등에 별도 보관한다고 가정하고, README에는 요약만 정리.

3-1. 요구사항 정의(요약)

회원/인증

회원가입, 로그인, 로그아웃

아이디/비밀번호 찾기 (휴대폰·이메일 인증)

비밀번호 변경(현재 비밀번호 검증 + 본인인증)

퇴직연금 상품

상품 목록/상세 조회

5단계 가입 프로세스(본인확인서 → 약관 → 중요안내 → 가입정보 → 비밀번호)

IRP/DC/DB 유형에 따른 가입 가능 상품 제약

보유상품 관리

보유상품 평가금액/수익률/운용비중 계산

일부매도/전부매도, 매수 기능

금액·비율(%) 중 택1 입력 / 비중 자동 재계산

증권(ETF/주식)

국내·해외 ETF/주식 실시간 랭킹

1분봉 차트(최근 300봉) + 실시간 호가/현재가

로그인 사용자 대상 매수/매도 기능

마이데이터

타행 IRP/연금 계좌 수집(가상 MyData 테이블)

수익률/위험등급 기반 상품 비교 및 추천

지점/ATM 조회

영업점, 365코너, 24/365, 대여금고, 외화ATM 카테고리

시/도, 구/군 2단계 필터 + 키워드 검색

Kakao Map 연동

관리자

상품/회원/지점 정보 관리

통계·현황 조회

3-2. WBS(Work Breakdown Structure) 요약

분석 단계: 요구사항 정의, 도메인 조사(퇴직연금·증권·마이데이터)

설계 단계: IA, 플로우차트, ERD, 화면 설계서

구현 단계:

공통/레이아웃

인증/회원

상품/IRP 계좌

증권/실시간 시세

마이데이터

지점/ATM

관리자

테스트 단계:

단위 테스트(계산 로직, 거래 트랜잭션)

통합 테스트(계좌·상품·거래 연계)

UAT(시나리오 기반 화면 점검)

3-3. 테스트 시나리오 예시

퇴직연금 상품 가입 시:

본인확인서 미등록 → Step1 강제 이동

약관 미열람 → Step2 진행 불가

비밀번호 4자리 불일치 → 가입 실패

보유상품 일부매도:

금액/비율 미입력 → 합계 반영 안 됨

매도금액 > 평가금액 → 예외 처리

ETF 주문:

미로그인 상태 → 주문 버튼 비활성화

보유 예수금 < 주문 총액 → 토스트 경고 + 모달 미노출

4. 프로젝트 작업일정 (Gantt 요약)

실제 Gantt 이미지는 /docs/gantt/bnk_gantt.png 등으로 관리.

11/05 ~ 11/07 : 아이디어 회의, 프로젝트 주제 선정, 역할 분담

11/07 ~ 11/10 : 요구사항 정의, IA/플로우차트/ERD 초안

11/10 ~ 11/19 : DB 설계·구축, 기본 화면/레이아웃 구축

11/19 ~ 11/30 : 각 파트별 기능 구현(상품·증권·회원·지점·마이데이터)

12/01 ~ 12/05 : 통합, 버그 수정, 성능 개선(멀티 스레드, WebSocket 안정화)

12/06 ~ 12/08 : 최종 점검, 발표 자료 및 보고서 작성

5. 아키텍처 및 서비스 정보
5-1. 시스템/인프라 구조

클라이언트: Web(Chrome, Edge 등)

서버: Spring Boot 3, Embedded Tomcat 10

DB: Oracle 21c

배포: AWS EC2(Amazon Linux 2023) + Docker

CI/CD: GitHub Actions

5-2. 기술 스택 상세
유형	구분	내용
SW	OS(Dev)	Windows 10
	Browser	Chrome 최신 버전
	WAS	Apache Tomcat 10 (Spring Boot 내장)
	Framework	Spring Boot 3.x
	Language	Java 17, Servlet 3.x
	Frontend	HTML5, CSS3, JavaScript(ES6)
	Library	Spring Boot Starters, MyBatis, JPA, Oracle Driver, Thymeleaf, QueryDSL, Lombok, ModelMapper, WebSocket, JWT, Apache Commons, SMS 인증, JSON 처리, lightweight-charts 등
	API	Kiwoom REST/WebSocket API, KoreaExim 환율 API, Kakao Map API, DB 증권 REST API
	DBMS	Oracle DB
	Tool	IntelliJ IDEA, SQL Developer, Git, GitHub, Figma
Infra/DevOps	Cloud(IaaS)	AWS EC2
	OS(Prod)	Amazon Linux 2023
	Container	Docker
	CI/CD	GitHub Actions
6. 정보 구조 (IA : Information Architecture)

실제 다이어그램은 /docs/ia 아래 이미지로 관리하고, README에는 상위 정보 구조만 텍스트로 정리.

상위 메뉴 구조

메인

메인 배너, 베스트 상품, 목적별 상품, 추천 상품 등

상품

퇴직연금(개인/기업), 예금/적금, 펀드/ETF

증권

증권 메인(실시간 랭킹/시세)

ETF/주식 주문(차트/호가/주문)

마이데이터

타행 IRP/연금 계좌 조회

수익률/위험등급 비교 및 추천

마이페이지

내 정보, 보유상품, 거래내역, IRP 계좌 관리

기업뱅킹

기업 퇴직연금, 납입/정산 흐름

지점/ATM

영업점/365/24·365/대여금고/외화ATM 조회 + 지도

고객센터

FAQ, 1:1 문의

관리자

회원 관리, 상품/지점 관리, 통계

7. 플로우 차트 (요약)

각 파트별 플로우 차트는 /docs/flows에 이미지로 존재하며, README에는 대표 흐름만 요약.

7-1. 로그인/회원 파트

로그인 페이지 진입

ID/PW 입력 → 비밀번호 해시 검증

성공 시 세션/토큰 발급, 헤더에 토스트(mid) 5초 표시

마이페이지/상품 가입/주문 기능 접근 가능

7-2. 퇴직연금 상품 가입 플로우

상품 상세 → “가입하기”

본인확인서(FATCA/CRS) 존재 여부 확인

없으면 Step1(작성)

있으면 Step2로 건너뛰기

약관/상품설명서 열람 + 필수 체크

중요 내용(금리/중도해지/세제 등) 확인

가입 정보 입력(기간, 금액, 비율, 출금계좌 선택)

출금계좌 비밀번호 4자리 입력 → 인증

가입 완료 화면 및 요약 표시

7-3. 증권 API / 환율 API 플로우

증권:
REST로 1분봉 300개 히스토리 + WebSocket(0A/0D) 실시간 시세

환율:
KoreaExim API에서 USD/KRW 실시간/일자별 환율 수신 → 주말 보정 후 화면 전달

7-4. 마이페이지 / 상품 변경 플로우

마이페이지 → 보유상품 목록 조회

전부매도 / 일부매도 / 매수 선택

금액 또는 비율 입력 (둘 중 하나만)

선택 상품 요약(평가금액, 수익률, 비중) 확인

비밀번호 인증 → @Transactional 매매 처리 → 결과 토스트/모달 표시

8. ERD 및 테이블 명세 (요약)

전체 ERD/테이블 명세는 /docs/erd 아래에 관리.

대표 테이블:

USERS

회원 정보(mId, mName, mEmail, mPhone, mGrade 등)

PCONTRACT

계좌번호(pacc), 상품ID, 잔액, 비밀번호 등

PRODUCT

금융상품 정보(퇴직연금/예금/적금/펀드/ETF 등)

FUND/ETF 관련 테이블

종목코드, 운용사, 위험등급, 수수료, 기준가 등

TRADE / ORDER

주문/체결 내역(매수/매도, 수량, 체결가, 수수료 등)

IRPACCOUNT

IRP 계좌 및 퇴직연금 관련 정보

MYDATAACCOUNT

타행 IRP/연금 계좌 정보(은행명, 상품명, 수익률, 위험등급 등)

BRANCH

영업점/ATM 정보(지점명, 주소, 서비스, 카테고리)

9. 주요 기능 (요약 정리)

이미 위에서 한 번씩 등장했지만, “무슨 프로젝트냐?”를 빠르게 보여주는 용도로 다시 정리.

퇴직연금 상품 가입(5단계 Wizard)

보유상품 변경(일부/전부매도 + 매수, 비율·금액 입력)

ETF/주식 실시간 시세 & 주문(REST + WebSocket 혼합)

QR 기반 IRP 계좌 개설(PC ↔ 모바일 인증)

마이데이터 기반 연금상품 비교/추천

BNK 지점/ATM 통합 조회 + Kakao Map 연동

회원가입/로그인/ID/PW 찾기/비밀번호 변경

관리자 페이지(회원/상품/지점 관리)
