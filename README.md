🏦 BNK 부산은행 퇴직연금 통합 플랫폼 – Team 2

K-Digital Training · BNK 금융DT 아카데미 실전 프로젝트

퇴직연금 + 실시간 증권 + 마이데이터 + 지점/ATM 조회까지 모두 담은 통합 금융 플랫폼
(부기은행 · Team 2조)

📌 1. 프로젝트 개요
항목	내용
프로젝트 주제	BNK 부산은행 퇴직연금 통합 플랫폼
프로젝트 기간	2025.11.05 ~ 2025.12.08
배경 및 목적	퇴직연금 상품 판매/관리 + 증권 + 마이데이터 + 지점 조회 통합 서비스 구현
주요 기능	퇴직연금 가입/변경, 실시간 증권 정보(국내/해외), 마이데이터 상품 비교, 지점/ATM 조회, 회원/로그인 관리
배포 주소	http://3.39.247.70:8080/BNK

GitHub	https://github.com/JinIleSon/BNK-The-First-Main-Project.git
👥 2. 팀 구성
팀 조직도 & 역할
이름	역할	주요 업무
손진일	PM, 팀장	실시간 증권(REST+WS), 마이페이지 ETF, 관리자, 멀티스레드 스케줄러, 시스템 통합
전세현	팀원	환율 API, 마이데이터 비교 서비스, 외부 API 파이프라인
강민철	팀원	퇴직연금 가입 5단계 Wizard, 보유상품 변경(매수/매도), 배포/운영(AWS)
이준우	팀원	로그인/회원가입/ID·PW 찾기, 인증, QR 기반 IRP 계좌 개설
조지영	팀원	지점/ATM 조회 + Kakao Map, 기업뱅킹, 공통 UI/UX
🧩 3. 요구사항 정의 · WBS · 테스트 시나리오

📁 실제 문서는 /docs/requirements, /docs/wbs, /docs/test-scenarios 에 존재한다고 가정하며 README에는 요약본만 포함합니다.

### 3-1. 요구사항 정의(요약)
회원/인증

회원가입 / 로그인 / 로그아웃

비밀번호 찾기·변경(휴대폰/이메일 인증 포함)

세션/토큰 관리

퇴직연금 상품

상품 목록/상세 조회

5단계 가입 플로우 (본인확인서 → 약관 → 안내 → 정보입력 → 인증)

IRP/DC/DB 유형 제약 검증

보유상품 관리

평가금액/수익률/운용비중 계산

일부·전부매도 / 매수 기능

금액·비율(%) 입력 중 택1

증권 · 실시간 시세

국내/해외 ETF·주식 TOP100 실시간 랭킹

1분봉 차트(REST) + 실시간 호가(WebSocket)

로그인 사용자 매수/매도 기능

마이데이터

타행 IRP/연금 수집

위험등급/수익률 기반 비교·추천

지점/ATM 조회

시/도 → 구/군 2단계 필터

Kakao Map 연동

카드형 UI & 지도 동기화

관리자

상품/회원/지점 관리

통계/현황 조회

### 3-2. WBS 요약

분석: 요구사항 정의, 도메인 조사

설계: IA, 플로우차트, ERD, 화면 설계

구현

공통/레이아웃

인증/회원

상품/IRP 계좌

증권/시세

마이데이터

지점/ATM

관리자

테스트

단위 테스트

통합 테스트

시나리오 기반 UAT

### 3-3. 테스트 시나리오 예시
🔸 퇴직연금 상품 가입

본인확인서 없으면 Step1 강제 이동

약관 미열람 시 진행 불가

PIN 4자리 불일치 → 가입 실패

🔸 보유상품 일부매도

금액/비율 미입력 → 합계 제외

매도금액 > 평가금액 → 오류 처리

🔸 ETF 주문

미로그인 → 주문 버튼 비활성화

예수금 부족 → 경고 + 모달 미노출

🗓 4. 프로젝트 작업일정(Gantt 요약)

전체 Gantt 이미지는 /docs/gantt/bnk_gantt.png에 관리한다고 가정

11/05~11/07: 기획/주제 선정/역할 분담

11/07~11/10: 요구사항 정의, ERD/IA/Flow 설계

11/10~11/19: DB 구축, 레이아웃/기본 화면 개발

11/19~11/30: 파트별 기능 구현

12/01~12/05: 통합·버그 수정·성능 개선(멀티스레드·WS)

12/06~12/08: 마감 점검/발표/보고서 완성

🏗 5. 아키텍처 및 기술 스택
### 5-1. 시스템/인프라 구조

Client: Web (Chrome/Edge)

Backend: Spring Boot 3 + Embedded Tomcat

DB: Oracle 21c

Infra: AWS EC2 + Docker

CI/CD: GitHub Actions

