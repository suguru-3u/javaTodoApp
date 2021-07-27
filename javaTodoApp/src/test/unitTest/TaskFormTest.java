package test.unitTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

import form.TaskForm;

class TaskFormTest {
	
//	@Before
//	public void taskFormSetUp() {
//		
//	}
	
	
	@Test
	void testTaskFormCreate() {		
		TaskForm taskForm = new TaskForm("testTitle","testMain");
		assertThat("testTitle", is(taskForm.getTaskTitle()));
		assertThat("testMain", is(taskForm.getTaskMain()));
	}
	
	@Test
	void testTaskFormSetTitle() {		
		TaskForm taskForm = new TaskForm("testTitle","testMain");
		taskForm.setTaskTitle("testTitleSet");
		assertThat("testTitleSet", is(taskForm.getTaskTitle()));
		assertThat("testMain", is(taskForm.getTaskMain()));
	}
	
	@Test
	void testTaskFormSetMain() {		
		TaskForm taskForm = new TaskForm("testTitle","testMain");
		taskForm.setTaskMain("testMainSet");
		assertThat("testMainSet", is(taskForm.getTaskMain()));
	}
	
	

}
