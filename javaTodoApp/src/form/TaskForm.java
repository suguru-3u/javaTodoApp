package form;

public class TaskForm {
	
	private String taskTitle;
	private String taskMain;
	
	public TaskForm(String taskTitle,String taskMain) {
		this.taskTitle = taskTitle;
		this.taskMain = taskMain;
	}
	
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	
	public void setTaskMain(String taskMain) {
		this.taskMain = taskMain;
	}
	
	public String getTaskTitle() {
		return this.taskTitle;
	}
	
	public String getTaskMain() {
		return this.taskMain;
	}
}
