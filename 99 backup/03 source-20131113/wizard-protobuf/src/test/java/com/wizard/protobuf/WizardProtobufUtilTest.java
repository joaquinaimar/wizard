package com.wizard.protobuf;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.wizard.protobuf.bean.WizardDataBean;
import com.wizard.protobuf.data.WizardData;

public class WizardProtobufUtilTest {

	@Test
	public void testToProtobuf() {

		WizardData.WizardDataInfo.Builder wizardDataInfo = WizardData.WizardDataInfo
				.newBuilder();

		WizardDataBean bean = new WizardDataBean();
		bean.setId(1111);
		bean.setName("dasdsd");

		List<String> data = new ArrayList<String>();
		data.add("ghjjhj");
		data.add("bcvbcvb");
		bean.setData(data);

		wizardDataInfo = WizardProtobufUtil.toProtobuf(bean, wizardDataInfo);

		Assert.assertEquals(1111, wizardDataInfo.getId());
		Assert.assertEquals("dasdsd", wizardDataInfo.getName());
		Assert.assertEquals("ghjjhj", wizardDataInfo.getDataList().get(0));
		Assert.assertEquals("bcvbcvb", wizardDataInfo.getDataList().get(1));

	}

	@Test
	public void testToObject() {

		WizardData.WizardDataInfo.Builder wizardDataInfo = WizardData.WizardDataInfo
				.newBuilder();

		wizardDataInfo.setId(111);
		wizardDataInfo.setName("asdasd");
		wizardDataInfo.addData("12232dsadsa");
		wizardDataInfo.addData("3123asdas121");

		WizardDataBean bean = WizardProtobufUtil.toObject(wizardDataInfo,
				new WizardDataBean());

		Assert.assertEquals(Integer.valueOf(111), bean.getId());
		Assert.assertEquals("asdasd", bean.getName());
		Assert.assertEquals("12232dsadsa", bean.getData().get(0));
		Assert.assertEquals("3123asdas121", bean.getData().get(1));

	}

}
