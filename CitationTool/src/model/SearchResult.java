package model;

import java.util.List;

public class SearchResult {

	private Article root;
	private List<Article> children;

	public SearchResult(Article root, List<Article> children) {
		super();
		this.root = root;
		this.children = children;
	}

	public Article getRoot() {
		return root;
	}

	public void addChild(Article a) {
		this.children.add(a);
	}
	public List<Article> getChildren() {
		return children;
	}

}
