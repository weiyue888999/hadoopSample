package org.myorg.testCase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HdfsTestCase {

    static{
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    private Configuration configuration = new Configuration();

    FileSystem fs = null;

    @Before
    public void init(){
        System.out.println("init......");

        try {
            fs = FileSystem.get(URI.create("hdfs://localhost:9000/"),configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void shutdown(){
        System.out.println("shutdown.......");

        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUriReadData(){

        InputStream is = null;
        OutputStream os = null;

        try {

            os = new FileOutputStream("/home/xiaomo/java_code_repo/hadoopSample_work/2.txt");

            URL url = new URL("hdfs://localhost:9000/user/xiaomo/1.txt");
            is = url.openStream();
            IOUtils.copyBytes(is,os,4096);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(is);
            IOUtils.closeStream(os);
        }
    }

    @Test
    public void testRead() throws Exception{

        OutputStream outputStream = null;

        Path path = new Path("/user/xiaomo/1.txt");
        FSDataInputStream fsDataInputStream = fs.open(path);

        outputStream = new FileOutputStream("/home/xiaomo/java_code_repo/hadoopSample_work/3.txt");

        IOUtils.copyBytes(fsDataInputStream,outputStream,4096);
        IOUtils.closeStream(outputStream);
        IOUtils.closeStream(fsDataInputStream);

    }

    @Test
    public void testWrite()throws Exception{

        Path path = new Path("/user/xiaomo/4.txt");
        FSDataOutputStream fsDataOutputStream = fs.create(path);
        fsDataOutputStream.writeUTF("hello,world!!!");
        fsDataOutputStream.flush();
        fsDataOutputStream.close();
    }

    @Test
    public void testCreateFile() throws Exception{

        boolean succ = fs.createNewFile(new Path("/user/xiaomo/4.txt"));
        if(succ){
            System.out.println("create file success");
        }else{
            System.out.println("create file fail");
        }
    }

    @Test
    public void testDelFile() throws Exception{
        fs.delete(new Path("/user/xiaomo/4.txt"),false);
    }

    @Test
    public void testMkdir()throws Exception{

        boolean succ = fs.mkdirs(new Path("/user/xiaomo/testcase"));
        if(succ){
            System.out.println("create dir success");
        }else{
            System.out.println("create dir fail");
        }
    }

    @Test
    public void testRmdir()throws Exception{
        fs.delete(new Path("/user/xiaomo/testcase"),true);
    }
}
