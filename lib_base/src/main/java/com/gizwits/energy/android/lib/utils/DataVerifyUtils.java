package com.gizwits.energy.android.lib.utils;

import android.support.annotation.NonNull;

import com.gizwits.energy.android.lib.base.AbstractConstantClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class DataVerifyUtils extends AbstractConstantClass {

	private static final String NUMBER_REGULAR = "^\\d+$|^\\d+\\.\\d+$";
	private static final String INTEGER_REGULAR = "^\\d+$";
	private static final String PHONENUM_REGULAR = "^((13[0-9])|(15[^4])|(18[0235-9])|(17[0-8])|(147))\\d{8}$";
	private static final String PASSWORD_REGULAR = "^[0-9a-zA-Z`~!?@#%^&*()|{}':;,.<>\\\\/]{5,17}$";



	private DataVerifyUtils() {
		super();
	}


	public static boolean isNotNull(Object... objects) {
		if (objects == null) return false;
		for (Object object : objects) {
			if (object == null) return false;
		}
		return true;
	}

	public static boolean isNotNull(Object object) {
		return (object != null);
	}

	public static boolean isNotNull(List<Object> objects) {
		if (objects == null) return false;
		for (Object object : objects) {
			if (object == null) return false;
		}
		return true;
	}

	public static boolean isAllAccessibleFieldsNotNull(@NonNull Class c, @NonNull Object... objects) {
		if (!isNotNull(objects) || !isNotNull(c)) return false;
		for (Object object : objects) {
			if (!isAllAccessibleFieldsNotNull(c, object)) return false;
		}
		return true;
	}

	public static boolean isAllFieldsNotNull(@NonNull Class c, @NonNull Object... objects) {
		if (!isNotNull(objects) || !isNotNull(c)) return false;
		for (Object object : objects) {
			if (!isFieldsNotNull(object, c.getDeclaredFields())) return false;
		}
		return true;
	}

	public static boolean isAllAccessibleFieldsNotNull(@NonNull Class c, @NonNull Object object) {
		Field[] fields = c.getFields();
		List<Field> accessibleField = new ArrayList<>();
		for (Field field : fields) {
			if (field.isAccessible()) {
				accessibleField.add(field);
			}
		}
		return isFieldsNotNull(object, accessibleField);
	}

	public static boolean isFieldsNotNull(@NonNull Object o, @NonNull Field... fields) {
		if (null == o) return false;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				field.get(o);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static boolean isFieldsNotNull(@NonNull Object o, @NonNull List<Field> fields) {
		if (null == o) return false;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				field.get(o);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static boolean isMatchRegular(@NonNull String regular, @NonNull String... strings) {
		if (null == regular) return false;
		for (String string : strings) {
			if (string == null) return false;
			if (!string.matches(regular)) return false;
		}
		return true;
	}

	public static boolean isNumberFormat(@NonNull String... numbers) {
		return isMatchRegular(NUMBER_REGULAR, numbers);
	}

	public static boolean isIntegerFormat(@NonNull String... numbers) {
		return isMatchRegular(INTEGER_REGULAR, numbers);
	}

	public static boolean isPhoneNumFormat(@NonNull String numbers) {
		Pattern p = Pattern.compile(PHONENUM_REGULAR);
		Matcher m = p.matcher(numbers);
		return m.matches();
	}

	public static boolean isPasswordFormat(@NonNull String password) {
		Pattern p = Pattern.compile(PASSWORD_REGULAR);
		Matcher m = p.matcher(password);
		return m.matches();
	}

}
