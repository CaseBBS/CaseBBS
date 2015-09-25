package com.casebbs.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.casebbs.model.ArticleAttchs;
import com.casebbs.service.CaseBBSService;


@Scope("prototype")
@Controller
@RequestMapping("/fileUpload")
public class FileUpLoadController extends CommonsMultipartResolver {


	@Autowired
	CaseBBSService caseBBSService;
	/*
	 * 上传控件后台接受地址 file：上传控件的输入流数据
	 */
	@RequestMapping(value = "uploadFile.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String uploadFile(
			@RequestParam("file") CommonsMultipartFile cmFile, // 请求参数一定要与form中的参数名对应
																// ,
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			String realPath = getClass().getResource("/").getFile().toString();
			String result = "";
			realPath = realPath.substring(0, (realPath.length() - 16));
			realPath = realPath + "uploadFile";
			File sF = new File(realPath);
			if (!sF.exists()) {
				sF.mkdir();
			}
			realPath = realPath + "/" + "Attchfile";
			File iconF = new File(realPath);
			if (!iconF.exists()) {
				iconF.mkdir();
			}
			realPath = realPath + "/";

			if (!cmFile.isEmpty()) {
				String name = cmFile.getFileItem().getName();
				String dirUrl = realPath + id;
				File filedir = new File(dirUrl);

				if (!filedir.exists()) {
					filedir.mkdir();
				}
				dirUrl = dirUrl + "/";
				String iconUrl = dirUrl + name;
				File existFile = new File(iconUrl);
				 
				String attchType = "";
				String[] types = name.split("\\.");
				cmFile.getFileItem().write(existFile);

				String filePath = "uploadFile/Attchfile/"+id+"/"+name;
				
				if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
					attchType = "图片文件"; 
				}else if(types[1].equals("doc")||types[1].equals("docx")){
					attchType = "文档文件"; 
				}else if(types[1].equals("ppt")||types[1].equals("pptx")){
					attchType = "演示文件"; 
				}else if(types[1].equals("xls")||types[1].equals("xlsx")){
					attchType = "Excel文件"; 
				}else if(types[1].equals("txt")){
					attchType = "文本文件"; 
				}else{
					attchType = "一般文件"; 
				}
				ArticleAttchs attch = new ArticleAttchs();
				attch.setId(0);
				attch.setArticleId(id);
				attch.setAttchType(attchType);
				attch.setAttchUrl(filePath);
				attch.setName(name);
				attch.setUploadTime(new Date());
				caseBBSService.insertAttchMents(attch);
			}
			return result;
		} catch (Exception ex) {
			return "File Upload Failed";
		}

	}
}
