package net.praqma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import hudson.model.AbstractBuild;
import hudson.scm.ChangeLogParser;
import hudson.scm.ChangeLogSet;
import hudson.scm.ChangeLogSet.Entry;
import hudson.util.Digester2;

public class ChangeLogParserImpl extends ChangeLogParser {
	
	protected static Debug logger = Debug.GetLogger();
	
	@Override
	public ChangeLogSet<? extends Entry> parse(AbstractBuild build, File changelogFile)
			throws IOException, SAXException {
		// TODO Auto-generated method stub
		logger.trace_function();
		logger.log("build: "+build.toString()+" changelogFile: "+changelogFile.toString());
		List<ChangeLogEntryImpl> entries = new ArrayList<ChangeLogEntryImpl>();
		
        BufferedReader in = new BufferedReader(new FileReader(changelogFile));
        //StringBuilder message = new StringBuilder();
        String s;

        ChangeLogEntryImpl entry = null;
        while((s = in.readLine()) != null) {
        	entry = new ChangeLogEntryImpl(s);
        	entries.add(entry);
        }
        
		//Digester digester = new Digester2();
		//digester.push(entries);
		//digester.addObjectCreate("*/file", ChangeLogEntryImpl.class);
		//digester.addSetNext("*/file","add");
		//FileReader reader = new FileReader(changelogFile);
		//digester.parse(reader);
		//reader.close();
		
		return new ChangeLogSetImpl(build, entries);
	}

}