package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Article;

import plugins.ContentFetcherFactory;
import plugins.ieee.IEEE_Article;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<String, Article> articles = new HashMap<String,Article>();
		Article startNode = ContentFetcherFactory.getContentFetcher(
				new IEEE_Article()).getArticle("6225717");
		System.out.println(startNode instanceof IEEE_Article);
		articles.put(startNode.getExternalID(), startNode);
		Map<String, Article> newMap = new HashMap<String,Article>();
		Map<String, Article> oldMap = new HashMap<String,Article>();
		oldMap.putAll(articles);
		for (int i = 1; i <= 2; i++) {
			System.out.println("Round " + i + ". Map size: " + articles.size());			
			for (Article art : oldMap.values()) {
				List<Article> artList = ContentFetcherFactory.getContentFetcher(art).getAllReferences(art);
				for (Article article : artList) {
					newMap.put(article.getExternalID(), article);
				}
			}
			System.out.println("Found " + newMap.size() + " new resources.");
			articles.putAll(newMap);
			oldMap.clear();
			oldMap.putAll(newMap);
			newMap.clear();
		}
		System.out.println("Finished. Articles found: " + articles.size());
		for (Article art : articles.values()) {
			art.resolveReferences(articles);
		}
	}
}