### 5-2. 기술 스택 상세
유형	구분	내용
SW	Language	Java 17, JS ES6, HTML/CSS
	Framework	Spring Boot 3.x
	Frontend	Thymeleaf, Vanilla JS
	Library	MyBatis, JPA, JWT, WebSocket, QueryDSL, lightweight-charts
	DB	Oracle 21c
API	증권	Kiwoom REST/TR, Kiwoom WebSocket, DB증권 REST
	외부	KoreaExim 환율 API, Kakao Maps
Tools	IDE	IntelliJ, SQL Developer, Git/GitHub, Figma
Infra	Cloud	AWS EC2(Amazon Linux 2023), Docker
DevOps	CI/CD	GitHub Actions
🧭 6. IA (Information Architecture)

전체 IA 다이어그램은 /docs/ia에 저장한다고 가정

상위 메뉴 구조

메인: 배너, 추천/베스트 상품

상품: 퇴직연금(IRP/DC/DB), 예금/적금, 펀드/ETF

증권: 실시간 랭킹, ETF/주식 주문

마이데이터: 타행 IRP 조회, 비교/추천

마이페이지: 내 정보, 보유상품, 거래내역

기업뱅킹: 납입/정산

지점/ATM: 위치·지도 조회

고객센터: FAQ/문의

관리자: 회원/상품/지점 관리, 통계

🔀 7. 플로우차트(요약)

상세 이미지는 /docs/flows에 별도 저장

7-1. 로그인/회원

ID/PW 입력 → 해시 검증 → 토스트(mid) 출력 → 세션 발급

7-2. 퇴직연금 가입

상세 → 가입 → FATCA/CRS → 약관 → 중요 안내 → 정보입력 → PIN 인증 → 완료

7-3. 증권/환율 API

REST: 히스토리

WS: 실시간

혼합 구조 적용

7-4. 마이페이지 (매수/매도/변경)

목록 → 선택 → 금액/비율 입력 → PIN 인증 → 트랜잭션 처리

🗄 8. ERD & 테이블 요약
주요 테이블

USERS (회원)

PCONTRACT (IRP 계좌)

PRODUCT (금융상품)

FUND/ETF 관련 테이블

TRADE/ORDER (거래/체결)

IRPACCOUNT

MYDATAACCOUNT

BRANCH

🚀 9. 주요 기능 요약

퇴직연금 상품 5단계 가입 Wizard

일부/전부매도 + 매수

실시간 ETF/주식 랭킹 + 차트 + 호가

QR 기반 IRP 계좌 개설

마이데이터 기반 비교·추천

지점/ATM + 지도

관리자 페이지

👤 10. 팀원별 담당 파트

각 팀원이 실제 담당한 영역만 정리한 상세 섹션입니다.

### 10-1. 손진일 – PM / 증권 · 마이페이지 · 관리자
📌 실시간 증권 메인

국내/해외 ETF·주식 TOP100

1.5초 간격 자동 갱신

TickerBar/Toast

📌 증권 주문(REST + WebSocket)

1분봉 차트(최신 300개)

실시간 호가/현재가 (0A/0D 채널)

REST+WS 혼합 구조 설계

📌 멀티 스레드 스케줄러

단일 스레드 → 4개 스레드 풀

해외 TOP100 초기 풀스캔 비동기 처리

📌 마이페이지 ETF

실시간 수익률 갱신

매도/매수 UX

📌 PM 업무

일정 관리, 문서 취합, 통합 조율

### 10-2. 강민철 – 상품 가입 · 보유상품 변경 · 배포
🧩 퇴직연금 가입(5단계 Wizard)

FATCA/CRS, 약관, 중요 안내, 정보입력, PIN 인증

입력값 state 관리

🧩 금액/비율 토글 입력

충돌 방지 로직

계좌 잔액 기반 안내문

🧩 보유상품 변경(매수/매도)

선택상품 집계 테이블

일부매도/전부매도

매수 UI 재사용

🧩 @Transactional 매매 처리
🧩 AWS 배포/운영

프로파일 분리

JAR 배포

Oracle 연결

### 10-3. 전세현 – 외부 API · 환율 · 마이데이터
💱 환율 API

KoreaExim 수집/보정

FxService 가공

📊 마이데이터 비교

MyDataAccount 설계

위험/수익률 기반 추천 알고리즘

### 10-4. 이준우 – 회원/인증 · QR 기반 IRP 개설
🔐 로그인/회원가입

휴대폰/이메일 인증

비밀번호 정책

ID/PW 찾기

📱 QR 기반 IRP 계좌 개설

PC ↔ 모바일 인증

상태 폴링 방식

### 10-5. 조지영 – 지점/ATM · 기업뱅킹 · 공통 프론트
📍 지점/ATM 조회 서비스

카테고리별 필터

시/도 → 구/군 필터

Kakao Map 연동

🏢 기업뱅킹 흐름 정리
🎨 공통 UI/UX

헤더/푸터

모바일 카드형 UI
