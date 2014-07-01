package com.wizard.web.application.common.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.common.service.WizardCommonService;
import com.wizard.web.basic.database.DynamicDataSource;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.domain.bean.ComboxBean;
import com.wizard.web.domain.entity.WizardCode;

@Controller
@RequestMapping("wizardframework/WizardCommon")
public class WizardCommonController {

	@Autowired
	private WizardCommonService wizardCommonService = null;

	@RequestMapping(value = "/getCodeList.do", method = RequestMethod.GET)
	@ResponseBody
	public ExtResponse<List<WizardCode>> doGetCodeList(String typeId, String flg) {

		List<WizardCode> codeList = wizardCommonService.getCodeList(typeId,
				"1".equals(flg));

		return new ExtResponse<List<WizardCode>>(true, codeList);
	}

	@RequestMapping(value = "/getDataSources.do", method = RequestMethod.GET)
	@ResponseBody
	public ExtResponse<List<ComboxBean>> doGetDataSources() {
		Map<Object, Object> map = DynamicDataSource.getTargetDataSources();

		if (null == map) {
			return new ExtResponse<List<ComboxBean>>(true, "数据源为空");
		}

		List<ComboxBean> comboxBeanList = new ArrayList<ComboxBean>();

		for (Object key : map.keySet()) {
			comboxBeanList.add(new ComboxBean(String.valueOf(key)));
		}

		return new ExtResponse<List<ComboxBean>>(true, comboxBeanList);
	}

	@RequestMapping(value = "/swtichDataSource.do", method = RequestMethod.GET)
	@ResponseBody
	public ExtResponse<Boolean> doSwtichDataSource(String dataSource) {
		DynamicDataSource.swtichDataSource(dataSource);
		return new ExtResponse<Boolean>(true, true);
	}

}
