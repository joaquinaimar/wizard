package com.wizard.util.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ThreadUtil {

	private ThreadUtil() {
		throw new RuntimeException("Cannot create util class instance!");
	}

	public BaseIntervalThread doInterval(final BaseIntervalThread it) {
		new Thread(it).start();
		return it;
	}

	public BaseIntervalThread doInterval(final BaseIntervalThread it,
			final long cycle) {
		it.setCycle(cycle);
		return doInterval(it);
	}

	public BaseIntervalThread doInterval(final Object obj, final Method method,
			final Object[] args, final long cycle) {
		BaseIntervalThread it = new BaseIntervalThread() {
			@Override
			protected void execute() {
				try {
					method.invoke(obj, args);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					return;
				}
			}
		};
		it.setCycle(cycle);
		return it;
	}

	public BaseIntervalThread doInterval(final Object obj, final Method method,
			final Object[] args) {
		return doInterval(obj, method, args, 0);
	}
}
