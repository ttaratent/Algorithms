package org.algorithms;

/**
 * quick union 
 * @author asus
 *
 */
public class QuickUnionUF {
	private int[] id;
	
	//constructor
	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
	}
	
	//if the value is equal to the index, it's the root
	//set the root
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	//check whether is connected
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	//union
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
