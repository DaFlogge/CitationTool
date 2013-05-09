package control;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import logging.Logger;
import model.Article;

import plugins.ieee.IEEE_Article;

public class Test implements Observer {

	public Test() throws IOException {
		Logger.init(true);
		HTTP_Executer exec = new HTTP_Executer();
		exec.addObserver(this);
		Article startNode = new IEEE_Article().getContentFetcher().getArticle("6225717");
		List<Article> articles= new LinkedList<Article>();
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		articles.add(startNode);
		
		for (Article art : articles) {
			exec.executeGetAllReferences(art);
		}
//		System.out.println(new Date());
//		for (int i = 0; i < 1; i++) {
//			for (final Article art : oldMap.values()) {
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						List<Article> artList = art.getContentFetcher().getAllReferences(art);
//						for (Article article : artList) {
//							newMap.put(article.getExternalID(), article);
//						}
//		System.out.println(new Date());
//
//					}
//				}).start();
//			}
////		}
//		for (int i = 1; i <= 1; i++) {
//			Logger.logInfo("Round " + i + ". Map size: " + articles.size());			
//			for (Article art : oldMap.values()) {
//					List<Article> artList = art.getContentFetcher().getAllReferences(art);
//				for (Article article : artList) {
//					newMap.put(article.getExternalID(), article);
//				}
//			}
//			Logger.logInfo("Found " + newMap.size() + " new resources.");
//			articles.putAll(newMap);
//			oldMap.clear();
//			oldMap.putAll(newMap);
//			newMap.clear();
//		}
//		System.out.println(new Date());
//		Logger.logInfo("Finished. Articles found: " + articles.size());
//		for (Article art : articles.values()) {
//			art.resolveReferences(articles);
//		}
		
		Logger.finish();
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new Test();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("updated");
	}
}