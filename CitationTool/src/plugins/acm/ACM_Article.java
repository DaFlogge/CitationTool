package plugins.acm;

import plugins.ContentFetcher;
import model.Article;

public class ACM_Article extends Article {

	@Override
	public ContentFetcher getContentFetcher() {
		return new ACM_ContentFetcher();
	}

}
