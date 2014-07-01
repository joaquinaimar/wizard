package com.wizard.web.application.maintain.code.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizard.web.application.maintain.code.bean.Code;
import com.wizard.web.application.maintain.code.service.CodeMaintainService;
import com.wizard.web.basic.io.PageResponse;
import com.wizard.web.basic.io.extjs.ExtPageRequest;
import com.wizard.web.basic.io.extjs.ExtPageResponse;
import com.wizard.web.basic.io.extjs.ExtResponse;
import com.wizard.web.basic.log.WizardWebLogger;

@Controller
@RequestMapping("wizardframework/CodeMaintain")
public class CodeMaintainController {

    @Autowired
    private WizardWebLogger wizardLogger = null;

    @Autowired
    private CodeMaintainService codeMaintainService = null;

    @RequestMapping(value = "/searchType.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<Code> doSearchType(ExtPageRequest pageRequest) {
        PageResponse<Code> page = codeMaintainService.searchType(pageRequest);
        return new ExtPageResponse<Code>(true, page);
    }

    @RequestMapping(value = "/searchCode.do", method = RequestMethod.GET)
    @ResponseBody
    public ExtPageResponse<Code> doSearchCode(@RequestParam String typeId,
            ExtPageRequest pageRequest) {
        PageResponse<Code> page = codeMaintainService.searchCode(typeId,
                pageRequest);
        return new ExtPageResponse<Code>(true, page);
    }

    @RequestMapping(value = "/downloadExcel.do", method = RequestMethod.GET)
    @ResponseBody
    public void doDownloadExcel(HttpServletRequest request,
            HttpServletResponse response) {
        List<Code> codeList = codeMaintainService.searchCode();
        codeMaintainService.downloadExcel(codeList, request, response);
        wizardLogger.info("EXCEL下载成功", request);
    }

    @RequestMapping(value = "/updateCodeType.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateCodeType(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String newId,
            @RequestParam String content) {
        int result = codeMaintainService.updateCodeType(pkId, newId, content);
        String message = (1 == result) ? "编码类型修改成功" : "编码类型修改失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/updateCodeContent.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doUpdateCodeContent(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String newId,
            @RequestParam String typeId, @RequestParam String content) {
        int result = codeMaintainService.updateCodeContent(pkId, newId, typeId,
                content);
        String message = (1 == result) ? "编码内容修改成功" : "编码内容修改失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteTypeCode.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteTypeCode(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = codeMaintainService.deleteTypeCode(pkIds);
        String message = "删除编码类型" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/deleteContentCode.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doDeleteContentCode(HttpServletRequest request,
            @RequestParam String[] pkIds) {
        int result = codeMaintainService.deleteContentCode(pkIds);
        String message = "删除编码内容" + result + "条";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }

    @RequestMapping(value = "/batchUpdateCode.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doBatchUpdateCode(HttpServletRequest request) {
        HSSFSheet sheet;
        String message = "";
        try {
            sheet = codeMaintainService.uploadExcel(request);
            int result = codeMaintainService.batchUpdateCode(sheet);
            if (0 <= result) {
                message = "批量更新编码记录" + result + "条";
            } else {
                message = "批量更新发生异常[" + result + "]";
            }
            wizardLogger.info(message, request);
            return new ExtResponse<Object>(true, message);
        } catch (IOException | FileUploadException e) {
            message = "文件上传异常";
            wizardLogger.warn(message, request);
            return new ExtResponse<Object>(false, message);
        }
    }

    @RequestMapping(value = "/insertCode.do", method = RequestMethod.POST)
    @ResponseBody
    public ExtResponse<Object> doInsertCode(HttpServletRequest request,
            @RequestParam String pkId, @RequestParam String typeId,
            @RequestParam String content) {
        int result = codeMaintainService.insertCode(pkId, typeId, content);
        String message = (1 == result) ? "新增编码成功" : "新增编码失败";
        wizardLogger.info(message, request);
        return new ExtResponse<Object>(true, message);
    }
}
