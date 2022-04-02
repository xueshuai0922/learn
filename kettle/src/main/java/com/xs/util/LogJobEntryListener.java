package com.xs.util;

import org.pentaho.di.core.Result;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobEntryListener;
import org.pentaho.di.job.entry.JobEntryCopy;
import org.pentaho.di.job.entry.JobEntryInterface;

/**
 * @author xueshuai
 * @date 2021/8/23 16:32
 * @description
 */
public class LogJobEntryListener  implements JobEntryListener {

    @Override
    public void beforeExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface) {

        JobEntryInterface entry = jobEntryCopy.getEntry();
        if("根据入参执行查询返回".equals(entry.getName())){
            System.out.println("2222222");
        }

    }

    @Override
    public void afterExecution(Job job, JobEntryCopy jobEntryCopy, JobEntryInterface jobEntryInterface, Result result) {

    }

}

