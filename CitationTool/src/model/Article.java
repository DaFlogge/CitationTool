package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import plugins.ContentFetcher;

public abstract class Article implements Comparable<Article> {

	private String internalID;
	private String externalID;
	private String URL;
	private String title;
	private List<String> author;
	private String date;
	private String abstractString;
	private List<String> keywords;
	private List<String> referenceIDs;
	private List<String> citingIDs;
	private List<Article> citing;
	private List<Article> references;
	private String publishedIn;

	private int referenceCount = 0;

	public static String resolveToExternalID (String internal) {
		return internal;
	}
	
	public ContentFetcher getContentFetcher() {
		return null;
	}
	
	public void resolveReferences(Map<String, Article> allArticles) {
		for (String refString : this.referenceIDs) {
			if (allArticles.containsKey(refString)) {
				Article refedArt = allArticles.get(refString);
				if (refedArt != null) {
					this.references.add(refedArt);
					refedArt.addCiting(this);
				}
			}
			if (this.citing.contains(refString)) {
				Article citingArt = allArticles.get(refString);
				if (citingArt != null) {
					this.addCiting(citingArt);
					citingArt.addReference(this);
				}
			}
		}
	}

	public Article() {
		this.author = new LinkedList<String>();
		this.keywords = new LinkedList<String>();
		this.referenceIDs = new LinkedList<String>();
		this.references = new LinkedList<Article>();
		this.citingIDs = new LinkedList<String>();
		this.citing = new LinkedList<Article>();
	}

	public int getReferenceCount() {
		return referenceCount;
	}

	public void increaseReferencCount() {
		this.referenceCount = this.referenceCount + 1;
	}

	public void increaseReferencCount(int i) {
		this.referenceCount = this.referenceCount + i;
	}

	public void resetReferenceCount() {
		this.referenceCount = 0;
	}

	public String getInternalID() {
		return internalID;
	}

	public String getExternalID() {
		return externalID;
	}

	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}

	public String getTitle() {
		return title;
	}

	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthor() {
		return author;
	}

	public void addAuthor(String author) {
		this.author.add(author);
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public void addKeyword(String keyword) {
		this.keywords.add(keyword);
	}

	public String getAbstract() {
		return abstractString;
	}

	public void setAbstract(String abstract1) {
		this.abstractString = abstract1;
	}

	public List<Article> getReferences() {
		return references;
	}

	public void addReference(Article a) {
		if (!this.references.contains(a)) {
			this.references.add(a);
		}
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public List<String> getReferenceIDs() {
		return referenceIDs;
	}

	public void addReferenceID(String id) {
		this.referenceIDs.add(id);
	}

	public void setReferenceIDs(List<String> referenceIDs) {
		this.referenceIDs = referenceIDs;
	}

	public String getPublishedIn() {
		return publishedIn;
	}

	public void setPublishedIn(String publishedIn) {
		this.publishedIn = publishedIn;
	}

	@Override
	public int compareTo(Article o) {
		if (this.referenceCount > o.referenceCount) {
			return 1;
		} else if (this.referenceCount < o.referenceCount) {
			return -1;
		}
		return 0;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Article> getCiting() {
		return citing;
	}

	public void addCiting(Article a) {
		if (!this.citing.contains(a)) {
			this.citing.add(a);
			this.increaseReferencCount();
		}
	}

	public List<String> getCitingIDs() {
		return citingIDs;
	}

	public void addCitingID(String citingID) {
		this.citingIDs.add(citingID);
	}
}