/**
 * 
 */
package com.wecat.small.utils;


import org.springframework.security.core.context.SecurityContextHolder;

import com.wecat.small.entity.Hr;

/**
 * @author Administrator
 *
 */
public class HrUtils {
	 public static Hr getCurrentHr() {
	        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    }
}
