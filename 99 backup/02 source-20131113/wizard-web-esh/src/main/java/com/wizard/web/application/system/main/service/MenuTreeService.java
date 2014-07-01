package com.wizard.web.application.system.main.service;

import java.util.List;

import com.wizard.web.application.system.main.bean.TreeNode;

public interface MenuTreeService {

	public List<TreeNode> getParentNodes(String roleName);
	
	public List<TreeNode> getChildNodes(String pid, String roleName);

}
