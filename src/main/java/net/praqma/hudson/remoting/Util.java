package net.praqma.hudson.remoting;

import java.io.File;

import net.praqma.hudson.exception.CCUCMException;
import net.praqma.hudson.scm.CCUCMState.State;
import hudson.FilePath;
import hudson.model.BuildListener;
import hudson.remoting.Future;

public abstract class Util {

    public static void completeRemoteDeliver( FilePath workspace, BuildListener listener, State state, boolean complete ) throws CCUCMException {
                
        try {
            Future<Boolean> i = null;

            i = workspace.actAsync(new RemoteDeliverComplete( state, complete ) );
            i.get();
            return;
            
        } catch( Exception e ) {
            throw new CCUCMException( "Failed to " + ( complete ? "complete" : "cancel" ) + " the deliver: " + e.getMessage() );
        }
    }
    
    public static void createRemoteBaseline( FilePath workspace, BuildListener listener, String baseName, String componentName, File view ) throws CCUCMException {
        
        try {
            Future<Boolean> i = null;

            i = workspace.actAsync(new CreateRemoteBaseline( baseName, componentName, view, listener ) );
            i.get();
            return;
            
        } catch( Exception e ) {
            throw new CCUCMException( e.getMessage() );
        }
    }
}
