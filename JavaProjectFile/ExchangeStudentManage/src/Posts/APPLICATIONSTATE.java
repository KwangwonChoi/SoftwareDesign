package Posts;

public enum APPLICATIONSTATE {
		EVALUATED, // 점수가 입력 되었을 때의 임시적인 상황 
		
		SUBMIT, // 제출
		UNDERREVIEW, //검토
		PASS, // 패스
		CANDIDATE, // 후보
		UNPASS // 탈락
}
