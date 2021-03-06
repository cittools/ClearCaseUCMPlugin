package net.praqma.hudson.remoting;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

import net.praqma.clearcase.exceptions.ClearCaseException;
import net.praqma.clearcase.ucm.entities.Project;
import net.praqma.clearcase.ucm.utils.BuildNumber;
import net.praqma.util.debug.Logger;
import net.praqma.util.debug.LoggerSetting;
import net.praqma.util.debug.appenders.StreamAppender;

import hudson.FilePath.FileCallable;
import hudson.remoting.Pipe;
import hudson.remoting.VirtualChannel;

public class GetClearCaseVersion implements FileCallable<String> {

	private static Logger logger = Logger.getLogger();

	private Project project;
	private Pipe pipe;
	private LoggerSetting loggerSetting;
	private PrintStream pstream;

	public GetClearCaseVersion( Project project, Pipe pipe, PrintStream pstream, LoggerSetting loggerSetting ) {
		this.project = project;
		this.pipe = pipe;
		this.loggerSetting = loggerSetting;
		this.pstream = pstream;
	}

	@Override
	public String invoke( File f, VirtualChannel channel ) throws IOException, InterruptedException {

		StreamAppender app = null;
		if( pipe != null ) {
			PrintStream toMaster = new PrintStream( pipe.getOut() );
			app = new StreamAppender( toMaster );
			app.lockToCurrentThread();
			Logger.addAppender( app );
			app.setSettings( loggerSetting );
		} else if( pstream != null ) {
			app = new StreamAppender( pstream );
			app.lockToCurrentThread();
			Logger.addAppender( app );
			app.setSettings( loggerSetting );
		}

		String version = "";

		try {
			version = BuildNumber.getBuildNumber( project );
		} catch( Exception e ) {
			logger.warning( "get clearcase version: " + e.getMessage() );
			Logger.removeAppender( app );
			throw new IOException( "Unable to load " + project.getShortname() + ":" + e.getMessage(), e );
		}

		Logger.removeAppender( app );

		return version;
	}

}
