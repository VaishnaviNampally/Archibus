package com.archibus.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.archibus.DAO.FileUploadDAO;
import com.archibus.model.FileUploadForm;

/**
 * Handles requests for the application file upload in BLOB field in database
 */
@Controller
public class FileUploadDBController {

	/**
	 * Upload single file using Spring Controller. Gets the file from view
	 * (upload.jsp)
	 */
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload)
			throws Exception {

		/**
		 * Establishing connection to database.
		 */
		FileUploadDAO userDAO = new FileUploadDAO();
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());

				/**
				 * Calling addFile() method in FileUploadDAO to store the file
				 * in database.
				 */
				FileUploadForm uploadFile = new FileUploadForm();

				uploadFile.setFileName(aFile.getOriginalFilename());
				uploadFile.setData(aFile.getBytes());
				userDAO.addFile(uploadFile);
			}
		}

		return "AddedSuccessfully";
	}

	/**
	 * Download single file using Spring Controller. Gets the fileID from view
	 * (upload.jsp)
	 */
	@RequestMapping("/download")
	public ModelAndView download(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("fileName") int fileID) {

		ModelAndView mv;
		FileUploadDAO userDAO = new FileUploadDAO();
		int size = userDAO.getAllFiles().size();
		/**
		 * Returns an error page when fileID entered is not valid in the
		 * database
		 */
		if (size < fileID) {
			mv = new ModelAndView("error");
			return mv;
		} else {
			/**
			 * Gets the file with the mentioned fileID and store in doc
			 */
			FileUploadForm doc = userDAO.getFile(fileID);
			try {
				response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getFileName() + "\"");
				OutputStream out = response.getOutputStream();
				out.write(doc.getData());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/**
			 * returns showFile view is the file is found in database
			 */
			mv = new ModelAndView("showFile", "doc", doc);
			return mv;
		}
	}
}