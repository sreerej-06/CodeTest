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
	public String greeting(Model model) {
		
		return "Sree";
	}

	

}
