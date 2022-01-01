package com.mayubix.taskcenter.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TaskList {
    public static final String OBJECT_NAME = "TaskList";
    private static Integer s_ObjectCounter = 1;

    private final String id;
    private final Long createTime;
    private ArrayList<Task> tasks;
    private String name;

    public TaskList(String id, Long createTime){
        this.id = id;
        s_ObjectCounter++;

        this.createTime = createTime;

        tasks = new ArrayList<>();
    }

    public TaskList(){
        this.id = OBJECT_NAME + ":" + s_ObjectCounter;
        s_ObjectCounter++;

        this.createTime = System.currentTimeMillis();

        tasks = new ArrayList<>();
    }

    public TaskStep findStepById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskStep step = null;

        while(!found && count < task.getSteps().size()){
            step = task.getSteps().get(count);
            if(step.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return step;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public void setName(String val){
        this.name = val;
    }

    public String getName(){
        return this.name;
    }

    public TaskNote findNoteById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskNote note = null;

        while(!found && count < task.getNotes().size()){
            note = task.getNotes().get(count);
            if(note.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return note;
    }

    public TaskHistoryItem findHistoryItemById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskHistoryItem historyItem = null;

        while(!found && count < task.getTaskHistoryItems().size()){
            historyItem = task.getTaskHistoryItems().get(count);
            if(historyItem.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return historyItem;
    }

    public TaskTag findTagById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskTag tag = null;

        while(!found && count < task.getTags().size()){
            tag = task.getTags().get(count);
            if(tag.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return tag;
    }

    public Task findTaskById(String id){
        return Task.s_taskObjects.get(id);
    }

    public String getId(){
        return this.id;
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public Task addTask(String taskName){
        Task task = new Task();
        task.setName(taskName);
        tasks.add(task);
        return task;
    }

    public Task removeTask(Task task){
        tasks.remove(task);
        task.clean();
        return task;
    }

    public void editTaskName(Task task, String newName){
        task.setName(newName);
    }

    public void editTaskDescription(Task task, String newDescription){
        task.setDescription(newDescription);
    }

    public void editTaskTargetDate(Task task, Long date){
        task.setTargetDate(date);
    }

    public void setTaskCurrentStep(Task task, TaskStep step){
        task.setCurrentStep(step);
    }

    public void editTaskSize(Task task, Short size){
        task.setSize(size);
    }

    public void editTaskPriority(Task task, Short priority){
        task.setPriority(priority);
    }

    public void initTaskStatus(Task task, TaskStatusValue statusValue){
        TaskStatus status = new TaskStatus(task);
        status.setStatus(statusValue);
        task.setStatus(status);
    }

    public void initTaskCategory(Task task, String categoryValue){
        TaskCategory category = new TaskCategory(task);
        category.setName(categoryValue);
        task.setCategory(category);
    }

    public void addTaskStep(Task task, String name, String description){
        task.createTaskStep(name, description);
    }

    public void editTaskStepDescription(Task task, TaskStep taskStep, String description){
        ArrayList<TaskStep> steps = task.getSteps();
        int index = steps.indexOf(taskStep);
        TaskStep step = steps.get(index);
        step.setDescription(description);
    }

    public void editTaskStepName(Task task, TaskStep taskStep, String name){
        ArrayList<TaskStep> steps = task.getSteps();
        int index = steps.indexOf(taskStep);
        TaskStep step = steps.get(index);
        step.setName(name);
    }

    public void removeTaskStep(Task task, TaskStep taskStep){
        ArrayList<TaskStep> steps = task.getSteps();
        steps.remove(taskStep);
    }

    public void addTaskNote(Task task, String name, String content){
        task.createTaskNote(name, content);
    }

    public void removeTaskNote(Task task, TaskNote note){
        ArrayList<TaskNote> notes = task.getNotes();
        notes.remove(note);
    }

    public void addTaskTag(Task task, String name){
        task.createTaskTag(name);
    }

    public void removeTaskTag(Task task, TaskTag tag){
        task.getTags().remove(tag);
    }

    public void addTaskHistoryItem(Task task,  String eventName, String eventDescription){
        task.createHistoryItem(eventName, eventDescription, System.currentTimeMillis());
    }

    public void removeTaskHistoryItem(Task task, TaskHistoryItem item){
        task.getTaskHistoryItems().remove(item);
    }

    public static TaskList loadFromXML(String fileName){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        int taskObjectCount = 1;

        try{
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(new File(fileName));

            Element taskListElement = (Element) doc.getElementsByTagName(TaskList.OBJECT_NAME).item(0);

            TaskList taskList = new TaskList(taskListElement.getAttribute("id"), Long.parseLong(taskListElement.getAttribute("createTime")));
            taskList.setName(taskListElement.getAttribute("name"));
            NodeList taskElements = taskListElement.getElementsByTagName(Task.OBJECT_NAME);
            for(int i = 0; i < taskElements.getLength(); i++){
                Node taskNode = taskElements.item(i);
                if(taskNode.getNodeType() == Node.ELEMENT_NODE){
                    Element taskElement = (Element) taskNode;

                    Task task = new Task(taskElement.getAttribute("id"), Long.parseLong(taskElement.getAttribute("createTime")));
                    task.setName(taskElement.getAttribute("name"));
                    task.setDescription(taskElement.getAttribute("description"));
                    task.setTargetDate(Long.parseLong(taskElement.getAttribute("targetDate")));
                    task.setTimeElapsed(Long.parseLong(taskElement.getAttribute("timeElapsed")));
                    task.setTimeWorked(Long.parseLong(taskElement.getAttribute("timeWorked")));
                    task.setCompletionDate(!taskElement.getAttribute("completionDate").equals("") ? Long.parseLong(taskElement.getAttribute("completionDate")) : null);
                    task.setTimePassedTargetDate(Long.parseLong(taskElement.getAttribute("timePassedTargetDate")));


                    //Create task steps
                    String currentStepId = taskElement.getAttribute("currentStep");
                    NodeList stepElements = taskElement.getElementsByTagName(TaskStep.OBJECT_NAME);
                    for(int j = 0; j < stepElements.getLength(); j++){
                        Node stepNode = stepElements.item(j);
                        if(stepNode.getNodeType() == Node.ELEMENT_NODE){
                            Element stepElement = (Element) stepNode;

                            TaskStep loadedStep = task.createTaskStep(stepElement.getAttribute("id"), stepElement.getAttribute("name"),
                                    stepElement.getAttribute("description"), Long.parseLong(stepElement.getAttribute("createTime")));

                            loadedStep.parseStatusString(stepElement.getAttribute("statusValue"));

                            if(loadedStep.getId().equals(currentStepId)){
                                task.setCurrentStep(loadedStep);
                            }

                        }
                    }

                    //Create task notes
                    NodeList noteElements = taskElement.getElementsByTagName(TaskNote.OBJECT_NAME);
                    for(int j = 0; j < noteElements.getLength(); j++){
                        Node noteNode = noteElements.item(j);
                        if(noteNode.getNodeType() == Node.ELEMENT_NODE){
                            Element noteElement = (Element) noteNode;

                            TaskNote loadedNote = task.createTaskNote(noteElement.getAttribute("id"),
                                    Long.parseLong(noteElement.getAttribute("createTime")), noteElement.getAttribute("name"),
                                    noteElement.getAttribute("content"));


                        }
                    }

                    //Initialize task size
                    task.setSize(Short.parseShort(taskElement.getAttribute("size")));

                    //Initialize task priority
                    task.setPriority(Short.parseShort(taskElement.getAttribute("priority")));

                    //Initialize TaskStatus
                    Element taskStatusElement = (Element) taskElement.getElementsByTagName(TaskStatus.OBJECT_NAME).item(0);
                    TaskStatus status = new TaskStatus(taskStatusElement.getAttribute("id"),
                            Long.parseLong(taskStatusElement.getAttribute("createTime")),
                            task, taskStatusElement.getAttribute("status"));
                    task.setStatus(status);

                    //Initialize task tags
                    NodeList tagElements = taskElement.getElementsByTagName(TaskTag.OBJECT_NAME);
                    for(int j = 0; j < tagElements.getLength(); j++){
                        Node tagNode = tagElements.item(j);
                        if(tagNode.getNodeType() == Node.ELEMENT_NODE){
                            Element tagElement = (Element) tagNode;

                            TaskTag loadedTag = task.createTaskTag(tagElement.getAttribute("id"),
                                    Long.parseLong(tagElement.getAttribute("createTime")),
                                    tagElement.getAttribute("name"));
                        }
                    }

                    //Initialize the timePending
                    task.setTimePending(Long.parseLong(taskElement.getAttribute("timePending")));

                    //Initialize the TaskCategory
                    Element taskCategoryElement = (Element) taskElement.getElementsByTagName(TaskCategory.OBJECT_NAME).item(0);
                    TaskCategory loadedCategory = new TaskCategory(taskCategoryElement.getAttribute("id"),
                            Long.parseLong(taskCategoryElement.getAttribute("createTime")), task);

                    loadedCategory.setName(taskCategoryElement.getAttribute("name"));
                    task.setCategory(loadedCategory);

                    //Initialize Task History Items
                    NodeList historyElements = taskElement.getElementsByTagName(TaskHistoryItem.OBJECT_NAME);
                    for(int j = 0; j < historyElements.getLength(); j++){
                        Node historyNode = historyElements.item(j);
                        if(historyNode.getNodeType() == Node.ELEMENT_NODE){
                            Element historyElement = (Element) historyNode;

                            task.createHistoryItem(historyElement.getAttribute("id"),
                                    Long.parseLong(historyElement.getAttribute("createTime")),
                                    historyElement.getAttribute("eventName"),
                                    historyElement.getAttribute("description"),
                                    Long.parseLong(historyElement.getAttribute("eventTime")));


                        }
                    }

                    String[] idTokens = task.getId().split(":");
                    int taskIDNum = Integer.parseInt(idTokens[1]);
                    Task.s_objectCounter = Integer.max(Task.s_objectCounter, taskIDNum);
                    System.out.println("S Object Counter: " + Task.s_objectCounter);
                    taskList.getTasks().add(task);
                    System.out.println("Task List Size: " + taskList.getTasks().size());

                }
            }

            return taskList;
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            return null;
        }
    }

    public void saveToXML(String fileName){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try{
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement(TaskList.OBJECT_NAME);
            rootElement.setAttribute("id", this.getId());
            rootElement.setAttribute("createTime", this.getCreateTime().toString());
            rootElement.setAttribute("name", this.getName());
            doc.appendChild(rootElement);

            //Create the underlying Task Elements
            for(Task task : this.getTasks()){
                Element taskElement = doc.createElement(Task.OBJECT_NAME);
                taskElement.setAttribute("id", task.getId());
                taskElement.setAttribute("name", task.getName());
                taskElement.setAttribute("description", task.getDescription());
                taskElement.setAttribute("targetDate", task.getTargetDate().toString());
                taskElement.setAttribute("timeElapsed", task.getTimeElapsed().toString());
                taskElement.setAttribute("timeWorked", task.getTimeWorked().toString());
                taskElement.setAttribute("completionDate", task.getCompletionDate() != null ? task.getCompletionDate().toString() : "");
                taskElement.setAttribute("timePassedTargetDate", task.getTimePassedTargetDate().toString());
                taskElement.setAttribute("currentStep", task.getCurrentStep() != null ? task.getCurrentStep().getId() : "null");
                taskElement.setAttribute("size", task.getSize().toString());
                taskElement.setAttribute("priority", task.getPriority().toString());
                taskElement.setAttribute("timePending", task.getTimePending().toString());
                taskElement.setAttribute("createTime", task.getCreateTime().toString());

                //Create underlying category
                Element categoryElement = doc.createElement(TaskCategory.OBJECT_NAME);
                TaskCategory category = task.getCategory();

                categoryElement.setAttribute("id", category.getId());
                categoryElement.setAttribute("name", category.getName());
                categoryElement.setAttribute("createTime", category.getCreateTime().toString());
                taskElement.appendChild(categoryElement);

                //Create underlying history items
                for(TaskHistoryItem taskHistoryItem : task.getTaskHistoryItems()){
                    Element taskHistoryItemElement = doc.createElement(TaskHistoryItem.OBJECT_NAME);

                    taskHistoryItemElement.setAttribute("id", taskHistoryItem.getId());
                    taskHistoryItemElement.setAttribute("description", taskHistoryItem.getDescription());
                    taskHistoryItemElement.setAttribute("eventName", taskHistoryItem.getEventName());
                    taskHistoryItemElement.setAttribute("eventTime", taskHistoryItem.getEventTime().toString());
                    taskHistoryItemElement.setAttribute("createTime", taskHistoryItem.getCreateTime().toString());
                    taskElement.appendChild(taskHistoryItemElement);
                }

                //Create underlying notes
                for(TaskNote note : task.getNotes()){
                    Element noteElement = doc.createElement(TaskNote.OBJECT_NAME);

                    noteElement.setAttribute("id", note.getId());
                    noteElement.setAttribute("name", note.getName());
                    noteElement.setAttribute("content", note.getContent());
                    noteElement.setAttribute("createTime", note.getCreateTime().toString());
                    taskElement.appendChild(noteElement);
                }

                //Create underlying status
                Element statusElement = doc.createElement(TaskStatus.OBJECT_NAME);
                TaskStatus taskStatus = task.getStatus();

                statusElement.setAttribute("id", taskStatus.getId());
                statusElement.setAttribute("status", taskStatus.toString());
                statusElement.setAttribute("createTime", taskStatus.getCreateTime().toString());
                taskElement.appendChild(statusElement);

                //Create underlying steps
                for(TaskStep taskStep : task.getSteps()){
                    Element taskStepElement = doc.createElement(TaskStep.OBJECT_NAME);

                    taskStepElement.setAttribute("id", taskStep.getId());
                    taskStepElement.setAttribute("name", taskStep.getName());
                    taskStepElement.setAttribute("description", taskStep.getDescription());
                    taskStepElement.setAttribute("createTime", taskStep.getCreateTime().toString());
                    taskStepElement.setAttribute("statusValue", taskStep.getStatusValueString());
                    taskElement.appendChild(taskStepElement);
                }

                //Create underlying tags
                for(TaskTag taskTag : task.getTags()){
                    Element taskTagElement = doc.createElement(TaskTag.OBJECT_NAME);

                    taskTagElement.setAttribute("id", taskTag.getId());
                    taskTagElement.setAttribute("name", taskTag.getName());
                    taskTagElement.setAttribute("createTime", taskTag.getCreateTime().toString());
                    taskElement.appendChild(taskTagElement);
                }

                rootElement.appendChild(taskElement);

                //Create the transformer and source
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                //Create the stream result
                StreamResult file = new StreamResult(new File(fileName));

                //Write the data
                transformer.transform(source, file);

            }
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }


}
