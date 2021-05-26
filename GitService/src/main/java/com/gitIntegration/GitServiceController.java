package com.gitIntegration;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dataobject.Persons;

@RestController
public class GitServiceController {
	/*private static final String REMOTE_URL = "https://github.com/centic9/jgit-cookbook.git";*/
	private static final String REMOTE_URL = "https://github.com/sreerej-06/TestCommit.git";
	
	@PostMapping("/CloneGitRepository")
	public Persons greeting(Model model) {

		Persons S = new Persons(3, "RamHanumans");
		File localPath = new File ("F:\\Test");
		/*
		 * try {
		 * 
		 * 
		 * if(!localPath.delete()) { throw new
		 * IOException("Could not delete temporary file " + localPath); }
		 */
			// then clone
			System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
			try (Git result = Git.cloneRepository().setURI(REMOTE_URL).setDirectory(localPath)
					.setProgressMonitor(new SimpleProgressMonitor()).call()) {
				// Note: the call() returns an opened repository already which needs to be
				// closed to avoid file handle leaks!
				System.out.println("Having repository: " + result.getRepository().getDirectory());
			} catch (InvalidRemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransportException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// clean up here to not keep using more and more disk-space for these samples
			/* FileUtils.deleteDirectory(localPath); */
		/*
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return S;
	}

	@PostMapping("/Commit")
	public Persons Commit(Model model) {
		/* String URL = "https://github.com/sreerej-06/TestCommit.git"; */
		File localPath = new File ("F:\\Test");
		Persons S = new Persons(3, "RamHanuman");
		   try {
			Git git = Git.open(localPath);
			git.add().addFilepattern("testfile.txt").call();
			git.commit().setMessage("test commit").call();
			 PushCommand pushCommand = git.push();
			    pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("sreerej.r.k@gmail.com", "Rajakumar@06"));
			    // you can add more settings here if needed
			    pushCommand.call();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return S;
		
	}
	private static class SimpleProgressMonitor implements ProgressMonitor {
		@Override
		public void start(int totalTasks) {
			System.out.println("Starting work on " + totalTasks + " tasks");
		}

		@Override
		public void beginTask(String title, int totalWork) {
			System.out.println("Start " + title + ": " + totalWork);
		}

		@Override
		public void update(int completed) {
			System.out.print(completed + "-");
		}

		@Override
		public void endTask() {
			System.out.println("Done");
		}

		@Override
		public boolean isCancelled() {
			return false;
		}
	}

}
