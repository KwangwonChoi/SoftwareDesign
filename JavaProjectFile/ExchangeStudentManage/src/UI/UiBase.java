package UI;

public abstract class UiBase {
	
	protected String _uiName;
	
	public UiBase(String uiName) {
		_uiName = uiName;
	}
	
	public void UiStart() {
		OnAwake();
		OnStart();
		OnFinished();
	}
	
	protected void PrintUiName() {
		System.out.println("\n\n¢º This is " + _uiName + " Ui");
	}
	
	protected abstract void OnAwake(); 
	protected abstract void OnStart();
	protected void OnFinished() {};
}
