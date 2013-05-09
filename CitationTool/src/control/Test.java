package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logging.Logger;
import model.Article;

import plugins.ieee.IEEE_Article;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Logger.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Article> articles = new HashMap<String,Article>();
		Article startNode = new IEEE_Article().getContentFetcher().getArticle("6225717");
		Logger.logInfo(startNode instanceof IEEE_Article);
		articles.put(startNode.getExternalID(), startNode);
		Map<String, Article> newMap = new HashMap<String,Article>();
		Map<String, Article> oldMap = new HashMap<String,Article>();
		oldMap.putAll(articles);
		for (int i = 1; i <= 2; i++) {
			Logger.logInfo("Round " + i + ". Map size: " + articles.size());			
			for (Article art : oldMap.values()) {
					List<Article> artList = art.getContentFetcher().getAllReferences(art);
				for (Article article : artList) {
					newMap.put(article.getExternalID(), article);
				}
			}
			Logger.logInfo("Found " + newMap.size() + " new resources.");
			articles.putAll(newMap);
			oldMap.clear();
			oldMap.putAll(newMap);
			newMap.clear();
		}
		Logger.logInfo("Finished. Articles found: " + articles.size());
		for (Article art : articles.values()) {
			art.resolveReferences(articles);
		}
	}
}