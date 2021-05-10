package com.xs.rpc.v1.server;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureBaseRSA;
import com.xs.rpc.v1.dto.RpcTransData;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xueshuai
 * @date 2021/5/3 14:05
 * @description 利用serversocket实现一个服务端
 */
public class RpcServer implements Callable {


        private  ExecutorService executorService = Executors.newCachedThreadPool();

        private RpcTransData rpcTransData;


    //通信服务端进行接收数据
    public void receive() throws Exception {
        InputStream inputStream=null;
        ObjectInputStream objectInputStream=null;
        Object result =null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket accept = serverSocket.accept();
                inputStream = accept.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
                //todo 这里接收传来数据，进行转
                rpcTransData=(RpcTransData)objectInputStream.readObject();
                Future submit = executorService.submit(this);
                result = submit.get();
                outputStream = accept.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(result);
            }





        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            objectInputStream.close();
            outputStream.close();
            objectOutputStream.close();
        }
    }


    @Override
    public Object call() throws Exception {
        RpcServiceImp rpcServiceImp = new RpcServiceImp();
        Class clazz = rpcTransData.getClazz();
        Object[] args = rpcTransData.getArgs();
        Class[] classes=null;
        if(args!=null){
             classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i]=args[i].getClass();
            }
        }
        String methodName = rpcTransData.getMethodName();
        Method method = clazz.getMethod(methodName, classes);
        //重点拿到基本信息，反射 方法回调
        Object invoke = method.invoke(rpcServiceImp, args);
        System.out.println("远程服务调用输出：  "+invoke);
        return invoke;
    }
}
