package Launch;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * Created by gerasymiuk on 14.05.17.
 */
public class App {
    public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapp/src/main/webapp";
        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if(webPort==null|| webPort.isEmpty()){
            webPort="8081";
        }
        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configure app with basedir: "+new File(webappDirLocation).getAbsolutePath());


        File additionWebInfClasses = new File("webapp/target/classes");
        WebResourceRoot resource = new StandardRoot(ctx);
        resource.addPreResources(new DirResourceSet(resource, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(),"/"));
        ctx.setResources(resource);

        tomcat.start();
        tomcat.getServer().await();
    }
}
