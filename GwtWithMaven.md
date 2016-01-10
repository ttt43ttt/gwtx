#Using GWT with Maven

# Introduction #

Apache Maven can be used to setup your GWT project and support the Java to JavaScript compilation process.

# Dependencies #

First of all, your Maven project must declare dependencies on the GWT binaries for compilation. According to the GWT version you want to use, add the following dependencies to your pom.xml :

```
<project>
 ...
 <dependencies>
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-user</artifactId>
      <version>${gwt.version}</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-servlet</artifactId>
      <version>${gwt.version}</version>
    </dependency>
 </dependencies>
 ...
</project>
```

GWT Web developpers don't need declare gwt-dev as a dependency : this tooling library includes many opensource libs that you may use in your own application with distinct versions. This can introduce strange NoSuchMethod errors (when running unit tests for example). The gwt-maven-plugin will automagically download this jar and use it when running GWT tools.

Only GWT library developpers may need to add gwt-dev as a dependency (to use the rebind API). In such case, use profiles to detect the adequate variant of this dependency for your OS :
```

  <dependencies>
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-user</artifactId>
      <version>${gwt.version}</version>
    </dependency>
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-dev</artifactId>
      <version>${gwt.version}</version>
      <classifier>${gwt.variant}</classifier>
      <scope>provided</scope>
    </dependency>
  </dependencies>

  <profiles>
    <profile>
      <id>mac</id>
      <activation>
        <os>
          <family>mac</family>
        </os>
      </activation>
      <properties>
        <gwt.variant>mac</gwt.variant>
      </properties>
    </profile>
    <profile>
      <id>linux</id>
      <activation>
        <os>
          <family>unix</family>
        </os>
      </activation>
      <properties>
        <gwt.variant>linux</gwt.variant>
      </properties>
    </profile>
    <profile>
      <id>windows</id>
      <activation>
        <os>
          <family>windows</family>
        </os>
      </activation>
      <properties>
        <gwt.variant>windows</gwt.variant>
      </properties>
    </profile>
  </profiles>
```


Configure gwt.version  to match your environment.

```
<project>
 ...
 <properties>
   <!-- GWT 1.6 final -->
   <gwt.version>1.6.4</gwt.version>
 </properties>
 ...
</project>
```

Now, your project can be used from your favorite IDE to write GWT code based on Maven dependencies.

# Issue with GWT 1.6.4 #

gwt-user 1.6.4 contains (by mistake ?) java source files for servlet-api. As a side effect, the compiler will compile them into your webapp classfolder, and you will get some strange `XXServlet is not a Servlet` error. To fix this setup you'll need to configure the compiler plugin to ignore those servlet-api sources :

```
<project>
...
 <build>
  <plugins>
    <plugin>
      <artifactId>maven-compiler-plugin</artifactId>
      <configuration>
        <source>1.5</source>
        <target>1.5</target>
        <excludes>
           <exclude>javax/servlet/**</exclude>
        </excludes>
      </configuration>  
    </plugin>
  </plugins>
 </build>
 ...
</project>

```


# Compilation #

The [Mojo](http://mojo.codehaus.org) project host a GWT plugin that can compile your GWT code into JavaScript and run other GWT tools. It also includes a code generator to automagically generate GWT-RPC async interfaces for your services.

This plugin is the result of merging the original Mojo plugin with the [gwt-maven](http://code.google.com/p/gwt-maven/) one hosted by googlecode.

```
<project>
...
 <build>
  <plugins>
    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>gwt-maven-plugin</artifactId>
        <version>1.1-SNAPSHOT</version>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
              <goal>generateAsync</goal>
            </goals>
          </execution>
        </executions>
    </plugin>
  </plugins>
 </build>
 ...
</project>

```

This maven plugin has many parameters to tweak the compiler. It can also run the I18n Generators, run a module in hosted mode or launch GwtTests. Refer to it's [official documentation](http://mojo.codehaus.org/gwt-maven-plugin) for more informations.

Please note the 1.1 version of this plugin is still a SNAPSHOT, you'll need to enable Mojo plugins Snapshot repository as [explained here](http://mojo.codehaus.org/using-sandbox-plugins.html).


# Setup your IDE #

The gwt-maven plugin can also setup your Eclipse IDE by creating launch configuration for your GWT modules. Just run `mvn gwt:eclipse` and they will be created in your project root . You can them right-clik them and select "Run as ..." to run the hosted browser.