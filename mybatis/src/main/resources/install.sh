echo "卸载之前安装的docker"
yum remove docker docker latest docker-latest-logrotate \
docker-logrotate docker-engine docker-client docker-client-latest docker-common
echo "安装必要依赖"
yum install -y yum-utils device-mapper-persistent-data lvm2
echo "添加软件源信息"
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum list | grep docker-ce
echo "更新yum缓存"
yum makecache fast
echo "安装docker"
yum install -y docker-ce-18.09.0 docker-ce-cli-18.09.0 containerd.io
echo "启动docker并设置开机启动"
systemctl start docker && systemctl enable docker
echo "测试docker安装是否成功"
docker run hello-world
