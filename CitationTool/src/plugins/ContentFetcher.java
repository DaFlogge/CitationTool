package plugins;

import java.util.List;

import model.Article;

public interface ContentFetcher {

	/**
	 * Returns an Article referenced by its external ID. May return null if
	 * Article not found or on connection errors.
	 * 
	 * @param externalID
	 *            external identifier of the Article
	 * @return article referenced by externalID. null on errors or if article
	 *         not found..
	 */
	public Article getArticle(String externalID);

	/**
	 * Returns a list of all articles referenced by the given article.
	 * 
	 * @param a
	 * @return
	 */
	public List<Article> getAllReferences(Article a);

	/**
	 * Returns a list of articles which are referenced by an article given and
	 * that share at least one keyword
	 * 
	 * @param a
	 *            article to lookup references for
	 * @return list of articles referenced with same keyword(s)
	 */
	public List<Article> getAllReferencesWithKeyword(Article a);

	/**
	 * @param a
	 * @param keywords
	 * @return
	 */
	public List<Article> getAllReferencesWithKeyword(Article a,
			List<String> keywords);
}