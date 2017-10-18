/**
 * 
 */
package co.hypcode.tasknode;

/**
 * @author Kong
 */
public interface SystemConfig {
   // Base URI the Grizzly HTTP server will listen on
   String PROP_BASE_URI = "base.uri";
   String PROP_BASE_URI_DEFAULT = "http://localhost:8900/";
   
   // Configuration Path
   String PROP_CONFIG_PATH = "config.path";
   String PROP_CONFIG_PATH_DEFAULT = "./conf/tasknode.yaml";
   
   // Constants
   String RESOURCES_PACKAGE = "co.hypcode.tasknode.api";
   String KEY_NODE_LIST = "nt_nodelist";
   String DEFAULT_WORKER_NAME = "nt_nodeworker";
}
