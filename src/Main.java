import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("services")
public class Main extends Application {
	
	
    @Override
    public Set<Class<?>> getClasses() {
    	
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(Mappa_Resource.class);
        return classes;
        
    }
}
