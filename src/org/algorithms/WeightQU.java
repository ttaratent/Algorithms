package org.algorithms;

/**
 * weight quick union 
 * @author asus
 *
 */
public class WeightQU {
	//parent
	private int[] id;
	//count number of objects in the tree rooted at i
	private int[] sz;
 	
	//constructor
	public WeightQU(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
		for (int i = 0; i < N; i++) sz[i] = 1;
	}
	
	//if the value is equal to the index, it's the root
	//to get the root
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
		if (i == j) return;
		if (sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i]; }
		else { id[j] =j; sz[i] += sz[j]; }
	}
}
