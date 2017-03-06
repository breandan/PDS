package main.java.clock;

import java.util.LinkedList;
import java.util.Queue;


public class Incubator {
    private Queue<Runnable> todo, tasks;
    private int content, capacity;

    public Incubator(int capacity) {
        todo = new LinkedList<Runnable>();
        tasks = new LinkedList<Runnable>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(Runnable task) {
        if (tasks.size() < capacity)
            tasks.add(task);
        else
            todo.add(task);
    }

    public synchronized Runnable dequeue() {
        if (!todo.isEmpty())
            tasks.add(todo.remove());
        return tasks.remove();
    }
}
