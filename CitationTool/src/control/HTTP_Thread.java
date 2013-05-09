package control;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.Article;
import model.SearchResult;

public class HTTP_Thread extends Observable implements Runnable{

	private SearchResult sr;
	private Article a;
	
	public HTTP_Thread(Article a) {
		super();
		this.a = a;
		this.sr = new SearchResult(a, new LinkedList<Article>());
	}

	@Override
	public void run() {
		List<Article> children = a.getContentFetcher().getAllReferences(a);
		for (Article art : children) {
			sr.addChild(art);
		}
		this.setChanged();
		notifyObservers(sr);
	}
}
