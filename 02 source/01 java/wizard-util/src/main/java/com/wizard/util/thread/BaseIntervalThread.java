package com.wizard.util.thread;

public abstract class BaseIntervalThread implements Runnable {

	private boolean isRun = true;

	private long cycle = 0;

	public BaseIntervalThread() {
		this(0);
	}

	public BaseIntervalThread(long cycle) {
		isRun = true;
		this.setCycle(cycle);
	}

	public long getCycle() {
		return cycle;
	}

	public void setCycle(long cycle) {
		this.cycle = cycle;
	}

	public void cease() {
		this.isRun = false;
	}

	@Override
	public void run() {
		while (this.isRun) {
			execute();
			try {
				Thread.sleep(cycle);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	protected abstract void execute();

}
