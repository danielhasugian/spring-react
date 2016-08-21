package com.organization.project.util;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
	/**
	 * method to change iterable to list
	 * 
	 * @param iterable
	 * @return {@link List}
	 */
	public static <E> List<E> IterabletoList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (List<E>) iterable;
		}
		ArrayList<E> list = new ArrayList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}
}
