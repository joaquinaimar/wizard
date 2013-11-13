package com.wizard.sql.annotation;

import com.wizard.sql.annotation.support.GenerateType;

public @interface Pk {

	public GenerateType type() default GenerateType.NATIVE;

}
