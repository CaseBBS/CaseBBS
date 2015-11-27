package com.casebbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.casebbs.model.ArticleAttchs;
import com.casebbs.model.ArticleItemAttchs;
import com.casebbs.service.CaseBBSService;


@Scope("prototype")
@Controller
@RequestMapping("/fileUpload")
public class FileUpLoadController extends CommonsMultipartResolver {


	@Autowired
	CaseBBSService caseBBSService;
	 
		@ResponseBody
		@RequestMapping(value = "/uploadAttch.do")
		public String uploadDeviceExcelFile(
				HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "messageid", required = true) Integer id,
				@RequestParam(value = "file1", required = false) MultipartFile file1,
				@RequestParam(value = "file2", required = false) MultipartFile file2,
				@RequestParam(value = "file3", required = false) MultipartFile file3,
				@RequestParam(value = "file4", required = false) MultipartFile file4,
				@RequestParam(value = "file5", required = false) MultipartFile file5) {
			try {
				String path = request.getSession().getServletContext()
						.getRealPath("uploadFile/Attchfile/");
				List<ArticleAttchs> list = new ArrayList<ArticleAttchs>();
				String fileName1 ="";
				String fileName2 ="";
				String fileName3 ="";
				String fileName4 ="";
				String fileName5 ="";
				path += "/"+id;
				if(file1!=null&&file1.getSize()>0){
					fileName1 = file1.getOriginalFilename();
					String[] types = fileName1.split("\\.");
					String attchType1 = "";
					if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
						attchType1 = "图片文件"; 
					}else if(types[1].equals("doc")||types[1].equals("docx")){
						attchType1 = "文档文件"; 
					}else if(types[1].equals("ppt")||types[1].equals("pptx")){
						attchType1 = "演示文件"; 
					}else if(types[1].equals("xls")||types[1].equals("xlsx")){
						attchType1 = "Excel文件"; 
					}else if(types[1].equals("txt")){
						attchType1 = "文本文件"; 
					}else{
						attchType1 = "一般文件"; 
					}
					
					String filepath1 = "uploadFile/Attchfile/"+id+"/"+fileName1;
					File targetFile = new File(path, fileName1);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try { 
						file1.transferTo(targetFile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					ArticleAttchs attch1 = new ArticleAttchs();
					attch1.setId(0);
					attch1.setArticleId(id);
					attch1.setAttchType(attchType1);
					attch1.setAttchUrl(filepath1);
					attch1.setName(fileName1);
					attch1.setUploadTime(new Date());
					list.add(attch1);
				}
				if(file2!=null&&file2.getSize()>0){
					fileName2 = file2.getOriginalFilename();
					String[] types = fileName2.split("\\.");
					String attchType1 = "";
					if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
						attchType1 = "图片文件"; 
					}else if(types[1].equals("doc")||types[1].equals("docx")){
						attchType1 = "文档文件"; 
					}else if(types[1].equals("ppt")||types[1].equals("pptx")){
						attchType1 = "演示文件"; 
					}else if(types[1].equals("xls")||types[1].equals("xlsx")){
						attchType1 = "Excel文件"; 
					}else if(types[1].equals("txt")){
						attchType1 = "文本文件"; 
					}else{
						attchType1 = "一般文件"; 
					}
					
					String filepath1 = "uploadFile/Attchfile/"+id+"/"+fileName2;
					File targetFile = new File(path, fileName2);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try { 
						file2.transferTo(targetFile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					ArticleAttchs attch2 = new ArticleAttchs();
					attch2.setId(0);
					attch2.setArticleId(id);
					attch2.setAttchType(attchType1);
					attch2.setAttchUrl(filepath1);
					attch2.setName(fileName2);
					attch2.setUploadTime(new Date());
					list.add(attch2);
				}
				if(file3!=null&&file3.getSize()>0){
					fileName3 = file3.getOriginalFilename();
					String[] types = fileName3.split("\\.");
					String attchType1 = "";
					if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
						attchType1 = "图片文件"; 
					}else if(types[1].equals("doc")||types[1].equals("docx")){
						attchType1 = "文档文件"; 
					}else if(types[1].equals("ppt")||types[1].equals("pptx")){
						attchType1 = "演示文件"; 
					}else if(types[1].equals("xls")||types[1].equals("xlsx")){
						attchType1 = "Excel文件"; 
					}else if(types[1].equals("txt")){
						attchType1 = "文本文件"; 
					}else{
						attchType1 = "一般文件"; 
					}
					
					String filepath1 = "uploadFile/Attchfile/"+id+"/"+fileName3;
					File targetFile = new File(path, fileName3);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try { 
						file3.transferTo(targetFile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					ArticleAttchs attch3 = new ArticleAttchs();
					attch3.setId(0);
					attch3.setArticleId(id);
					attch3.setAttchType(attchType1);
					attch3.setAttchUrl(filepath1);
					attch3.setName(fileName3);
					attch3.setUploadTime(new Date());
					list.add(attch3);
				}
				if(file4!=null&&file4.getSize()>0){
					fileName4 = file4.getOriginalFilename();
					String[] types = fileName4.split("\\."); 
					String attchType1 = "";
					if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
						attchType1 = "图片文件"; 
					}else if(types[1].equals("doc")||types[1].equals("docx")){
						attchType1 = "文档文件"; 
					}else if(types[1].equals("ppt")||types[1].equals("pptx")){
						attchType1 = "演示文件"; 
					}else if(types[1].equals("xls")||types[1].equals("xlsx")){
						attchType1 = "Excel文件"; 
					}else if(types[1].equals("txt")){
						attchType1 = "文本文件"; 
					}else{
						attchType1 = "一般文件"; 
					}
					
					String filepath1 = "uploadFile/Attchfile/"+id+"/"+fileName4;
					File targetFile = new File(path, fileName4);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try { 
						file4.transferTo(targetFile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					ArticleAttchs attch4 = new ArticleAttchs();
					attch4.setId(0);
					attch4.setArticleId(id);
					attch4.setAttchType(attchType1);
					attch4.setAttchUrl(filepath1);
					attch4.setName(fileName4);
					attch4.setUploadTime(new Date());
					list.add(attch4);
				}
				if(file5!=null&&file5.getSize()>0){
					fileName5 = file5.getOriginalFilename();
					String[] types = fileName5.split("\\."); 
					String attchType1 = "";
					if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
						attchType1 = "图片文件"; 
					}else if(types[1].equals("doc")||types[1].equals("docx")){
						attchType1 = "文档文件"; 
					}else if(types[1].equals("ppt")||types[1].equals("pptx")){
						attchType1 = "演示文件"; 
					}else if(types[1].equals("xls")||types[1].equals("xlsx")){
						attchType1 = "Excel文件"; 
					}else if(types[1].equals("txt")){
						attchType1 = "文本文件"; 
					}else{
						attchType1 = "一般文件"; 
					}
					
					String filepath1 = "uploadFile/Attchfile/"+id+"/"+fileName5;
					File targetFile = new File(path, fileName5);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					try { 
						file5.transferTo(targetFile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					ArticleAttchs attch5 = new ArticleAttchs();
					attch5.setId(0);
					attch5.setArticleId(id);
					attch5.setAttchType(attchType1);
					attch5.setAttchUrl(filepath1);
					attch5.setName(fileName5);
					attch5.setUploadTime(new Date());
					list.add(attch5);
				} 
				if(list.size()>0){
					caseBBSService.insertAttchMents(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "File Upload Success";
		}
		
			

			@ResponseBody
			@RequestMapping(value = "/uploadItemAttch.do")
			public String uploadItemAttch(
					HttpServletRequest request, HttpServletResponse response,
					@RequestParam(value = "itemmessageid", required = true) Integer id,
					@RequestParam(value = "itemCreator", required = true) String itemCreator,
					@RequestParam(value = "itemfile1", required = false) MultipartFile file1,
					@RequestParam(value = "itemfile2", required = false) MultipartFile file2,
					@RequestParam(value = "itemfile3", required = false) MultipartFile file3 ) {
				try {
					String path = request.getSession().getServletContext()
							.getRealPath("uploadFile/ItemAttchfile/"); 
					List<ArticleItemAttchs> list = new ArrayList<ArticleItemAttchs>();
					String fileName1 ="";
					String fileName2 ="";
					String fileName3 ="";
					String fileName4 ="";
					String fileName5 ="";
					path += "/"+id;
					if(file1!=null&&file1.getSize()>0){
						fileName1 = file1.getOriginalFilename();
						String[] types = fileName1.split("\\.");
						String attchType1 = "";
						if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
							attchType1 = "图片文件"; 
						}else if(types[1].equals("doc")||types[1].equals("docx")){
							attchType1 = "文档文件"; 
						}else if(types[1].equals("ppt")||types[1].equals("pptx")){
							attchType1 = "演示文件"; 
						}else if(types[1].equals("xls")||types[1].equals("xlsx")){
							attchType1 = "Excel文件"; 
						}else if(types[1].equals("txt")){
							attchType1 = "文本文件"; 
						}else{
							attchType1 = "一般文件"; 
						}
						
						String filepath1 = "uploadFile/ItemAttchfile/"+id+"/"+fileName1;
						File targetFile = new File(path, fileName1);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						try { 
							file1.transferTo(targetFile);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						ArticleItemAttchs attch1 = new ArticleItemAttchs();
						attch1.setId(0);
						attch1.setArticlaId(id);
						attch1.setAttchType(attchType1);
						attch1.setAttchUrl(filepath1);
						attch1.setName(fileName1);
						attch1.setCreateTime(new Date());
						attch1.setCreator(itemCreator);
						list.add(attch1);
					}
					if(file2!=null&&file2.getSize()>0){
						fileName2 = file2.getOriginalFilename();
						String[] types = fileName2.split("\\.");
						String attchType1 = "";
						if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
							attchType1 = "图片文件"; 
						}else if(types[1].equals("doc")||types[1].equals("docx")){
							attchType1 = "文档文件"; 
						}else if(types[1].equals("ppt")||types[1].equals("pptx")){
							attchType1 = "演示文件"; 
						}else if(types[1].equals("xls")||types[1].equals("xlsx")){
							attchType1 = "Excel文件"; 
						}else if(types[1].equals("txt")){
							attchType1 = "文本文件"; 
						}else{
							attchType1 = "一般文件"; 
						}
						
						String filepath1 = "uploadFile/ItemAttchfile/"+id+"/"+fileName2;
						File targetFile = new File(path, fileName2);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						try { 
							file2.transferTo(targetFile);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						ArticleItemAttchs attch1 = new ArticleItemAttchs();
						attch1.setId(0);
						attch1.setArticlaId(id);
						attch1.setAttchType(attchType1);
						attch1.setAttchUrl(filepath1);
						attch1.setName(fileName2);
						attch1.setCreateTime(new Date());
						attch1.setCreator(itemCreator);
						list.add(attch1);
					}
					if(file3!=null&&file3.getSize()>0){
						fileName3 = file3.getOriginalFilename();
						String[] types = fileName3.split("\\.");
						String attchType1 = "";
						if(types[1].equals("png")||types[1].equals("jpg")||types[1].equals("jpeg")||types[1].equals("gif")||types[1].equals("ico")||types[1].equals("bmp")){
							attchType1 = "图片文件"; 
						}else if(types[1].equals("doc")||types[1].equals("docx")){
							attchType1 = "文档文件"; 
						}else if(types[1].equals("ppt")||types[1].equals("pptx")){
							attchType1 = "演示文件"; 
						}else if(types[1].equals("xls")||types[1].equals("xlsx")){
							attchType1 = "Excel文件"; 
						}else if(types[1].equals("txt")){
							attchType1 = "文本文件"; 
						}else{
							attchType1 = "一般文件"; 
						}
						
						String filepath1 = "uploadFile/ItemAttchfile/"+id+"/"+fileName3;
						File targetFile = new File(path, fileName3);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						try { 
							file3.transferTo(targetFile);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						ArticleItemAttchs attch1 = new ArticleItemAttchs();
						attch1.setId(0);
						attch1.setArticlaId(id);
						attch1.setAttchType(attchType1);
						attch1.setAttchUrl(filepath1);
						attch1.setName(fileName3);
						attch1.setCreateTime(new Date());
						attch1.setCreator(itemCreator);
						list.add(attch1);
					}
					
					if(list.size()>0){
						caseBBSService.insertItemAttchMents(list);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "File Upload Success";
			}
		@ResponseBody
		@RequestMapping(value = "/downfile.do")
		public void downFile(HttpServletRequest request,
				HttpServletResponse response,
				@RequestParam(value = "filepath", required = true) String filepath) {

			try {
				String moduleName = new String(filepath.getBytes("iso8859-1"), "utf-8");  
				String filePath = request.getRealPath("/") + moduleName;
				String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

				response.reset();
				response.setContentType("APPLICATION/OCTET-STREAM; charset=UTF-8");
				response.setHeader("Content-disposition", "attachment;filename=\""
						+ new String(fileName.getBytes("GB2312"), "ISO-8859-1")
						+ "\"");
				FileInputStream inStream = new FileInputStream(filePath);
				byte[] b = new byte[100]; 
				int len; 
				while((len=inStream.read(b)) >0){ 
					response.getOutputStream().write(b,0,len);  
					//this.getRes().getOutputStream().write(b,0,len);  
				}
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
				//caseBBSService.insertAttchMents(attch);
			}
			return result;
		} catch (Exception ex) {
			return "File Upload Failed";
		}

	}
}
