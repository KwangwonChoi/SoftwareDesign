# SoftwareDesign
## 1. 프로그램 소개
### 1.1 해외대학 교환학생 지원 시스템

경북대학교에서는 재학기간동안 한 학기 또는 두 학기 동안 해외 대학에서 공부하고 학
점을 인정받는 제도를 시행하고 있다. 현재는 교환학생 모집 공고를 인터넷에서 찾아보
고 지원서류를 국제교류처에 제출하여 응시하게 되어 있으며, 선발, 결과 통보, 학점 인
정 등 모든 과정을 국제교류처 담당 직원이 직접 처리하고 있다. 현재의 시스템을 개선
해서 최대한 모든 프로세스를 자동화 하는 새로운 시스템을 개발하고자 한다. 특히, 사
용자는 다음과 같은 기능들을 담당직원의 도움 없이 수행할 수 있어야 한다
- 교환학생 모집 공고의 조회
- 응시원서의 접수 및 결과 조회
- 학점 인정 신청 및 결과 조회
- 기간별 교환학생 파견 실적 조회 

### 1.2 제작기간

#### ~ 10.4 Requirements & Usecase Diagram.
#### ~ 11.1 Class Diagram & Sequence Diagram
#### ~ 11.22 Design Documents
#### ~ 12.7 Implement

## 2. Documents
### 2.1 System Proposal
<https://github.com/KwangwonChoi/SoftwareDesign/raw/master/SystemProposal/08%ED%8C%80%20%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EB%AC%B8%EC%84%9C_ver02.docx>
### 2.2 Usecase Diagram
![UseCaseDiagram](./Images/UseCase.jpg)
### 2.3 Activity Diagram
#### ![ActivityDiagram](./Images/Activity_upload.png)
#### ![ActivityDiagram](./Images/Activity_submit.png)
#### ![ActivityDiagram](./Images/Activity_result_view_edit.png)

### 2.4 Class Diagram
#### ![ClassDiagram](./Images/ClassDiagram.jpg)

### 2.5 Sequence Diagram
#### ![SequenceDiagram](./Images/SequenceDiagram_submitApplication.jpg)

#### 아래의 시퀀스 다이어그램은 클래스 다이어그램의 수정으로 수정이 필요함.
#### ![SequenceDiagram](./Images/SequenceDiagram_update응시원서.jpg)
#### ![SequenceDiagram](./Images/SequenceDiagram_Upload모집공고.jpg)

## 3. Others

### 3.1 Log
#### 3.1.1 프로그램을 개발하면서 로그를 사용하게 되면 다음과 같은 이점을 가진다. 이에 본 프로그램에 로그를 사용하고자 한다.
##### 1. 로그를 기록하여 나중에 결과를 분석할 수 있다.
##### 2. 개발 기간 중에 문제가 발생한 시점을 찾아낼 수 있다.
##### 3. 어플리케이션이 실행되는 동안의 상황과 상태정보를 알 수 있다.
#### 3.1.2 로그 개념 관련 링크
##### <https://opentutorials.org/course/697/3958/>
#### 3.1.3 Log4j
##### 1. 본 프로그램 에서는 가장 보편적인 Log4j를 사용하고자 한다.
##### 2. 위키피디아 : <https://ko.wikipedia.org/wiki/Log4j>
##### 3. log4j 사용 방법 : <http://wiki.intellicode.co.kr/doku.php?id=%ED%98%95%EC%86%8C%EB%8B%B4:log4j_%EC%82%AC%EC%9A%A9_%EB%B0%A9%EB%B2%95/>

