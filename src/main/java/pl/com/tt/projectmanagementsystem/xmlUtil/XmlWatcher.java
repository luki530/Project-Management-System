package pl.com.tt.projectmanagementsystem.xmlUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.sun.xml.xsom.impl.util.Uri;

public class XmlWatcher implements Runnable {
	static FileSystem fileSystem;
	static WatchService watcher;
	static Path xmlDirectory;
	static WatchKey watchKey;

	@Override
	public void run() {

		try {
			XmlWatcher.fileSystem = FileSystems.getDefault();
			XmlWatcher.watcher = XmlWatcher.fileSystem.newWatchService();
			URL url = getClass().getResource("Permissions.xml");
			URI uri = url.toURI();
			XmlWatcher.xmlDirectory = Paths.get(uri);
			XmlWatcher.watchKey = xmlDirectory.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
			for (;;) {
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					System.out.println("EDYTOWALES PLIK !!!!");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void start() {
		XmlWatcher xmlWatcher = new XmlWatcher();
		Thread thread = new Thread(xmlWatcher);
		thread.start();
	}

}
