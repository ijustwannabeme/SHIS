# 사내 영업직원 평가시스템

## 프로젝트 개요

매월 직원들을 평가하는 사내 시스템

### 주요 기능

- 월별 평가 대상 목록 조회
- 월별 변동 이력 조회
- 월별 팀 평가 순위 산정
- 개인 평가 관리
- 평가 목록 확정

## 기술 스택

- **Backend**: Spring Boot 3.3.4, Spring Data JPA
- **Language**: Java 17
- **Database**: MySQL
- **Build Tool**: Gradle
- **Version Control**: Git, GitHub
- **IDE**: IntelliJ IDEA, MySQL Workbench

## 주요 API

1. 평가 대상 목록 조회 API
2. 월별 변동 이력 조회 API
3. 평가 목록 순위 산정 API
4. 평가 목록 확정 API
5. 직원 정보 조회 API
6. 직원 평가 조회 API

## 데이터베이스 설계

- employee (직원 정보)
- evaluation (평가 결과)
- evaluation_list (평가 확정 목록)
- change_history (직원 변동 이력)
- team (팀)
- hgrn (소속부서)

## 주요 고려사항

- 평가 대상인 직원의 월별 평가 항목 및 점수 관리
- 점수 확정 전 수정 가능, 확정 후 수정 불가
- 인사이동, 팀 변경, 퇴사자 관리
- 월별 변동 이력 관리

## api 화면

![image](https://github.com/user-attachments/assets/8ac2c787-daeb-4459-9d4a-a0f8eca4f831)
![image](https://github.com/user-attachments/assets/82c8add8-18ff-409f-967b-321eaeb9ea69)
![image](https://github.com/user-attachments/assets/8f9aa05e-2b32-400f-bc38-ba3a6ea9ffa1)


## 향후 개선 사항

- 개인별 순위 변화 내역 시각화
- 페이지네이션 구현
- 개인 및 담당자의 권한 관리 개선

## 기여 방법

1. 이 저장소를 포크합니다.
2. 새 브랜치를 생성합니다 (`git checkout -b feature/AmazingFeature`)
3. 변경 사항을 커밋합니다 (`git commit -m 'Add some AmazingFeature'`)
4. 브랜치에 푸시합니다 (`git push origin feature/AmazingFeature`)
5. Pull Request를 생성합니다.

## 연락처

프로젝트 관리자 - [이메일 주소]

프로젝트 링크: [https://github.com/yourusername/your-repo-name](https://github.com/yourusername/your-repo-name)
