package com.tmt.hurricane.model.document;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2022 Thomas Meyer. License see license.txt
 * @version		0.1.4
 --------------------------------------------------------------------------------*/

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.tmt.hurricane.model.global.UserNote;
import com.tmt.hurricane.user.model.User;

/**
 * This class gives information about the uploaded or updated document
 * 
 * @DBRef
 *User created_by;														the creator (uploader)
 *LocalDateTime created_at;												the created date
 *   
 * @NotBlank
 * String filename;														the filename in the storage
 *
 * @NotBlank
 * String orgFilename;													the original filename of the document
 *
 * List<DocumentViewer> viewer = new ArrayList<DocumentViewer>();		a list of people who have already seen this document
 *   
 * List<UserNote> notes = new ArrayList<UserNote>();					Comments from a viewer
 *
 */
public class DoumentFile {
	
    @DBRef
    private User created_by;													// the creator (uploader)
    private LocalDateTime created_at;											// the created date
    
    @NotBlank
    private String filename;													// the filename in the storage

    @NotBlank
    private String orgFilename;													// the original filename of the document

    private List<DocumentViewer> viewer = new ArrayList<DocumentViewer>();		// a list of people who have already seen this document
    
    private List<UserNote> notes = new ArrayList<UserNote>();					// Comments from a viewer


	public DoumentFile(
			User created_by, 
			LocalDateTime created_at, 
			@NotBlank String filename,
			@NotBlank String orgFilename, 
			List<DocumentViewer> viewer, 
			List<UserNote> notes) {
		super();
		
		this.created_by = created_by;
		this.created_at = created_at;
		this.filename = filename;
		this.orgFilename = orgFilename;
		this.viewer = viewer;
		this.notes = notes;
	}


	public User getCreatedBy() {
		return created_by;
	}


	public void setCreatedBy(User created_by) {
		this.created_by = created_by;
	}


	public LocalDateTime getCreatedAt() {
		return created_at;
	}


	public void setCreatedAt(LocalDateTime created_at) {
		this.created_at = created_at;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getOrgFilename() {
		return orgFilename;
	}


	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}


	public List<DocumentViewer> getViewer() {
		return viewer;
	}


	public void setViewer(List<DocumentViewer> viewer) {
		this.viewer = viewer;
	}


	public List<UserNote> getNotes() {
		return notes;
	}


	public void setNotes(List<UserNote> notes) {
		this.notes = notes;
	}


	@Override
	public int hashCode() {
		return Objects.hash(created_at, created_by, filename, notes, orgFilename, viewer);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoumentFile other = (DoumentFile) obj;
		return Objects.equals(created_at, other.created_at) && Objects.equals(created_by, other.created_by)
				&& Objects.equals(filename, other.filename) && Objects.equals(notes, other.notes)
				&& Objects.equals(orgFilename, other.orgFilename) && Objects.equals(viewer, other.viewer);
	}


	@Override
	public String toString() {
		return "DoumentFile [created_by=" + created_by + ", created_at=" + created_at + ", filename=" + filename
				+ ", orgFilename=" + orgFilename + ", viewer=" + viewer + ", notes=" + notes + "]";
	}
}
