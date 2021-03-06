package net.praqma.hudson.remoting;

import java.io.Serializable;

import net.praqma.clearcase.ucm.view.SnapshotView;
import net.praqma.hudson.scm.ClearCaseChangeset;

public class EstablishResult implements Serializable {

	private static final long serialVersionUID = 3620570179096736870L;

	public enum ResultType {
		NOT_STARTED,
		MERGE_ERROR,
		DELIVER_REQUIRES_REBASE,
		INTERPROJECT_DELIVER_DENIED,
		INITIALIZE_WORKSPACE_ERROR,
		DELIVER_IN_PROGRESS,
		DELIVER_IN_PROGRESS_NOT_CANCELLED,
		SUCCESS
	}
	
	private ResultType type = ResultType.NOT_STARTED;
	private String viewtag = "";
	private String log = "";
	
	private ClearCaseChangeset changeset = new ClearCaseChangeset();
	
	private SnapshotView view;
	
	private String message;
	
	public EstablishResult() {
	}
	
	public EstablishResult( String viewtag ) {
		this.viewtag = viewtag;
	}
	
	
	public void setResultType( ResultType type ) {
		this.type = type;
	}
	
	public ResultType getResultType() {
		return type;
	}
	
	public boolean isSuccessful() {
		return type.equals( ResultType.SUCCESS );
	}
	
	public boolean isFailed() {
		return !type.equals( ResultType.SUCCESS );
	}
	
	public boolean isMergeError() {
		return type.equals( ResultType.MERGE_ERROR );
	}
	
	public boolean isDeliverRequiresRebase() {
		return type.equals( ResultType.DELIVER_REQUIRES_REBASE );
	}
	
	public boolean isInterprojectDeliverDenied() {
		return type.equals( ResultType.INTERPROJECT_DELIVER_DENIED );
	}
	
	public boolean isDeliverInProgress() {
		return type.equals( ResultType.DELIVER_IN_PROGRESS );
	}
	
	public boolean isDeliverInProgressNotCancelled() {
		return type.equals( ResultType.DELIVER_IN_PROGRESS_NOT_CANCELLED );
	}
	
	public boolean isCancellable() {
		return ( type.equals( ResultType.SUCCESS ) || type.equals( ResultType.MERGE_ERROR ) );
	}
	
	public boolean isStarted() {
		return !type.equals( ResultType.NOT_STARTED );
	}
	
	
	
	public String getViewtag() {
		return this.viewtag;
	}
	
	public void setViewtag( String viewtag ) {
		this.viewtag = viewtag;
	}

	public String getLog() {
		return log;
	}

	public void setLog( String log ) {
		this.log = log;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage( String message ) {
		this.message = message;
	}

	public ClearCaseChangeset getChangeset() {
		return changeset;
	}
	
	public void setChangeset( ClearCaseChangeset changeset ) {
		this.changeset = changeset;
	}

	public SnapshotView getView() {
		return view;
	}

	public void setView( SnapshotView view ) {
		this.view = view;
	}
	

}
