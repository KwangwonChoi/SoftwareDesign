# UI
## UiBase
### UiBase는 모든 Ui가 상속 받아야하는 Class이다.
#### UiBase를 보면 모든 Ui의 LifeCycle이 OnAwake() -> OnStart() -> OnFinished() 임을 알 수 있다.
##
## MenuUiBase
### MenuUi의 Base가 되는 Class이다.
#### _uiLists에 메뉴를 선택하면 가게 되는 class만 instantiate해 주면 된다.
##### SignMenuUi 참고.
##
## MakeUiBase

#
# Communiation
## Server-Client
### Server-Client의 Communiation양식은 기본적으로 Json을 사용한다.
### Client에서 받은 정보를 Server에서 재가공 하기 위해 JsonWrapper class의 type을 이용하여 Client object의 정보를 읽어낸다.
#### EchoServer의 handleMessageFromClient에서 확인할 수 있다.
##
## Ui-Server
### Ui에서 Server에 정보를 보내기위해 OCSF의 ChatClient를 이용해야한다. 이 ChatClient는 OCSF.client의 ClientData 안에 구현되어있다.
#### client의 instantiate는 ClientConsole에서 이루어진다.
#### 예제는 SignUpBaseUi의 SignUpToServer 참고.
##
## Server-File
### Server에서 File에 저장하는 모든 내용은 Json으로 저장된다.
### File에 접근하기위해서는 FileManager만 이용해야 하며, 모든 파일의 경로는 ../Data/* 이어야 한다.
