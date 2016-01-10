# Compile Errors #

If you get compile errors with GWTx such as: "The method close() is undefined for the type `OutputStream`" or "Errors in
`'jar:file:/path/to/GWTx.jar!/com/googlecode/gwtx/java/io/emul/java/io/ByteArrayOutputStream.java'"` then odds are you need to put the GWTx.jar before the gwt-user.jar in your project's classpath.

See
[this thread](http://groups.google.com/group/Google-Web-Toolkit/browse_thread/thread/66ab00f550dc3976/0abc5059ed9fd0e0?#0abc5059ed9fd0e0) for more details.

# GWT 1.5 compatibility #

Q: Is GWTx compatible with GWT 1.5 ?
A: The SVN development branch focus on GWT 1.5 compatibility. Releases of GWTx now include in version scheme the GWT version it is related to.

# java.io.Serializable (GWTx for GWT 1.3) #

Q: What does java.io.Serializable provided by GWTx do?
A: Nothing. It just lets a class that implements Serializable compile and run in web mode. **It does not replace GWT's IsSerializable interface and providers zero functionality.** Using Serializable and IsSerializable is helpful if you want to use Java serialization somehow on the server (eg: clustering) and send the same class to the client via GWT's RPC.