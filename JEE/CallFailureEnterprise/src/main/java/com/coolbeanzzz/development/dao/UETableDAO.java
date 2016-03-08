/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.dao;

import java.util.Collection;

public interface UETableDAO extends FailureTableDAO{
	/**
	 * Retrieves all ue type values from the UETable
	 * @return a collection of ue type integers from the underlying UETable
	 */
	Collection<Integer> getUETypes();
}