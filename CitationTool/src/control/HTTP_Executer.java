package control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import model.Article;
import model.SearchResult;

public class HTTP_Executer extends Observable implements Observer {

	private static final int CONNECTIONS = 50;

	private static Queue<Article> waiting = new LinkedList<Article>();
	private static Map<Article, HTTP_Thread> current = new HashMap<Article, HTTP_Thread>();
	private static int currentConnections = 0;

	public void executeGetAllReferences(Article a) {
		waiting.add(a);
		repop();
	}

	@Override
	public void update(Observable o, Object arg) {
		SearchResult sr = (SearchResult) arg;
		HTTP_Executer.current.remove(sr.getRoot());
		--currentConnections;
		repop();
		this.setChanged();
		notifyObservers(sr);
	}

	private void repop() {
		while (currentConnections < CONNECTIONS && !waiting.isEmpty()) {
			Article a = waiting.poll();
			HTTP_Thread thread = new HTTP_Thread(a);
			thread.addObserver(HTTP_Executer.this);
			current.put(a, thread);
			++currentConnections;
			new Thread(thread).start();
		}
	}
}