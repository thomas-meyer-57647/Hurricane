package com.tmt.hurricane.model.document;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * a document for a project
 *
 * List<DoumentFile> files = new ArrayList<DoumentFile>();						the list of all versioned files
 */
public class Document {
	
    private	List<DoumentFile> files = new ArrayList<DoumentFile>();						// the list of all versioned files

    public Document(
    		List<DoumentFile> files) {
		super();
		
		this.files = files;
	}

	public List<DoumentFile> getFiles() {
		return files;
	}

	public void setFiles(List<DoumentFile> files) {
		this.files = files;
	}

	@Override
	public int hashCode() {
		return Objects.hash(files);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		return Objects.equals(files, other.files);
	}

	@Override
	public String toString() {
		return "Document [files=" + files + "]";
	}
}
