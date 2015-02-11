/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooadproject;
import com.service.ProjectUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import ooadproject.EntityTables.Project;
import ooadproject.EntityTables.Task;
import ooadproject.EntityTables.User;


/**
 *
 * @author Coolth
 */
public class TaskInfoService {
        private EntityManager manager;

	public TaskInfoService(EntityManager manager) {
		this.manager = manager;
	}
//        public Project editTask(int taskId, String userName, String projectDescr, String status, String category,
//                String desiredOutcome, int budgetId, int timelineId, Date lastupdatedDate, String lastupdatedId ){
//            Project project = new Project();
//            return project;
//        }
        
//        //method to read a record
//        public Project readProject(int projectId){
//            Project project = manager.find(Project.class, projectId);
//            return project;
//        }
        
        // method to update a record
//	public Project updateProject(int projectId,String projectName, String projectDescr, String status,String category, String desiredOutcome, String startDate, String endDate, 
//                String equipmentName, String estimatedCost, String projectedCost, String link) {
//		 Project projectinfo = manager.find(Project.class, projectId
//                 );
//		if (projectinfo != null) {
//			projectinfo.setProjectName(projectName);
//			projectinfo.setProjectDescr(projectDescr);
//			projectinfo.setStatus(status);
//                        projectinfo.setCategory(category);
//                        projectinfo.setDesiredOutcome(desiredOutcome);
//                        projectinfo.setStartDate(ProjectUtil.parseDate(null,startDate));
//			projectinfo.setEndDate(ProjectUtil.parseDate(null,endDate));
//                        projectinfo.getBudgetCollection().iterator().next().setEquipmentName(equipmentName);
//                        projectinfo.getBudgetCollection().iterator().next().setEstimatedCost(estimatedCost);
//                        projectinfo.getBudgetCollection().iterator().next().setProjectedCost(projectedCost);
//                        projectinfo.getBudgetCollection().iterator().next().setLink(link);
//	
//		}
//		return projectinfo;
//	}
        
        //method to read all records
        public List<User> readAll(){
            //"SELECT p FROM Project p"
           String sql = "SELECT u from user u join u.taskCollection tc on u=tc.userId join project p on p=tc.projectId where p.projectId="+EditProjectInfoGUI.passprojecId;
           System.out.println(sql);
           TypedQuery<User> query = manager.createQuery(sql, User.class);
           // TypedQuery<User> query = manager.createQuery("SELECT u.name,t.taskId,t.taskDescr,t.startDate,t.endDate FROM user u JOIN u.taskCollection t  on u.taskCollection.userId = t.userId where t.projectId = 1", User.class);
            List<User> result = query.getResultList();
//            Iterator it = result.iterator();
//            while(it.hasNext()){
//               System.out.println(it.next());
//            }

            return result;
        }
//        public List<ProjectBudget> readProjectAndBudget(){
//            TypedQuery<ProjectBudget> query = manager.createQuery("SELECT p, b FROM Project p left join Budget b on p.project_id = b.project_id", ProjectBudget.class);
//            List<ProjectBudget> result = query.getResultList();
//            
//            return result;
//        }
//        public List<Budget> readBudgetAll(){
//            TypedQuery<Budget> query = manager.createQuery("SELECT b from Budget WHERE project_id =  ", Budget.class);
//            List<Budget> result = query.getResultList();
//            return result;
//        }

    public Task updateUserTask(int taskId, String taskDescri, String startDate, String endDate) {
        Task taskinfo = manager.find(Task.class, taskId);
        if (taskinfo != null) {
            taskinfo.setTaskId(taskId);
            taskinfo.setTaskDescr(taskDescri);
            taskinfo.setStartDate(ProjectUtil.parseDate(null,startDate));
            taskinfo.setEndDate(ProjectUtil.parseDate(null,endDate));
//            userinfo.getTaskCollection().iterator().next().setEndDate(ProjectUtil.parseDate(null,endDate));             	
        }
        return taskinfo;
    }
 
        
}
