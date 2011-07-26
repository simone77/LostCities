package it.stessaro.lostcities.ai;

public class BinNode {
	int value;
	BinNode sin;
	BinNode des;

	public BinNode(int i, BinNode s, BinNode d) {
		this.value = i;
		this.sin = s;
		this.des = d;
	}
}
