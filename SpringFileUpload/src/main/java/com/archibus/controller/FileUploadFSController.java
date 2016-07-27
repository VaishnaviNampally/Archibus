package com.archibus.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application file upload in a file system requests
 */
@Controller
public class FileUploadFSController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadFSController.class);

	/**
	 * Storing path of the file to be uploaded in a global variable finalPath
	 */
	String finalPath = null;

	ArrayList<File> fileList = new ArrayList<File>();

	/**
	 * Upload single file using Spring Controller. Gets name of the file and the
	 * file from view (upload.jsp)
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,
			Model model) {

		ModelAndView modelAndView = new ModelAndView("list");
		/**
		 * If file uploaded is not empty, data from file is stored in a byte
		 * array
		 */
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				/**
				 * Creating the directory to store file.
				 */

				String rootPath = System.getProperty("catalina.home");
				/**
				 * Creates a new directory 'tempFiles' for storing all the files
				 * uploaded
				 */
				File dir = new File(rootPath + File.separator + "tmpFiles");
				/**
				 * Creates a new directory if directory doesn't exist
				 */
				if (!dir.exists())
					dir.mkdirs();

				File sFile = new File(dir.getAbsolutePath() + File.separator + name);

				finalPath = sFile.getAbsolutePath();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(sFile));
				stream.write(bytes);
				stream.close();

				/**
				 * Outputs location of the uploaded file
				 */
				logger.info("File Location=" + sFile.getAbsolutePath());

				fileList.add(sFile);
				model.addAttribute(fileList);
				return modelAndView;

			} catch (Exception e) {
				/**
				 * Redirecting to an error page if file not uploaded
				 */
				 ModelAndView modelAndView = new ModelAndView("error1");
				return modelAndView;
			}
		} else {
			/**
			 * Redirecting to an error page if file to be uploaded is left blank
			 */
			 ModelAndView modelAndView = new ModelAndView("error2");
			return modelAndView;
		}
	}
}
