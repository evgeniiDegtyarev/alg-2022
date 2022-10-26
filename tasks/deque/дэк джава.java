package ru.bgpu.task.deque;

import java.util.Arrays;

public class Deque<T> implements IDeque<T> {
	private Object[] objects;
	private int size;
	private int backPointer;

	public Deque(int size) {
		if (size <= 0) {
			throw new RuntimeException("Invalid array size");
		}
		this.objects = new Object[size];
		this.size = size;
		this.backPointer = 0;
	}

	@Override
	public boolean isEmpty() {
		for (Object item : objects) {
			if (item != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isFull() {
		for (Object item : objects) {
			if (item == null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void pushBack(T value) {
		if ((backPointer) > (objects.length - 1)) {
			allocMemory();
		}
		objects[backPointer] = value;
		backPointer++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T popBack() {
		if (backPointer != 0) {
			backPointer--;
			Object tmp = objects[backPointer];
			objects[backPointer] = null;
			return (T) tmp;
		}
		return (T) objects[0];
	}

	@Override
	@SuppressWarnings("unchecked")
	public T peekBack() {
		if (backPointer != 0) {
			return (T) objects[backPointer - 1];
		}
		return (T) objects[0];
	}

	@Override
	public void pushFront(T value) {
		if ((backPointer) > (objects.length - 1)) {
			allocMemory();
		}
		if (objects.length - 1 >= 0) {
			System.arraycopy(objects, 0, objects, 1, objects.length - 1);
		}
		objects[0] = value;
		backPointer++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T popFront() {
		if (backPointer != 0) {
			Object tmp = objects[0];
			System.arraycopy(objects, 1, objects, 0, objects.length - 1);
			backPointer--;
			return (T) tmp;
		}
		Object tmp = objects[0];
		objects[0] = null;
		return (T) tmp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T peekFront() {
		return (T) objects[0];
	}

	public int getDequeSize() {
		return objects.length;
	}

	private void allocMemory() {
		objects = Arrays.copyOf(objects, size * 2);
		size = objects.length;
	}

	public void optimizeMemorySize() {
		int count = 0;
		for (Object item : objects) {
			if (item != null) {
				count++;
			}
		}
		Object[] tmp = new Object[count];
		for (int i = 0; i < count; i++) {
			if (objects[i] != null) {
				tmp[i] = objects[i];
			}
		}
		objects = tmp;
		size = objects.length;
		backPointer = objects.length;
	}
}