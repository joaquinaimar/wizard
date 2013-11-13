package com.wizard.web.basic.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

import com.wizard.web.basic.tag.service.MenuService;
import com.wizard.web.domain.entity.WizardMenu;
import com.wizard.web.utils.SpringContextUtils;
import com.wizard.web.utils.WizardUtils;
import com.wizard.web.utils.WizardWebUtils;

@Component
public class MenuTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		JspContext jspContext = super.getJspContext();
		JspWriter out = jspContext.getOut();
		String roleName = WizardWebUtils.getRoleName(jspContext);
		MenuService menuService = SpringContextUtils.getBean("menuServiceImpl",
				MenuService.class);
		List<WizardMenu> parentNodes = menuService.getParentNodes(roleName);

		for (WizardMenu parentNode : parentNodes) {
			out.print("<a href=\"#menu");
			out.print(parentNode.getPkId());

			if (!WizardUtils.isBlank(parentNode.getMenuPath())) {
				out.print("\" onClick=\"addTabItem('");
				out.print(parentNode.getMenuName());
				out.print("', '");
				out.print(parentNode.getMenuPath());
				out.print("');");
			}
			out.print("\" class=\"nav-header menu-first collapsed\" data-toggle=\"collapse\">");
			out.print(parentNode.getMenuName());
			out.println("</a>");
			out.println("<ul id=\"menu" + parentNode.getPkId()
					+ "\" class=\"nav nav-list collapse menu-second\">");
			List<WizardMenu> childNodes = menuService.getChildNodes(
					parentNode.getPkId(), roleName);
			for (WizardMenu childNode : childNodes) {
				out.print("<li><a href=\"#\" onClick=\"addTabItem('");
				out.print(childNode.getMenuName());
				out.print("', '");
				out.print(childNode.getMenuPath());
				out.print("');\">");
				out.print(childNode.getMenuName());
				out.println("</a></li>");
			}
			out.println("</ul>");
		}

	}

}
