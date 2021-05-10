package com.xs.rpc.v1.client;

import com.xs.rpc.v1.dto.RpcTransData;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author xueshuai
 * @date 2021/5/4 11:49
 * @description  远程调用代理类
 *                  1。远程调用
 *                  2.返回接口代理
 */
public class RpcClient implements InvocationHandler {

    public Object send(RpcTransData rpcTransData) throws Exception {
        Socket socket =null;
        OutputStream outputStream =null;
        ObjectOutputStream objectOutputStream=null;
        try {
             socket = new Socket("127.0.0.1",8080);
             outputStream = socket.getOutputStream();
             objectOutputStream = new ObjectOutputStream(outputStream);
             //todo 传送数据
            objectOutputStream.writeObject(rpcTransData);

            //
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object o = objectInputStream.readObject();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            objectOutputStream.close();
            socket.close();
        }
        return null;
    }

    public <T>  T getBean(Class<T> clazz){
        return  (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理调用的时候，将类，方法，参数发送到远程服务器，让远程服务器调用后返回结果回来
        Class<?> declaringClass = method.getDeclaringClass();
        RpcTransData rpcTransData = new RpcTransData(declaringClass,method.getName(),args);
        return send(rpcTransData);


        //正常的操作是这样的
//        method.invoke(proxy,args);


    }
}
