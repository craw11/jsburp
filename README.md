# JSBURP保姆级文档

## 请仔细跟着文档来!!!只需要配置一次!!!非常简单!!!!

## 主要介绍验证码爆破的设置,因为绕过前端加密的爆破只是不用输入下面的验证码(微笑)

### 1.检查自己的硬性配置

- **Java1.8**
- **python>=3.6**
- **安装chrome浏览器**

### 2.检查自己的chrome浏览器版本

```html
1.浏览器点击设置->关于Chrome  ||查看自己的浏览器版本
2.下载对应的chromedriver
【国外】chromedriver所有版本下载地址：http://chromedriver.storage.googleapis.com/index.html
【国内】chromedriver所有版本下载地址：https://npm.taobao.org/mirrors/chromedriver
【国内】chrome所有版本下载地址：https://www.chromedownloads.net/chrome64win/
【国外】浏览器版本和驱动版本对应关系查看网址：http://chromedriver.chromium.org/downloads

注意：一个大的chromedriver版本里面可能包含的有多个小版本，如果下载的Chromedriver版本和当前使用的chrome浏览器版本不匹配则可以换其他的试试。比如：chromedriver  79版里面有v79.0.3945.16    v79.0.3945.36
ps:我内置了Chrome 版本 103.0.5060.134（正式版本）（64 位）的chromedriver,如果你是这个版本就不用下载了
```

打开文件夹的Tools文件夹,下载完之后替换里面的Chromedriver

![image-20220729195651512](https://user-images.githubusercontent.com/68812236/181764767-ba8e306d-ebd6-4fbd-bd8a-592ff33b40ae.png)

### 3.安装浏览器证书

打开文件夹的cert文件夹,安装里面的证书

![image-20220729195948221](https://user-images.githubusercontent.com/68812236/181765152-fe4170ea-7291-49a0-affd-ff282dc2ca7e.png)

安装他(没错)

![image-20220729195931864](https://user-images.githubusercontent.com/68812236/181765212-a3f0f705-dbfe-42e1-9b9e-c5188c051049.png)

证书应该都会安装吧.....

## 4.安装识别验证码的python服务

打开文件夹的py文件夹,在顶部输入cmd打开命令行窗口

![image-20220729200244885](https://user-images.githubusercontent.com/68812236/181765255-d4e43b40-1708-44b6-a71a-3bc33899e332.png)

![image-20220729200408065](https://user-images.githubusercontent.com/68812236/181765322-1775c089-76cc-482a-8517-423aee60eeed.png)

安装里面requirement.txt的包

```cmdCancel changes
pip3 install -r requirements.txt -i http://pypi.douban.com --trusted-host pypi.douban.com
```

## 4.启动ocr服务

```txt
# 最简单运行方式，只开启ocr模块并以新模型计算
python3 ocr_server.py --port 9898 --ocr

# 开启ocr模块并使用旧模型计算
python3 ocr_server.py --port 9898 --ocr --old
```

随机选择一个,都可以

**出现这个界面就代表第一步成功了,然后就可以关闭了**

![image-20220729200812232](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20220729200812232.png)

## 5.主目录底下

打开cmd输入

```cmd
java -jar jsburp.jar
```

出现这个界面就代表成功了

![image-20220729201043183](https://user-images.githubusercontent.com/68812236/181765437-c039a0f1-f837-4a2d-9b92-eeec76e18443.png)

### 5.1,点击下面的启动ocr服务器,应该会自动弹出ocr服务器的窗口,

### 点击下面的测试连接,出现连接成功就代表没毛病了

![image-20220729201245691](https://user-images.githubusercontent.com/68812236/181765490-d133abae-fb0f-4207-98a6-52f8d739a5d4.png)

## 6.随便找一个带验证码的网站

点击f12

![image-20220729201816947](https://user-images.githubusercontent.com/68812236/181765552-d6282e43-9d2d-4854-aaa2-650bcca5f3c2.png)

![image-20220729201850487](https://user-images.githubusercontent.com/68812236/181765589-496f8222-af50-4f9f-856e-739e8854884d.png)

**点击复制selector**

![image-20220729202536485](https://user-images.githubusercontent.com/68812236/181765656-bc608863-e3cc-4efa-9ae5-0951ef2fbc36.png)

**出现浏览器就代表成功了,要启动ocr服务器并且显示连接成功才可以爆破**





## 8.底下的验证码是可以不用输入的,是因为有的网站是前端js加密的,这样的话用jsburp爆破就可以直接爆破,就不用逆向前端的js源码了

## 9.点击顶部的数据加密,可以批量加密你的密码字典输出excel(其实最后都会输出excel),然后放到burpsuite中进行更加快速爆破了(微笑)

## 10.输出文档在文件夹的/output文件夹里

## 11.config文件夹里的config.properties文件夹将openchrome的参数修改为false修改为就可以不显示浏览器了,可以获得更快的爆破速度

# ps.最后有问题可以加我微信,一天20小时在新,最后推下我们的公众号"硬核颜究",安全圈核弹级大佬**龙哥**在里面坐镇(重点)

