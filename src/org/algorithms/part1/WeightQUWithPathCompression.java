package org.algorithms.part1;

/**
 * weight quick union 
 * @author asus
 *
 */
public class WeightQUWithPathCompression {
	//parent
	private int[] id;
	//count number of objects in the tree rooted at i
	private int[] sz;
 	
	//constructor
	public WeightQUWithPathCompression(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) id[i] = i;
		for (int i = 0; i < N; i++) sz[i] = 1;
	}
	
	//if the value is equal to the index, it's the root
	//to get the root
	//make every other node in path point to its grandparent(thereby halving path length).
	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
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
