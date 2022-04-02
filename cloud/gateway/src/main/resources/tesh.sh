#给install读写权限
chmod -R 777 ./install.sh
application_path=/winning/app/190-sampledelivery-platform/1.0.10/20170/application.properties
#判断否安装了60了？否，退出
if
  [ -e /winning ]
then
  if
    [ -e /sample_platform ]
  then
    #复制相关文件到指定文件路径下

    cp -r /sample_platform/sampledelivery /winning/app/html/
    cp -r /sample_platform/190-sampledelivery-platform /winning/app/
    cp /sample_platform/190-sampledelivery-platform-1.0.10-20170.ini /etc/supervisor/config.d/
    cp /sample_platform/190-sampledelivery-platform-route.conf /winning/winmid/nginx/conf/default.d/
    cp /sample_platform/web-sampledelivery.conf /winning/winmid/nginx/conf/default.d/
    cp /sample_platform/190-sampledelivery-platform-upstream.conf /winning/winmid/nginx/conf/vhost/

    #赋权
    chmod +x /winning/app/190-sampledelivery-platform /etc/supervisor/config.d

    #修改application中的配置信息
    echo "请输入数据库ip(回车确定)"
    read ip
    sed -i "s/IP_VALUE/${ip}/g" ${application_path}

    echo "请输入数据库端口(回车确定)"
    read port
    sed -i "s/PORT_VALUE/${port}/g" ${application_path}

    echo "请输入数据库名称(回车确定)"
    read daname
    sed -i "s/DBNAME_VALUE/${daname}/g" ${application_path}

    echo "请输入数据库用户名(回车确定)"
    read user
    sed -i "s/USERNAME_VALUE/${user}/g" ${application_path}

    echo "请输入数据库密码(回车确定)"
    read pwd
    sed -i "s/PASSWORD_VALUE/${pwd}/g" ${application_path}

    echo "后续修改可在${application_path}中修改对应数据库连接信息"

    #通过supervisor进行启动
    supervisor_result=$(supervisorctl update)
    echo "${supervisor_result}"


    flag="true"

    #nginx 热加载  globs
    /winning/winmid/nginx/sbin/nginx -t
    /winning/winmid/nginx/sbin/nginx -s reload

    #判断404
    nginx_api_result=$(curl http://127.0.0.1/sampledelivery/api/sampleplatform/)

    if [[ ${nginx_api_result} == *"refused"* ]]; then
      echo "请求失败，请检查nginx配置文件--error"
      flag="false"

    else
      echo "nginx后端请求正确--right"
    fi

    nginx_html_result=$(curl http://127.0.0.1/sampledelivery/index.html)
    if [[ ${nginx_html_result} == *"app"* ]]; then
      echo "nginx前端页面请求成功--right"
    else
      echo "nginx页面请求失败，请检查nginx配置文件和前端文件的路径--error"
      flag="false"
    fi
    echo "--------------"

    if [ ${flag} == "true" ]
    then

      echo -e "标本流转平台部署成功！接下来步骤:
                1)执行数据库脚本
                2)请通过超级管理员登录,在[菜单管理]中增加菜单 "
    else

      echo "部署失败"
    fi
  else

    echo "不存在samplep_latform文件"
  fi

else
  echo "没有安装lis60,请提前安装"
fi
