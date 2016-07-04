package com.archibus.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * FileUploadForm class is the model for creating a table with the name
 * UploadFile while storing in a database.
 */
@Entity
@Table(name = "UploadFile")
public class FileUploadForm {
	/**
	 * ID serves as the primary key for the table UploadFile. By default, it
	 * starts from 1 and auto-increments.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String fileName;

	/**
	 * File is stored as a byte array named data.
	 */
	@NotEmpty(message = "File could not be left blank/ Invalid Characters")
	@Lob
	private byte[] data;

	/**
	 * Getters and setters for the attributes are written below.
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
