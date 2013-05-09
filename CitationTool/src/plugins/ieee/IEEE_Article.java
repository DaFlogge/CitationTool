package plugins.ieee;

import plugins.ContentFetcher;
import model.Article;

public class IEEE_Article extends Article{

	@Override
	public ContentFetcher getContentFetcher() {
		return new IEEE_ContentFetcher();
	}

}
