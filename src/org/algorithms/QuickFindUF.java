package org.algorithms;

/**
 * Quick Find union between two points
 *
 */
public class QuickFindUF {
	
	//the array to save the releation
	private int[] id;
	
	//initial
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}
	
	//to check whether two points is connected
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	//union
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid) id[i] = qid;
	}
}
