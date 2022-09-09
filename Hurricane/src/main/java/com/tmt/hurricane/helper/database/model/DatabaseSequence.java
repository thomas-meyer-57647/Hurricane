package com.tmt.hurricane.helper.database.model;
/*-------------------------------------------------------------------------------
 * Hurrican
 *-------------------------------------------------------------------------------
 * @author    	Thomas Meyer
 * @copyright 	Copyright (C) 2020 Thomas Meyer. License see license.txt
 * @package     database
 * @version		0.1.3
 --------------------------------------------------------------------------------*/
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The entity stores the the auto-incremented sequence for other collections
 *
 * id;									id
 * long sequence;						the auto-incremented sequence
 */
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;									// the sequence name
    private long sequence;								// the auto-incremented sequence

    /**
     * default constructor
     *
     * @return void
     */
    public DatabaseSequence() {}

    /**
     * get the id of this database sequence
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * set the id of this database sequence
     *
     * @param id
     * @return id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the sequence
     *
     * @return String
     */
    public long getSequence() {
        return this.sequence;
    }

    /**
     * set the sequence
     *
     * @param sequence
     * @return void
     */
    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sequence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DatabaseSequence other = (DatabaseSequence) obj;
        return Objects.equals(id, other.id) && sequence == other.sequence;
    }

    @Override
    public String toString() {
        return "DatabaseSequence [id=" + id + ", sequence=" + sequence + "]";
    }
}


