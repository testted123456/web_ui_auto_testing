package com.nonobank.apps.utils.entity;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		list1.add("g");
		list1.add("s");
		list1.add("a");
		list1.add("f");
		list2.add("g");
		list2.add("c");
		list2.add("b");
		list2.add("a");
		
		list3.add("clist1");
		list1.retainAll(list2);
		list1.retainAll(list3);
		System.out.print(list1);
	}
}
