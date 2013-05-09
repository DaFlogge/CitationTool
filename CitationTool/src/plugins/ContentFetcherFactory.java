package plugins;

import model.Article;
import plugins.acm.ACM_Article;
import plugins.acm.ACM_ContentFetcher;
import plugins.ieee.IEEE_Article;
import plugins.ieee.IEEE_ContentFetcher;

public class ContentFetcherFactory {

	/**
	 * Empty private Constructor for static class
	 */
	private ContentFetcherFactory(){
	}
	
	public static ContentFetcher getContentFetcher(IEEE_Article a) {
		return new IEEE_ContentFetcher();
	}
	
	public static ContentFetcher getContentFetcher(ACM_Article a) {
		return new ACM_ContentFetcher();
	}
	
	public static ContentFetcher getContentFetcher(Article a) {
		if (a instanceof IEEE_Article) {
			return new IEEE_ContentFetcher();
		}
		if (a instanceof ACM_Article) {
			return new ACM_ContentFetcher();
		}
		System.err.println("CLASS TYPE NOT FOUND FOR " + a);
		return null;
	}
}
