package plugins.ieee;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import control.Utility;

import model.Article;
import plugins.ContentFetcher;

public class IEEE_ContentFetcher implements ContentFetcher {

	private static final String URL_ART_DETAILS_PRE = "http://ieeexplore.ieee.org/xpl/articleDetails.jsp?arnumber=";
	private static final String URL_ART_FULLTEXT_PRE = "http://docweb.rz.uni-passau.de:2166/stamp/stamp.jsp?tp=&arnumber=";
	private static final String URL_ART_REFERENCES_PRE = "http://ieeexplore.ieee.org/xpl/references.jsp?arnumber=";

	private static final String CONT_ERR_FRAG = "<title>IEEE Xplore - Error Page</title>";
	private static final String CONT_TITLE_PRE = "<meta name=\"citation_title\" content=\"";
	private static final String CONT_TITLE_SUF = "\">";
	private static final String CONT_AUTHOR_PRE = "<meta name=\"citation_author\" content=\"";
	private static final String CONT_AUTHOR_SUF = "\">";
	private static final String CONT_DATE_PRE = "<meta name=\"citation_date\" content=\"";
	private static final String CONT_DATE_SUF = "\">";
	private static final String CONT_ABSTRACT_PRE = "<a name=\"Abstract\"><h2>Abstract</h2></a>\n					<p>";
	private static final String CONT_ABSTRACT_SUF = "</p>";
	private static final String CONT_KEYWORDS_PRE = "<meta name=\"citation_keywords\" content=\"";
	private static final String CONT_KEYWORDS_SUF = "\">";
	private static final String CONT_PUB_PRE = "<meta name=\"citation_journal_title\" content=\"";
	private static final String CONT_PUB_SUF = "\">";
	private static final String CONT_REF_PRE = "<a href=\"/stamp/stamp.jsp?arnumber=";
	private static final String CONT_REF_SUF = "\">PDF</a>";

	@Override
	public IEEE_Article getArticle(String externalID) {
		IEEE_Article article = new IEEE_Article();

		// try to get article detail page content
		String content = "";
		try {
			content = Utility.getContent(new URL(URL_ART_DETAILS_PRE
					+ externalID));
		} catch (MalformedURLException e) {
			System.err.println("Malformatted URL for Article " + externalID);
		} catch (IOException e) {
			System.err.println("Error occured connecting to article "
					+ externalID);
		}

		// early return on errors
		if (content.equals("") || content.contains(CONT_ERR_FRAG)) {
			return null;
		}

		article.setInternalID("ieee" + externalID);
		article.setExternalID(externalID);
		System.out.println(article.getExternalID());

		article.setURL(URL_ART_FULLTEXT_PRE + externalID);

		// extract Title
		getTitle(article, content);

		// extract author
		getAuthor(article, content);

		// extract date
		getDate(article, content);

		// extract keywords
		getKeywords(article, content);

		// extract abstract
		getAbstract(article, content);

		// extract publishedIn
		getPublishedIn(article, content);

		// extract references
		getReferenceIDs(article);
		System.out.println(article.getReferenceIDs());
		return article;
	}

	//TODO: extract citing docs. 
	
	private void getReferenceIDs(IEEE_Article article) {
		try {
			String content = Utility.getContent(new URL(
					URL_ART_REFERENCES_PRE + article.getExternalID()));
			List<String> references = Utility.getAllOccurences(CONT_REF_PRE,
					CONT_REF_SUF, content);
			if (references.isEmpty()) {
				System.err.println("Could not extract References for Article "
						+ article.getExternalID());
			} else {
				for (String string : references) {
					article.addReferenceID(string);
				}
			}
		} catch (MalformedURLException e) {
			System.err.println("Malformatted URL for References of Article"
					+ article.getExternalID());
		} catch (IOException e) {
			System.err
					.println("Error occured connecting to references of article "
							+ article.getExternalID());
		}

	}

	private void getPublishedIn(IEEE_Article article, String content) {
		String publishedInString = Utility.getFirstOccurence(CONT_PUB_PRE,
				CONT_PUB_SUF, content);
		if (publishedInString != null && !publishedInString.isEmpty()) {
			article.setPublishedIn(publishedInString);
		} else {
			System.err
					.println("Could not extract publishing medium for Article "
							+ article.getExternalID());
		}
	}

	private void getKeywords(IEEE_Article article, String content) {
		String keywordString = Utility.getFirstOccurence(CONT_KEYWORDS_PRE,
				CONT_KEYWORDS_SUF, content);
		if (keywordString != null && !keywordString.isEmpty()) {
			String[] keyStrings = keywordString.split(",");
			for (String string : keyStrings) {
				article.addKeyword(string.replaceAll("\\n\\t\\t\\t", ""));
			}
		} else {
			System.err.println("Could not extract keywords for Article "
					+ article.getExternalID());
		}
	}

	private void getAbstract(IEEE_Article article, String content) {
		String abstractString = Utility.getFirstOccurence(CONT_ABSTRACT_PRE,
				CONT_ABSTRACT_SUF, content);
		if (abstractString != null && !abstractString.isEmpty()) {
			article.setAbstract(abstractString);
		} else {
			System.err.println("Could not extract abstract for Article "
					+ article.getExternalID());
		}
	}

	private void getDate(IEEE_Article article, String content) {
		String dateString = Utility.getFirstOccurence(CONT_DATE_PRE,
				CONT_DATE_SUF, content);
		if (dateString != null && !dateString.isEmpty()) {
			article.setDate(dateString);
		} else {
			System.err.println("Could not extract date for Article "
					+ article.getExternalID());
		}
	}

	private void getAuthor(IEEE_Article article, String content) {
		List<String> authorStrings = Utility.getAllOccurences(CONT_AUTHOR_PRE,
				CONT_AUTHOR_SUF, content);
		if (authorStrings.isEmpty()) {
			System.err.println("Could not extract author(s) for Article "
					+ article.getExternalID());
		} else {
			for (String string : authorStrings) {
				article.addAuthor(string);
			}
		}
	}

	private void getTitle(IEEE_Article article, String content) {
		String titleString = Utility.getFirstOccurence(CONT_TITLE_PRE,
				CONT_TITLE_SUF, content);
		if (titleString != null) {
			article.setTitle(titleString);
		} else {
			System.err.println("Could not extract title for Article "
					+ article.getExternalID());
		}
	}

	@Override
	public List<Article> getAllReferences(Article a) {
		List<String> references = a.getReferenceIDs();
		List<Article> articles = new LinkedList<Article>();
		for (String string : references) {
			Article art = this.getArticle(string);
			if (art != null && art.getExternalID() != null) {
				articles.add(art);
			}
		}
		return articles;
	}

	@Override
	public List<Article> getAllReferencesWithKeyword(Article a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getAllReferencesWithKeyword(Article a,
			List<String> keywords) {
		// TODO Auto-generated method stub
		return null;
	}

}
