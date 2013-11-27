package com.wizard.util.file.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.wizard.util.common.CommonUtil;

public class ResourceObejct {

	private ResourceBundle resb = null;

	public ResourceObejct(final String url, final Locale locale) {
		load(url, locale);
	}

	public ResourceObejct(final String url, final String... args) {
		load(url, args);
	}

	public void load(final String url, final Locale locale) {
		try {
			this.resb = ResourceBundle.getBundle(url, locale);
		} catch (MissingResourceException mre) {
			load(url, Locale.getDefault());
		}
	}

	public void load(final String url, final String... args) {
		load(url, getLocale(args));
	}

	public String get(final String key, final Object... args) {
		String value = null;
		if (containsKey(key)) {
			value = this.resb.getString(key);
			if (!CommonUtil.isEmpty(args))
				value = MessageFormat.format(value, args);
		}
		return value;
	}

	public boolean containsKey(final String key) {
		return this.resb.containsKey(key);
	}

	private Locale getLocale(final String... args) {
		Locale locale = null;
		switch (args.length) {
		case 1:
			locale = new Locale(args[0]);
			break;
		case 2:
			locale = new Locale(args[0], args[1]);
			break;
		case 3:
			locale = new Locale(args[0], args[1], args[2]);
			break;
		default:
			locale = Locale.getDefault();
			break;
		}
		return locale;
	}
}
