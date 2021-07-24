package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Task;

public class TaskTest {
	
public static Task task = null;
public static Task taskFinsh = null;
	
	@Before
	public void userSetUp() {
		task = new Task(1,"testTitle","testMain",0,1);
		taskFinsh = new Task(2,"testFinishTitle","testFinishMain",1,1);
	}
	
	@Test
    public void testConstractTask(){
		Task task = new Task(5,"test","test",0,1);
		assertEquals(5, task.getId());
		assertEquals("test", task.getTitle());
		assertEquals("test", task.getMain());
		assertEquals(0, task.getFinishFlg());
		assertEquals(1, task.getUserId());
    }
	
	@Test
    public void testSetTitle(){
		task.setTitle("testTitleEdit");
		assertEquals("testTitleEdit", task.getTitle());
    }

	@Test
    public void testSetMain(){
        task.setMain("testMainEdit");
        assertEquals("testMainEdit", task.getMain());
    }

	@Test
    public void testSetFinishFlg(){
        task.setFinishFlg(1);
        assertEquals(1, task.getFinishFlg());
    }
	
	@Test
	public void testTaskToString(){
		String taskString =  "タイトル：　" + "testTitle" + " 詳細:　" + "testMain";
		assertEquals(taskString, task.toString());
	}
	
	@Test
	public void testTaskFinishToString(){
		String taskString =  "このTaskは終了しています..  タイトル：　" + "testFinishTitle" + "詳細:　" + "testFinishMain";
		assertEquals(taskString, taskFinsh.toString());
	}

}
