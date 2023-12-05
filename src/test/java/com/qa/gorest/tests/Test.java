package com.qa.gorest.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		int[] a= {4,5,8};
		int[] b= {3,2,1,9};
		
		Object[] res = mergeArray(a, b);
		for (Object object : res) {
			System.out.println(object);
		}
		
	}
	
	public static Object[] mergeArray(int a[],int b[]) {
		
		LinkedList<Integer> al = new LinkedList<Integer>();
		for(int i=0;i<a.length;i++) {
			al.add(a[i]);
		}
		for(int i=0;i<b.length;i++) {
			al.add(b[i]);
		}
		
		 Object[] c= al.toArray();
		 Arrays.sort(c);
		
		
		return c;
		
	}

}
