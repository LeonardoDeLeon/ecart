package com.learning.ecart.util;

import org.bson.types.ObjectId;

public class CreateTestDataUtil {
 
	/**
	 * create a new objectId for a test User data
	 * @return ObjectId
	 */
	public static ObjectId createObjectId() {
        return new ObjectId();
    }    
}
