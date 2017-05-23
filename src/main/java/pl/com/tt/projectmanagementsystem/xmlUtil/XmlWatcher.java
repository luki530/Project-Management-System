package pl.com.tt.projectmanagementsystem.xmlUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import pl.com.tt.projectmanagementsystem.appContext.AppContext;

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
			File file = new File("Permissions.xml");
			XmlWatcher.xmlDirectory = Paths.get(file.getAbsolutePath()+"\\..\\");
			XmlWatcher.watchKey = xmlDirectory.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
			for (;;) {
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					AppContext.getLoggedUser().refreshPermissions();
				}
			}

		} catch (IOException e) {
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
