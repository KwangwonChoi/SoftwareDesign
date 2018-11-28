# 1. UI
## 1.1. UiBase
### 1.1.1. UiBase는 모든 Ui가 상속 받아야하는 Class이다.
#### UiBase를 보면 모든 Ui의 LifeCycle이 OnAwake() -> OnStart() -> OnFinished() 임을 알 수 있다.
##
## 1.2. MenuUiBase
### 1.2.1 MenuUi의 Base가 되는 Class이다.
#### _uiLists에 메뉴를 선택하면 가게 되는 class만 instantiate해 주면 된다.
##### SignMenuUi 참고.
##
## 1.3. MakeUiBase

#
# 2. Communiation
## 2.1 Server-Client
### 2.1.1 Server-Client의 Communiation양식은 기본적으로 Json을 사용한다.
### 2.1.2 Client에서 받은 정보를 Server에서 재가공 하기 위해 JsonWrapper class의 type을 이용하여 Client object의 정보를 읽어낸다.
#### EchoServer의 handleMessageFromClient에서 확인할 수 있다.
##
## 2.2 Ui-Server
### 2.2.1 Ui에서 Server에 정보를 보내기위해 OCSF의 ChatClient를 이용해야한다. 이 ChatClient는 OCSF.client의 ClientData 안에 구현되어있다.
#### client의 instantiate는 ClientConsole에서 이루어진다.
#### 예제는 SignUpBaseUi의 SignUpToServer 참고.
##
## 2.3 Server-File
### 2.3.1 Server에서 File에 저장하는 모든 내용은 Json으로 저장된다.
### 2.3.2 File에 접근하기위해서는 FileManager만 이용해야 하며, 모든 파일의 경로는 ../Data/* 이어야 한다.

#
# 3. Framework
## 3.1 OCSF
### 3.1.1 본 프로그램의 OCSF를 이용하여 구현하였다.

#
# 4. External Libraries
## 4.1 Gson
### 4.1.1 본 프로그램의 Json형식은 Gson을 이용하여 구현하였다.
## 4.2 Log4j
