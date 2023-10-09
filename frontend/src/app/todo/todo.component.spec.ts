import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TodoComponent } from './todo.component';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { TaskService } from "../services/task.service";
import { Task } from "../model/task.model";

describe('TodoComponent', () => {
  let component: TodoComponent;
  let fixture: ComponentFixture<TodoComponent>;
  let taskService: TaskService;
  let sampleTask: Task;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      declarations: [TodoComponent],  // Only declare TodoComponent
      providers: [
        {
          provide: TaskService,
          useValue: {
            findAll: jasmine.createSpy('getTodos').and.returnValue(of([])),
            create: jasmine.createSpy('createTodo').and.returnValue(of({})),
            update: jasmine.createSpy('updateTodo').and.returnValue(of({})),
            deleteById: jasmine.createSpy('deleteTodo').and.returnValue(of({}))
          }
        }
      ]
    });

    fixture = TestBed.createComponent(TodoComponent);
    component = fixture.componentInstance;
    taskService = TestBed.inject(TaskService);

    sampleTask = { id: 1, title: 'Sample Task', completed: false };
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch tasks on initialization', () => {
    (taskService.findAll as jasmine.Spy).and.returnValue(of([sampleTask]));
    component.fetchTasks();

    expect(taskService.findAll).toHaveBeenCalled();
    expect(component.tasks).toEqual([sampleTask]);
  });

  it('should add a task', () => {
    (taskService.create as jasmine.Spy).and.returnValue(of(sampleTask));
    component.newTaskTitle = 'Sample Task';
    component.addTask();

    expect(taskService.create).toHaveBeenCalledWith({ title: 'Sample Task', completed: false });
    expect(component.tasks).toContain(sampleTask);
  });

  it('should update a task', () => {
    const updatedTask = { ...sampleTask, title: 'Updated Task' };
    (taskService.update as jasmine.Spy).and.returnValue(of(updatedTask));

    component.tasks = [sampleTask];
    component.updateTask(updatedTask);

    if (updatedTask.id) {
      expect(taskService.update).toHaveBeenCalledWith(updatedTask.id, updatedTask);
    }
  });

  it('should delete a task', () => {
    component.tasks = [sampleTask];

    if (sampleTask.id) {
      component.deleteTask(sampleTask.id);
      expect(taskService.deleteById).toHaveBeenCalledWith(sampleTask.id);
      expect(component.tasks.length).toBe(0);
    }
  });
});
